import java.util.Random;

public class WordProvider {
    private final String[] words = {"java", "modular", "hangman", "guess", "random", "program"};
    private final Random random = new Random();

    public String getRandomWord() {
        return words[random.nextInt(words.length)];
    }
}
