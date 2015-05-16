package control;

import java.rmi.RemoteException;

import javax.swing.JPanel;

import model.Broadcast;
import model.operation.Operable;
import model.operation.Operation;
import server.interfaces.RMIGame;
import dto.GameDTO;

/*
 * 分发一个线程负责与服务器通信，检查GameDTO里面的unhandledOperations
 * 提供可以调用的方法
 */
public class GameControl {
	
	private RMIGame rmig;
	private GameDTO gameDTO;

	public GameControl(RMIGame rmig) {
		this.rmig = rmig;
		
		// 初始化GameDTO
		try {
			GameDTO.setUp(rmig.getPlayers());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		gameDTO = GameDTO.getInstance();
		gameDTO.init();
//		
//		// 启动同步线程
//		new SyncThread().start();
	}
	
	private class SyncThread extends Thread{
		
		String id = gameDTO.getUser().getAccount().getId();

		@Override
		public void run() {
			while(!gameDTO.isGameOver()){
				if(!gameDTO.getUnhandledOperations().isEmpty()){
					try {
						// 上传unhandledOperations
						rmig.uploadOperation(id, gameDTO.getUnhandledOperations());
						
						// handle 本地的 Operable
						handleOperation(gameDTO.getUnhandledOperations());
						
						// 存入historyOperations并清空unhandledOperations
						gameDTO.addToHistoryOperations(gameDTO.getUnhandledOperations());
						gameDTO.setHandled();
						
						// handle 同步过来的人家的 Operable
						handleOperation(rmig.downloadOperation(id));
						// 存入historyOperations
						gameDTO.addToHistoryOperations(gameDTO.getUnhandledOperations());
						
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			} //while
		} //run
		
		
		private void handleOperation(Iterable<Operation> unhandleOperations){
			for (Operation operation : unhandleOperations){
				if(operation instanceof Operable){
					((Operable)operation).process();
				}
				// make broadcasts
				if(operation.getOperator().equals(gameDTO.getUser().getAccount().getId())){
					if(operation.toOperator()!=null){
						gameDTO.depositBroadcast(new Broadcast(
								operation.getOperator(),
								operation.getReceiver(),
								operation.toOperator()));
					}
				}else if(operation.getReceiver().equals(gameDTO.getUser().getAccount().getId())){
					if(operation.toReceiver()!=null){
						gameDTO.depositBroadcast(new Broadcast(
								operation.getOperator(),
								operation.getReceiver(),
								operation.toReceiver()));
					}
				}else{
					if(operation.toOthers()!=null){
						gameDTO.depositBroadcast(new Broadcast(
								operation.getOperator(),
								operation.getReceiver(),
								operation.toOthers()));
					}
				}
			} // for
		} // handleOperation
	} // syncThread
		
	public class TimeThread extends Thread{

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
