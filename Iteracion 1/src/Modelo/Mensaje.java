package Modelo;

import java.io.Serializable;

public class Mensaje implements Serializable {
	private String mensaje;
	private int puerto;
	
	public Mensaje(String mensaje, int puerto) {
		this.mensaje=mensaje;
		this.puerto=puerto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public int getPuerto() {
		return puerto;
	}
	
	
}

