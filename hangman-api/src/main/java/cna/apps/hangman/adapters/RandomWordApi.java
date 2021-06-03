package cna.apps.hangman.adapters;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cna.apps.hangman.apiclients.ApiException;
import cna.apps.hangman.apiclients.randomwords.DefaultApi;
import cna.apps.hangman.apiclients.randomwords.model.RandomWord;
import cna.apps.hangman.domain.ports.WordProvider;

public class RandomWordApi implements WordProvider {

  private static final Logger LOGGER = LoggerFactory.getLogger(RandomWordApi.class);

  private final DefaultApi defaultApi;

  public RandomWordApi(DefaultApi defaultApi) {
    this.defaultApi = defaultApi;
  }

  @Override
  public String provide() {
    try {
      List<RandomWord> randomWordResponse = defaultApi.fetchWord();
      String word = randomWordResponse.get(0).getWord();
      LOGGER.debug("Provided word = '{}'", word);
      return word;
    } catch (ApiException e) {
      throw new RuntimeException("Random word API unavailable!", e);
    }
  }
  
}
