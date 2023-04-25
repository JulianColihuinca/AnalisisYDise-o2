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
		
		
		this.usuario.addObserver((Observer) this.vista.addObserver());
		Thread t= new Thread(usuario);
		t.start();
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command= e.getActionCommand();
		
		if (command.equalsIgnoreCase("Atender Llamada")) {//ACEPTO LA LLAMADA ENTRANTE------------------------------------------
			
		}
		else if (command.equalsIgnoreCase("Rechazar Llamada")) {//RECHAZO LA LLAMADA ENTRANTE------------------------------------
			
			RespuestaLlamada respuesta=new RespuestaLlamada(this.usuario.getLlamada(),false);
			UsuarioCliente c = new UsuarioCliente(respuesta.getPuertoOrigen(),respuesta);//para un futuro agregar ip
			Thread t = new Thread(c);
	        t.start();
			System.out.println("ESTOY RECHAZANDO LLAMADO DE PUERTO: "+this.usuario.getLlamada().getPuertoOrigen());
		}
		else if (command.equalsIgnoreCase("Comenzar Chat")) {//SOLICITO COMENZAR UN CHAT------------------------------------------
			
			int puerto;
			String ip;
			try {
				
				puerto=Integer.parseInt( this.vista.getPuerto());
				ip=this.vista.getIP(); 
				
				Llamada llamada=new Llamada(this.usuario.getPuerto(),Conexion.getIP()); 
				
				System.out.println("---------------------------------------------------------\n Puerto Origen del Llamado: "+
						llamada.getPuertoOrigen()+"\n--------------------------------------------------------");
				UsuarioCliente c = new UsuarioCliente(puerto,llamada);
				Thread t = new Thread(c);
		        t.start();
				
			}catch(NumberFormatException ex) {
				System.out.println("Formato puerto mal ingresado,ingrese numero entero");
			}
			  
		}//-------------------------------------------------------------------------------------------------------------------------
				
	}

}
