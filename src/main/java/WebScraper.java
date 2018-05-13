import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import webScraper.Events;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WebScraper {

    private static final String EVENT_LOCATION = "tbody > tr > td > table > tbody > tr > td:nth-child(2) > p";
    private static final String EVENT_TIME = "tbody > tr > td > table > tbody > tr > td:nth-child(2) > h3";
    private static final String EVENT_NAME = "tbody > tr > td > table > tbody > tr > td:nth-child(2) > h2";


    static LocalTime endTime;

    public static void main(String[] args) {

        //initialization
        Document doc = null;
        String eventName = "";
        String eventTime = "";
        String eventLocation = "";
        String eventDetails = "";
        List<Events> upcomingEvents = new ArrayList<>();
        LocalTime eventEndDate = null;


        //Jsoup set up
        try {
            doc = Jsoup.connect("http://www.dublineventguide.com/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ScrapeDataFromWeb(doc, upcomingEvents);

        System.out.println("Total number of events retrieved: " + upcomingEvents.size());


    }


    /**
     * Take the HTML string and return the date / time
     *
     * @param hTML the HTML string we wat to get the Date/ start time from
     * @return The parsed locatDateTime
     */
/*    public static LocalDateTime getDateStartTimeFromHTML(String hTML) {

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("HH:mm, E d MMM")
                .parseDefaulting(ChronoField.YEAR, 2018)
                .toFormatter(Locale.ENGLISH);

        return LocalDateTime.parse(hTML, formatter);
    }*/


    /**
     * If date is in the format of 13:00 – 17:30, Sat 05 May we will strip out the end time.
     *
     * @param dateToSanatize date returned from the event.
     */
  /*  private static LocalDateTime SanitizeDate(String dateToSanatize) {

        LocalDateTime dateTime1 = null;
        // LocalTime endTime =  null;

        DateTimeFormatter formatter1 = new DateTimeFormatterBuilder()
                .appendPattern("HH:mm, E d MMM")
                .parseDefaulting(ChronoField.YEAR, 2018)
                .toFormatter(Locale.ENGLISH);


        String endtimeHTML = dateToSanatize.substring(5, 13);
        dateTime1 = LocalDateTime.parse(dateToSanatize.replace(endtimeHTML, ""), formatter1);

        // endTime of event if specified
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("  HH:mm");
        endTime = LocalTime.parse(endtimeHTML.replace("–", ""), dateTimeFormatter);
        //  System.out.println(endTime);


        return dateTime1;
    }
*/
    /**
     * specify what data we want to retrieve from the URL
     *
     * @param doc
     * @param upcomingEvents
     */
    private static void ScrapeDataFromWeb(Document doc, List<Events> upcomingEvents) {

        //get all sibling nodes of a table with id of "Day_..." this puts us on right level node
        for (Element allHTMLTables : doc.select("table[id^=day_]").first().siblingElements()) {
            //if id is empty and we have the right table structure
            if (!allHTMLTables.select("*:not([id])").isEmpty() && !allHTMLTables.select("tbody > tr > td > table > tbody > tr > td:nth-child(2) >  table > tbody > tr").isEmpty()) {
                printToConsole(allHTMLTables);
                populateListOfEvents(upcomingEvents, allHTMLTables);
            }
        }
    }

    /**
     * populate a list of {@link Events} with the data we have scraped
     *
     * @param upcomingEvents list of events to populate
     * @param tableData      HTML table elements we are intrested in
     */
    private static void populateListOfEvents(List<Events> upcomingEvents, Element tableData) {

        LocalDateTime EventTime = null;

        ParseDataImp parseDataImp = new ParseDataImp();

        if (!tableData.select(EVENT_TIME).text().contains("–")) {
            EventTime = parseDataImp.getDateStartTimeFromHTML(tableData.select(EVENT_TIME).text());
            endTime = null;
        } else {
            EventTime = parseDataImp.SanitizeDate(tableData.select(EVENT_TIME).text());
            endTime=parseDataImp.getEndTime();
        }

        Events eventDetails = new Events.EventsBuilder()
                .setEventName(tableData.select(EVENT_NAME).text())
                .setDetails("")
                .setLocation(tableData.select(EVENT_LOCATION).text())
                .setTime(EventTime)
                .setEndTime(endTime)
                .createEvents();


        upcomingEvents.add(eventDetails);
    }

    /**
     * method for test purposes to print data returned to the console
     *
     * @param tableData the table data we are intrested in
     */
    private static void printToConsole(Element tableData) {
        System.out.println(tableData.select(" tbody > tr > td > table > tbody > tr > td:nth-child(2) > h2").text());
        System.out.println(tableData.select(" tbody > tr > td > table > tbody > tr > td:nth-child(2) > h3").text());
        System.out.println(tableData.select(" tbody > tr > td > table > tbody > tr > td:nth-child(2) > p").text() + "\n");
    }


    /**
     * Strip out the extra data from the day of the week returned
     *
     * @param dayAttribuateFromHTML
     * @return string stripped of extra data
     */
/*    private static String sanitizeDay(String dayAttribuateFromHTML) {
        String regex = "\\s*\\bback to top\\b\\s*";
        return dayAttribuateFromHTML.replaceAll(regex, "");
    }*/


}
