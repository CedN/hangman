package cna.apps.hangman.adapters;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cna.apps.hangman.domain.ports.WordProvider;

public class WordList implements WordProvider {

  private static final Logger LOGGER = LoggerFactory.getLogger(WordList.class);

  private final String[] words;

  private int index = -1;

  public WordList(String... words) {
    this.words = Arrays.copyOf(words, words.length);
  }

  @Override
  public String provide() {
    increasedIndex();
    var word = words[index];
    LOGGER.debug("Provided word = '{}'", word);
    return word;
  }

  private void increasedIndex() {
    index = ++index % words.length;
  }

}
