package time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DemoTest {

    public static void main(String[] args) {
        System.out.println(LocalDate.now().toString());

        // DateTimeFormatter是线程安全的，SimpleDateFormat非安全
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss")));


        System.out.println(Date.from(Instant.now()));
    }
}
