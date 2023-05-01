package Modelo;

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
	
	public static void crearUsuarioCliente(int puerto,Object o) {
		UsuarioCliente c;
		
		if (o!=null) {
			if (o instanceof Mensaje) {
				 c = new UsuarioCliente(puerto, (Mensaje) o);// para un futuro agregar ip
				 Thread t = new Thread(c);
				  t.start();
			}
			else if(o instanceof RespuestaLlamada) {
				c = new UsuarioCliente(puerto, (RespuestaLlamada) o);// para un futuro agregar ip
				Thread t = new Thread(c);
				t.start();
			}
			else if(o instanceof Llamada) {
				// CREO UN USUARIO CLIENTE QUE ENVIA LA LLAMADA AL PUERTO DESTINO Y EJECUTO EL HILO
				c = new UsuarioCliente(puerto, (Llamada) o );
				Thread t = new Thread(c);
				t.start();
			}
			
		}
		
		else {
			c=new UsuarioCliente(puerto);
			Thread t = new Thread(c);
			t.start();
		}
		
		
	}
	
	

}
