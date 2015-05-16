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
	private static GameDTO dto;
    
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
    	this.players = players;
    	
    	//TODO test
//    	this.players.add(new Player(new Account("A"),new Earth(),Coordinate.generateCoordinate(),false));
//    	this.players.add(new Player(new Account("B"),new Unifier(),Coordinate.generateCoordinate(),false));
//    	this.players.add(new Player(new Account("C"),new ThreeBody(),Coordinate.generateCoordinate(),false));
//    	user = this.players.get(0);
    	for(Player player:players){
    		if(player.getAccount().getId().equals(AccountDTO.getInstance().getId())){
    			user = player;
    		}
    	}
    	
    	broadcasts = new LinkedList<Broadcast>();
    	historyOperations = new LinkedList<Operation>();
    	unhandledOperations = new LinkedList<Operation>();
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

	public Player getWhoseTurn() {
		return whoseTurn;
	}

	public void setWhoseTurn(Player whoseTurn) {
		this.whoseTurn = whoseTurn;
	}

}
