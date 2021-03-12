package cna.apps.hangman.provider;

import java.util.Arrays;

import cna.apps.hangman.domain.WordProvider;

public class WordList implements WordProvider {

  private final String[] words;

  public WordList(String... words) {
    this.words = Arrays.copyOf(words, words.length);
  }

  @Override
  public String provide() {
    return words[0];
  }

}
