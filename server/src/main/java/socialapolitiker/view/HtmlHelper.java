package socialapolitiker.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class HtmlHelper {
    private static final Pattern URL_PATTERN = Pattern
            .compile("(?i)\\b((?:https?://|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:\'\".,<>?«»“”‘’]))");

    public String convertUrlsToAnchors(String string) {
        Matcher matcher = URL_PATTERN.matcher(string);
        return matcher.replaceAll("<a href=\"$1\" target=\"_blank\">$1</a>");
    }
}
