package andrewhossam.thed.tools;

/**
 * Created by Andrew on 5/30/2017.
 */

public class httptohttps {
    public static String convert(String s) {
        StringBuilder stringBuilder = new StringBuilder(s);
        stringBuilder.insert(4, 's');
        return stringBuilder.toString();
    }
}