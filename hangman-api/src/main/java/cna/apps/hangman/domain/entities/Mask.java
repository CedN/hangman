package cna.apps.hangman.domain.entities;

class Mask {

  private static final Character MASKED_LETTER = Character.valueOf('-');
  private String letterFounds;

  Mask() {
    this.letterFounds = "";
  }

  void addLetterFound(char c) {
    letterFounds = letterFounds + c;
  }

  boolean isLetterAlreadyFound(char c) {
    return letterFounds.indexOf(c) != -1;
  }

  String showMaskedWord(WordToGuess wordToGuess) {
    return wordToGuess.getValue().codePoints().mapToObj(c -> (char) c).map(this::maskLetterNotFound)
        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
  }

  private char maskLetterNotFound(char c) {
    if (letterFounds.indexOf(c) != -1)
      return c;
    return MASKED_LETTER;
  }

  boolean isDiscovered(WordToGuess wordToGuess) {
    return showMaskedWord(wordToGuess).indexOf(MASKED_LETTER) == -1;
  }

}
