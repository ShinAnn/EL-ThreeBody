package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import server.interfaces.RMIServerControl;

public class ServerControl extends UnicastRemoteObject implements RMIServerControl{
	
	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;
	private AccountCenter accountCenter;
	@SuppressWarnings("unused")
	private LobbyServer lobbyServer;
	@SuppressWarnings("unused")
	private ImageServer imageServer;

	protected ServerControl() throws RemoteException {
		super();
		try {
			try {
				Naming.rebind("ServerControl", (RMIServerControl)this);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			accountCenter = new AccountCenter();
			lobbyServer = new LobbyServer(accountCenter);
			imageServer = new ImageServer();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		try {
			new ServerControl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String command(String command) throws RemoteException {
		switch(command){
		case "shut down":
			try {
				lobbyServer = null;
				accountCenter = null;
				Naming.unbind("AccountCenter");
				Naming.unbind("LobbyServer");
				ServerDatabase.getInstance().closeDB();
				System.exit(0);
			} catch (MalformedURLException | NotBoundException e) {
				e.printStackTrace();
			}
			return "shut down successfully";
		case "reboot":
			
		}
		return "INVALID";
	}


}
