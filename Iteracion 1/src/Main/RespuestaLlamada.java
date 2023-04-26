package Main;

public class RespuestaLlamada extends Llamada{
	
	private boolean respuesta;
	
	
	public RespuestaLlamada(Llamada llamada, boolean respuesta) {
		super(llamada.getPuertoOrigen(),llamada.getIPOrigen());
		this.respuesta = respuesta;
	}


	public boolean isRespuesta() {
		return respuesta;
	}
	
	
	
	
}
