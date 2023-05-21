package Servidor;

public class UsuarioRegistro {
	
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
	
	

}
