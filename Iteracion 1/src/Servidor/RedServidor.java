package Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Red.Conexion;
import Red.Llamada;
import Red.Mensaje;
import Red.RespuestaLlamada;

public class RedServidor {
	
	private ServerSocket servidor;
	private ArrayList<UsuarioRegistro> usuarioRegistrados;
	
	public RedServidor() throws IOException{
		this.usuarioRegistrados= new ArrayList<UsuarioRegistro>();
		this.servidor=new ServerSocket(Conexion.getPuertoServidor());
		new Thread() {public void run() {escuchar(); }}.start(); // Ejecuta el hilo para escuchar
	}
	
	
	private  void registrarUsuario(UsuarioRegistro u) {
		int i=0;
		boolean existe=false;
		while(i<this.usuarioRegistrados.size() && !existe ) {
			UsuarioRegistro us= this.usuarioRegistrados.get(i);
			if ( us.getIp().equals(u.getIp()) && us.getPuerto()==u.getPuerto() ) {
			    existe=true;
			}
		}
		if(!existe) {
			this.usuarioRegistrados.add(u);
			System.out.println("IP="+u.getIp() +", Puerto= "+ u.getPuerto() + "  --> Registrado en el servidor");
		}
	}
	
	public void enviarConfirmacionRegistro() { // Envia confirmacion de registro al servidor
		
	}
	public void enviarMensaje() {}
	public void enviarLlamada() {}
	
	// Para escuchar algo, lo va a ejecutar un hilo
	public void escuchar() {
		Socket sc = null;
        ObjectInputStream in;
        
        try {
            //SIEMPRE QUEDA ESCUCHANDO PETICIONES
            while (true) {
            	
                //ESPERA A QUE UN CLIENTE SE CONECTE
                sc = servidor.accept();
                System.out.println("servidor a la espera de que se conecte alguien");
                //SE CONECTO UN USUARIO
                
                in = new ObjectInputStream(sc.getInputStream());
                
                Object o;
				
                //SE LEE EL OBJETO PASADO
				o =in.readObject();
				
				if(o instanceof RespuestaLlamada) {
					
					RespuestaLlamada respuesta=(RespuestaLlamada)o;
					System.out.println("El servidor recive "+respuesta.toString());
					//el servidor recibio una respuesta, ahora debe enviarla al estinatario
					Conexion.EnviarLlamada(respuesta.getPuertoOrigen(),respuesta);
					
				}else if(o instanceof Llamada) {
					
					Llamada llamada=(Llamada)o;
					System.out.println("El servidor recive "+llamada.toString());
					//el servidor recibio una llamada, ahora debe enviarla al destinatario
					Conexion.EnviarLlamada(llamada.getPuertoDestino(),llamada);
					
				}else if(o instanceof Mensaje){
					
					Mensaje mensaje=(Mensaje)o;
					System.out.println("El servidor recibe "+mensaje.toString());
					//el servidor recibio un mensaje, ahora debe enviarlo al destinatario
					Conexion.EnviarMensaje(mensaje.getPuertoDestino(), mensaje);
					
				}
				else if(o instanceof UsuarioRegistro) { //Registra un nuevo usuario en el servidor
					this.registrarUsuario( (UsuarioRegistro) o );
				}
				
                //CIERRO EL SOCKET DONDE SE CONECTO EL CLIENTE
                sc.close();
                
            }

        } catch (IOException ex) {
            //Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	

}
