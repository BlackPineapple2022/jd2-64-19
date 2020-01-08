package by.academy.it.travelcompany.security;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public final class EncryptUtils {

    public static final String STRING_512 = "%0128x";
    public static final String STRING_256 = "%064x";
    public static final int SALT_SIZE = 20;

    public static String getSHA512(String input, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.reset();
            md.update(stringToBytes(salt));
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return String.format(STRING_512, new BigInteger(1, digest));
        } catch (Exception e) {
            throw new RuntimeException("Error while encrypting password", e);
        }
    }

    public static String getSHA256(String input, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.reset();
            md.update(stringToBytes(salt));
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return String.format(STRING_256, new BigInteger(1, digest));
        } catch (Exception e) {
            throw new RuntimeException("Error while encrypting password", e);
        }
    }

    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[SALT_SIZE];
        random.nextBytes(bytes);
        return bytes;
    }

    public static String generateSaltString() {
        return byteToString(generateSalt());
    }

    public static String byteToString(byte[] salt) {
        return Base64.getEncoder().encodeToString(salt);
    }

    public static byte[] stringToBytes(String bytes) {
        return Base64.getDecoder().decode(bytes);
    }

    public static void main(String[] args) {
        String pass = "123";
        String salt = EncryptUtils.generateSaltString();
        System.out.println("salt = " + salt);
        System.out.println("pass = " + pass);

        String hash = EncryptUtils.getSHA256(pass, salt);
        System.out.println("hash " + hash);
    }

}