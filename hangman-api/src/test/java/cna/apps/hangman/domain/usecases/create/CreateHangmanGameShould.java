package cna.apps.hangman.domain.usecases.create;

import static cna.apps.hangman.domain.usecases.WordFixtures.GAME;
import static cna.apps.hangman.domain.usecases.WordFixtures.GAME_MASK;
import static cna.apps.hangman.domain.usecases.WordFixtures.HANGMAN;
import static cna.apps.hangman.domain.usecases.WordFixtures.HANGMAN_MASK;
import static cna.apps.hangman.domain.usecases.create.NewGameAssertions.assertDifferentNewGames;
import static cna.apps.hangman.domain.usecases.create.NewGameAssertions.assertNewGame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cna.apps.hangman.adapters.InMemoryGameRepository;
import cna.apps.hangman.adapters.WordList;
import cna.apps.hangman.domain.entities.HangmanGame;
import cna.apps.hangman.domain.ports.GameRepository;
import cna.apps.hangman.domain.ports.create.CreateHangmanGameInputBoundary;
import cna.apps.hangman.domain.ports.create.NewGame;
import cna.apps.hangman.domain.usecases.CreateHangmanGame;

public class CreateHangmanGameShould {
  
  private NewGamePresenterSpy presenter;
  private InMemoryGameRepository gameRepository;
  private CreateHangmanGameInputBoundary createHangmanGame;
  private WordList wordList;

  @BeforeEach
  void setUp() {
    wordList = new WordList(HANGMAN, GAME);
    gameRepository = new InMemoryGameRepository();
    presenter = new NewGamePresenterSpy();
    createHangmanGame = new CreateHangmanGame(wordList, gameRepository, presenter);
  }

  @Test
  void start_new_game() {
    createHangmanGame.startNewGame();
    assertNewGame(presenter.getNewGame(), HANGMAN_MASK);
    assertNewGameInGameRepository(presenter.getNewGame());
  }

  private void assertNewGameInGameRepository(NewGame newGame) {
    HangmanGame hangmanGame = gameRepository.get(newGame.getId());
    assertNotNull(hangmanGame);
    assertEquals(newGame.getMask(), hangmanGame.getMask());
  }

  @Test
  void start_different_new_game() {
    var newGame = startNewGame();
    var otherNewGame = startNewGame();
    assertDifferentNewGames(newGame, HANGMAN_MASK, otherNewGame, GAME_MASK);
  }

  private NewGame startNewGame() {
    createHangmanGame.startNewGame();    
    return presenter.getNewGame();
  }

}
