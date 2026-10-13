package pd1;
import lombok.Getter;

@Getter
public class Player implements Comparable<Player> {
    private final int id;
    private final int[] results;
    private final int sumOfResults;
    private boolean isWinner = false;

    Player(int id, int[] results, int sumOfResults) {
        this.id = id;
        this.results = results;
        this.sumOfResults = sumOfResults;

    }

    public void markAsWinner() {
        isWinner = true;
    }

    @Override
    public int compareTo(Player p) {
        return Integer.compare(p.sumOfResults, this.sumOfResults);
    }
}