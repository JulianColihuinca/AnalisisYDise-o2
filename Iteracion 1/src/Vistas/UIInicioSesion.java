package Vistas;

import java.awt.event.ActionListener;

public interface UIInicioSesion {
	
	public String getPuerto();
	public void modificarIP(String ip);
	public void addActionListener(ActionListener a);
    void setVisible(boolean b);
}
