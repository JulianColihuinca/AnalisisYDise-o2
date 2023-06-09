package Servidor;

import java.io.Serializable;

public class ConfirmacionServidor  implements Serializable {  // Para avisar al monitor que esta disponible
        private int puerto;
        private boolean disponible;
		public ConfirmacionServidor(int puerto, boolean disponible) {
			
			this.puerto = puerto;
			this.disponible = disponible;
		}
		public int getPuerto() {
			return puerto;
		}
		public boolean isDisponible() {
			return disponible;
		}
        
        
}
