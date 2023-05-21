package Servidor;

import java.io.Serializable;

public class ConfirmacionRegistro implements Serializable{
	
	private boolean registrado;

	public ConfirmacionRegistro(boolean registrado) {
		super();
		this.registrado = registrado;
	}

	public boolean isRegistrado() {
		return registrado;
	}
	
	

}
