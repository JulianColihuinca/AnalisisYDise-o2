package Red;

public class RespuestaLlamada extends Llamada{
	
private boolean respuesta;
	
	
	
	public RespuestaLlamada(Llamada llamada, boolean respuesta) {
		super(llamada.getPuertoOrigen(),llamada.getIPOrigen(),llamada.getNickname(),llamada.getPuertoDestino(),llamada.getIpDestino());
		this.respuesta = respuesta;
		
	}


	public boolean isRespuesta() {
		return respuesta;
	}


	@Override
	public String toString() {
		return "RespuestaLlamada [PuertoOrigen: "+this.getPuertoOrigen()+",Respuesta= " + respuesta + "]";
	}
	
	
}
