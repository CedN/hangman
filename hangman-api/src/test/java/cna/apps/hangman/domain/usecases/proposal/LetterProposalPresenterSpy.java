/*
LetterProposalPresenterSpy.java
Copyright 2021 @CedN

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package cna.apps.hangman.domain.usecases.proposal;

import cna.apps.hangman.domain.ports.proposal.GameOver;
import cna.apps.hangman.domain.ports.proposal.LetterProposalOutputBoundary;
import cna.apps.hangman.domain.ports.proposal.ProgressingGame;
import cna.apps.hangman.domain.ports.proposal.LostGame;
import cna.apps.hangman.domain.ports.proposal.WonGame;

public class LetterProposalPresenterSpy implements LetterProposalOutputBoundary {

	private ProgressingGame progessingGame;
	private LostGame lostGame;
	private WonGame wonGame;
	private GameOver gameOver;

	@Override
	public void gameInProgress(ProgressingGame progessingGame) {
		this.progessingGame = progessingGame;
	}

	public ProgressingGame getProgressingGame() {
		return progessingGame;
	}

	@Override
	public void lostGame(LostGame lostGame) {
		this.lostGame = lostGame;
	}

	public LostGame getLostGame() {
		return lostGame;
	}

	@Override
	public void wonGame(WonGame wonGame) {
		this.wonGame = wonGame;
	}

	public WonGame getWonGame() {
		return wonGame;
	}

	@Override
	public void gameIsOver(GameOver gameOver) {
		this.gameOver = gameOver;
	}

	public GameOver getGameOver() {
		return gameOver;
	}

}
