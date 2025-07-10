public class PigGame {
    public static void main(String[] args) {
        Dice dice = new Dice();
        InputHandler inputHandler = new InputHandler();
        int totalScore = 0;
        int turnCount = 0;
        final int WIN_SCORE = 20;

        while (totalScore < WIN_SCORE) {
            turnCount++;
            Turn turn = new Turn(dice, inputHandler);
            int turnScore = turn.playTurn(turnCount);
            totalScore += turnScore;
            if (turnScore > 0) {
                System.out.println("Total score: " + totalScore + "\n");
            }
        }
        System.out.println("You finished in " + turnCount + " turns!\n");
        System.out.println("Game over!");
        inputHandler.close();
    }
}
