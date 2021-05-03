package cna.apps.hangman.domain.ports.proposal;

public class WonGame {

	private final String mask;
	private final int hangmanStep;

	public WonGame(String mask, int hangmanStep) {
		this.mask = mask;
		this.hangmanStep = hangmanStep;
	}

	public String getMask() {
		return mask;
	}

	public int getHangmanStep() {
		return hangmanStep;
	}

}
