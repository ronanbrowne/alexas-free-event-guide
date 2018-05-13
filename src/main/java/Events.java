import java.time.LocalDateTime;
import java.time.LocalTime;

public class Events {


    private String eventName;
    private String location;
    private LocalDateTime dateAndStartTime;
    private String Details;
    private LocalTime endTime;


    public Events(String eventName, String location, LocalDateTime dateAndStartTime, String details, LocalTime endTime) {
        this.eventName = eventName;
        this.location = location;
        this.dateAndStartTime = dateAndStartTime;
        this.Details = details;
        this.endTime = endTime;
    }

    public String getEventName() {
        return eventName;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getTime() {
        return dateAndStartTime;
    }

    public String getDetails() {
        return Details;
    }

    public LocalDateTime getDateAndStartTime() {
        return dateAndStartTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    //here


    @Override
    public String toString() {
        return "Events{" +
                "eventName='" + eventName + '\'' +
                ", location='" + location + '\'' +
                ", Date / Start time='" + dateAndStartTime + '\'' +
                ", Details='" + Details + '\'' +
                '}';
    }

    public static class EventsBuilder {
        private String eventName;
        private String location;
        private LocalDateTime dateAndStartTime;
        private String details;
        private LocalTime endTime;

        public EventsBuilder setEventName(String eventName) {
            this.eventName = eventName;
            return this;
        }

        public EventsBuilder setLocation(String location) {
            this.location = location;
            return this;
        }

        public EventsBuilder setTime(LocalDateTime dateAndStartTime) {
            this.dateAndStartTime = dateAndStartTime;
            return this;
        }

        public EventsBuilder setDetails(String details) {
            this.details = details;
            return this;
        }

        public EventsBuilder setEndTime(LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Events createEvents() {
            return new Events(eventName, location, dateAndStartTime, details, endTime);
        }


    }
}
