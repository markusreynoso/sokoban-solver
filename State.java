public class State {
    private String moveSequence;
    private char[][] fullMapData;
    private int playerX;
    private int playerY;
    private int aStarScore;

    // CONSTRUCTORS
    public State(int playerX, int playerY, String moveSequence, char[][] fullMapData, int aStarScore){
        this.playerY = playerY;
        this.playerX = playerX;
        this.moveSequence = moveSequence;
        this.fullMapData = fullMapData;
        this.aStarScore = aStarScore;
    }

    public State(int playerX, int playerY, String moveSequence, char[][] fullMapData, int aStarScore, char appendedMove){
        this.playerY = playerY;
        this.playerX = playerX;
        this.moveSequence = moveSequence + appendedMove;
        this.fullMapData = fullMapData;
        this.aStarScore = aStarScore;
        switch (appendedMove) {
            case 'u' -> this.playerY--;
            case 'd' -> this.playerY++;
            case 'l' -> this.playerX--;
            case 'r' -> this.playerX++;
        }
    }

    // GETTERS
    public String getMoveSequence() {
        return moveSequence;
    }
    public char[][] getFullMapData() {
        return fullMapData;
    }
    public int getPlayerY() {return playerY;}
    public int getPlayerX() {return playerX;}
    public int getAStarScore() {return aStarScore;}


}