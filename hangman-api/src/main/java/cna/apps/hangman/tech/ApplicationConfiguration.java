/*
ApplicationConfiguration.java
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
package cna.apps.hangman.tech;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

import cna.apps.hangman.adapters.InMemoryGameRepository;
import cna.apps.hangman.adapters.RandomWordApi;
import cna.apps.hangman.adapters.WordList;
import cna.apps.hangman.adapters.WordProviderAdapter;
import cna.apps.hangman.adapters.creation.HangmanGameCreatedPresenter;
import cna.apps.hangman.adapters.creation.HangmanGameCreationController;
import cna.apps.hangman.adapters.proposal.LetterProposalController;
import cna.apps.hangman.adapters.proposal.LetterProposedPresenter;
import cna.apps.hangman.apiclients.randomwords.DefaultApi;
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
    WordProvider main = new RandomWordApi(new DefaultApi());
    WordProvider backup = new WordList(WORD_LIST);
    return new WordProviderAdapter(main, backup);
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
