package Servidor;

public class ControladorServidor {
	
	private IVentanaServidor vistaServidor;
	private RedServidor redServidor;
	
	
	public ControladorServidor() {
		super();
		this.vistaServidor = new VentanaServidor();
		this.redServidor = new RedServidor();
	}
	
	

}
