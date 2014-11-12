package socialapolitiker.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import socialapolitiker.model.dto.WordViewData;
import socialapolitiker.repository.KeyValueCacheRepository;
import socialapolitiker.service.KeyValueCacheService;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KeyValueCacheServiceImpl implements KeyValueCacheService {
    private static Logger log = LoggerFactory.getLogger(KeyValueCacheServiceImpl.class);

    @Autowired
    private KeyValueCacheRepository keyValueCacheRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public LocalDateTime getCacheGenerated() {
        String cacheGenerated = keyValueCacheRepository.getOne("cache.generated").getValue().replace("\"", "");

        return ZonedDateTime.parse(cacheGenerated).toLocalDateTime();
    }

    @Override
    public WordViewData getWordViewData(String key) {
        String wordViewDataJson = keyValueCacheRepository.getOne(key).getValue();
        WordViewData wordViewData = null;

        try {
            wordViewData = objectMapper.readValue(wordViewDataJson, WordViewData.class);
        } catch (IOException e) {
            log.error("Could not parse word view data from cache", e);
        }

        return wordViewData;
    }
}
