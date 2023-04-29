package Interfaces;

import java.awt.event.ActionListener;

public interface IVentanaChat {
	String getMensaje();
	void addMensaje(String mensaje);
	void setVisible(boolean res);
	
	void addActionListener(ActionListener listener);
	
	void tituloPuerto(String puerto);
	void actualizarEtiquetas(String ip1,int puerto1,String ip2,int puerto2);
}
