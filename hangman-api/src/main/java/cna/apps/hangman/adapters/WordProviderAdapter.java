/*
WordProviderAdapter.java
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
