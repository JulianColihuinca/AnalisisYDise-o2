package Servidor;

import java.io.Serializable;
import java.util.ArrayList;

public class ConfirmacionServidor  implements Serializable {  // Para avisar al monitor que esta disponible
        private int puerto;
        private boolean disponible;
        private ArrayList<UsuarioRegistro> usuarioRegistrados;
        
		public ConfirmacionServidor(int puerto, boolean disponible, ArrayList<UsuarioRegistro> usuarioRegistrados ) {
			
			this.puerto = puerto;
			this.disponible = disponible;
			this.usuarioRegistrados=usuarioRegistrados;
		}
		public int getPuerto() {
			return puerto;
		}
		public boolean isDisponible() {
			return disponible;
		}
		public ArrayList<UsuarioRegistro> getUsuarioRegistrados() {
			return usuarioRegistrados;
		}
		
		
        
        
}
