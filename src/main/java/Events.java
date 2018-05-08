public class Events {


    private String eventName;
    private String location;
    private String time;
    private String Details;

    public Events(String eventName, String location, String time, String details) {
        this.eventName = eventName;
        this.location = location;
        this.time = time;
        Details = details;
    }

    public String getEventName() {
        return eventName;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
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
                ", time='" + time + '\'' +
                ", Details='" + Details + '\'' +
                '}';
    }

    public static class EventsBuilder {
        private String eventName;
        private String location;
        private String time;
        private String details;

        public EventsBuilder setEventName(String eventName) {
            this.eventName = eventName;
            return this;
        }

        public EventsBuilder setLocation(String location) {
            this.location = location;
            return this;
        }

        public EventsBuilder setTime(String time) {
            this.time = time;
            return this;
        }

        public EventsBuilder setDetails(String details) {
            this.details = details;
            return this;
        }

        public Events createEvents() {
            return new Events(eventName, location, time, details);
        }
    }
}
