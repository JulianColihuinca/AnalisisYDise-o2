package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorChat implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String command= e.getActionCommand();
		if (command.equalsIgnoreCase("Enviar Mensaje")) {
			
		}
		else if (command.equalsIgnoreCase("Finalizar Chat")) {
			
		}
		
	}

}
