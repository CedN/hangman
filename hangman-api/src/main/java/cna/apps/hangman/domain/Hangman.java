package cna.apps.hangman.domain;

public class Hangman {

  private static final int INITIAL_STEP = 0;
  
  private static final int LAST_STEP = 7;

  private int step = INITIAL_STEP;

  public boolean isEmpty() {
    return step == INITIAL_STEP;
  }

  public int getStep() {
    return step;
  }

  public void increaseStep() throws CompletedHangmanException {
    step++;
    if (step == LAST_STEP) {
      throw new CompletedHangmanException();
    }
  }

}
