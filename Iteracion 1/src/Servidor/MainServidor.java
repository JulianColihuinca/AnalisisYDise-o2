package Servidor;

import Red.Conexion;

public class MainServidor {

	public static void main(String[] args) {
		ControladorServidor controladorServidor= new ControladorServidor();
		System.out.println(Conexion.getPuertoServidor("configServidor.properties"));
	}

}
