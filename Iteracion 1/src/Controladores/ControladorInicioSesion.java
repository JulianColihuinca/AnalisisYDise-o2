package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import Interfaces.UIInicioSesion;
import Modelos.Conexion;
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
			System.out.println("InicioSesion en localhost y puerto: " + this.vista.getPuerto());
		}
		
	}

}
