package socialapolitiker.view;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class DateFormatter {
    public String formatDate(LocalDateTime date) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(date);
    }

    public String formatMonth(Month month) {
        String monthDisplayName = month.getDisplayName(TextStyle.FULL, Locale.getDefault());

        return StringUtils.capitalize(monthDisplayName);
    }

}
