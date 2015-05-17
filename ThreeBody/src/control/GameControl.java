package control;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import model.Information;
import model.operation.Operable;
import model.operation.Operation;
import server.interfaces.RMIGame;
import dto.GameDTO;

/*
 * 分发一个线程负责与服务器通信，检查GameDTO里面的unhandledOperations
 * 提供可以调用的方法
 */
public class GameControl {
	
	private static GameControl instance;
	private RMIGame rmig;
	private GameDTO gameDTO;
	
	public static GameControl getInstance(){
		return instance;
	}
	
	public static void init(RMIGame rmig){
		instance = new GameControl(rmig);
	}

	private GameControl(RMIGame rmig) {
		this.rmig = rmig;
		
		// 初始化GameDTO
		try {
			GameDTO.setUp(rmig.getPlayers());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		gameDTO = GameDTO.getInstance();
		gameDTO.init();
		
		// 启动同步线程
		new SyncThread().start();
	}
	
	public synchronized void doOperation(Operation operation){
		gameDTO.depositUnSyncOperation(operation);
		List<Operation> operations = new LinkedList<Operation>();
		operations.add(operation);
		handleOperations(operations);
	}
	
	private List<Operation> handleOperation(Operation operation){
		// 加到历史里面
		gameDTO.depositHistoryOperation(operation);
		// make information
		String id = gameDTO.getUser().getAccount().getId();
		if (id.equals(operation.getOperator())) {
			if (operation.toOperator() != null) {
				// TODO TEST
				System.out.println("!!!!!!!!!!!put in as toOperator");
				gameDTO.depositInformation(new Information(
						operation.getOperator(),
						operation.getReceiver(), 
						operation.toOperator()));
			}
		} else if (id.equals(operation.getReceiver())) {
			if (operation.toReceiver() != null) {
				// TODO TEST
				System.out.println("!!!!!!!!!!!put in as toReceiver");
				gameDTO.depositInformation(new Information(
						operation.getOperator(),
						operation.getReceiver(), 
						operation.toReceiver()));
			}
		} else {
			if (operation.toOthers() != null) {
				// TODO TEST
				System.out.println("!!!!!!!!!!!put in as toOther");
				gameDTO.depositInformation(new Information(
						operation.getOperator(),
						operation.getReceiver(), 
						operation.toOthers()));
			}
		}
		// handle掉Operable
		if (operation instanceof Operable) {
			return ((Operable) operation).process();
		}
		return null;
	}
	
	/*
	 * 递归调用operation和它的subOperation
	 */
	private void handleOperations(List<Operation> operations){
		if (operations == null){
			return;
		}
		for(Operation operation:operations){
			handleOperations(handleOperation(operation));
		}
	}
	
	private class SyncThread extends Thread{
		
		String id = gameDTO.getUser().getAccount().getId();
		
		@Override
		public void run() {
			while(!gameDTO.isGameOver()){
				try {
					upload();
					// handle 同步过来的人家的 Operable
					handleOperations(rmig.downloadOperation(id));
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} //while
		} //run
	} // syncThread
	
	private synchronized void upload() throws RemoteException{
		// 上传unhandledOperations
		rmig.uploadOperation(gameDTO.getUser().getAccount().getId(), gameDTO.getUnSyncOperations());
		gameDTO.setSynced();
	}
		
	private class TimeThread extends Thread{

		private int seconds;
		private JPanel countDown;
		
		TimeThread(JPanel  countDown){
			this.countDown= countDown;
			this.seconds=60;
		}
		
		public int getSecond(){
			return seconds;
		}
		
		@Override
		public void run() {
			while(seconds>0){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				countDown.repaint();
				seconds--;
			}
		}
	}
	
}
