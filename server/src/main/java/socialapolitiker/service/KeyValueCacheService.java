package socialapolitiker.service;

import java.time.LocalDateTime;

import socialapolitiker.model.dto.WordViewData;

public interface KeyValueCacheService {
    LocalDateTime getCacheGenerated();

    WordViewData getWordViewData(String string);
}
