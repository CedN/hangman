package cna.apps.hangman.domain.entities;

public record WordToGuess(String value) {

  boolean contains(char c) {
    return value.indexOf(c) != -1;
  }

}
