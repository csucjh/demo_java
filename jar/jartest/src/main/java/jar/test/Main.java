package jar.test;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("distribute");

        Enumeration<String> enumerations = resourceBundle.getKeys();
        while (enumerations.hasMoreElements()) {
            String key = enumerations.nextElement();
            System.out.println(key + "=" + resourceBundle.getString(key));
        }
    }
}
