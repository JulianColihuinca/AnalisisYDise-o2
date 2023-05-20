package Servidor;

import java.util.ArrayList;

public class RedServidor {
	
	private ArrayList<Usuario> usuarioRegistrados;
	
	public RedServidor() {
		this.usuarioRegistrados= new ArrayList<Usuario>();
		new Thread() {public void run() {registrarUsuario(); }}.start(); // Ejecuta el hilo para agregar usuarios
		new Thread() {public void run() {escuchar(); }}.start(); // Ejecuta el hilo para escuchar
	}
	
	// Para registrar usuarios, lo ejecuta un hilo
	public void registrarUsuario() {
		while (true) {
			
		}
	}
	
	private  void addUsuario(Usuario u) {
		this.usuarioRegistrados.add(u);
	}
	
	
	public void enviarMensaje() {}
	public void enviarLlamada() {}
	
	// Para escuchar algo, lo va a ejecutar un hilo
	public void escuchar() {
		
	}
	
	

}
