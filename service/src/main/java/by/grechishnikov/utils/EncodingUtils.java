package by.grechishnikov.utils;

/**
 * Util class for encoding tasks
 */
public class EncodingUtils {
    /**
     * Encode ISO-8859-1 string to UTF-8 (for multipart/form-data).
     *
     * @param JSON - request data
     * @return - data by current encoding
     */
    public static String encodeFromISOToUTF(String JSON) throws Exception {
        byte[] bytes = JSON.getBytes("ISO-8859-1");
        return new String(bytes);
    }
}
