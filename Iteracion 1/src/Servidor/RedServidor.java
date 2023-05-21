package Servidor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

import Red.Llamada;
import Red.Mensaje;
import Red.RespuestaLlamada;

public class RedServidor {
	
	private ServerSocket servidor;
	private ArrayList<Usuario> usuarioRegistrados;
	
	public RedServidor() throws IOException{
		this.usuarioRegistrados= new ArrayList<Usuario>();
		this.servidor=new ServerSocket(RedServidor.getPuertoServidor("config.properties"));
		new Thread() {public void run() {registrarUsuario(); }}.start(); // Ejecuta el hilo para agregar usuarios
		new Thread() {public void run() {escuchar(); }}.start(); // Ejecuta el hilo para escuchar
	}
	
	// Para registrar usuarios, lo ejecuta un hilo
	public void registrarUsuario() {
		while (true) {
			
		}
	}
	
	private  void addUsuario(Usuario u) {
		this.usuarioRegistrados.add(u);
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

                //SE CONECTO UN USUARIO
                
                in = new ObjectInputStream(sc.getInputStream());
                
                Object o;
				
                //SE LEE EL OBJETO PASADO
				o =in.readObject();
				
				if(o instanceof RespuestaLlamada) {
					RespuestaLlamada respuesta=(RespuestaLlamada)o;
					System.out.println("El servidor recive una respuesta de "+respuesta.getPuertoDestino()+" de la llamada iniciada por puerto "+respuesta.getPuertoOrigen());
					//el servidor recibio una respuesta, ahora debe enviarla al estinatario
				}else if(o instanceof Llamada) {
					Llamada llamada=(Llamada)o;
					System.out.println("El servidor recive una solicitud de llamada de puerto "+llamada.getPuertoOrigen()+" a puerto "+llamada.getPuertoOrigen());
					//el servidor recibio una llamada, ahora debe enviarla al destinatario
				}else if(o instanceof Mensaje){
					Mensaje mensaje=(Mensaje)o;
					System.out.println("El servidor recibe un mensaje para el puerto "+mensaje.getPuertoDestino());
					//el servidor recibio un mensaje, ahora debe enviarlo al destinatario
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
	
	
	//ver donde dejar este metodo
	
	public static int getPuertoServidor(String pathConfig) {
		Properties prop=new Properties();
		int puertoServidor=0;
		
		try {
			FileInputStream in=new FileInputStream(pathConfig);
			prop.load(in);
			
			String puertoServidorString=prop.getProperty("puerto");
			puertoServidor=Integer.parseInt(puertoServidorString);
			
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Archivo "+pathConfig+" no encotnrado");
		} catch (IOException e) {
			System.out.println("Error al leer el archivo");
		}
		
		return puertoServidor;
	}

}
