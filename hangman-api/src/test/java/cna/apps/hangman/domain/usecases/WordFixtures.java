package cna.apps.hangman.domain.usecases;

public class WordFixtures {
  
  public final static String HANGMAN = "hangman";

  public final static String HANGMAN_MASK = maskWord(HANGMAN);

  public final static String HANGMAN_MASK_WITH_H = HANGMAN.replaceAll("[^h]", "-");

  public final static String HANGMAN_MASK_WITH_A = HANGMAN.replaceAll("[^a]", "-");

  public final static String GAME = "game";

  public final static String GAME_MASK = maskWord(GAME);

  private static String maskWord(String hangman) {
    return hangman.replaceAll("[a-z]", "-");
  }

}
