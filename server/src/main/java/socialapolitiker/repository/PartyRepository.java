package socialapolitiker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import socialapolitiker.model.domain.Party;
import socialapolitiker.model.dto.PopularWord;
import socialapolitiker.model.dto.TweetedEntity;

public interface PartyRepository extends JpaRepository<Party, Long> {
    @Query(name = "popularWordsParty")
    List<PopularWord> getPopularWords(String urlName);

    @Query(name = "tweetedWordsParty")
    List<TweetedEntity> getTweetedWords(String urlName);

    Party findOneByUrlName(String urlName);
}
