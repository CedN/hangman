package cna.apps.hangman.domain;

public class Word implements Game {

  private static final Character UNKNOWN_LETTER = Character.valueOf('-');

  private final String wordToGuess;

  private final Hangman hangman;

  private String letterFounds;

  public Word(String wordToGuess) {
    this.wordToGuess = wordToGuess;
    hangman = new Hangman();
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

  public void tryLetter(char c) throws GameOverException, GameAlreadyWonException {
    assertGameNotAlreaydWon();
    if (wordToGuess.indexOf(c) == -1) {
      increaseHangman();
    }
    letterFounds = letterFounds + c;
  }

  private void assertGameNotAlreaydWon() throws GameAlreadyWonException {
    if (getMask().equals(wordToGuess)) {
      throw new GameAlreadyWonException();
    }
  }

  private void increaseHangman() throws GameOverException {
    try {
      hangman.increaseStep();
    } catch (CompletedHangmanException e) {
      throw new GameOverException(wordToGuess);
    }
  }

  public boolean isTheGoodWord(String proposal) throws GameOverException, GameAlreadyWonException {
    assertGameNotAlreaydWon();
    boolean theGoodWord = wordToGuess.equals(proposal);
    if (!theGoodWord) {
      increaseHangman();
    }
    return theGoodWord;
  }

  public Hangman getHangMan() {
    return hangman;
  }

}
