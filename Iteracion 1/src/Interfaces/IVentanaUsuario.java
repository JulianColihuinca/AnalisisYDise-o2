package Interfaces;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

public interface IVentanaUsuario {
	
	void addActionListener(ActionListener a);
	String getIP();
	String getPuerto();
	void actualizarDatos(String ip,int puerto);
	
	JFrame addObserver();

}
