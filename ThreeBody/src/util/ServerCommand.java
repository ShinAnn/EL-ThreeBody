package util;

import io.NetClient;

import java.rmi.RemoteException;
import java.util.Scanner;

import server.interfaces.RMIAccountCenter;
import server.interfaces.RMILobby;
import server.interfaces.RMIServerControl;

public class ServerCommand {
	
	static RMIAccountCenter accountCenter = NetClient.getInstance().getAccountCenter();
	static RMILobby lobbyServer = NetClient.getInstance().getLobbyServer();
	static RMIServerControl rmisc = NetClient.getInstance().getRmisc();
	
	public static void main(String[] args) throws RemoteException {
		Scanner scanner = new Scanner(System.in);
		boolean finish = false;
		while(!finish){
			String command = scanner.nextLine();
			String[] parts = command.split(" ");
			String result;
			switch(parts[0]){
			case "close":
				finish = true;
				break;
			case "cr":
			case "dr":
				result = lobbyServer.command(command);
				System.out.println(result);
				break;
			case "cacc":
			case "aacc":
			case "cconn":
			case "cinvi":
			case "ainvi":
				result = accountCenter.command(command);
				System.out.println(result);
				break;
			case "shut":
				rmisc.command("shut down");
			default:
				System.out.println("wrong command");
			}
		}
		scanner.close();
	}
}
