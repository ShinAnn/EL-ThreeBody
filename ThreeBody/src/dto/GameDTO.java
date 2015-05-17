package dto;

import java.util.LinkedList;
import java.util.List;

import model.Information;
import model.Player;
import model.operation.Operation;

public class GameDTO {
	
	/*
	 * singleton
	 */
	private static GameDTO dto;
    
	/*
	 * 所有玩家
	 */
    private List<Player> players;
    
    /*
     * 该回合的玩家
     */
    private Player whoseTurn;
    
    /*
     * 回合数，每人完成一次操作即为增加一个回合
     */
    private int bout;
    
    /*
     * 轮次，所有玩家均完成一次操作即为增加一轮
     */
    private int round;
 
    /*
     * 本地玩家
     */
    private Player user;
    /*
     * 历史消息记录
     */
    private List<Information> informations;
    /*
     * 历史操作记录
     */
    private List<Operation> historyOperations;
    /*
     * 待同步操作
     */
    private List<Operation> unSyncOperations;
    /*
     * 游戏是否结束
     */
    private boolean gameOver;
    
    public static void setUp(List<Player> players){
    	dto = new GameDTO(players);
    }
    
    private GameDTO(List<Player> players){
    	this.players = players;
    	// 找USER
    	for(Player player:players){
    		if(player.getAccount().getId().equals(AccountDTO.getInstance().getId())){
    			user = player;
    		}
    	}
    	informations = new LinkedList<Information>();
    	historyOperations = new LinkedList<Operation>();
    	unSyncOperations = new LinkedList<Operation>();
    	gameOver = false;
    }
    
    public void init(){
    	for (Player player : this.players) {
			player.initFoundCoordinates();
		}
    }
    
    public static GameDTO getInstance(){
    	return dto;
    }
    
    public void depositHistoryOperation(Operation operation){
    	this.historyOperations.add(operation);
    }
    
    public synchronized void depositInformation(Information br){
    	this.informations.add(br);
    }
    
    public void depositUnSyncOperation(Operation operation){
    	this.unSyncOperations.add(operation);
    }
    
    public void setSynced(){
    	this.unSyncOperations.clear();
    }

    /*
     * getters and setters
     */
	public List<Player> getPlayers() {
		return players;
	}
	
	public int getBout() {
		return bout;
	}

	public void setBout(int bout) {
		this.bout = bout;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public Player getUser() {
		return user;
	}

	public synchronized String[] getInformations(){
		String[] infos = new String[informations.size()];
		for (int i = 0; i < infos.length; i++) {
			infos[i] = informations.get(i).getContent();
		}
		return infos;
	}

	public List<Operation> getHistoryOperations() {
		return historyOperations;
	}
	
	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public Player getWhoseTurn() {
		return whoseTurn;
	}

	public void setWhoseTurn(Player whoseTurn) {
		this.whoseTurn = whoseTurn;
	}

	public List<Operation> getUnSyncOperations() {
		return unSyncOperations;
	}
}
