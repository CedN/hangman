package cna.apps.hangman.domain.core;

import static cna.apps.hangman.domain.core.MoveResult.LOST_GAME;
import static cna.apps.hangman.domain.core.MoveResult.WRONG;

public class Hangman {

  private static final int INITIAL_STEP = 0;
  
  private static final int LAST_STEP = 7;

  private int step = INITIAL_STEP;

  public int getStep() {
    return step;
  }

  public MoveResult increaseStep() {
    step++;
    if (isFull()) {
      return LOST_GAME;
    }
    return WRONG;
  }

  public boolean isFull() {
    return step == LAST_STEP;
  }

}
