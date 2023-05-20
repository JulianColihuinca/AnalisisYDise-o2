package Red;

import java.io.Serializable;

public class Mensaje implements Serializable {
	private String mensaje;
	private int puertoOrigen;
	
	public Mensaje(String mensaje, int puertoOrigen) {
		this.mensaje=mensaje;
		this.puertoOrigen=puertoOrigen;
	}

	public String getMensaje() {
		return mensaje;
	}

	public int getPuerto() {
		return puertoOrigen;
	}
	
	
}

