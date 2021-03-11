package cna.apps.hangman.domain;

public class Word {

  private static final Character UNKNOWN_LETTER = Character.valueOf('-');

  private final String wordToGuess;

  private String letterFounds;

  public Word(String wordToGuess) {
    this.wordToGuess = wordToGuess;
    letterFounds = "";
  }

  public String getMask() {
    return wordToGuess
      .codePoints()
      .mapToObj(c -> (char) c)
      .map(this::maskLettersNotFound)
      .collect(
        StringBuilder::new, 
        StringBuilder::append, 
        StringBuilder::append)
      .toString();
  }

  private char maskLettersNotFound(char c) {
    if (letterFounds.indexOf(c) != -1)
      return c;
    return UNKNOWN_LETTER;
  }

  public void tryLetter(char c) throws BadLetterException {
    if (wordToGuess.indexOf(c) == -1) {
      throw new BadLetterException();
    }
    letterFounds = letterFounds + c;
  }

}
