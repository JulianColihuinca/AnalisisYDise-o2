package Conexion;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class Conexion {
	
	public static String getIP() {
		String ip="";
		InetAddress adress;
		try {
			adress = InetAddress.getLocalHost();
			ip= adress.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ip;
	}
	
	public static boolean puertoDisponible(int puerto) {

		try {
			ServerSocket s=new ServerSocket(puerto);
			s.close();
			return true;
		} catch (IOException e) {
			
			return false;
		}
	}

}
