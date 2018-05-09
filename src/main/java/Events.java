import java.time.LocalDateTime;

public class Events {


    private String eventName;
    private String location;
    private LocalDateTime dateAndStartTime;
    private String Details;

    public Events(String eventName, String location, LocalDateTime dateAndStartTime, String details) {
        this.eventName = eventName;
        this.location = location;
        this.dateAndStartTime = dateAndStartTime;
        Details = details;
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

        public EventsBuilder setEventName(String eventName) {
            this.eventName = eventName;
            return this;
        }

        public EventsBuilder setLocation(String location) {
            this.location = location;
            return this;
        }

        public EventsBuilder setTime(LocalDateTime time) {
            this.dateAndStartTime = dateAndStartTime;
            return this;
        }

        public EventsBuilder setDetails(String details) {
            this.details = details;
            return this;
        }

        public Events createEvents() {
            return new Events(eventName, location, dateAndStartTime, details);
        }
    }
}
