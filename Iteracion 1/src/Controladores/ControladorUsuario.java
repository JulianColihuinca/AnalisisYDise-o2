package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import Conexion.Conexion;
import Interfaces.IVentanaUsuario;
import Main.Llamada;
import Main.RespuestaLlamada;
import Main.UsuarioCliente;
import Main.UsuarioServidor;
import Vistas.VentanaUsuario;

public class ControladorUsuario implements ActionListener {
	
	private IVentanaUsuario vista;
	private UsuarioServidor usuario;
	
	
	public ControladorUsuario(UsuarioServidor usuario) {
		this.vista=new VentanaUsuario();
		this.vista.addActionListener(this);
		this.usuario=usuario;
		
		this.vista.actualizarDatos(Conexion.getIP(), this.usuario.getPuerto());	
		
		
		//AL CREARSE ESTE CONTROLADOR CREA LA VENTANA USUARIO Y EJECUTA EL HILO DEL USUARIO PARA 
		//QUE QUEDE PENDIENTE A RECIBIR UNA LLAMADA
		this.usuario.addObserver((Observer) this.vista.addObserver());
		Thread t= new Thread(usuario);
		t.start();
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command= e.getActionCommand();
		
		//---------------------------------------ACEPTO LA LLAMADA ENTRANTE-----------------------------------------
		if (command.equalsIgnoreCase("Atender Llamada")) {
			
		}//--------------------------------------RECHAZO LA LLAMADA ENTRANTE------------------------------------
		else if (command.equalsIgnoreCase("Rechazar Llamada")) {
			
			//CREO UNA RESPUESTA A LA LLAMADA CON LA LLAMADA RECIBIDA Y CON UN BOOLEAN FALSE 
			RespuestaLlamada respuesta=new RespuestaLlamada(this.usuario.getLlamada(),false);
			
			//CREO EL USUARIO CLIENTE QUE ENVIA LA RESPUESTA AL PUERTO ORIGEN DE LA LLAMADA Y EJECUTO EL HILO
			UsuarioCliente c = new UsuarioCliente(respuesta.getPuertoOrigen(),respuesta);//para un futuro agregar ip
			Thread t = new Thread(c);
	        t.start();
			
		}//---------------------------------------SOLICITO COMENZAR UN CHAT------------------------------------------
		else if (command.equalsIgnoreCase("Comenzar Chat")) {
			
			int puerto;
			String ip;
			try {
				//VERIFICO QUE ESTEN BIEN INGRESADO EL PUERTO, FALTA VERIFICAR IP
				puerto=Integer.parseInt( this.vista.getPuerto());
				ip=this.vista.getIP(); 
				
				//CREO UNA LLAMADA CON EL PUERTO DEL USUARIO ORIGEN, FALTA INCLUIR IP ESPECIFICA 
				Llamada llamada=new Llamada(this.usuario.getPuerto(),Conexion.getIP()); 
				
				//System.out.println("---------------------------------------------------------\n Puerto Origen del Llamado: "+llamada.getPuertoOrigen()+"\n--------------------------------------------------------");
				
				//CREO UN USUARIO CLIENTE QUE ENVIA LA LLAMADA AL PUERTO DESTINO Y EJECUTO EL HILO
				UsuarioCliente c = new UsuarioCliente(puerto,llamada);
				Thread t = new Thread(c);
		        t.start();
				
			}catch(NumberFormatException ex) {
				System.out.println("Formato puerto mal ingresado,ingrese numero entero");
			}
			  
		}//-------------------------------------------------------------------------------------------------------------------------
				
	}

}
