package control;

import io.NetClient;
import io.UserData;

import java.rmi.RemoteException;

import model.Account;
import server.interfaces.RMIAccount;
import server.interfaces.RMIAccountCenter;
import util.R;
import dto.AccountDTO;

/**
 * 跟账户控制有关
 * 与UserData交互，实现缓存读取
 * 与服务器的AccountCenter，AccountServer通信，实现同步
 * @author Sissel
 */
public class AccountControl {
    
    private Account account;
    private RMIAccountCenter rmiac;
    private RMIAccount rmia;
    private MainControl mainControl;
    
    public AccountControl(MainControl mc) {
    	this.mainControl = mc;
		// 读取本地缓存
		account = UserData.loadAccount();
		AccountDTO.initializeByLocalData(account);
	}
    
    public R.info logUp(String id,String password,String invitationID){
    	setUpRMIAC();
    	R.info feedback = null;
    	try {
			feedback = rmiac.logUp(id, password, invitationID);
			if(feedback == R.info.SUCCESS){
				rmia = rmiac.getService(id);
				// 设成连接状态
				mainControl.setConnected(true);
				// 保存transientID
				String transientID = rmiac.getTransientID(id);
				UserData.saveTransientID(transientID);
				// 同步网络端的account
				account = rmia.getAccount();
				AccountDTO.synchronize(account);	
				UserData.saveAccount(account);
				// 开启检查连接的线程
				new ConnectionChecker().start();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	return feedback;
    }

    // TODO 比较两端的不同
	public R.info login(String id,String password){
		setUpRMIAC();
		R.info feedback = null;
		try {
			feedback = rmiac.login(id, password);
			if(feedback == R.info.SUCCESS){
				rmia = rmiac.getService(id);
				// 设成连接状态
				mainControl.setConnected(true);
				// 保存transientID
				String transientID = rmiac.getTransientID(id);
//				UserData.saveTransientID(transientID);
				// 同步网络端的account
				account = rmia.getAccount();
				AccountDTO.synchronize(account);	
//				UserData.saveAccount(account);
				// 开启检查连接的线程
				new ConnectionChecker().start();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	return feedback;
    }
	
	// TODO 比较两端的不同
	public R.info loginByTransientID(String id){
		setUpRMIAC();
		R.info feedback = null;
		String transientID = UserData.loadTransientID();
		try {
			feedback = rmiac.loginByTransientID(id, transientID);
			if(feedback == R.info.SUCCESS){
				rmia = rmiac.getService(id);
				// 设成连接状态
				mainControl.setConnected(true);
				// 同步网络端的account
				account = rmia.getAccount();
				AccountDTO.synchronize(account);
				UserData.saveAccount(account);
				// 开启检查连接的线程
				new ConnectionChecker().start();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	return feedback;
	}
    
	/*
     *  与checkLink存在race，设成synchronized
     */
    public synchronized R.info logout(){
    	setUpRMIAC();
    	R.info feedback = null;
    	try {
			feedback = rmiac.logout(account.getId());
			if(feedback == R.info.SUCCESS){
				mainControl.setConnected(false);
				rmia = null;
		    	account = null;
		    	AccountDTO.synchronize(account);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	return feedback;
    }
    
    /*
     *  与checkLink存在race，设成synchronized
     */
    public synchronized R.info logoutAndClear(){
    	setUpRMIAC();
    	R.info feedback = null;
    	try {
			feedback = rmiac.logoutAndClear(account.getId());
			if(feedback == R.info.SUCCESS){
				mainControl.setConnected(false);
				UserData.clearAccount();
				rmia = null;
		    	account = null;
		    	AccountDTO.synchronize(account);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	return feedback;
    }
    
    /*
     * 更改密码
     */
    public R.info editPassword(String password,String newPassword){
    	try {
			return rmiac.editPassword(account.getId(), password,newPassword);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /*
     * 将头像上传到服务器端
     */
    public R.info uploadHead(){
    	setUpRMIAC();
    	return null;
    }
    
    /*
     * 将服务器端改变下载,同步
     */
    public void synchronize(){
    	try {
			account = rmia.getAccount();
			AccountDTO.synchronize(account);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    
    private class ConnectionChecker extends Thread{
    	@Override
    	public void run(){
    		while(true){
    			if(!checkLink()){
					break;
				}
    			try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    	}
    }
    
    /*
     *  与logout存在race，设成synchronized
     */
    private synchronized boolean checkLink(){
    	if(rmia == null){
    		return false;
    	}
    	try {
			rmia.connect();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	return true;
    }
    
    private void setUpRMIAC(){
    	if(this.rmiac == null){
    		rmiac = NetClient.getInstance().getAccountCenter();
    	}
    }

}
