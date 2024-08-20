package mouse.hoi.tools.utils;

import java.util.Comparator;

public class Comparators {
    public static final Comparator<String> numberAwareComparator = (s1, s2) -> {
        int n1 = 0;
        int n2 = 0;
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            if (Character.isDigit(c1)) {
                n1++;
            } else {
                break;
            }
        }
        for (int i = 0; i < s2.length(); i++) {
            char c2 = s2.charAt(i);
            if (Character.isDigit(c2)) {
                n2++;
            } else {
                break;
            }
        }
        if (n1 == 0 && n2 == 0) {
            return s1.compareTo(s2);
        }
        if (n1 != n2) {
            return n1 - n2;
        }
        int val1 = Integer.parseInt(s1.substring(0, n1));
        int val2 = Integer.parseInt(s2.substring(0, n2));
        return val1 - val2;
    };
}
