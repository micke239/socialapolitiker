package socialapolitiker.service;

import java.util.List;

import socialapolitiker.model.dto.PoliticianSearchable;
import socialapolitiker.model.dto.PopularWord;
import socialapolitiker.model.dto.TweetedEntity;

public interface SocialapolitikerSearchService {

    List<TweetedEntity> getTweetedWordsByPolitician(String politician);

    List<TweetedEntity> getTweetedWordsByParty(String party);

    List<PopularWord> getPopularWordsByParty(String party);

    List<PopularWord> getPopularWordsByPolitician(String politician);

    List<PoliticianSearchable> getPoliticians();

    List<PoliticianSearchable> getPoliticians(String partyUrlName);
}
