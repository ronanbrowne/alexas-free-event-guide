package DublinEvenGuideWebScraper.Support;

import DublinEvenGuideWebScraper.DataModel.Events;
import DublinEvenGuideWebScraper.DataParsing.ParseDataImp;
import org.jsoup.nodes.Element;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private static final String EVENT_LOCATION = "tbody > tr > td > table > tbody > tr > td:nth-child(2) > p";
    private static final String EVENT_TIME = "tbody > tr > td > table > tbody > tr > td:nth-child(2) > h3";
    private static final String EVENT_NAME = "tbody > tr > td > table > tbody > tr > td:nth-child(2) > h2";


    List<EventManager> AllEvents;

    /**
     * populate a list of {@link Events} with the data we have scraped
     *
     * @param upcomingEvents list of events to populate
     * @param tableData      HTML table elements we are intrested in
     */
    public static void populateListOfEvents(List<Events> upcomingEvents, Element tableData) {

        LocalDateTime EventTime = null;
        LocalTime endTime = null;

        ParseDataImp parseDataImp = new ParseDataImp();

        if (!tableData.select(EVENT_TIME).text().contains("–")) {
            EventTime = parseDataImp.getDateStartTimeFromHTML(tableData.select(EVENT_TIME).text());
            endTime = null;
        } else {
            EventTime = parseDataImp.SanitizeDate(tableData.select(EVENT_TIME).text());
            endTime = parseDataImp.getEndTime();
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
     * get todays events for a given day
     *
     * @param upcomingEvents listr of all events
     * @return the list of filtered events
     */
    public static List<Events> getEventsForRestOFToday(List<Events> upcomingEvents) {

        // TODO: 13/05/2018 see can we do all this in one stream

        List<Events> eventsAfterToday = new ArrayList<>();
        List<Events> todaysEvent = new ArrayList<>();

        upcomingEvents.stream()
                .filter(e -> e.getDateAndStartTime().isAfter(LocalDateTime.now()))
                .forEach(eventsAfterToday::add);

        eventsAfterToday.stream()
                .filter(a -> a.getDateAndStartTime().isBefore(LocalDateTime.of(LocalDate.now(), LocalTime.MAX)))
                .forEach(todaysEvent::add);


        return todaysEvent;
    }


}
