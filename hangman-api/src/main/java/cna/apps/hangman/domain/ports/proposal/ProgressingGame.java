package cna.apps.hangman.domain.ports.proposal;

public class ProgressingGame {

  private final String mask;
  private final int hangmanStep;

  public ProgressingGame(String mask, int hangmanStep) {
    this.mask = mask;
    this.hangmanStep = hangmanStep;
	}

  public int getHangmanStep() {
    return hangmanStep;
  }

  public String getMask() {
    return mask;
  }

}
