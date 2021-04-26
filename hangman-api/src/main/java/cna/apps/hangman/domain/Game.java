package cna.apps.hangman.domain;

public interface Game {
  
  public String getMask();

  public void tryLetter(char c) throws GameOverException, GameAlreadyWonException;

  public boolean isTheGoodWord(String proposal) throws GameOverException, GameAlreadyWonException;

  public Hangman getHangMan();
}
