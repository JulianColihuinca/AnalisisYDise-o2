package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import Conexion.Conexion;
import Interfaces.IVentanaUsuario;
import Main.Llamada;
import Main.RespuestaLlamada;
import Main.UsuarioCliente;
import Main.UsuarioServidor;
import Vistas.VentanaUsuario;

public class ControladorUsuario implements ActionListener, Observer {

	private IVentanaUsuario vista;
	private UsuarioServidor usuario;

	public ControladorUsuario(UsuarioServidor usuario) {
		this.vista = new VentanaUsuario();
		this.vista.addActionListener(this);
		this.usuario = usuario;

		this.vista.actualizarDatos(Conexion.getIP(), this.usuario.getPuerto());

		// AL CREARSE ESTE CONTROLADOR CREA LA VENTANA USUARIO Y EJECUTA EL HILO DEL 	USUARIO PARA
		// QUE QUEDE PENDIENTE A RECIBIR UNA LLAMADA
		this.usuario.addObserver((Observer) this);
		Thread t = new Thread(usuario);
		t.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		// ---------------------------------------ACEPTO LA LLAMADA ENTRANTE-----------------------------------------
		if (command.equalsIgnoreCase("Atender Llamada")) {
			
			this.enviarRespuesta(true);
			this.vista.llamadaAceptada(); //este metodo solo oculta la ventana usuario
			
			ControladorChat controladorChat= new ControladorChat();//ESTE CONTROLADOR LANZA LA VENTANA CHAT DEL 
																   //USUARIO QUE ATIENDE LA LLAMADA
			
			this.usuario.setModoChat();
			this.usuario.setLlamada(null);// SE ATENDIO LA LLAMADA, YA ESTOY EN MODO CHAT
		} // --------------------------------------RECHAZO LA LLAMADA ENTRANTE------------------------------------
		else if (command.equalsIgnoreCase("Rechazar Llamada")) {
			// OCULTO LA PARTE DONDE RECIBI LA LLAMADA
			this.vista.llamadaRechazada();

			this.enviarRespuesta(false);
			this.usuario.setLlamada(null);

		} // ---------------------------------------SOLICITO COMENZAR UN CHAT------------------------------------------
		else if (command.equalsIgnoreCase("Comenzar Chat")) {

			int puerto;
			String ip;
			
			try {
				//SOLO INICIA UN LLAMADO SI NO TIENE UNO QUE RESPONDER Y SI NO ESPERA RESPUESTA DE OTRO LLAMADO
				if(this.usuario.getLlamada()==null && this.usuario.getModo().equals("ESCUCHA")) {				
					// VERIFICO QUE ESTEN BIEN INGRESADO EL PUERTO, FALTA VERIFICAR IP
					puerto = Integer.parseInt(this.vista.getPuerto());
					ip = this.vista.getIP();

					// CREO UNA LLAMADA CON EL PUERTO DEL USUARIO ORIGEN, FALTA INCLUIR IP ESPECIFICA
					Llamada llamada = new Llamada(this.usuario.getPuerto(), Conexion.getIP());
				
					
					//ESTE USUARIO COMIENZA UN INTENTO DE LLAMADA Y PASA A MODO LLAMANDO
					//MIENTRAS ESPERA QUE LE CONTESTEN
					
					this.usuario.setModoLlamando();
				
				
					// CREO UN USUARIO CLIENTE QUE ENVIA LA LLAMADA AL PUERTO DESTINO Y EJECUTO EL HILO
					UsuarioCliente c = new UsuarioCliente(puerto, llamada);
					Thread t = new Thread(c);
					t.start();
				}
			} catch (NumberFormatException ex) {
				System.out.println("Formato puerto mal ingresado,ingrese numero entero");
			}

		} // -------------------------------------------------------------------------------------------------------------------------

	}

	
	@Override
	public void update(Observable o, Object arg) {
		// ---------------------EL OBJETO RECIBIDO ES UNA RESPUESTA A LA LLAMADA----------------------------------
		if (arg instanceof RespuestaLlamada) {
			System.out.println("RECIBI UNA RESPUESTA A LA LLAMADA");
			RespuestaLlamada respuesta = (RespuestaLlamada) arg;
			
			if (!respuesta.isRespuesta()) { // SI NO ME ATENDIERON
				System.out.println("LA RESPUESTA A LA LLAMADA FUE NEGATIVA");

				//this.usuario.setLlamada(null); EL USUARIO QUE LLAMA YA TIENE NULL EN LLAMADA
				this.usuario.setModoEscucha();//VUELVE A MODO ESCUCHA
				this.vista.llamadaRechazada();

			} else {
				System.out.println("LA RESPUESTA A LA LLAMADA FUE POSITIVA");
				
				this.vista.llamadaAceptada();
				
				//CREAR CONTROLADOR DE LA VENTANA CHAT
				ControladorChat controladorChat= new ControladorChat();//ESTE CONTROLADOR LANZA LA VENTANA CHAT DEL 
				   													   //USUARIO QUE SOLICITO INICIAR LA LLAMADA
				this.usuario.setModoChat(); //PASA A MODO CHAT
			}

			// -----------------------------EL OBJETO RECIBIDO ES UNA LLAMADA----------------------------------
		} else if (arg instanceof Llamada) {

			Llamada llamada = (Llamada) arg;

			String ip = llamada.getIPOrigen();
			int puerto = llamada.getPuertoOrigen();

			this.vista.recibirLlamada(ip, puerto);
			
			this.usuario.setLlamada(llamada);
		}

		// SI RECIBO OTRA COSA QUE NO SEA UNA LLAMADA O UNA RESPUESTA NO HAGO NADA
	}
	
	public void enviarRespuesta(boolean res) {
		RespuestaLlamada respuesta= new RespuestaLlamada(this.usuario.getLlamada(),res);
		UsuarioCliente c = new UsuarioCliente(respuesta.getPuertoOrigen(), respuesta);// para un futuro agregar ip
		Thread t = new Thread(c);
		t.start();
	}

}
