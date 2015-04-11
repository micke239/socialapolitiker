package socialapolitiker.service;

import java.util.List;

import socialapolitiker.model.dto.PopularWord;
import socialapolitiker.model.dto.TweetedWord;

public interface SocialapolitikerSearchService {

    List<TweetedWord> getTweetedWordsByPolitician(String politician);

    List<TweetedWord> getTweetedWordsByParty(String party);

    List<PopularWord> getPopularWordsByParty(String party);

    List<PopularWord> getPopularWordsByPolitician(String politician);
}
