package Main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Modelo.Conexion;
import Modelo.CustomHashUtility;


public class PruebaHash {

	
	 
	    public static void main(String[] args) {
	        int num1 = 8000;
	        int num2 = 8001;

	        String hash = CustomHashUtility.generateCustomHash(num1, num2);
	        System.out.println("Hash: " + hash);
	        
	        int num3 = 8000;
	        int num4 = 8001;

	        String hash1 = CustomHashUtility.generateCustomHash(num3, num4);
	        System.out.println("Hash: " + hash1);
	        
	        
	        /*try {
				String mensajeEncriptado=Conexion.encriptar(hash, "hola mundo!","TripleDES");
				System.out.println(mensajeEncriptado);
				String mensajeDesencriptado=Conexion.desencriptar(hash,mensajeEncriptado,"TripleDES");
				System.out.println(mensajeDesencriptado);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	        
	    }

	    /*private static String generateCustomHash(int num1, int num2) {
	        // Concatenar los dos números enteros como cadena
	        String input = num1 + "" + num2;

	        try {
	            // Aplicar un algoritmo de hash, como MD5, SHA-256, etc.
	            MessageDigest md = MessageDigest.getInstance("SHA-256");
	            byte[] hashedBytes = md.digest(input.getBytes());

	            // Convertir los bytes del hash a una representación hexadecimal
	            StringBuilder sb = new StringBuilder();
	            for (byte b : hashedBytes) {
	                sb.append(String.format("%02x", b));
	            }
	            String hash = sb.toString();

	            // Asegurarse de que el hash tenga una longitud de 24 caracteres
	            if (hash.length() > 24) {
	                hash = hash.substring(0, 24);
	            } else if (hash.length() < 24) {
	                // Agregar caracteres adicionales al final del hash
	                int paddingLength = 24 - hash.length();
	                for (int i = 0; i < paddingLength; i++) {
	                    hash += "1";
	                }
	            }

	            return hash;
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }

	        return null;
	    }
	*/
}
