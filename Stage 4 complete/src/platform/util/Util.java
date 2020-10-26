package platform.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

    public static String getCurrentDateTime() {
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return today.format(formatter);
    }
}
