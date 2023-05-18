package Modelo;

import java.io.Serializable;

public class Llamada implements Serializable{
	private int puertoOrigen;//EL QUE LLAMO
	private String ipOrigen;
	private int puertoDestino;//EL QUE ES LLAMADO
	private String ipDestino;
	

	public Llamada(int puertoOrigen, String ipOrigen, int puertoDestino, String ipDestino) {
		this.puertoOrigen = puertoOrigen;
		this.ipOrigen = ipOrigen;
		this.puertoDestino = puertoDestino;
		this.ipDestino = ipDestino;
	}

	public int getPuertoDestino() {
		return puertoDestino;
	}

	public String getIpDestino() {
		return ipDestino;
	}

	public int getPuertoOrigen() {
		return puertoOrigen;
	}

	public String getIPOrigen() {
		return ipOrigen;
	}
	
	
	
}
