package socialapolitiker.controller;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import socialapolitiker.repository.KeyValueCacheRepository;

@RestController
public class StatusController {

    class DbStatus {
        public long validationInterval;
        public boolean testOnBorrow;
        public String validationQuery;
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    private KeyValueCacheRepository keyValueCacheRepository;

    @RequestMapping("/status/db")
    public DbStatus dbStatus() {
        DbStatus dbStatus = new DbStatus();
        dbStatus.validationInterval = dataSource.getValidationInterval();
        dbStatus.validationQuery = dataSource.getValidationQuery();
        dbStatus.testOnBorrow = dataSource.isTestOnBorrow();
        return dbStatus;
    }

    @RequestMapping("/status/cache-generated")
    public String cacheGenerated() {
        return keyValueCacheRepository.getOne("cache.generated").getValue();
    }
}
