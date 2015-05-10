package dto;

import java.util.LinkedList;
import java.util.List;

import model.Broadcast;
import model.Player;
import model.operation.Operation;

public class GameDTO {
	
	/*
	 * singleton
	 */
	private static GameDTO dto = new GameDTO(null);
    
    private List<Player> players;
 
	/*
     * 本回合操作者
     */
    private Player whoseTurn;
    /*
     * 本地玩家
     */
    private Player user;
    /*
     * 历史消息记录
     */
    private List<Broadcast> broadcasts;
    /*
     * 历史操作记录
     */
    private List<Operation> historyOperations;
    /*
     * 待执行操作
     */
    private List<Operation> unhandledOperations;
    /*
     * 游戏是否结束
     */
    private boolean gameOver;
    
    public static void setUp(List<Player> players){
    	dto = new GameDTO(players);
    }
    
    private GameDTO(List<Player> players){
    	//TODO 本地账号
//    	this.players = players;
    	this.players = new LinkedList<Player>();
    	broadcasts = new LinkedList<Broadcast>();
    	historyOperations = new LinkedList<Operation>();
    	unhandledOperations = new LinkedList<Operation>();
    	gameOver = false;
    }
    
    public static GameDTO getInstance(){
    	return dto;
    }
    
    public void depositOperation(Operation operation){
        this.unhandledOperations.add(operation);
    }
    
    public void depositBroadcast(Broadcast br){
    	this.broadcasts.add(br);
    }

    /*
     * getters and setters
     */
	public List<Player> getPlayers() {
		return players;
	}
	
	public Player getWhoseTurn() {
		return whoseTurn;
	}

	public void setWhoseTurn(Player whoseTurn) {
		this.whoseTurn = whoseTurn;
	}

	public Player getUser() {
		return user;
	}

	public void setUser(Player user) {
		this.user = user;
	}

	public List<Broadcast> getBroadcasts() {
		return broadcasts;
	}

	public List<Operation> getHistoryOperations() {
		return historyOperations;
	}
	
	public void addToHistoryOperations(List<Operation> oprts){
		historyOperations.addAll(oprts);
	}

	public List<Operation> getUnhandledOperations() {
		return unhandledOperations;
	}
	
	public void setHandled(){
		unhandledOperations.clear();
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

}
