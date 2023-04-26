package Modelos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

public class UsuarioServidor extends Observable implements Runnable {
	
	private int puerto;
	private ServerSocket servidor;
	private  Llamada llamada;
	private RespuestaLlamada respuesta;
	

	public UsuarioServidor(int puerto) throws IOException {
		this.puerto = puerto;
		this.servidor=new ServerSocket(puerto);
		this.llamada=null;
		this.respuesta=null;
	}


	
	

	public Llamada getLlamada() {
		return llamada;
	}





	public void setLlamada(Llamada llamada) {
		this.llamada = llamada;
	}





	public int getPuerto() {
		return puerto;
	}





	public ServerSocket getServidor() {
		return servidor;
	}





	@Override
	public void run() {
		Socket sc=null;
		ObjectInputStream in;
		try {
			System.out.println("Servidor iniciado");
			while(true) {
				sc=this.servidor.accept();
				System.out.println("Cliente Conectado");
				in= new ObjectInputStream(sc.getInputStream());
				Object o;
				o= in.readObject();
				if(o instanceof RespuestaLlamada) {
					System.out.println("Servidor recibe una llamada del puerto " + this.puerto);
				}
				else if(o instanceof Llamada) {
					System.out.println("El servidor recibe una llamada");
					this.llamada= (Llamada) o;
				}
				
				else if(o instanceof String) {
					String mensaje= (String) o;
					System.out.println(mensaje);
				}
				
				this.setChanged();
				this.notifyObservers(o);
				this.clearChanged();
				sc.close();
				System.out.print("Cliente desconectado");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
