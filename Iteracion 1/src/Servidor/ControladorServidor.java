package Servidor;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import Red.Conexion;
import Red.Llamada;
import Red.Mensaje;
import Red.RespuestaLlamada;

public class ControladorServidor implements Observer{
	
	private IVentanaServidor vistaServidor;
	private RedServidor redServidor;
	
	
	public ControladorServidor() {
		super();
		
		try {
			this.redServidor = new RedServidor(Conexion.getPuertoServidor());
			this.vistaServidor = new VentanaServidor();
			this.vistaServidor.setVisible(true);
			this.redServidor.addObserver(this);
		} catch (IOException e) {
			try {
				this.redServidor = new RedServidor(Conexion.getPuertoServidor2());
				this.vistaServidor = new VentanaServidor();
				this.vistaServidor.setVisible(true);
				this.redServidor.addObserver(this);
			}catch (IOException e2) {
				System.out.println("ERROR AL CREAR EL CONTROLADOR SERVIDOR, PUERTO DEL SERVIDOR EN USO");
			}
			
		}
	}


	@Override
	public void update(Observable o, Object arg) {
		//actualizo solicitud en ventanaServidor
		if(arg instanceof RespuestaLlamada) {
			
			RespuestaLlamada respuesta=(RespuestaLlamada)arg;
			this.vistaServidor.actualizarLista("Recibo "+respuesta.toString());
			
			
		}else if(arg instanceof Llamada) {
			
			Llamada llamada=(Llamada)arg;
			this.vistaServidor.actualizarLista("Recibo "+llamada.toString());
			
		}else if(arg instanceof Mensaje){
			
			Mensaje mensaje=(Mensaje)arg;
			this.vistaServidor.actualizarLista("Recibo "+mensaje.toString());
			
		}
		else if(arg instanceof UsuarioRegistro) { //Registra un nuevo usuario en el servidor
			UsuarioRegistro usuarioReg=(UsuarioRegistro)arg;
			this.vistaServidor.actualizarLista("Recibo "+usuarioReg.toString());
		}
		
	}



}