package cna.apps.hangman.tech;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

import cna.apps.hangman.adapters.InMemoryGameRepository;
import cna.apps.hangman.adapters.WordList;
import cna.apps.hangman.adapters.creation.HangmanGameCreatedPresenter;
import cna.apps.hangman.adapters.creation.HangmanGameCreationController;
import cna.apps.hangman.adapters.proposal.LetterProposalController;
import cna.apps.hangman.adapters.proposal.LetterProposedPresenter;
import cna.apps.hangman.domain.ports.GameRepository;
import cna.apps.hangman.domain.ports.WordProvider;
import cna.apps.hangman.domain.ports.create.CreateHangmanGameInputBoundary;
import cna.apps.hangman.domain.ports.create.NewGameOutputBoundary;
import cna.apps.hangman.domain.ports.proposal.LetterProposalOutputBoundary;
import cna.apps.hangman.domain.ports.proposal.ProposeLetterInputBoundary;
import cna.apps.hangman.domain.usecases.CreateHangmanGame;
import cna.apps.hangman.domain.usecases.ProposeLetter;

@Configuration
public class ApplicationConfiguration {

  static final String[] WORD_LIST = new String[] { "hangman", "computer", "architecture", "openapi" };
  
  @Bean
  public GameRepository getGameRepository() {
    return new InMemoryGameRepository();
  }

  @Bean
  public WordProvider getWordProvider() {
    return new WordList(WORD_LIST);
  }

  @Bean
  @RequestScope
  public HangmanGameCreatedPresenter getNewGameOutputBoundary() {
    return new HangmanGameCreatedPresenter();
  }

  @Bean
  public CreateHangmanGameInputBoundary getCreateHangmanGameInputBoundary(GameRepository gameRepository, WordProvider wordProvider, NewGameOutputBoundary presenter) {
    return new CreateHangmanGame(wordProvider, gameRepository, presenter);
  }

  @Bean
  public HangmanGameCreationController getHangmanGameCreationController(CreateHangmanGameInputBoundary usecase) {
    return new HangmanGameCreationController(usecase);
  }

  @Bean
  @RequestScope
  public LetterProposedPresenter getLetterProposalOutputBoundary() {
    return new LetterProposedPresenter();
  }

  @Bean
  public ProposeLetterInputBoundary getLetterInputBoundary(GameRepository gameRepository, LetterProposalOutputBoundary presenter) {
    return new ProposeLetter(gameRepository, presenter);
  }

  @Bean
  public LetterProposalController getLetterProposalController(ProposeLetterInputBoundary usecase) {
    return new LetterProposalController(usecase);
  }

}
