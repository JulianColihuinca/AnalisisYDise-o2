package Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JOptionPane;

import Red.Conexion;
import Red.Llamada;
import Red.Mensaje;
import Red.RespuestaLlamada;

public class RedServidor extends Observable{
	
	private ServerSocket servidor;
	private ArrayList<UsuarioRegistro> usuarioRegistrados;
	
	public RedServidor(int puerto) throws IOException{
		this.usuarioRegistrados= new ArrayList<UsuarioRegistro>();
		this.servidor=new ServerSocket(puerto);
		new Thread() {public void run() {escuchar(); }}.start(); // Ejecuta el hilo para escuchar
		new Thread() {public void run() { 
			while(true) {
				try {
					sleep(3000);
					enviarMonitor(); 
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			   
			}
	    }}.start();
	}
	
	
	private  boolean registrarUsuario(UsuarioRegistro u) {
		int i=0;
		boolean existe=false;
		while(i<this.usuarioRegistrados.size() && !existe ) {
			UsuarioRegistro us= this.usuarioRegistrados.get(i);
			if ( us.getIp().equals(u.getIp()) && us.getPuerto()==u.getPuerto() ) {
			    existe=true;
			}
			i++;
		}
		if(!existe) {
			this.usuarioRegistrados.add(u);
			System.out.println("IP="+u.getIp() +", Puerto= "+ u.getPuerto() + "  --> Registrado en el servidor");
		}
		return existe;
		
	}

	
	// Para escuchar algo, lo va a ejecutar un hilo
	public void escuchar() {
		Socket sc = null;
        ObjectInputStream in;
        
        try {
            //SIEMPRE QUEDA ESCUCHANDO PETICIONES
            while (true) {
            	
                //ESPERA A QUE UN CLIENTE SE CONECTE
            	System.out.println("servidor a la espera de que se conecte alguien");
                sc = servidor.accept();
                System.out.println("alguien se conecto");
                //SE CONECTO UN USUARIO
                
                in = new ObjectInputStream(sc.getInputStream());
                
                Object o;
				
                //SE LEE EL OBJETO PASADO
				o =in.readObject();
				System.out.println("Servidor recibio algo");
				if(o instanceof RespuestaLlamada) {
					
					RespuestaLlamada respuesta=(RespuestaLlamada)o;
					System.out.println("El servidor recive "+respuesta.toString());
					//el servidor recibio una respuesta, ahora debe enviarla al estinatario
					Conexion.EnviarLlamada(respuesta.getPuertoOrigen(),respuesta);
					
				}else if(o instanceof Llamada) {
					
					
					Llamada llamada=(Llamada)o;
					System.out.println("El servidor recive "+llamada.toString());
					//el servidor recibio una llamada, ahora debe enviarla al destinatario
					System.out.println("el puerto destino es "+llamada.getPuertoDestino()+" y el puerto del servidor es "+Conexion.getPuertoServidor());
					
					if(puertosRegistrados(llamada.getPuertoOrigen(), llamada.getIPOrigen()) && puertosRegistrados(llamada.getPuertoDestino(), llamada.getIpDestino())){
						Conexion.EnviarLlamada(llamada.getPuertoDestino(),llamada);
					}
					else {
						System.out.println("El servidor no envia la llamada ya que al menos un usuario no esta registrado");
					}
					
					
				}else if(o instanceof Mensaje){
					
					Mensaje mensaje=(Mensaje)o;
					System.out.println("El servidor recibe "+mensaje.toString());
					//el servidor recibio un mensaje, ahora debe enviarlo al destinatario
					Conexion.EnviarMensaje(mensaje.getPuertoDestino(), mensaje);
					
				}else if(o instanceof UsuarioRegistro) { //Registra un nuevo usuario en el servidor
					
					System.out.println("red servidor recibio usaurio registro");
					
					UsuarioRegistro usuarioReg=(UsuarioRegistro)o;
					boolean registrado= !this.registrarUsuario(usuarioReg);
					Conexion.EnviarConfirmacion(usuarioReg.getPuerto(), new ConfirmacionRegistro(registrado) );
					this.enviarListaUsuarios();
					System.out.println(usuarioReg.toString());
					
				}else if(o instanceof ListaUsuarios) {
					ListaUsuarios usuarios=(ListaUsuarios)o;
					this.usuarioRegistrados=usuarios.getUsuarios();
				}
				
				this.setChanged();
            	this.notifyObservers(o);
            	this.clearChanged();
            	
                //CIERRO EL SOCKET DONDE SE CONECTO EL CLIENTE
                sc.close();
               System.out.println("servidor sc cerrado");
            }

        } catch (IOException ex) {
            //Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        	System.out.println("explota todo");
        
        	JOptionPane.showMessageDialog(null, "Usuario no disponible");
        	
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public boolean puertosRegistrados(int puerto,String ip) {
		int i=0;
		boolean encontrado=false;
		
		while(i<this.usuarioRegistrados.size()&& !encontrado) {
			UsuarioRegistro usuarioReg=this.usuarioRegistrados.get(i);
			if(usuarioReg.getIp().equals(ip)&&usuarioReg.getPuerto()==puerto) {
				encontrado=true;
			}
			
			i++;
		}
		
		return encontrado;
	}
	
	private void enviarMonitor() {
		Conexion.EnviarListaUsuarios(Conexion.getPuertoMonitor(), new ListaUsuarios(this.usuarioRegistrados) );
	    System.out.println("Enviando a monitor"+Conexion.getPuertoMonitor() +" (Servidor= "+Conexion.getPuertoServidor() +")");
	}
	
	private  void enviarListaUsuarios() {
		ArrayList<UsuarioRegistro> us= this.usuarioRegistrados;
		System.out.println(us);
		for (int i=0; i<us.size();i++) {
		int puerto=us.get(i).getPuerto();
		Conexion.EnviarListaUsuarios(puerto, new ListaUsuarios(this.usuarioRegistrados) );
		System.out.println("Envio a puerto "+puerto+" lista de usuarios");
		}
	}
	
	
	

}
