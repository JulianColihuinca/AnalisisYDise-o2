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
    private RespuestaLlamada respuesta=null;
    

  
    
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
  //CONSTRUCTOR USADO PARA MANDAR RESPUESTA A LLAMADA ENTRANTE
    public UsuarioCliente(int puerto, RespuestaLlamada res) {
		this.puerto = puerto;
		this.respuesta = res;
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
                        
            out = new ObjectOutputStream(sc.getOutputStream());
            
            //---------------------------------Envio -----------------------------------
            
            if(this.llamada==null) { 
            	
            	if(this.mensaje==null) {//----> ENVIO RESPUESTA A PETICION DE LLAMADA
            		
            		out.writeObject(this.respuesta);
            	}else
            		out.writeUTF(this.mensaje);//----> ENVIO MENSAJE
            	
            }else {//----> ENVIO SOLICITUD DE LLAMADA
            	
            	out.writeObject(this.llamada);
            	
            }
            
            
            sc.close();
           
        } catch (IOException ex) {
            //Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        	System.out.println("hubo un problema");
        }

    }

}
