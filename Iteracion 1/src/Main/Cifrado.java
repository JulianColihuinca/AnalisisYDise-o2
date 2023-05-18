package Main;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Cifrado {
	public static void main(String[] args) {
		try {
			String texto = "Encriptado con algoritmo DES";
			byte[] textoEncriptado = encriptar("12345678", texto, "DES");
			String textoEncriptadoBase64 = Base64.getEncoder().encodeToString(textoEncriptado);
			System.out.println(textoEncriptadoBase64);
			textoEncriptado = Base64.getDecoder().decode(textoEncriptadoBase64);
			String textoOriginal = desencriptar("12345678", textoEncriptado, "DES");
			System.out.println(textoOriginal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String texto = "Encriptado con algoritmo AES";
			byte[] textoEncriptado = encriptar("123456781234567812345678", texto, "AES");
			String textoOriginal = desencriptar("123456781234567812345678", textoEncriptado, "AES");
			System.out.println(textoOriginal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String texto = "Encriptado con algoritmo TripleDES";
			byte[] textoEncriptado = encriptar("123456781234567812345678", texto, "TripleDES");
			String textoOriginal = desencriptar("123456781234567812345678", textoEncriptado, "TripleDES");
			System.out.println(textoOriginal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static byte[] encriptar(String pass, String texto, String algoritmo) throws Exception {
		java.security.Key key = new SecretKeySpec(pass.getBytes(), algoritmo);
		Cipher cipher = Cipher.getInstance(algoritmo);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(texto.getBytes());
	}

	public static String desencriptar(String pass, byte[] encriptado, String algoritmo) throws Exception {
		java.security.Key key = new SecretKeySpec(pass.getBytes(), algoritmo);
		Cipher cipher = Cipher.getInstance(algoritmo);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] bytes = cipher.doFinal(encriptado);
		return new String(bytes);
	}
}
