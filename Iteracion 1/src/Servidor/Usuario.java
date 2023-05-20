package Servidor;

public class Usuario {
	
	private String ip;
	private int puerto;
	
	
	public Usuario(String ip, int puerto) {
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
	
	

}
