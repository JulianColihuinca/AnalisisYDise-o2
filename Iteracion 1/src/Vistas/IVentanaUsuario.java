package Vistas;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

public interface IVentanaUsuario {
	
	void addActionListener(ActionListener a);
	String getIP();
	String getPuerto();
	void actualizarDatos(String ip,int puerto);
	
	JFrame addObserver();
	
	void llamadaRechazada();
	void recibirLlamada(String ip,int puerto);
	void llamadaAceptada();

	void setVisible(boolean res);
}
