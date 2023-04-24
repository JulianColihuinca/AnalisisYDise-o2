package Conexion;

import java.net.InetAddress;
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
	
	

}
