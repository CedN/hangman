package cna.apps.hangman.domain.ports.proposal;

import cna.apps.hangman.domain.entities.GameOverException;

public class GameOver {

	private boolean isWonGame;

	public GameOver(GameOverException e) {
		this.isWonGame = e.isWonGame();
	}

	public boolean isWonGame() {
		return isWonGame;
	}

}
