package Monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class ControladorMonitor implements Observer {
	private IVistaMonitor vista;
	private RedMonitor redMonitor;

	public ControladorMonitor() throws IOException {
		this.vista= new VistaMonitor();
		this.redMonitor=new RedMonitor();
		this.redMonitor.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		String servidor= (String) arg;
		if(servidor.equalsIgnoreCase("ServidorA")) {
			this.vista.actualiarVista(1, "Disponible");
			this.vista.actualiarVista(2, "No Disponible");
		}
		else if(servidor.equalsIgnoreCase("ServidorB")) {
			this.vista.actualiarVista(2, "Disponible");
			this.vista.actualiarVista(1, "No Disponible");
		}
	}
	
	

}
