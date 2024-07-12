package utilities;

import java.util.Random;

/**
 * This class is for generating random strings, integers and mobile numbers.
 *
 * @author ranjit.biswal
 * @since
 */
public class RandomUtil {

    public static enum Mode {
        ALPHA, ALPHANUMERIC, NUMERIC
    }

    /**
     * This method is used to generate random string of the given length and of
     * given type.
     *
     * @param length
     * @param mode
     * @return
     * @throws Exception
     */
    public static String generateRandomString(int length, Mode mode) {
        StringBuffer buffer = new StringBuffer();
        String characters = "";
        switch (mode) {
            case ALPHA:
                characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                break;
            case ALPHANUMERIC:
                characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                break;
            case NUMERIC:
                characters = "1234567890";
                break;
        }
        int charactersLength = characters.length();
        for (int i = 0; i < length; i++) {
            double index = Math.random() * charactersLength;
            buffer.append(characters.charAt((int) index));
        }
        return buffer.toString();
    }

    /**
     * This method is used to generate the random mobile number
     *
     * @return
     */
    public static String generateMobileNumber() {
        String input = Integer.toString(1234567890);
        String mobilenumber = "(" + input.substring(0, 3) + ") " + input.substring(3, 6) + "-" + input.substring(6, 10);
        return mobilenumber;
    }

    /**
     * This method is used to generate random integers.
     *
     * @return
     */
    public static int generateInteger() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(95);
        return randomInt;
    }
}