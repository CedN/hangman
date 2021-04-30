package cna.apps.hangman.api;

import cna.apps.hangman.domain.GameAlreadyWonException;
import cna.apps.hangman.domain.GameOverException;
import cna.apps.hangman.domain.Hangman;

public interface Game {
  
  public String getMask();

  public void tryLetter(char c) throws GameOverException, GameAlreadyWonException;

  public boolean isTheGoodWord(String proposal) throws GameOverException, GameAlreadyWonException;

  public Hangman getHangMan();
}
