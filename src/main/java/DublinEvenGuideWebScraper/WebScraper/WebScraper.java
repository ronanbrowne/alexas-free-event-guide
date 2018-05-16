package DublinEvenGuideWebScraper.WebScraper;

import DublinEvenGuideWebScraper.DataModel.Events;
import DublinEvenGuideWebScraper.Support.EventManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebScraper {


    List<Events> todaysEvents = new ArrayList<>();

    public  List<Events> gettodaysEvents(){

        return  todaysEvents;
    }

    public static void setTodaysEvents(List<Events> todaysEvents) {
        todaysEvents = todaysEvents;
    }

    public static void main(String[] args) {

        //initialization
        Document doc = null;
        List<Events> upcomingEvents = new ArrayList<>();
        List<Events> todaysEvents = new ArrayList<>();

        //Jsoup set up
        try {
            doc = Jsoup.connect("http://www.dublineventguide.com/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ScrapeDataFromWeb(doc, upcomingEvents);

        System.out.println("Total number of events retrieved: " + upcomingEvents.size());

        // test todays midnight System.out.println(LocalDateTime.now().toLocalDate().atStartOfDay());

        todaysEvents = EventManager.getEventsForRestOFToday(upcomingEvents);
;
        setTodaysEvents(todaysEvents);

        System.out.println(todaysEvents.size());

    }

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
                EventManager.populateListOfEvents(upcomingEvents, allHTMLTables);
            }
        }
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


}
