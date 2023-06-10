package Red;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Properties;

import Servidor.ConfirmacionRegistro;
import Servidor.ListaUsuarios;
import Servidor.UsuarioRegistro;

public class Conexion {
 
	public static String getIP() {
		String ip = "";
		InetAddress adress;
		try {
			adress = InetAddress.getLocalHost();
			ip = adress.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ip;
	}

	public static synchronized boolean puertoDisponible(int puerto) {
		if (puerto != Conexion.getPuertoServidor() && puerto != Conexion.getPuertoServidor2() &&puerto != Conexion.getPuertoMonitor() ) {
			try {
				ServerSocket s = new ServerSocket(puerto);
				s.close();
				return true;
			} catch (IOException e) {

				return false;
			}
		}else
			return false;
	}
    
	public static void EnviarRegistro(UsuarioRegistro usaurioReg) {
		int puertoServidor=Conexion.getPuertoServidor();
		UsuarioCliente c;
		
		c = new UsuarioCliente(puertoServidor, usaurioReg);
		Thread t = new Thread(c);
		t.start();
	}
	
	public static void EnviarLlamada(int puerto, Object o) {
		UsuarioCliente c;

		if (o != null) {
			if (o instanceof RespuestaLlamada) {
				c = new UsuarioCliente(puerto, (RespuestaLlamada) o);// para un futuro agregar ip
				Thread t = new Thread(c);
				t.start();
			} else if (o instanceof Llamada) {
				// CREO UN USUARIO CLIENTE QUE ENVIA LA LLAMADA AL PUERTO DESTINO Y EJECUTO EL
				// HILO
				c = new UsuarioCliente(puerto, (Llamada) o);
				Thread t = new Thread(c);
				t.start();
			}

		}

		else {
			c = new UsuarioCliente(puerto);
			Thread t = new Thread(c);
			t.start();
		}
	}

	public static void EnviarMensaje(int puerto, Mensaje m) {
		UsuarioCliente c;
		c = new UsuarioCliente(puerto, m);// para un futuro agregar ip
		Thread t = new Thread(c);
		t.start();
	}
	
	public static void EnviarConfirmacion(int puerto, ConfirmacionRegistro conf) {
		UsuarioCliente c;
		c = new UsuarioCliente(puerto, conf);// para un futuro agregar ip
		Thread t = new Thread(c);
		t.start();
	}
	
	public static void EnviarListaUsuarios(int puerto, ListaUsuarios l) {
		UsuarioCliente c;
		c = new UsuarioCliente(puerto, l);// para un futuro agregar ip
		Thread t = new Thread(c);
		t.start();
	}
	

	public static void Escuchar(UsuarioServidor usuario) {
		Thread t = new Thread(usuario);
		t.start();
	}

	public static  synchronized int getPuertoServidor() {
		String pathConfig="configServidor.properties";
		Properties prop = new Properties();
		int puertoServidor = 0;

		try {
			FileInputStream in = new FileInputStream(pathConfig);
			prop.load(in);

			String puertoServidorString = prop.getProperty("puertoServidor");
			puertoServidor = Integer.parseInt(puertoServidorString);

		} catch (FileNotFoundException e) {
			System.out.println("Archivo " + pathConfig + " no encotnrado");
		} catch (IOException e) {
			System.out.println("Error al leer el archivo");
		}

		return puertoServidor;
	}
	
	public static int getPuertoServidor2() {
		String pathConfig="configServidor.properties";
		Properties prop = new Properties();
		int puertoServidor = 0;

		try {
			FileInputStream in = new FileInputStream(pathConfig);
			prop.load(in);

			String puertoServidorString = prop.getProperty("puertoServidor2");
			puertoServidor = Integer.parseInt(puertoServidorString);

		} catch (FileNotFoundException e) {
			System.out.println("Archivo " + pathConfig + " no encotnrado");
		} catch (IOException e) {
			System.out.println("Error al leer el archivo");
		}

		return puertoServidor;
	}
	
	public static int getPuertoMonitor() {
		String pathConfig="configServidor.properties";
		Properties prop = new Properties();
		int puertoMonitor = 0;

		try {
			FileInputStream in = new FileInputStream(pathConfig);
			prop.load(in);

			String puertoMonitorString = prop.getProperty("puertoMonitor");
			puertoMonitor = Integer.parseInt(puertoMonitorString);

		} catch (FileNotFoundException e) {
			System.out.println("Archivo " + pathConfig + " no encotnrado");
		} catch (IOException e) {
			System.out.println("Error al leer el archivo");
		}

		return puertoMonitor;
	}
}
