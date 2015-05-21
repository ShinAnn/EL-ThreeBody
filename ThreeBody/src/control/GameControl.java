package control;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import model.Information;
import model.operation.Operable;
import model.operation.Operation;
import model.operation.TurnChange;
import server.interfaces.RMIGame;
import ui.FrameUtil;
import ui.game.GamePanel;
import dto.AccountDTO;
import dto.GameDTO;

/*
 * 分发一个线程负责与服务器通信，检查GameDTO里面的unhandledOperations
 * 提供可以调用的方法
 */
public class GameControl {
	
	private static GameControl instance;
	private RMIGame rmig;
	private GameDTO gameDTO;
	private GamePanel gamePanel;
	
	public static GameControl getInstance(){
		return instance;
	}
	
	public static void init(RMIGame rmig){
		instance = new GameControl(rmig);
	}
	
	public void setPanel(GamePanel gamePanel){
		this.gamePanel = gamePanel;
		new RefreshPanelThread().start();
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
	
	public void startCountdown(){
		new TimeThread().start();
	}
	
	public void gameOver() {
		try {
			upload();
			gameDTO.setGameOver(true);
			switch(rmig.exitGame(gameDTO.getUser(), true)){
			case SUCCESS:
				System.out.println("exit game successfully");
				break;
			default:
				System.out.println("exit game unsuccessfully");
			}
			FrameUtil.sendMessageByFrame("游戏结束", "游戏结束，5秒后转跳");
			System.out.println("游戏结束，5秒后转跳");
			new Thread(new Runnable(){
				public void run(){
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					MainControl.getInstance().toScore(gameDTO.getUser().isLost());;
				}
			}).start();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void doOperation(Operation operation){
		gameDTO.depositUnSyncOperation(operation);
		List<Operation> operations = new LinkedList<Operation>();
		operations.add(operation);
		handleOperations(operations);
	}
	
	/*
	 * 顺序为先放入消息后执行,TurnChange依赖于此
	 */
	private List<Operation> handleOperation(Operation operation){
		// 加到历史里面
		gameDTO.depositHistoryOperation(operation);
		// make information
		String id = gameDTO.getUser().getAccount().getId();
		if (id.equals(operation.getOperator())) {
			if (operation.toOperator() != null) {
				// TODO TEST
				System.out.println("!!!!!!!!!!!put in as toOperator");
				System.out.println(operation.toOperator());
				gameDTO.depositInformation(new Information(
						operation.getOperator(),
						operation.getReceiver(), 
						operation.toOperator()));
			}
		} else if (id.equals(operation.getReceiver())) {
			if (operation.toReceiver() != null) {
				// TODO TEST
				System.out.println("!!!!!!!!!!!put in as toReceiver");
				System.out.println(operation.toReceiver());
				gameDTO.depositInformation(new Information(
						operation.getOperator(),
						operation.getReceiver(), 
						operation.toReceiver()));
			}
		} else {
			if (operation.toOthers() != null) {
				// TODO TEST
				System.out.println("!!!!!!!!!!!put in as toOther");
				System.out.println(operation.toOthers());
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
			try {
				while(!gameDTO.isGameOver()){
					upload();
					// handle 同步过来的人家的 Operable
					handleOperations(rmig.downloadOperation(id));
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		
		TimeThread(){
			countDown = gamePanel.getCountdownPanel();
			this.seconds = 61;
		}
		
		@Override
		public void run() {
			while(seconds > 0 && gameDTO.getUser() == gameDTO.getWhoseTurn() && !gameDTO.isGameOver()){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				seconds--;
				gameDTO.setCountdowns(seconds);
				countDown.repaint();
			}
			gameDTO.setCountdowns(0);
			countDown.repaint();
			turnChange();
		}
	}
	
	/**
	 * 玩家结束使用这个方法，不用doOperation方法
	 */
	public synchronized void turnChange(){
		// 这个判断加上synchronized可以防止玩家按 和 倒计时线程冲突
		if(gameDTO.getUser() == gameDTO.getWhoseTurn()){
			Operation operation = new TurnChange(AccountDTO.getInstance().getId(),null);
			gameDTO.depositUnSyncOperation(operation);
			List<Operation> operations = new LinkedList<Operation>();
			operations.add(operation);
			handleOperations(operations);
			
		}
	}

	private class RefreshPanelThread extends Thread{
		public void run(){
			while(!gameDTO.isGameOver()){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				gamePanel.refresh();
			}
		}
	}

	
}
