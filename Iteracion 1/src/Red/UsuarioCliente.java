package Red;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import Servidor.ConfirmacionRegistro;
import Servidor.ListaUsuarios;
import Servidor.UsuarioRegistro;

public class UsuarioCliente implements Runnable {

    private int puerto;
    private Llamada llamada=null;
    private Mensaje mensaje=null;
    private RespuestaLlamada respuesta=null;
    private UsuarioRegistro registro=null;
    private ConfirmacionRegistro conf=null;
    private ListaUsuarios listaUsuarios=null;

  
    
    //CONSTRUCTOR USADO PARA INICIAR UNA LLAMADA
    public UsuarioCliente(int puerto,Llamada llamada) {
        this.puerto = puerto;
        this.llamada=llamada;
    }
      
    //CONSTRUCTOR USADO PARA MANDAR MENSAJES
    public UsuarioCliente(int puerto, Mensaje mensaje) {
		this.puerto = puerto;
		this.mensaje = mensaje;
		
	}
    
    //CONSTRUCTOR USADO PARA MANDAR RESPUESTA A LLAMADA ENTRANTE
    public UsuarioCliente(int puerto, RespuestaLlamada res) {
		this.puerto = puerto;
		this.respuesta = res;
	}
    
  //CONSTRUCTOR USADO PARA MANDAR UNA SOLICITUD DE REGISTRO AL SERVIDOR
    public UsuarioCliente(int puerto, UsuarioRegistro registro) {
		this.puerto = puerto;
		this.registro=registro;
	}
    
    public UsuarioCliente(int puerto, ConfirmacionRegistro conf) {
		this.puerto = puerto;
		this.conf=conf;
	}
    
    //CONSTRUCTOR USADO CUANDO SE ENVIA UNA FINALIZACION DE LLAMADA
    public UsuarioCliente(int puerto) {
		this.puerto = puerto;
		
	}
    
    //CONSTRUCTOR USADO CUANDO UN SERVIDOR LE ENVIA SU ESTADO AL MONITOR
    public UsuarioCliente(int puerto, ListaUsuarios lista) {
		this.puerto = puerto;
		this.listaUsuarios=lista;
		
	}
    
    



	@Override
    public void run() {
        //Host del servidor
        final String HOST = Conexion.getIP();
        
        //-----------------------------Puerto del servidor 
        ObjectOutputStream out;

        try {
            //-----------------------------Creo el socket para conectarme con el cliente
            Socket sc = new Socket(HOST,/* Conexion.getPuertoServidor()*/this.puerto);
                        
            out = new ObjectOutputStream(sc.getOutputStream());
            
            //---------------------------------Envio -----------------------------------
          if(this.listaUsuarios!=null) {
        	  out.writeObject(this.listaUsuarios);
          } 
          else {  
	           if(this.conf!=null) {
	        	   out.writeObject(this.conf);
	           }
	           else { 
		            if(this.registro!= null) {
		            	//System.out.println("Registrarme");
		        		out.writeObject(this.registro);//----> ENVIO MENSAJE
		        		//System.out.println("Regsitrarme2");
		            }
		            else {
				            if(this.llamada==null) { 
				            //	System.out.println("no recibi llamada");
				            	if(this.mensaje==null) {
				            	//	System.out.println("no es mensaje");
				            		if(this.respuesta==null)
				            			out.writeObject(new FinalizarLlamada());
				            		else
				            			out.writeObject(this.respuesta);//----> ENVIO RESPUESTA A PETICION DE LLAMADA
				            	}else {
				            	//	System.out.println("intento enviar mensaje");
				            		out.writeObject(this.mensaje);//----> ENVIO MENSAJE
				            	
				            	}
				            }else {//----> ENVIO SOLICITUD DE LLAMADA
				            	System.out.println("es llamada");
				            	out.writeObject(this.llamada);
				            	System.out.println("se envio la llamada");
				            }
		            }
	            }
          }
            sc.close();
           
        } catch (IOException ex) {
            //Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        	System.out.println("hubo un problema");
        	
        	if(this.puerto==Conexion.getPuertoServidor()) {//aca no esta creado el servidor
        		JOptionPane.showMessageDialog(null, "El servidor no disponible");
        	}
        }

    }

}
