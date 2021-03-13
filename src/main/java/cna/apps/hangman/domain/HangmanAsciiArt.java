package cna.apps.hangman.domain;

final class HangmanAsciiArt {

  private HangmanAsciiArt() {}

  private static final String[] ASCII_ART_STEPS = new String[] {  
  """







  """,
  """
           \s
           \s
           \s
           \s
           \s
           \s
  ==========\s
  """,
  """
           \s
          |\s
          |\s
          |\s
          |\s
          |\s
  ==========\s
  """,
  """
      +---+\s
          |\s
          |\s
          |\s
          |\s
          |\s
  ==========\s
  """,
  """
      +---+\s
      |   |\s
          |\s
          |\s
          |\s
          |\s
  ==========\s
  """,
  """
      +---+\s
      |   |\s
      o   |\s
          |\s
          |\s
          |\s
  ==========\s
  """,
  """
      +---+\s
      |   |\s
      o   |\s
     /|\\  |\s
          |\s
          |\s
  ==========\s
  """,
  """
      +---+\s
      |   |\s
      o   |\s
     /|\\  |\s
     / \\  |\s
          |\s
  ==========\s
  """
  };

  static String fromStep(int i) {
    return ASCII_ART_STEPS[i];
  }

}
