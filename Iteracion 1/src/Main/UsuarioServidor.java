package Main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

public class UsuarioServidor extends Observable implements Runnable{
	private int puerto;
	private ServerSocket servidor;
	
	public UsuarioServidor(int puerto) throws IOException{
		this.puerto=puerto;
		//Creamos el socket del servidor
		this.servidor=new ServerSocket(puerto);
	}

	public int getPuerto() {
		return this.puerto;
		
	}
	public ServerSocket getServidor() {
		return this.servidor;
	}
	
	@Override
	public void run() {
		Socket sc = null;
        ObjectInputStream in;

        try {
            
            System.out.println("Servidor iniciado");

            //Siempre estara escuchando peticiones
            
            while (true) {

                //Espero a que un cliente se conecte
                sc = servidor.accept();

                System.out.println("Cliente conectado");
                in = new ObjectInputStream(sc.getInputStream());
                
                Object o;
				
				o =in.readObject();
				
                //------------------Leo el mensaje que me envia o llamada
				
                if(o instanceof Llamada) {//ES LLAMADA
                	System.out.println("SERVIDOR RECIBE LLAMADA");
                }else if(o instanceof String){//ES MENSAJE
                	String mensaje = in.readUTF();
                	System.out.println(mensaje);
                }
                
                this.setChanged();
                this.notifyObservers(o);
                this.clearChanged();
                
                //Cierro el socket
                sc.close();
                System.out.println("Cliente desconectado");

            }

        } catch (IOException ex) {
            //Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
