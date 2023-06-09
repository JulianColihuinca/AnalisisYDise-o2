package Monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorMonitor {
	private IVistaMonitor vista;
	private RedMonitor redMonitor;

	public ControladorMonitor() {
		this.vista= new VistaMonitor();
		this.redMonitor=new RedMonitor();
	}
	
	

}
