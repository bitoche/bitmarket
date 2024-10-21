package ru.bitoche.basemarket.features;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


public class Encoder {
    // Фиксированный секретный ключ (16 байт для AES-128)
    private static final String SECRET_KEY = "1234567890123456";

    // Кодирование строки
    public static String encodeString(String data) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        String encoded = Base64.getEncoder().encodeToString(encryptedBytes);

        // Замена символов
        return encoded.replace("+", "PLUS").replace("/", "SLASH");
    }

    // Декодирование строки
    public static String decodeString(String encodedData) throws Exception {
        // Восстановление символов
        String decoded = encodedData.replace("PLUS", "+").replace("SLASH", "/");
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(decoded));
        return new String(decryptedBytes);
    }
}
