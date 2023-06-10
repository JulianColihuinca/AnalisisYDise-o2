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
import Servidor.ConfirmacionServidor;
import Servidor.UsuarioRegistro;

public class RedMonitor extends Observable {

	private int puertoServidorA,puertoServidorB;
	private int puertoMonitor;
	private ServerSocket monitorSS;
	private ArrayList<UsuarioRegistro> usuarioRegistrados;
	
	
	
	
	public RedMonitor() throws IOException {
		this.setearPuertos();
		this.monitorSS=new ServerSocket(this.puertoMonitor);
		new Thread() {public void run() {escuchar(); }}.start();
	}

   
   public void escuchar() {
	   Socket sc = null;
       ObjectInputStream in;
       String serv="ninguno";
       try {
    	   while(true) {
    		   sc = monitorSS.accept();
    		   System.out.println("El monitor recibio algo");
    		   in = new ObjectInputStream(sc.getInputStream());
    		   Object o;
    		   o =in.readObject();
    		   if(o instanceof ConfirmacionServidor) { // Si me llega una confirmacion del servidor quiere decir que esta disponible
					ConfirmacionServidor infoServidor= (ConfirmacionServidor) o;
					if(infoServidor.getPuerto()==this.puertoServidorA) { // La confirmacion es del sertvidorA
						serv="ServidorA";
					}
					else if(infoServidor.getPuerto()==this.puertoServidorB) { //La confirmacion es del servidorB
						serv="ServidorB";
					}
					this.usuarioRegistrados=infoServidor.getUsuarioRegistrados();
				}
    		   this.setChanged();
           	   this.notifyObservers(serv);
           	   this.clearChanged();
    		   sc.close();
    		   
    	   }
 
       }catch (IOException ex) {
           //Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
       	System.out.println("explota todo");
       
       	JOptionPane.showMessageDialog(null, "Usuario no disponible");
       	
       } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }


	private  void setearPuertos() {
		String pathConfig="configMonitor.properties";
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
}
