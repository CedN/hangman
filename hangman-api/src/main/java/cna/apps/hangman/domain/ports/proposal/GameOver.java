package cna.apps.hangman.domain.ports.proposal;

public class GameOver {

	private boolean isWonGame;

	public GameOver(boolean isWonGame) {
		this.isWonGame = isWonGame;
	}

	public boolean isWonGame() {
		return isWonGame;
	}

}
