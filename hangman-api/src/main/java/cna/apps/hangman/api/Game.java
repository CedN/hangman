package cna.apps.hangman.api;

import cna.apps.hangman.domain.core.GameOverException;
import cna.apps.hangman.domain.core.Hangman;

public interface Game {
  
  public String getMask();

  public void tryLetter(char c) throws GameOverException;

  public boolean isTheGoodWord(String proposal) throws GameOverException;

  public Hangman getHangMan();
}
