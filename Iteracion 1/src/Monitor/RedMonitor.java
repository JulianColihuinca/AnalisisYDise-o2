package Monitor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class RedMonitor {

	private int puertoServidorA,puertoServidorB;
	private int puertoMonitor;
	
	
	
	
	
	public RedMonitor() {
		this.setearPuertos();
		this.mostrarpuertos();
	}

    private void mostrarpuertos() {
    	System.out.println("ServidorA: " + this.puertoServidorA);
    	System.out.println("ServidorB: " + this.puertoServidorB);
    	System.out.println("Monitor: " + this.puertoMonitor);
    }



	private  void setearPuertos() {
		String pathConfig="configMonitor.properties";
		Properties prop = new Properties();

		try {
			FileInputStream in = new FileInputStream(pathConfig);
			prop.load(in);

			String puertoServidorAString = prop.getProperty("puertoServidorA");
			puertoServidorA = Integer.parseInt(puertoServidorAString);
			String puertoServidorBString = prop.getProperty("puertoServidorB");
			puertoServidorB = Integer.parseInt(puertoServidorBString);
			String puertoMonitorString = prop.getProperty("puertoMonitor");
			puertoMonitor = Integer.parseInt(puertoMonitorString);

		} catch (FileNotFoundException e) {
			System.out.println("Archivo " + pathConfig + " no encotnrado");
		} catch (IOException e) {
			System.out.println("Error al leer el archivo");
		}
	}
}
