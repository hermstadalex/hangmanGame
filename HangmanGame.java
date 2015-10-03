import java.util.Scanner;
import java.lang.Character;
import java.util.Random;


public class HangmanGame{
  private final static int PHRASE_LENGTH_MAX = 50;
  private final static int ALPHABET_LENGTH = 26;
  private String currentWord;
  private int stageInGame;
  private char[] lettersGuessed = new char[ALPHABET_LENGTH];
  private String wordProgress;
  private char currentGuess;
  private int currNumIndicies = 0;
  private int numLettersGuessed = 0;
  private int correctGuesses = 0;
  private boolean letterGuessed = false;


  HangmanGame() {
    currentWord = "tester";
    stageInGame = 0;
    wordProgress = "_ _ _ _ _ _";
  }

  HangmanGame(String currentWord) {
    setCurrentWord(currentWord);
    stageInGame = 0;
    this.initializeWordProgress();
  }
  
  public String getCurrentWord(){
    return currentWord;
  }

  public String getWordProgress(){
    return wordProgress;
  }

  public int getCurrNumIndicies(){
    return currNumIndicies;
  }

  public int getStageInGame(){
    return stageInGame;
  }

  public void setCurrentWord(String word){
    if(word.length() < PHRASE_LENGTH_MAX){
      this.currentWord = word;
    }
  }

  public void setStageInGame(int stage){
    if (stage >= 0 && stage <= 6) {
      stageInGame = stage;
    }
  }
  
  public void initializeWordProgress(){
    for(int i = 0; i < currentWord.length(); i++){
      if (i == 0){
        wordProgress = "_";
      }
      else {
        wordProgress += " _";
      }
    }
  }
  
  public boolean getUserLetter(){
    Scanner reader = new Scanner(System.in);
    char userGuess = reader.next().charAt(0);
    if (Character.isLetter(userGuess)){
      if (numLettersGuessed == 0){
        currentGuess = Character.toUpperCase(userGuess);
        lettersGuessed[0] = currentGuess;
        numLettersGuessed += 1;
        return true;
      }

      else {
        for(int i = 0; i < numLettersGuessed; i++) {
          if (lettersGuessed[i] == Character.toUpperCase(userGuess) ) {
            return false;
          }
        }
        currentGuess = Character.toUpperCase(userGuess);
        lettersGuessed[numLettersGuessed] = currentGuess;
        numLettersGuessed += 1;
        return true;
      }
    }
    else {
      return false;
    }
  }
  
  public int[] checkLetter(){
    int numIndicies = 0;
    int[] letterLocationIndicies = new int[currentWord.length()];
    for(int i = 0; i < currentWord.length(); i++){
      if(currentGuess == Character.toUpperCase(currentWord.charAt(i))){
        letterLocationIndicies[numIndicies] = i;
        numIndicies+=1;
      }
    }
    currNumIndicies = numIndicies;
    if (numIndicies == 0) {
      stageInGame += 1;
    }
    correctGuesses += currNumIndicies;
    return letterLocationIndicies;
  }

  public void updateWordProgress(int[] letterLocationIndicies){
    String newWordProgress = wordProgress;
    if(currNumIndicies > 0){                     //Use Substring
      for(int i = 0; i < currNumIndicies; i++){
        if(letterLocationIndicies[i] == 0){
          newWordProgress = currentGuess + wordProgress.substring(1);
          wordProgress = newWordProgress;
        }
        else if(letterLocationIndicies[i] == (currentWord.length() - 1) ) {
          newWordProgress = wordProgress.substring(0, wordProgress.length() - 1)
          + currentGuess;
          wordProgress = newWordProgress;
        }
        else{
          newWordProgress = wordProgress.substring(0,
              2*letterLocationIndicies[i]) + currentGuess +
            wordProgress.substring(2 * letterLocationIndicies[i] + 1);
          wordProgress = newWordProgress;
        }
      }
    }
    wordProgress = newWordProgress;
    currNumIndicies = 0;
  }

  public void showWordProgress() {
    System.out.println(wordProgress);
  }

  public void showFigure() {
    switch (stageInGame) {
      case 0: 
        break;
      case 1:
        System.out.println("          *");
        break;
      case 2:
        System.out.println("          *");
        System.out.println("          |");
        break;
      case 3:
        System.out.println("          *");
        System.out.println("         -|");
        break;
      case 4:
        System.out.println("          *");
        System.out.println("         -|-");
        break;
      case 5:
        System.out.println("          *");
        System.out.println("         -|-");
        System.out.println("         /  ");
        break;
      case 6:
        System.out.println("          *");
        System.out.println("         -|-");
        System.out.println("         / \\");
        break;
    }

  }

  public void showLettersUsed() {
    System.out.println("Letters Used: ");
    for (int i = 0; i < numLettersGuessed; i++) {
      if (i == (numLettersGuessed - 1) ) {
        System.out.print(lettersGuessed[i]);
      }
      else {
        System.out.print(lettersGuessed[i] + ", ");
      }
    }
  }

  public void displayUI() {
    System.out.println("\n\n\n");
    this.showFigure();
    System.out.println("\n\n");
    this.showWordProgress();
    System.out.println("\n\n");
    this.showLettersUsed();
    System.out.println("\n\nPlease enter your guess: \n\n ");
  }

  public void gameRunner() {
    displayUI();
    while (true) {
      if(this.getUserLetter()) {
        this.updateWordProgress(this.checkLetter());
      }
      else {
        System.out.println("Not a valid letter, or already guessed. Try again");
      }
      displayUI();
      if ( correctGuesses == currentWord.length() ) {
        System.out.println("\nCongragulations, you win! \n");
        break;
      }
      if ( stageInGame == 6 ) {
        System.out.println("\nSorry, you lose! \n");
        break;
      }
    }
  }

  public static void main( String[] args){
    final String[] hangmanTestWords = {"keyboard", "mouse", "computer", "fantastic",
                                  "excellent", "eraser", "mechanical"};
    int numWords = 7;
    Random wordSelector = new Random();
    int wordIndex = wordSelector.nextInt(numWords);
    HangmanGame testGame = new HangmanGame(hangmanTestWords[wordIndex]);
    testGame.gameRunner();
    }
  
}
