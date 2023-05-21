package Red;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CustomHashUtility {
    public static String generateCustomHash(int num1, int num2) {
        String input = num1 + "-" + num2;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            String hash = sb.toString();

            // Asegurarse de que el hash tenga una longitud de 24 caracteres y no predominen los ceros
            if (hash.length() > 24) {
                hash = hash.substring(0, 24);
            } else if (hash.length() < 24) {
                int paddingLength = 24 - hash.length();
                StringBuilder padding = new StringBuilder();
                for (int i = 0; i < paddingLength; i++) {
                    padding.append("1");
                }
                hash += padding.toString();
            }

            return hash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println("return null");
        return null;
    }
}
