package se.breached.encryption;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashEncoder {
    public static String encryptPassword(String password) {

        try {
            // API uses UTF-8 encoded SHA-1.
            ByteBuffer buffer = StandardCharsets.UTF_8.encode(password);
            String encodedPassword = StandardCharsets.UTF_8.decode(buffer).toString();

            // Hashing password with SHA-1 standard
            MessageDigest message = MessageDigest.getInstance("SHA-1");
            byte[] byteArray = message.digest(encodedPassword.getBytes());
            BigInteger bi = new BigInteger(1, byteArray);
            StringBuilder hashText = new StringBuilder(bi.toString(16));

            while (hashText.length() < 32) {
                hashText.insert(0, "0");
            }
            return hashText.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
