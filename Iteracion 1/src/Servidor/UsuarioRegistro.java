package Servidor;

import java.io.Serializable;

public class UsuarioRegistro implements Serializable{
	
	private String ip;
	private int puerto;
	
	
	public UsuarioRegistro(String ip, int puerto) {
		super();
		this.ip = ip;
		this.puerto = puerto;
	}


	public String getIp() {
		return ip;
	}


	public int getPuerto() {
		return puerto;
	}


	@Override
	public String toString() {
		return "UsuarioRegistro [ip=" + ip + ", puerto=" + puerto + "]";
	}
	
	
	
}
