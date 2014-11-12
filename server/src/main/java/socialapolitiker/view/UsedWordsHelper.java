package socialapolitiker.view;

import org.springframework.stereotype.Component;

@Component
public class UsedWordsHelper {
    public double calculateBarWidth(int count, int highestCount) {
        return 10 + ((double) count / (double) highestCount) * 90;
    }
}
