package Main;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.Socket;

import Conexion.Conexion;

public class UsuarioCliente implements Runnable {

    private int puerto;
    private Llamada llamada=null;
    private String mensaje=null;
    

    //CONSTRUCTOR USADO PARA INICIAR UNA LLAMADA
    public UsuarioCliente(int puerto,Llamada llamada) {
        this.puerto = puerto;
       this.llamada=llamada;
    }
      
    //CONSTRUCTOR USADO PARA MANDAR MENSAJES
    public UsuarioCliente(int puerto, String mensaje) {
		this.puerto = puerto;
		this.mensaje = mensaje;
	}



	@Override
    public void run() {
        //Host del servidor
        final String HOST = Conexion.getIP();
        
        //-----------------------------Puerto del servidor
        
        ObjectOutputStream out;

        try {
            //-----------------------------Creo el socket para conectarme con el cliente
            Socket sc = new Socket(HOST, puerto);
            System.out.println("SOCKET DE CONEXION CREADO");
            
            out = new ObjectOutputStream(sc.getOutputStream());
            System.out.println("OBJECT OUTPUT STREAM CREADO");
            //---------------------------------Envio un mensaje al cliente o llamada
            
            if(this.llamada.equals(null)) { //ENVIO MENSAJE
            	out.writeUTF(this.mensaje);
            }else {//ENVIO LLAMADA
            	System.out.println("TROTO ENVIAR LLAMADA");
            	out.writeObject(this.llamada);
            	System.out.println("ENVIE LLAMADA");
            }
            
            
            sc.close();
            System.out.println("SOCKET DE CONEXION CERRADO");
            System.out.println("se envio el objeto");
        } catch (IOException ex) {
            //Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        	System.out.println("hubo un problema");
        }

    }

}
