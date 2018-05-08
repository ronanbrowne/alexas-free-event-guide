import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebScraper {


    public static void main(String[] args) {

        Document doc = null;
        String eventName = "";
        String eventTime = "";
        String eventLocation = "";
        String eventDetails = "";

        try {
            doc = Jsoup.connect("http://www.dublineventguide.com/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //we get the day

        for ( Element el : doc.select("table[id^=day_]") ) {



          // day`of week
            System.out.println("****");
            System.out.println("\n\n"+el.text());


            for(Element e: el.siblingElements()){

              //  section.select("h2:contains(See) ~ *").remove();


              if (!e.select("*:not([id])").isEmpty()){
              //  if (!e.select("table[id^=day_] ~ table[id^=day_]").isEmpty()){
                    System.out.println("\n\n"+e.text());



                }
            }

            //event one
         // System.out.println(el.nextElementSibling());



            Element sibling = el.nextElementSibling();

         //   System.out.println(sibling.nextElementSibling());
            // get sibling content
        }


 /*      for (int i = 0; i <= 1; i++) {

            Element days = doc.select("table[id^=day_]").get(1);
            String dayOfweek = days.text();
            String day = sanitizeDay(dayOfweek);

           Elements x = days.nextElementSibling().getAllElements();



       }
*/

    //    System.out.println(x);


         //   System.out.println(".."+days.nextElementSibling().attr("id"));

           // System.out.println((days.nextElementSibling().select("*:not([id])")));

         //   Elements allEventForAGivenDay = days.nextElementSibling().select("*:not([id])");

         //   allEventForAGivenDay.stream().map(e -> e.s)

           /* for (Element x: allEventForAGivenDay){

            }
*/

           // while()

  /*          while (true) {

                Element EventData = days.nextElementSibling();

                eventName = EventData.select("h2").first().text();
                eventTime = EventData.select("h3").first().text();
                eventLocation = EventData.select("p").first().text();
                eventDetails = EventData.select("p").get(1).text();


                Events firstEvent = new Events.EventsBuilder()
                        .setEventName(eventName)
                        .setDetails(eventDetails)
                        .setLocation(eventLocation)
                        .setTime(eventTime)
                        .createEvents();


                System.out.println(firstEvent.toString());
            }*/


  //      }


        //gotta do this bit for every day


        // System.out.println("."+eventName+eventTime+eventLocation+eventDetails);


        //  System.out.println(Event.select("h2").first().text());


        //    System.out.println( day );






   /*     System.out.println(firstEvent.getEventName());
        System.out.println(firstEvent.getTime());
        System.out.println(firstEvent.getDetails());
        System.out.println("\n"+firstEvent.getLocation());*/
    }


    /**
     * Strip out the extra data from the day of the week returned
     *
     * @param dayAttribuateFromHTML
     * @return string stripped of extra data
     */
    private static String sanitizeDay(String dayAttribuateFromHTML) {
        String regex = "\\s*\\bback to top\\b\\s*";
        return dayAttribuateFromHTML.replaceAll(regex, "");
    }


}
