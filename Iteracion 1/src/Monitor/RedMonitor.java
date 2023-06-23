package Monitor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Properties;

import javax.swing.JOptionPane;

import Red.Conexion;
import Red.RespuestaLlamada;
import Servidor.ListaUsuarios;
import Servidor.MainServidor;
import Servidor.RedundanciaPasiva;
import Servidor.UsuarioRegistro;

public class RedMonitor extends Observable {

	private int puertoServidorA, puertoServidorB;
	private int puertoMonitor;
	private ServerSocket monitorSS;
	private ArrayList<UsuarioRegistro> usuarioRegistrados;
	private boolean hayServidor = false;

	public RedMonitor() throws IOException {
		this.setearPuertos();
		this.monitorSS = new ServerSocket(this.puertoMonitor);
		new Thread() {
			public void run() {
				escuchar();
			}
		}.start();
		new Thread() {
			public void run() {
				while (true) {
					try {
						sleep(4000);
						verificarEstadoServidor();
						if(hayServidor && puertoDisponible(Conexion.getPuertoServidor())) { //se rompio el server
							enviarUsuarios(); //actualizo lista de usuarios registrados en el otro server
							RedundanciaPasiva.cambioServidor();
							sleep(5000);
							MainServidor.main(null);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	private void verificarEstadoServidor() {
		String server = "";

		if (puertoDisponible(this.puertoServidorA) && puertoDisponible(this.puertoServidorB)) {
			server = "ninguno";
		} else {
			this.hayServidor=true;
			if (!puertoDisponible(this.puertoServidorA) && !puertoDisponible(this.puertoServidorB)) {

				server = "ambos";
			} else if (!puertoDisponible(this.puertoServidorA) && puertoDisponible(this.puertoServidorB)) {
				server = "ServidorA";
			} else if (puertoDisponible(this.puertoServidorA) && !puertoDisponible(this.puertoServidorB)) {
				server = "ServidorB";
			}
		}

		this.setChanged();
		this.notifyObservers(server);
		this.clearChanged();
	}

	public void escuchar() {
		Socket sc = null;
		ObjectInputStream in;
		String serv = "ninguno";
		try {
			while (true) {
				sc = monitorSS.accept();
				in = new ObjectInputStream(sc.getInputStream());
				Object o;
				o = in.readObject();
				if (o instanceof ListaUsuarios) { // Si me llega una confirmacion del servidor quiere decir que esta
													// disponible
					ListaUsuarios lista = (ListaUsuarios) o;
					this.usuarioRegistrados = lista.getUsuarios();
				}
				sc.close();

			}

		} catch (IOException ex) {
			// Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("explota todo");

			JOptionPane.showMessageDialog(null, "Usuario no disponible");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setearPuertos() {
		String pathConfig = "configMonitor.properties";
		Properties prop = new Properties();

		try {
			FileInputStream in = new FileInputStream(pathConfig);
			prop.load(in);

			String puertoServidorAString = prop.getProperty("puertoServidorA");
			puertoServidorA = Integer.parseInt(puertoServidorAString);
			String puertoServidorBString = prop.getProperty("puertoServidorB");
			puertoServidorB = Integer.parseInt(puertoServidorBString);
			String puertoMonitorString = prop.getProperty("puertoMonitor");
			puertoMonitor = Integer.parseInt(puertoMonitorString);

		} catch (FileNotFoundException e) {
			System.out.println("Archivo " + pathConfig + " no encotnrado");
		} catch (IOException e) {
			System.out.println("Error al leer el archivo");
		}
	}

	private boolean puertoDisponible(int puerto) {

		try {
			ServerSocket s = new ServerSocket(puerto);
			s.close();
			return true;
		} catch (IOException e) {

			return false;
		}

	}
	
	public void enviarUsuarios() {
		Conexion.EnviarListaUsuarios(Conexion.getPuertoServidor2(), new ListaUsuarios(this.usuarioRegistrados));
	}
}
