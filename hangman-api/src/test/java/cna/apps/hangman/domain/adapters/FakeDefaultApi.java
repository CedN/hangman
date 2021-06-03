package cna.apps.hangman.domain.adapters;

import java.util.Arrays;
import java.util.List;

import cna.apps.hangman.apiclients.ApiException;
import cna.apps.hangman.apiclients.randomwords.DefaultApi;
import cna.apps.hangman.apiclients.randomwords.model.RandomWord;

public class FakeDefaultApi extends DefaultApi {

  private String word;

  public void setProvidedWord(String word) {
    this.word = word;
  }

  @Override
  public List<RandomWord> fetchWord() throws ApiException {
    if (word != null) {
      return createRandomWord();
    }
    throw new ApiException("No word available!");
  }

  private List<RandomWord> createRandomWord() {
    RandomWord randomWord = new RandomWord();
    randomWord.setWord(word);
    randomWord.setDefinition("definition");
    randomWord.setPronunciation("pronunciation");
    return Arrays.asList(randomWord);
  }

}
