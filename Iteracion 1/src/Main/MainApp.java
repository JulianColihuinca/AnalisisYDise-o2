package Main;

import java.io.IOException;
import java.util.Objects;

import Controladores.ControladorInicioSesion;
import Modelo.Conexion;
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
			
			
			
			int puerto1=8000;
			int puerto2=8001;
			
			int hash1= Objects.hash(puerto1,puerto2);
			int hash2= Objects.hash(puerto2,puerto1);
			
			System.out.println(hash1);
			System.out.println(hash2);
			
			try {
				UsuarioServidor u1=new UsuarioServidor(1500);
				UsuarioServidor u2=new UsuarioServidor(1600);
				
				int hash3= Objects.hash(u1.getPuerto(),u2.getPuerto());
				int hash4= Objects.hash(u1.getPuerto(),u2.getPuerto());
				
				System.out.println(hash3);
				System.out.println(hash4);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	}

}
