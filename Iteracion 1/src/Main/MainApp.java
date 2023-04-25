package Main;

import Controladores.ControladorInicioSesion;

public class MainApp {

	public static void main(String[] args) {
		ControladorInicioSesion c= new ControladorInicioSesion();
		/*UsuarioServidor usuario;
		try {
			usuario = new UsuarioServidor(8000);
			ControladorUsuario cUsuario=new ControladorUsuario(usuario);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*Llamada llamada=new Llamada(8,"hola");
		
		Object o=llamada;
		ins
		if(o.getClass().equals(o))
			System.out.println("o.getClass() = Object");

		if(o.getClass().equals(llamada))
			System.out.println("o.getClass() = Llamada");*/
		/*
		Object o= new Llamada(5,"hola");
		o="HOLA MUNDO!";
		
		if (o instanceof Llamada) {
			System.out.println("o es una llamda");
			
		}
		if(o instanceof String)
			System.out.println("o es un String");*/
	}

}
