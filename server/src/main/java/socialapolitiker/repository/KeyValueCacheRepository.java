package socialapolitiker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import socialapolitiker.model.domain.KeyValueCache;

public interface KeyValueCacheRepository extends JpaRepository<KeyValueCache, String> {

}
