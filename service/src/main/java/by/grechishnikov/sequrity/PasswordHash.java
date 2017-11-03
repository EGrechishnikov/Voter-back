package by.grechishnikov.sequrity;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ResourceBundle;

public class PasswordHash {
    private static String localHexParameter = ResourceBundle.getBundle("config").
            getString("localHexParameter");
    final protected static char[] hexArray = "0123456789abcdef".toCharArray();

    /**
     * Hash the string with salt and local parameter
     *
     * @param line - string for hashing
     * @return - hash line
     */
    public static String getHex(String line) {
        try {
            line += localHexParameter;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(line.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Get salt in 20 symbols
     *
     * @return salt
     */
    public static String getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[10];
        random.nextBytes(salt);
        char[] hexChars = new char[salt.length * 2];
        for (int j = 0; j < salt.length; j++) {
            int v = salt[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
