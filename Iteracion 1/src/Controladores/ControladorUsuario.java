package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorUsuario implements ActionListener {

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
