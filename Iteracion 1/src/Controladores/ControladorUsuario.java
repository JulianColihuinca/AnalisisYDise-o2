package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Interfaces.IVentanaUsuario;
import Vistas.VentanaUsuario;

public class ControladorUsuario implements ActionListener {
	
	private IVentanaUsuario vista;
	
	public ControladorUsuario() {
		this.vista=new VentanaUsuario();
		this.vista.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command= e.getActionCommand();
		if (command.equalsIgnoreCase("Atender Llamada")) {
			
		}
		else if (command.equalsIgnoreCase("Comenzar Chat")) {
			
		}
		else if (command.equalsIgnoreCase("Rechazar Llamada")) {
			
		}
		
	}

}
