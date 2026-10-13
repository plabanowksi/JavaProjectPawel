package pd1;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Player implements Comparable<Player> {
    private final int id;
    private final int[] results;
    private final int sumOfResults;
    private boolean isWinner = false;

    public void markAsWinner() {
        isWinner = true;
    }

    @Override
    public int compareTo(Player p) {
        return Integer.compare(p.sumOfResults, this.sumOfResults);
    }
}