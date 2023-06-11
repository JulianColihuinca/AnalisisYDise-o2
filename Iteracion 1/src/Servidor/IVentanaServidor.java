package Servidor;

import java.awt.event.WindowListener;

public interface IVentanaServidor {
     public void actualizarLista(String item) ;
     public void setVisible(boolean bool);
     public void addWindowListener(WindowListener l);
}