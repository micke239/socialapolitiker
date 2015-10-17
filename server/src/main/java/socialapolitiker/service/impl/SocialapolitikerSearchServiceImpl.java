package socialapolitiker.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import socialapolitiker.model.dto.PoliticianSearchable;
import socialapolitiker.model.dto.PopularWord;
import socialapolitiker.model.dto.TweetedEntity;
import socialapolitiker.service.SocialapolitikerSearchService;

@Service
public class SocialapolitikerSearchServiceImpl implements SocialapolitikerSearchService {

    private RestTemplate restTemplate;
    private String searchHost;

    @Autowired
    public SocialapolitikerSearchServiceImpl(RestTemplate restTemplate,
            @Value("${socialapolitiker.search}") String searchHost) {
        this.restTemplate = restTemplate;
        this.searchHost = searchHost;
    }

    @Override
    public List<TweetedEntity> getTweetedWordsByPolitician(String politician) {
        TweetedEntity[] tweetedWords = restTemplate.getForObject(searchHost + "/tweeted-words/politician?politician="
                + politician, TweetedEntity[].class);

        return Arrays.asList(tweetedWords);
    }

    @Override
    public List<TweetedEntity> getTweetedWordsByParty(String partyUrlName) {
        TweetedEntity[] tweetedWords = restTemplate.getForObject(searchHost + "/tweeted-words/party?partyUrlName="
                + partyUrlName, TweetedEntity[].class);

        return Arrays.asList(tweetedWords);
    }

    @Override
    public List<PopularWord> getPopularWordsByPolitician(String politician) {
        PopularWord[] popularWords = restTemplate.getForObject(searchHost + "/popular-words/politician?politician="
                + politician, PopularWord[].class);

        return Arrays.asList(popularWords);
    }

    @Override
    public List<PopularWord> getPopularWordsByParty(String partyUrlName) {
        PopularWord[] popularWords = restTemplate.getForObject(searchHost + "/popular-words/party?partyUrlName="
                + partyUrlName, PopularWord[].class);

        return Arrays.asList(popularWords);
    }

    @Override
    public List<TweetedEntity> getTweetedDomainsByPolitician(String politician) {
        TweetedEntity[] popularWords = restTemplate.getForObject(searchHost + "/tweeted-domains/politician?politician="
                + politician, TweetedEntity[].class);

        return Arrays.asList(popularWords);
    }

    @Override
    public List<TweetedEntity> getTweetedDomainsByParty(String partyUrlName) {
        TweetedEntity[] popularWords = restTemplate.getForObject(searchHost + "/tweeted-domains/party?partyUrlName="
                + partyUrlName, TweetedEntity[].class);

        return Arrays.asList(popularWords);
    }

    @Override
    public List<TweetedEntity> getTweetedHashtagsByPolitician(String politician) {
        TweetedEntity[] popularWords = restTemplate.getForObject(searchHost
                + "/tweeted-hashtags/politician?politician=" + politician, TweetedEntity[].class);

        return Arrays.asList(popularWords);
    }

    @Override
    public List<TweetedEntity> getTweetedHashtagsByParty(String partyUrlName) {
        TweetedEntity[] popularWords = restTemplate.getForObject(searchHost + "/tweeted-hashtags/party?partyUrlName="
                + partyUrlName, TweetedEntity[].class);

        return Arrays.asList(popularWords);
    }

    @Override
    public List<TweetedEntity> getTweetedUserMentionsByPolitician(String politician) {
        TweetedEntity[] popularWords = restTemplate.getForObject(searchHost
                + "/tweeted-user-mentions/politician?politician=" + politician, TweetedEntity[].class);

        return Arrays.asList(popularWords);
    }

    @Override
    public List<TweetedEntity> getTweetedUserMentionsByParty(String partyUrlName) {
        TweetedEntity[] popularWords = restTemplate.getForObject(searchHost
                + "/tweeted-user-mentions/party?partyUrlName=" + partyUrlName, TweetedEntity[].class);

        return Arrays.asList(popularWords);
    }

    @Override
    public List<PoliticianSearchable> getPoliticians(String partyUrlName) {
        PoliticianSearchable[] popularWords = restTemplate.getForObject(searchHost + "/politicians?partyUrlName="
                + partyUrlName, PoliticianSearchable[].class);

        return Arrays.asList(popularWords);
    }

    @Override
    public List<PoliticianSearchable> getPoliticians() {
        PoliticianSearchable[] popularWords = restTemplate.getForObject(searchHost + "/politicians",
                PoliticianSearchable[].class);

        return Arrays.asList(popularWords);
    }

}
