            package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import model.Account;
import model.Coordinate;
import model.Player;
import model.operation.Operation;
import model.role.Role;
import server.interfaces.RMIGame;
import util.R;
import util.R.info;
import util.RandomCombiner;

public class GameServer extends UnicastRemoteObject implements RMIGame{
	
	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String,LinkedList<Operation>> unhandledOperations;
	private LinkedList<Player> players;

	protected GameServer(List<Account> accounts) throws RemoteException {
		super();
		// 初始化unhandled
		unhandledOperations = new HashMap<String,LinkedList<Operation>>();
		for (Account account : accounts) {
			unhandledOperations.put(account.getId(), new LinkedList<Operation>());
		}
		// 初始化players，随机搭配
		players = new LinkedList<Player>();
		RandomCombiner rc = new RandomCombiner(accounts.size());
		rc.addColumn(accounts.toArray());
		// 角色数量分配
		switch(accounts.size()){
		case 3:
			rc.addColumn(Role.generateRoles(2, 1, 0));
			break;
		case 6:
			rc.addColumn(Role.generateRoles(3, 2, 1));
			break;
		case 8:
			rc.addColumn(Role.generateRoles(4, 3, 1));
			break;
		}
		rc.addColumn(Coordinate.generateCoordinates(accounts.size()));
		for (Object[] records : rc.generate()) {
			Account ac = (Account)records[0];
			Role rl = (Role)records[1];
			Coordinate cd = (Coordinate)records[2];
			players.add(new Player(ac,rl,cd,false));
		}
	}
	
	@Override
	public LinkedList<Operation> downloadOperation(String id) throws RemoteException {
		LinkedList<Operation> result = unhandledOperations.get(id);
		// 清空
		unhandledOperations.put(id, new LinkedList<Operation>());
		return result;
	}

	@Override
	public info uploadOperation(String id, List<Operation> unhandled)
			throws RemoteException {
		for (Entry<String, LinkedList<Operation>> entries : unhandledOperations.entrySet()) {
			if(entries.getKey()!=id){
				entries.getValue().addAll(unhandled);
			}
		}
		return R.info.SUCCESS;
	}

	@Override
	public LinkedList<Player> getPlayers() throws RemoteException {
		return players;
	}

}
