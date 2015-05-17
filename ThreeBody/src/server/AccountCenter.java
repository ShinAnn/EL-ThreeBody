package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.rmi.PortableRemoteObject;

import model.Account;
import server.AccountServer.State;
import server.interfaces.RMIAccount;
import server.interfaces.RMIAccountCenter;
import util.R;
import util.R.info;

/**
 * 
 * @author Sissel
 * 
 *         这个类作为单一对象给客户端登陆，并分发各自的后续连接端口
 */
public class AccountCenter extends UnicastRemoteObject implements
		RMIAccountCenter {

	private static final long serialVersionUID = 1L;

	/*
	 * 对应整个服务器上的全部account
	 */
	private Map<String, Account> accounts;
	
	/*
	 * 账号密码对照
	 */
	private Map<String, String> passwords;
	
	/*
	 * 账号暂时登陆码对照
	 */
	private Map<String, String> transientIDs;
	
	/*
	 * 在运行的服务
	 */
	private Map<String, AccountServer> actives;
	
	/*
	 * 邀请码
	 */
	private Set<String> invitationIDs;
	
	/*
	 * 数据库交互
	 */
	private ServerDatabase database;

	protected AccountCenter() throws RemoteException {
		try {
			database = ServerDatabase.getInstance();
			database.openDB();
			database.loadData();
			
			this.accounts = database.getAccounts();
			this.passwords = database.getPasswords();
			this.transientIDs = database.getTransientIDs();
			this.invitationIDs = database.getInvitationIDs();
			this.actives = new HashMap<String, AccountServer>();
			
			Naming.rebind("AccountCenter", (RMIAccountCenter)this);
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 退出但不删除transientID
	 */
	@Override
	public info logout(String id) throws RemoteException {
		if(actives.containsKey(id)){
			this.informLogout(id);
			return R.info.SUCCESS;
		}else{
			return R.info.NOT_EXISTED;
		}
	}
	
	/**
	 * 退出且删除transientID
	 */
	@Override
	public info logoutAndClear(String id) throws RemoteException {
		if(actives.containsKey(id)){
			this.informLogout(id);
			transientIDs.remove(id);
			database.updateTransientID(id, null);
			return R.info.SUCCESS;
		}else{
			return R.info.NOT_EXISTED;
		}
	}

	@Override
	public info login(String id, String password) throws RemoteException {
		Account account = accounts.get(id);
		if(actives.containsKey(id)){
			return R.info.ALREADY_IN;
		}
		if (account == null) {
			return R.info.NOT_EXISTED;
		} else if (!passwords.get(id).equals(password)) {
			return R.info.INVALID;
		}
		// 生成暂时登陆码
		String transientID = generateRandomTransientID();
	    transientIDs.put(id, transientID);
	    database.updateTransientID(id, transientID);
	    // 分发远程对象
		this.dispatchAccountServer(account);
		return R.info.SUCCESS;
	}

	@Override
	public info logUp(String id, String password, String invitationID)
			throws RemoteException {
		if (accounts.containsKey(id)) {
			return R.info.ALREADY_EXISTED;
		} else if (!invitationIDs.contains(invitationID)) {
			return R.info.NOT_EXISTED;
		}
		// 移除已经使用的邀请码
		invitationIDs.remove(invitationID);
		database.deleteInvitationID(invitationID);
		// 生成暂时登陆码
		String transientID = generateRandomTransientID();
		transientIDs.put(id,transientID);
		// 生成账号
		Account newAccount = new Account(id);
		accounts.put(id, newAccount);
		passwords.put(id, password);
		database.addAccount(id, password, transientID);
		// 分发远程对象
		this.dispatchAccountServer(newAccount);
		return R.info.SUCCESS;
	}

	/**
	 * 使用transientID登陆
	 */
	@Override
	public info loginByTransientID(String id, String transientID)
			throws RemoteException {
		Account account = accounts.get(id);
		if(actives.containsKey(id)){
			return R.info.ALREADY_IN;
		}
		if (account == null) {
			return R.info.NOT_EXISTED;
		} else if (transientIDs.get(id) == null || !transientIDs.get(id).equals(transientID)) {
			return R.info.INVALID;
		}
		this.dispatchAccountServer(account);
		return R.info.SUCCESS;
	}

	@Override
	public String command(String command) {
		
		String[] parts = command.split(" ");
		StringBuffer sb;

		switch (parts[0]) {
		case "aacc":
			Account acc = new Account(parts[1]);
			accounts.put(parts[1], acc);
			passwords.put(parts[1], parts[2]);
			String transientID = generateRandomTransientID();
			transientIDs.put(parts[1],transientID);
			database.addAccount(parts[1], parts[2], transientID);
			return "success";
		case "ainvi":
			int beforeSize = invitationIDs.size();
			invitationIDs.add(parts[1]);
			int afterSize = invitationIDs.size();
			if(afterSize > beforeSize){
				database.addInvitationID(parts[1]);
			}
			return "now size:"+invitationIDs.size();
		case "cconn":
			sb = new StringBuffer();
			sb.append("connections:"+actives.size()+"\n");
			for (String name : actives.keySet()) {
				sb.append(name+"\n");
			}
			return sb.toString();
		case "cinvi":
			sb = new StringBuffer();
			sb.append("invitationIDs:"+invitationIDs.size()+"\n");
			for (String invitationID : invitationIDs) {
				sb.append(invitationID+"\n");
			}
			return sb.toString();
		case "cacc":
			sb = new StringBuffer();
			sb.append("accounts:"+accounts.size()+"\n");
			for (String name : accounts.keySet()) {
				sb.append(name+"\n");
			}
			return sb.toString();
		}

		return R.info.INVALID.name();
	}

	@Override
	public RMIAccount getService(String id) {
		return actives.get(id);
	}

	@Override
	public String getTransientID(String id) {
		return transientIDs.get(id);
	}
	
	public Account getAccount(String id){
		return accounts.get(id);
	}
	
	/**
	 * 生成暂时登陆码
	 */
	private static String generateRandomTransientID(){
		Random random = new Random();
		String result = "k";
		for (int i = 0; i < 10; i++) {
			result += random.nextInt(9);
		}
		return result;
	}
	
	private void dispatchAccountServer(Account account) {
		try {
			if(!actives.containsKey(account.getId())){
				AccountServer as = new AccountServer(this, account);
				actives.put(account.getId(), as);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void informLogout(String id) {
		actives.get(id).setState(State.FAIL);
	}
	
	public void unbindAccount(Account account){
		try {
			PortableRemoteObject.unexportObject(actives.get(account.getId()));
		} catch (NoSuchObjectException e) {
			e.printStackTrace();
		}
		actives.remove(account.getId());
	}
	
	// TODO 保存Account
	public void saveAccount(Account account) {
		accounts.put(account.getId(), account);
	}

	@Override
	public info editPassword(String id,String password, String newPassword) throws RemoteException {
		if(passwords.get(id).equals(password)){
			passwords.put(id, newPassword);
			database.updatePassword(id, newPassword);
			return R.info.SUCCESS;
		}else{
			return R.info.INVALID;
		}
	}
	
}
