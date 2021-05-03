package cna.apps.hangman.provider;

import java.util.Arrays;

import cna.apps.hangman.domain.ports.WordProvider;

public class WordList implements WordProvider {

  private final String[] words;

  private int index = -1;

  public WordList(String... words) {
    this.words = Arrays.copyOf(words, words.length);
  }

  @Override
  public String provide() {
    increasedIndex();
    return words[index];
  }

  private void increasedIndex() {
    index = ++index % words.length;
  }

}
