package Monitor;

public class EstadoServidor {
	private int nroServidor;
	private String estado;
	public EstadoServidor(int nroServidor, String estado) {
		this.nroServidor = nroServidor;
		this.estado = estado;
	}
	public int getNroServidor() {
		return nroServidor;
	}
	public String getEstado() {
		return estado;
	}	
	
}
