package Servidor;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaUsuarios implements Serializable {
    private ArrayList<UsuarioRegistro> usuarios;

	public ListaUsuarios(ArrayList<UsuarioRegistro> usuarios) {
		this.usuarios = usuarios;
	}

	public ArrayList<UsuarioRegistro> getUsuarios() {
		return usuarios;
	}
	
	
    
}
