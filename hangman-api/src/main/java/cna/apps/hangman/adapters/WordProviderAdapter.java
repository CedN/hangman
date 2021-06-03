package cna.apps.hangman.adapters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cna.apps.hangman.domain.ports.WordProvider;

public class WordProviderAdapter implements WordProvider {

  private static final Logger LOGGER = LoggerFactory.getLogger(WordProviderAdapter.class);

  private final WordProvider mainWordProvider;
  private final WordProvider wordProviderBackup;

  public WordProviderAdapter(WordProvider mainWordProvider, WordProvider wordProviderBackup) {
    this.mainWordProvider = mainWordProvider;
    this.wordProviderBackup = wordProviderBackup;
  }

  @Override
  public String provide() {
    try {
      LOGGER.info("Call main word provider");
      return mainWordProvider.provide();
    } catch (RuntimeException e) {
      LOGGER.warn("Error during main word provider call = {}: {}", e.getCause().getClass().getName(), e.getCause().getMessage());
      LOGGER.info("Call word provider backup");
      return wordProviderBackup.provide();
    }
  }

}
