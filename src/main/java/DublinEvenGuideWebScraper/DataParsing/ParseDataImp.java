package DublinEvenGuideWebScraper.DataParsing;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class ParseDataImp implements ParseData {

    private LocalTime endTime;


    /**
     * Take the HTML string and return the date / time
     *
     * @param hTML the HTML string we wat to get the Date/ start time from
     * @return The parsed locatDateTime
     */
    @Override
    public LocalDateTime getDateStartTimeFromHTML(String hTML) {

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("HH:mm, E d MMM")
                .parseDefaulting(ChronoField.YEAR, 2018)
                .toFormatter(Locale.ENGLISH);

        return LocalDateTime.parse(hTML, formatter);

    }


    /**
     * If date is in the format of 13:00 – 17:30, Sat 05 May we will strip out the end time.
     *
     * @param dateToSanatize date returned from the event.
     */
    @Override
    public LocalDateTime SanitizeDate(String dateToSanatize) {
        LocalDateTime dateTime1 = null;
        // LocalTime endTime =  null;

        DateTimeFormatter formatter1 = new DateTimeFormatterBuilder()
                .appendPattern("HH:mm, E d MMM")
                .parseDefaulting(ChronoField.YEAR, 2018)
                .toFormatter(Locale.ENGLISH);


        String endtimeHTML = dateToSanatize.substring(5, 13);
        dateTime1 = LocalDateTime.parse(dateToSanatize.replace(endtimeHTML, ""), formatter1);

        // endTime of event if specified
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        endTime = LocalTime.parse(endtimeHTML.replace(" – ", ""), dateTimeFormatter);
        //  System.out.println(endTime);


        return dateTime1;
    }



    /**
     * Strip out the extra data from the day of the week returned
     *
     * @param dayAttribuateFromHTML
     * @return string stripped of extra data
     */
    @Override
    public String sanitizeDay(String dayAttribuateFromHTML) {
        String regex = "\\s*\\bback to top\\b\\s*";
        return dayAttribuateFromHTML.replaceAll(regex, "");
    }


    public LocalTime getEndTime() {
        return endTime;
    }
}
