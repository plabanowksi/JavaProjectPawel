package pd1;

public class Player {
    private final int [] results;
    private final int id;
    private int sumOfResults;
    private final boolean isWinner;

    Player(int id, int[] results){
        this.id = id;
        this.results = results;
        this.isWinner = false;
    }

    public int[] getResults() {
        return results;
    }

    public int getId() {
        return id;
    }


    public void setSumOfResults(int sumOfResults) {
        this.sumOfResults = sumOfResults;
    }

    public boolean isWinner() {
        return isWinner;
    }

}