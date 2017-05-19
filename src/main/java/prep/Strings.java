package prep;

/**
 * @author Steve Ash
 */
public class Strings {

    public static String reverse(String value) {
        if (value == null) return null;
        if (value.equals("")) {
            return "";
        }
        return value.substring(value.length() - 1) + reverse(value.substring(0, value.length() - 1));
    }

    public static String reverse2(String value) {
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            int j = chars.length - 1 - i;
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
        }
        return String.copyValueOf(chars);
    }

}
