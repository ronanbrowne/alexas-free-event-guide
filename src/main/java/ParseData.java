import java.time.LocalDateTime;

public interface ParseData {

    public LocalDateTime getDateStartTimeFromHTML(String hTML);

    public LocalDateTime SanitizeDate(String dateToSanatize);

    public String sanitizeDay(String dayAttribuateFromHTML);



}
