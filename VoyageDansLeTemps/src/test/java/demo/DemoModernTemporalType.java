package demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.MessageFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

// Java 8
public class DemoModernTemporalType {

    @Test
    void demoDateHeureSystem() {
        LocalDate d = LocalDate.now(); // date maintenant et ici
        System.out.println(d); // ISO : 2025-11-27

        LocalDateTime dt = LocalDateTime.now();
        System.out.println(dt); // ISO : 2025-11-27T14:52:49.660539400

        LocalTime t = LocalTime.now();
        System.out.println(t); // 14:56:02.438112700

        ZonedDateTime zdt = ZonedDateTime.now();
        System.out.println(zdt); // 2025-11-27T14:57:03.810951500+01:00[Europe/Paris]
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "America/Los_Angeles",
            "Europe/Paris",
            "Europe/London",
            "Pacific/Easter",
            "Asia/Tokyo",
            "Australia/Sydney"
    })
    void demoTimeZone(String zoneName){
        ZoneId zoneId = ZoneId.of(zoneName);
        ZonedDateTime zdt = ZonedDateTime.now(zoneId);
        LocalDateTime dt = LocalDateTime.now(zoneId);
        System.out.println(zoneId);
        System.out.println(zdt);
        System.out.println(dt);
        System.out.println();
    }

    @Test
    void demo29fevrier(){
        IntStream.range(2000, 2105)
                .forEach(year -> {
                    try {
                        LocalDate d = LocalDate.of(year, 2, 29);
                        System.out.println(MessageFormat.format(
                                "date = {0} ; year = {1} ; month = {2} ; day={3}",
                                d,
                                d.getYear(),
                                d.getMonthValue(),
                                d.getDayOfMonth()
                        ));
                    } catch (DateTimeException e) {
                        System.out.println(MessageFormat.format(
                                "{0} n est pas une année bissextile",
                                year
                        ));
                    }
                });
    }

    @Test
    void demoJourApres(){
        IntStream.range(2000, 2005)
                .mapToObj(year -> LocalDate.of(year, 2, 28))
                .forEach(date -> {
                    LocalDate dateNext = date.plus(Period.ofDays(1));
                    System.out.println(MessageFormat.format(
                            "{0} -> {1}",
                            date,
                            dateNext
                    ));
                });
    }

    // parse/format de date heure
    @Test
    void demoParseFormat(){
        String dateStrFr = "08/05/1945";
        String dateIso = "1789-07-14";

        LocalDate d1 = LocalDate.parse(dateIso);
        System.out.println(d1);

        DateTimeFormatter formatDateFr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate d2 = LocalDate.parse(dateStrFr, formatDateFr);
        System.out.println(d2);
        System.out.println(d2.format(formatDateFr));
        System.out.println(d2.format(DateTimeFormatter.ofPattern("eeee dd MMMM yyyy"))); // TODO : Locale
    }

}
