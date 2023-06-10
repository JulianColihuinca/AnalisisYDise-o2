package Red;

import java.io.Serializable;

public class Llamada implements Serializable{
	private int puertoOrigen;//EL QUE LLAMO
	private String ipOrigen;
	private String nickname;
	private int puertoDestino;//EL QUE ES LLAMADO
	private String ipDestino;
	

	public Llamada(int puertoOrigen, String ipOrigen,String nickname, int puertoDestino, String ipDestino) {
		this.puertoOrigen = puertoOrigen;
		this.ipOrigen = ipOrigen;
		this.nickname=nickname;
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
	
	

	
	public String getNickname() {
		return nickname;
	}

	@Override
	public String toString() {
		return "Llamada [puertoOrigen=" + puertoOrigen + ", ipOrigen=" + ipOrigen + ", puertoDestino=" + puertoDestino
				+ ", ipDestino=" + ipDestino + "]";
	}
	
	
	
	
	
}
