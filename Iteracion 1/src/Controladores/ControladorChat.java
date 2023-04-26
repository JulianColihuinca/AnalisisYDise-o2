package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Interfaces.IVentanaChat;
import Vistas.VentanaChat;

public class ControladorChat implements ActionListener {
	
	private IVentanaChat vistaChat;
	
	public ControladorChat(){
		this.vistaChat=new VentanaChat();
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command= e.getActionCommand();
		if (command.equalsIgnoreCase("Enviar Mensaje")) {
			
		}
		else if (command.equalsIgnoreCase("Finalizar Chat")) {
			
		}
		
	}

}
