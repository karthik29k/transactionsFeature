package karthik.com.commBank.utils;

public class StringUtils {

    public static boolean isEmpty(CharSequence cs) {
        return (cs == null || cs.length() == 0);
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !StringUtils.isEmpty(cs);
    }
}
