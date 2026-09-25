package pd1;

public class Player implements Comparable<Player> {
    private final int[] results;
    private final int id;
    private final int sumOfResults;
    private boolean isWinner;

    Player(int id, int[] results, int sumOfResults) {
        this.id = id;
        this.results = results;
        this.isWinner = false;
        this.sumOfResults = sumOfResults;
    }

    public int getSumOfResults() {
        return sumOfResults;
    }

    public int getId() {
        return id;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public int[] getResults() {
        return results;
    }

    public void markAsWinner() {
        isWinner = true;
    }

    @Override
    public int compareTo(Player p) {
        return Integer.compare(p.sumOfResults, this.sumOfResults);
    }
}