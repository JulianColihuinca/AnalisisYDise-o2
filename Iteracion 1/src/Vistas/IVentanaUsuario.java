package Vistas;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import Servidor.UsuarioRegistro;

public interface IVentanaUsuario {
	
	void addActionListener(ActionListener a);
	String getIP();
	String getPuerto();
	void actualizarDatos(String ip,int puerto,String nickname);
	
	JFrame addObserver();
	
	void llamadaRechazada();
	void recibirLlamada(String ip,int puerto,String nickname);
	void llamadaAceptada();

	void setVisible(boolean res);
	void actualizarEstadoServidor(boolean estado);
	void actualizarTablaUsuarios(ArrayList<String> nicknames,ArrayList<String> ips,ArrayList<Integer> puertos );
}
