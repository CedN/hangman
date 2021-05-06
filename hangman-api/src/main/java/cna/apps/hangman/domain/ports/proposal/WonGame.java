package cna.apps.hangman.domain.ports.proposal;

public class WonGame {

	private final String mask;
	private final int hangmanStep;
	private final String message;

	public WonGame(String message, String mask, int hangmanStep) {
		this.message = message;
		this.mask = mask;
		this.hangmanStep = hangmanStep;
	}

	public String getMask() {
		return mask;
	}

	public int getHangmanStep() {
		return hangmanStep;
	}

  public String getMessage() {
    return message;
  }

}
