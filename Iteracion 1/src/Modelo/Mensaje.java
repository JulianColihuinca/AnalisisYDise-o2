package Modelo;

import java.io.Serializable;

public class Mensaje implements Serializable {
	private String mensaje;
	private int puertoDestino;
	
	public Mensaje(String mensaje, int puertoDestino) {
		this.mensaje=mensaje;
		this.puertoDestino=puertoDestino;
	}

	public String getMensaje() {
		return mensaje;
	}

	public int getPuertoDestino() {
		return puertoDestino;
	}
	
	
}

