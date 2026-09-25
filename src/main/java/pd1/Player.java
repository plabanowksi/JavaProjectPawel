package pd1;

public class Player implements Comparable{
    private final int [] results;
    private final int id;
    private final int sumOfResults;
    boolean isWinner;

    Player(int id, int[] results, boolean isWinner, int sumOfResults){
        this.id = id;
        this.results = results;
        this.isWinner = isWinner;
        this.sumOfResults=sumOfResults;
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

    @Override
    public int compareTo(Object o) {
        Player other = (Player) o;
        return Integer.compare(other.sumOfResults, this.sumOfResults);
    }
}