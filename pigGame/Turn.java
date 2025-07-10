public class Turn {
    private Dice dice;
    private InputHandler inputHandler;
    private int turnScore;
    private boolean turnOver;

    public Turn(Dice dice, InputHandler inputHandler) {
        this.dice = dice;
        this.inputHandler = inputHandler;
        this.turnScore = 0;
        this.turnOver = false;
    }

    public int playTurn(int turnNumber) {
        System.out.println("\nTURN " + turnNumber);
        turnScore = 0;
        turnOver = false;
        while (!turnOver) {
            String choice = inputHandler.getRollOrHold();
            if (choice.equals("r")) {
                int die = dice.roll();
                System.out.println("Die: " + die);
                if (die == 1) {
                    System.out.println("Turn over. No score.\n");
                    turnScore = 0;
                    turnOver = true;
                } else {
                    turnScore += die;
                }
            } else {
                System.out.println("Score for turn: " + turnScore);
                turnOver = true;
            }
        }
        return turnScore;
    }
}
