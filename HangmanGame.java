import java.util.Scanner;
import java.lang.Character;


public class HangmanGame{
  private final static int PHRASE_LENGTH_MAX = 50;
  private final static int ALPHABET_LENGTH = 26;
  private String currentWord;
  private int stageInGame;
  private char[] lettersGuessed = new char[ALPHABET_LENGTH];
  private String wordProgress;
  private char currentGuess;
  private int currNumIndicies = 0;


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

  public int getStageInGame(){
    return stageInGame;
  }

  public void setCurrentWord(String word){
    if(word.length() < PHRASE_LENGTH_MAX){
      this.currentWord = word;
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
      currentGuess = Character.toUpperCase(userGuess);
      return true;
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
    return letterLocationIndicies;
  }

  private int

  public void updateWordProgress(int[] letterLocationIndicies){
    String newWordProgress = ""
    if(letterLocationIndicies[0] != null){                     //Use Substring
      for(int i = 0; i < currNumIndicies; i++)
        if(letterLocationIndicies[i] == 0){
          
        }
    }

  }

  public static void main( String[] args){
    HangmanGame firstGame = new HangmanGame("longtest");
    System.out.println(firstGame.getWordProgress());
  }
}
