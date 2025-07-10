import java.util.Scanner;

public class Display {
    private final Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("Welcome to the Word Guess Game!");
    }

    public void showBlanks(char[] blanks) {
        for (char c : blanks) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    public char askForGuess() {
        System.out.print("Guess a letter: ");
        String input = scanner.nextLine();
        while (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            System.out.print("Invalid input. Guess a single letter: ");
            input = scanner.nextLine();
        }
        return Character.toLowerCase(input.charAt(0));
    }

    public void showCorrectGuess() {
        System.out.println("Correct guess!");
    }

    public void showIncorrectGuess(int lives) {
        System.out.println("Incorrect guess. Lives left: " + lives);
    }

    public void showWin(String word) {
        System.out.println("Congratulations! You guessed the word: " + word);
    }

    public void showLose(String word) {
        System.out.println("Game over! The word was: " + word);
    }
}
