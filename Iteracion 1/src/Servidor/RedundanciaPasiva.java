package Servidor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class RedundanciaPasiva {
	
	
	//El metodo cambia el puerto principal del .properties
	public static void cambioServidor() {
		String pathConfig="configServidor.properties";
		Properties prop = new Properties();
		int servidorActual = 0;
		int servidorNuevo = 0;
		
		try {
			FileInputStream in = new FileInputStream(pathConfig);
			prop.load(in);

			String puertoServidorString = prop.getProperty("puertoServidor");
			servidorActual = Integer.parseInt(puertoServidorString);
			
			String puertoServidor2String = prop.getProperty("puertoServidor2");
			servidorNuevo = Integer.parseInt(puertoServidor2String);
			
			//System.out.println("puertoServidor= "+servidorActual+" puertoServidor2= "+servidorNuevo);
			
			prop.setProperty("puertoServidor", puertoServidor2String);
			prop.setProperty("puertoServidor2", puertoServidorString);
			
			
			FileOutputStream fileOutputStream = new FileOutputStream(pathConfig);
			prop.store(fileOutputStream, null);
			
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Archivo " + pathConfig + " no encotnrado");
		} catch (IOException e) {
			System.out.println("Error al leer el archivo");
		}	
	}
	
	

}
