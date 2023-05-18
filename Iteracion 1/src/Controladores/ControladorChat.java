package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import Modelo.Conexion;
import Modelo.CustomHashUtility;
import Modelo.FinalizarLlamada;
import Modelo.Mensaje;
import Vistas.IVentanaChat;
import Vistas.VentanaChat;

public class ControladorChat implements ActionListener, Observer {
	
	private IVentanaChat vistaChat;
	private ControladorUsuario consUsuario;
	
	private int puertoOrigen; 
	private int puertoDestino;
	private String ipOrigen;
	private String ipDestino;
	
	
	
	public ControladorChat( ControladorUsuario consUsuario, int puertoOrigen, int puertoDestino,
			String ipOrigen, String ipDestino) {
		
		this.vistaChat=new VentanaChat();
		this.vistaChat.addActionListener(this);
		
		
		this.consUsuario = consUsuario;
		this.puertoOrigen = puertoOrigen;
		this.puertoDestino = puertoDestino;
		this.ipOrigen = ipOrigen;
		this.ipDestino = ipDestino;
		
		
		this.vistaChat.tituloPuerto("MODO: CHAT");
		this.consUsuario.getUsuario().addObserver(this);
		this.vistaChat.actualizarEtiquetas(ipOrigen, puertoOrigen, ipDestino, puertoDestino);
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		String command= e.getActionCommand();
		if (command.equalsIgnoreCase("Enviar Mensaje")) {
			String mensaje= this.vistaChat.getMensaje();
			this.enviarMensaje(mensaje);
			
			
			
		}
		else if (command.equalsIgnoreCase("Finalizar Chat")) {
			this.finalizarChat();
		}
		
	}




	@Override
	public void update(Observable o, Object arg) {
		// EN ESTA INSTANCIA SOLO DEBERIA RECIBIR UN MENSAJE PARA AGREGAR AL CHAT O LA FINALIZACION DEL CHAT
		if( arg instanceof Mensaje) {
			Mensaje mensaje= (Mensaje) arg;
			this.recibirMensaje(mensaje);
			System.out.println("hash al recibir mensaje"+CustomHashUtility.generateCustomHash(puertoOrigen, puertoDestino));
		}else if(arg instanceof FinalizarLlamada) {
			this.recibirFinalizarChat();
		}
		
	}
	
	
	private void enviarMensaje(String mensaje) {
		//DEBERIA LEER EL MENSAJE INGRESADO, AGREGARLO AL TEXTAREA Y MANDARLO PARA QUE LO AGREGUE EL DESTINATARIO
		String mensajeCompleto="TU: "+mensaje+"\n";
		this.vistaChat.addMensaje(mensajeCompleto);
		this.vistaChat.mensajeEnviado();// borra el mensaje ya enviado del area de texto
		
		try {
			int n1=this.puertoOrigen;
			int n2=this.puertoDestino;
			
			System.out.println("hash al enviar mensaje: "+CustomHashUtility.generateCustomHash(n1, n2));
			
			String hash= CustomHashUtility.generateCustomHash(n1,n2);
			String mensajeEncriptado=Conexion.encriptar(hash,mensaje, "TripleDES");
			
			Conexion.crearUsuarioCliente(Conexion.PUERTO_SERVER,new Mensaje(mensajeEncriptado/*mensaje*/,this.puertoDestino) );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void finalizarChat(){
		//CIERRA VENTANA CHAT Y VUELVE A ABRIR VENTANA USUARIO
		//MODIFICA EL MODO DEL USUARIO A ESCUCHA
		//Y NOTIFICA AL OTRO USUARIO QUE CERRO EL CHAT PARA QUE HAGA LO MISMO 
		this.vistaChat.setVisible(false);
		this.consUsuario.getVista().setVisible(true);
		
		this.consUsuario.getUsuario().setModoEscucha();
		this.consUsuario.getUsuario().setLlamada(null);
		this.consUsuario.getUsuario().setRespuesta(null);
		JOptionPane.showMessageDialog(null, "El chat ha finalizado");
		Conexion.crearUsuarioCliente(this.puertoDestino, null);
	}
	
	private void recibirMensaje(Mensaje mensaje) {
		
		int n1=this.puertoOrigen;
		int n2=this.puertoDestino;
		
		System.out.println("hash al recibir mensaje: "+CustomHashUtility.generateCustomHash(n2, n1));
		try {
		    
			String hash= CustomHashUtility.generateCustomHash(n2, n1);
			String mensajeDescifrado= Conexion.desencriptar(hash,mensaje.getMensaje(), "TripleDES");
			String mensajeCompleto = "PUERTO "+ this.puertoDestino+": "+mensajeDescifrado/*mensaje.getMensaje()*/+"\n";
			this.vistaChat.addMensaje(mensajeCompleto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void recibirFinalizarChat() {
		this.vistaChat.setVisible(false);
		this.consUsuario.getVista().setVisible(true);
		
		this.consUsuario.getUsuario().setModoEscucha();
		this.consUsuario.getUsuario().setLlamada(null);
		this.consUsuario.getUsuario().setRespuesta(null);
	}
	
	

}
