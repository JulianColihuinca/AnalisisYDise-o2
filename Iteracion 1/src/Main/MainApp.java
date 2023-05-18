package Main;

import java.io.IOException;
import java.util.Objects;

import Controladores.ControladorInicioSesion;
import Modelo.Conexion;
import Modelo.CustomHashUtility;
import Modelo.UsuarioServidor;

public class MainApp {

	public static void main(String[] args) {
		
		 
		try {
			//SE CREA SERVIDOR EN PUERTO 5000
			UsuarioServidor servidor=new UsuarioServidor(Conexion.PUERTO_SERVER);
			Thread t = new Thread(servidor);
			t.start();
		} catch (IOException e) {
			//SI EL SERVIDOR YA EXISTE NO PASA NADA
		}
		
		
		
		//SE CREA CONTROLADOR DE LA VENTANA DE INICIO DONDE SE LANZA LA MISMA
			ControladorInicioSesion c= new ControladorInicioSesion();
			
			
	}
	
}
