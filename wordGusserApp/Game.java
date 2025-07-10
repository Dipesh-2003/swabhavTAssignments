public class Game {
    private final WordProvider wordProvider;
    private final Player player;
    private final Display display;
    private String word;
    private char[] blanks;

    public Game() {
        this.wordProvider = new WordProvider();
        this.word = wordProvider.getRandomWord();
        this.player = new Player(6); // 6 lives
        this.display = new Display();
        this.blanks = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            blanks[i] = '_';
        }
    }

    public void start() {
        display.showWelcome();
        while (!player.isOutOfLives() && !isWordGuessed()) {
            display.showBlanks(blanks);
            char guess = display.askForGuess();
            if (word.indexOf(guess) >= 0) {
                boolean found = false;
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess && blanks[i] == '_') {
                        blanks[i] = guess;
                        found = true;
                    }
                }
                if (found) {
                    display.showCorrectGuess();
                }
            } else {
                player.loseLife();
                display.showIncorrectGuess(player.getLives());
            }
        }
        display.showBlanks(blanks);
        if (isWordGuessed()) {
            display.showWin(word);
        } else {
            display.showLose(word);
        }
    }

    private boolean isWordGuessed() {
        for (char c : blanks) {
            if (c == '_') return false;
        }
        return true;
    }
}
