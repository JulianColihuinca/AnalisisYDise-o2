package Main;

import java.io.DataInputStream;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.ServerSocket;
import java.net.Socket;

public class UsuarioServidor implements Runnable{
	private int puerto;
	private ServerSocket servidor;
	
	public UsuarioServidor(int puerto) throws IOException{
		this.puerto=puerto;
		//Creamos el socket del servidor
		this.servidor=new ServerSocket(puerto);
	}

	
	
	@Override
	public void run() {
		Socket sc = null;
        DataInputStream in;

        try {
            
            System.out.println("Servidor iniciado");

            //Siempre estara escuchando peticiones
            
            while (true) {

                //Espero a que un cliente se conecte
                sc = servidor.accept();

                System.out.println("Cliente conectado");
                in = new DataInputStream(sc.getInputStream());
               
                //Leo el mensaje que me envia
                String mensaje = in.readUTF();

                System.out.println(mensaje);
                /*
                this.setChanged();
                this.notifyObservers(mensaje);
                this.clearChanged();*/
                
                //Cierro el socket
                sc.close();
                System.out.println("Cliente desconectado");

            }

        } catch (IOException ex) {
            //Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}
	
	

}
