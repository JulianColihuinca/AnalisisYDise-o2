package Modelos;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class UsuarioCliente implements Runnable {


	private int puerto;
	private String mensaje;
	private Llamada llamada;
	private RespuestaLlamada respuesta;
	

	


	public UsuarioCliente(int puerto, Llamada llamada) {
		super();
		this.puerto = puerto;
		this.llamada = llamada;
	}


	



	public UsuarioCliente(int puerto, RespuestaLlamada respuesta) {
		super();
		this.puerto = puerto;
		this.respuesta = respuesta;
	}



	



	public UsuarioCliente(int puerto, String mensaje) {
		super();
		this.puerto = puerto;
		this.mensaje = mensaje;
	}






	@Override
	public void run() {
		final String HOST= Conexion.getIP();
		ObjectOutputStream out;
		try {
			Socket sc= new Socket(HOST,puerto);
			out= new ObjectOutputStream(sc.getOutputStream());
			if (this.llamada==null) {
				if(this.mensaje==null) {
					out.writeObject(this.respuesta);
				}
				else {
					out.writeUTF(this.mensaje);
				}
				
				
			}
			else {
				out.writeObject(this.llamada);
			}
			sc.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
