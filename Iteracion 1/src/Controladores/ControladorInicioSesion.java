package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import Modelo.Conexion;
import Modelo.UsuarioServidor;
import Vistas.UIInicioSesion;
import Vistas.VentanaInicioSesion;

public class ControladorInicioSesion implements ActionListener {

	private UIInicioSesion vista;
	
	
	//CUANDO SE INSTANCIA EL CONTROLADOR CREA LA VENTANA
	public ControladorInicioSesion() {
		this.vista= new VentanaInicioSesion();
		this.vista.addActionListener(this);
		this.vista.modificarIP(Conexion.getIP());
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		String command= e.getActionCommand();
		
		//SE PRESIONA BOTON INICIA SECION
		if (command.equalsIgnoreCase("Iniciar Sesion")) {
			int puerto;
			try {
				//SE VERIFICA QUE EL VALOR INGRESADO EN EL PUERTO SEA VALIDO
				puerto=Integer.parseInt(this.vista.getPuerto());
				
				//SE VERIFICA QUE EL PUERTO INGRESADO ESTE DISPONIBLE
				if(Conexion.puertoDisponible(puerto)) {
					
					System.out.println("InicioSesion en localhost y puerto: " + this.vista.getPuerto());
					
					//SE CREA UN USUARIO SERVIDOR CON ESTE PUERTO Y SE CREA UN CONTROLADOR DE LA VENTANA USUARIO
					UsuarioServidor usuario=new UsuarioServidor(puerto);
					
					ControladorUsuario controladorUsuario=new ControladorUsuario(usuario);
					
					
				}else 
					JOptionPane.showMessageDialog(null, "Puerto ya en uso");
					
			}catch(NumberFormatException ex) {
				System.out.println("Formato de puerto mal ingresado, ingrese un numero entero");
			} catch (IOException e1) {
				
				e1.printStackTrace();
			} 
		}	
	}

}
