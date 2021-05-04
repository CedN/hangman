package cna.apps.hangman.domain.entities;

public class WordToGuess {

	private final String value;

  public WordToGuess(String value) {
    this.value = value;
	}

  public String getValue() {
    return value;
  }

  boolean contains(char c) {
    return value.indexOf(c) != -1;
  }
  
}
