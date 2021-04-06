import java.util.regex.Pattern;

public class Decimal62Converter {
    private static final String TABLE = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE = 62;

    public static void main(String[] args) {
        Long seeds = Long.parseLong(args[0]);
        String base62 = encrypt(seeds);
        long reverse = decrypt(base62);
        System.out.println(seeds);
        System.out.println(base62);
        System.out.println(reverse);
    }

    /** concatenate integers
     * 
     * @param digits
     * @return string value of concatenated integers
     */
    public static long concatnateDigits(int... digits)
    {
        StringBuffer sb = new StringBuffer(digits.length);
        for(int digit: digits) {
            sb.append(digit);
        }
        return Long.parseLong(sb.toString());
    }

    /** convert integer to alphanumeric characters
     * 
     * @param number
     * @return encrypted string
     */
    public static String encrypt(long number)
    {
        StringBuffer sb = new StringBuffer();
        while(true) {
            sb.insert(0, TABLE.charAt((int)(number % BASE)));
            number = number / BASE;

            if (number == 0) {
                break;
            }
        }
        return sb.toString();
    }

    /** convert alphanumeric characters to integer
     * 
     * @param phrase
     * @return Integer converted from phrase.
     *         Return -1 if the phrase contains not convertible character.
     */
    public static long decrypt(String phrase)
    {
        // validation.
        // if the phrase has character other than in TABLE
        // return -1;
        if(Pattern.compile("[^" + TABLE + "]").matcher(phrase).find()) {
            return -1;
        }
        String reverse = new StringBuffer(phrase).reverse().toString();
        long decimal = 0;
        for(int i = 0; i < reverse.length(); i++) {
            int digit = TABLE.indexOf(reverse.charAt(i));
            decimal = (long) (decimal + digit * Math.pow(BASE, i));
        }
        return decimal;
    }
}