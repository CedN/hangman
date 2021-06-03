package cna.apps.hangman.domain.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cna.apps.hangman.adapters.RandomWordApi;
import cna.apps.hangman.adapters.WordList;
import cna.apps.hangman.adapters.WordProviderAdapter;
import cna.apps.hangman.domain.ports.WordProvider;

public class WordProviderAdapterShould {
  
  private static final String WORD_FROM_API = "humankind";
  private static final String WORD_FROM_LIST = "alien";

  private WordProviderAdapter wordProviderAdapter;
  private WordProvider mainWordProvider;
  private WordProvider wordProviderBackup;
  private FakeDefaultApi defaultApi;

  @BeforeEach
  void setup() {
    defaultApi = new FakeDefaultApi();
    mainWordProvider = new RandomWordApi(defaultApi);
    wordProviderBackup = new WordList(WORD_FROM_LIST);
    wordProviderAdapter = new WordProviderAdapter(mainWordProvider, wordProviderBackup);
  }

  @Test
  void provide_word_from_random_word_api() {
    defaultApi.setProvidedWord(WORD_FROM_API);
    String word = wordProviderAdapter.provide();
    assertEquals(WORD_FROM_API, word);
  }

  @Test
  void provide_word_from_word_list_when_random_word_api_fails() {
    String word = wordProviderAdapter.provide();
    assertEquals(WORD_FROM_LIST, word);
  }

}
