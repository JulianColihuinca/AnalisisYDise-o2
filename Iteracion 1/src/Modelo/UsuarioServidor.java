package Modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

public class UsuarioServidor extends Observable implements Runnable {
	private int puerto;
	private ServerSocket servidor;
	private Llamada llamada = null;
	private RespuestaLlamada respuesta = null;
	private String modo;
	/*
	 * TRES MODOS POSIBLES: 1) ESCUCHA -> MODO POR DEFECTO, PUEDE RECIBIR LLAMDAS E
	 * INICIARLAS 2) LLAMANDO -> MODO CUANDO INICIA LLAMADA, NO PUEDE RECIBIR LLAMAS
	 * HASTA QUE LE CONTESTEN 3) CHAT -> MODO CUANDO SE ENCUENTRA EN UN CHAT
	 */

	public UsuarioServidor(int puerto) throws IOException {
		this.puerto = puerto;
		this.modo = "ESCUCHA";
		this.servidor = new ServerSocket(puerto); // SE CREA EL SOCKET DEL SERVIDOR

	}

	public int getPuerto() {
		return this.puerto;

	}

	public ServerSocket getServidor() {
		return this.servidor;
	}

	public Llamada getLlamada() {
		return llamada;
	}

	public void setLlamada(Llamada llamada) {
		this.llamada = llamada;
	}

	@Override
	public void run() {
		Socket sc = null;
		ObjectInputStream in;
		boolean cond;
		try {

			// System.out.println("Servidor iniciado");

			// SIEMPRE QUEDA ESCUCHANDO PETICIONES
			while (true) {
				cond = true;

				// ESPERA A QUE UN CLIENTE SE CONECTE
				System.out.println(
						"----------------CREO UN USUARIO SERVIDOR, ESPERA A QUE SE CONECTE ALGUIEN-----------------------------------");
				sc = servidor.accept();

				System.out.println("----------------ALGUIEN SE CONECTO-----------------------------------");

				in = new ObjectInputStream(sc.getInputStream());

				Object o;

				o = in.readObject();

				// SE LEYO EL OBJETO PASADO POR EL CLIENTE
				// ESTE SABEMOS QUE ES DE TIPO LLAMADA O RESPPUESTALLAMADA ESTANDO EN LA VENTANA
				// USUARIO
				// O DE TIPO STRING = MENSAJE ESTANDO EN LA VENTANA CHAT
				
				
				

				if (this.puerto == Conexion.PUERTO_SERVER) { // EL SERVIDOR RECIBE ALGO
					if (o instanceof RespuestaLlamada) {
						// SERVIDOR RECIBE RESPUESTA A LLAMADA, ENVIA ESTA RESPUESTA AL USUARIO
						RespuestaLlamada respuestaLlamada = (RespuestaLlamada) o;

						Conexion.crearUsuarioCliente(respuestaLlamada.getPuertoOrigen(), respuestaLlamada);
					} else if (o instanceof Llamada) {
						// SERVIDOR RECIBE LLAMADA, ENVIA LLAMADA AL USUARIO
						Llamada llamada = (Llamada) o;

						Conexion.crearUsuarioCliente(llamada.getPuertoDestino(), llamada);

					} else if (o instanceof Mensaje) {
						// SERVIDOR RECIBE MENSAJE, ENVIA MENSAJE AL USUARIO
						Mensaje mensaje = (Mensaje) o;
						System.out.println(mensaje.getMensaje());//aca se muestra el mensaje en el servidor, tiene que estar cifrado
						Conexion.crearUsuarioCliente(mensaje.getPuertoDestino(),mensaje);
						
					}

				} else { // EL USUARIO RECIBIO ALGO

					if (o instanceof RespuestaLlamada) {// ES RESPUESTA A UNA LLAMADA
						this.respuesta = (RespuestaLlamada) o;
					} else if (o instanceof Llamada) {// ES LLAMADA
						
						// SI NO TIENE NINGUNA LLAMADA ESPERANDO RESPUESTA Y ESTA EN MODO ESCUCHA
						if (this.llamada == null && this.modo.equals("ESCUCHA"))
							this.llamada = (Llamada) o;
						else
							cond = false;

					} else if (o instanceof Mensaje) {// ES MENSAJE
						Mensaje mensaje = (Mensaje) o;
						
					}

					if (cond) { // SI LA CONDICION ES FALSA EL USUARIO NO PUEDE RECIBIR UNA LLAMADA, O PORQUE
								// TIENE UNA
								// O PORQUE INICIO UNA
						// SE NOTIFICAN A LOS OBSERVERS QUE ALGO PASO
						this.setChanged();
						this.notifyObservers(o);
						this.clearChanged();
					} else {

						// COMO EL USUARIO NO PUEDE RECIBIR LA LLAMADA, MANDA UNA RESPUESTA NEGATIVA AL
						// EMISOR
						Llamada llamada = (Llamada) o;
						UsuarioCliente cliente = new UsuarioCliente(Conexion.PUERTO_SERVER,
								new RespuestaLlamada(llamada, false));
						Thread t = new Thread(cliente);
						t.start();
					}
					// CIERRO EL SOCKET DONDE SE CONECTO EL CLIENTE
					sc.close();
					// System.out.println("Cliente desconectado");

				}
			}

		} catch (IOException ex) {
			// Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setModoEscucha() {
		this.modo = "ESCUCHA";
	}

	public void setModoLlamando() {
		this.modo = "LLAMANDO";
	}

	public void setModoChat() {
		this.modo = "CHAT";
	}

	public String getModo() {
		return this.modo;
	}

	public void setRespuesta(RespuestaLlamada res) {
		this.respuesta = res;
	}

}
