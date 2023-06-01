package Servidor;

import java.io.Serializable;

public class UsuarioRegistro implements Serializable{
	
	private String ip;
	private int puerto;
	private String nickname;
	
	
	public UsuarioRegistro(String ip, int puerto,String nickname) {
		super();
		this.ip = ip;
		this.puerto = puerto;
		this.nickname=nickname;
	}


	public String getIp() {
		return ip;
	}


	public int getPuerto() {
		return puerto;
	}


	public String getNickname() {
		return nickname;
	}


	@Override
	public String toString() {
		return "UsuarioRegistro [ip=" + ip + ", puerto=" + puerto + "]";
	}
	
	
	
}
