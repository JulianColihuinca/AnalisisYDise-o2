package Servidor;

import java.io.IOException;

public class ControladorServidor {
	
	private IVentanaServidor vistaServidor;
	private RedServidor redServidor;
	
	
	public ControladorServidor() {
		super();
		
		try {
			this.redServidor = new RedServidor();
			this.vistaServidor = new VentanaServidor();
		} catch (IOException e) {
			System.out.println("ERROR AL CREAR EL CONTROLADOR SERVIDOR, PUERTO DEL SERVIDOR EN USO");
		}
	}
	
	

}
