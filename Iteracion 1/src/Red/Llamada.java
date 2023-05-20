package Red;

import java.io.Serializable;

public class Llamada implements Serializable{
	private int puertoOrigen;
	private String IPOrigen;
	
	public Llamada(int puertoOrigen, String iPOrigen) {
		this.puertoOrigen = puertoOrigen;
		IPOrigen = iPOrigen;
	}

	public int getPuertoOrigen() {
		return puertoOrigen;
	}

	public String getIPOrigen() {
		return IPOrigen;
	}
	
	
	
}
