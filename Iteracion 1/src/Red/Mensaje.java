package Red;

import java.io.Serializable;

public class Mensaje implements Serializable {
	private String mensaje;
	private int puertoDestino;
	private String nickname;
	
	public Mensaje(String mensaje, int puertoDestino,String nickname) {
		this.mensaje=mensaje;
		this.puertoDestino=puertoDestino;
		this.nickname=nickname;
	}

	public String getMensaje() {
		return mensaje;
	}

	public int getPuertoDestino() {
		return puertoDestino;
	}

	
	public String getNickname() {
		return nickname;
	}

	@Override
	public String toString() {
		return "Mensaje [mensaje=" + mensaje + ", puertoDestino=" + puertoDestino + "]";
	}
	
	
}

