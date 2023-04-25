package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Conexion.Conexion;
import Interfaces.UIInicioSesion;
import Main.UsuarioServidor;
import Vistas.VentanaInicioSesion;

public class ControladorInicioSesion implements ActionListener {

	private UIInicioSesion vista;
	
	
	
	public ControladorInicioSesion() {
		this.vista= new VentanaInicioSesion();
		this.vista.addActionListener(this);
		this.vista.modificarIP(Conexion.getIP());
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		String command= e.getActionCommand();
		if (command.equalsIgnoreCase("Iniciar Sesion")) {
			int puerto;
			try {
				puerto=Integer.parseInt(this.vista.getPuerto());
				if(Conexion.puertoDisponible(puerto)) {
					//PUERTO BIEN INGRESADO Y DISPONIBLE--------------------------------------------------------------
					System.out.println("InicioSesion en localhost y puerto: " + this.vista.getPuerto());
					
					UsuarioServidor usuario=new UsuarioServidor(puerto);
					
					ControladorUsuario controladorUsuario=new ControladorUsuario(usuario);
					
					
				}else 
					System.out.println("Puerto ya en uso");
					
			}catch(NumberFormatException ex) {
				System.out.println("Formato de puerto mal ingresado, ingrese un numero entero");
			} catch (IOException e1) {
				
				e1.printStackTrace();
			} 
		}	
	}

}
