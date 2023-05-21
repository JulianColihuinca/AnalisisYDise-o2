package Cifrado;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Cifrado {

	public static String getHash(int puerto1, int puerto2) {

		// Concatenar los dos números enteros como cadena
		String input = puerto1 + "" + puerto2;

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

	public static String encriptar(String pass, String mensaje, String algoritmo) throws Exception {
		java.security.Key key = new SecretKeySpec(pass.getBytes(), algoritmo);
		Cipher cipher = Cipher.getInstance(algoritmo);
		cipher.init(Cipher.ENCRYPT_MODE, key);

		String textoEncriptadoBase64 = Base64.getEncoder().encodeToString(cipher.doFinal(mensaje.getBytes()));
		return textoEncriptadoBase64;
	}

	public static String desencriptar(String pass, String encriptado, String algoritmo) throws Exception {

		byte[] mensajeEncriptado = Base64.getDecoder().decode(encriptado);

		java.security.Key key = new SecretKeySpec(pass.getBytes(), algoritmo);
		Cipher cipher = Cipher.getInstance(algoritmo);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] bytes = cipher.doFinal(mensajeEncriptado);
		return new String(bytes);
	}
}
