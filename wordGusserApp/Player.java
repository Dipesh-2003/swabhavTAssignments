public class Player {
    private int lives;

    public Player(int lives) {
        this.lives = lives;
    }

    public void loseLife() {
        lives--;
    }

    public int getLives() {
        return lives;
    }

    public boolean isOutOfLives() {
        return lives <= 0;
    }
}
