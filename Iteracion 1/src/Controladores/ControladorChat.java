package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import Interfaces.IVentanaChat;
import Main.FinalizarLlamada;
import Main.Mensaje;
import Main.UsuarioCliente;
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
		
		
		this.vistaChat.tituloPuerto(this.consUsuario.getUsuario().getPuerto()+"");
		System.out.println("el controlador chat de puerto: "+this.puertoOrigen+" observa al puerto: "+this.consUsuario.getUsuario().getPuerto());
		this.consUsuario.getUsuario().addObserver(this);
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		String command= e.getActionCommand();
		if (command.equalsIgnoreCase("Enviar Mensaje")) {
			
			//DEBERIA LEER EL MENSAJE INGRESADO, AGREGARLO AL TEXTAREA Y MANDARLO PARA QUE LO AGREGUE EL DESTINATARIO
			String mensaje= this.vistaChat.getMensaje();
			String mensajeCompleto="TU: "+mensaje+"\n";
			this.vistaChat.addMensaje(mensajeCompleto);
			
			
			//REVISAR PORQUE NO RECIVE MENSAJE
			
			System.out.println("MANDO MENSAJE A "+this.puertoDestino);
			
			UsuarioCliente c = new UsuarioCliente(this.puertoDestino, new Mensaje(mensaje,this.puertoOrigen));// para un futuro agregar ip
			Thread t = new Thread(c);
			t.start();
			
		}
		else if (command.equalsIgnoreCase("Finalizar Chat")) {
			
			//CIERRA VENTANA CHAT Y VUELVE A ABRIR VENTANA USUARIO
			//MODIFICA EL MODO DEL USUARIO A ESCUCHA
			//Y NOTIFICA AL OTRO USUARIO QUE CERRO EL CHAT PARA QUE HAGA LO MISMO 
			
		}
		
	}




	@Override
	public void update(Observable o, Object arg) {
		// EN ESTA INSTANCIA SOLO DEBERIA RECIBIR UN MENSAJE PARA AGREGAR AL CHAT O LA FINALIZACION DEL CHAT
		System.out.println("\n\n METODO UPDATE EN CONTROLADOR CHAT\n\n");
		
		if( arg instanceof Mensaje) {
			Mensaje mensaje= (Mensaje) arg;
			String mensajeCompleto = "PUERTO "+ mensaje.getPuerto()+": "+mensaje.getMensaje()+"\n";
			this.vistaChat.addMensaje(mensajeCompleto);
		}else if(arg instanceof FinalizarLlamada) {
			//CREAR JOPTION PANE
			
			this.vistaChat.setVisible(false);
			this.consUsuario.getVista().setVisible(true);
			
			//volver usaurio a modo escucha
			
		}
		
	}

}
