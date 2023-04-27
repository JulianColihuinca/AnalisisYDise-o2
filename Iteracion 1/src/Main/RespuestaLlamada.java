package Main;

public class RespuestaLlamada extends Llamada{
	
	private boolean respuesta;
	private int puertoDestino;
	private String ipDestino;
	
	
	public RespuestaLlamada(Llamada llamada, boolean respuesta,int puerto,String ip) {
		super(llamada.getPuertoOrigen(),llamada.getIPOrigen());
		this.respuesta = respuesta;
		this.puertoDestino=puerto;
		this.ipDestino=ip;
	}


	public boolean isRespuesta() {
		return respuesta;
	}


	public int getPuertoDestino() {
		return puertoDestino;
	}


	public String getIpDestino() {
		return ipDestino;
	}
	
	
	
	
}
