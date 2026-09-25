package pd1;

import java.util.*;

public class TournamentLaderboard {
    public static void main(String[] args) {
        System.out.println("Insert number of Players (2-10): ");
        Scanner sn = new Scanner(System.in);

        int playersNumber = sn.nextInt();

        if (playersNumber < 2 || playersNumber > 10) {
            throw new IllegalArgumentException("Invalid number of Players");
        }

        Player[] players = new Player[playersNumber];
        int[] sumOfPlayerResults = new int[playersNumber];

        for (int i = 0; i < playersNumber; i++) {
            int[] scores = new int[3];
            System.out.println("Enter ID for player " + (i + 1));
            int id = sn.nextInt();

            for (int j = 0; j < 3; j++) {
                System.out.println("Enter your " + (j + 1) + "result for player number " + id + ":");
                scores[j] = sn.nextInt();
            }
            int sumOfResults = sumResults(scores); // suma wynniku dla gracza
            sumOfPlayerResults[i] = sumOfResults;
            players[i] = new Player(id, scores, false, sumOfResults);
        }

        setWinner(players, playersNumber, sumOfPlayerResults);

        //tu wywolac sortowanie

        showResults(players);
        sn.close();
    }

    private static void setWinner(Player[] players, int playersNumber, int[] sumOfPlayerResults) {
        for (int j = 0; j < playersNumber; j++) {
            if (players[j].getSumOfResults() == maxResult(sumOfPlayerResults)) {
                players[j].isWinner = true;
                break;
            }
        }
    }

    private static int sumResults(int[] scores) {
        return scores[0] + scores[1] + scores[2];
    }

    private static int getAverageResult(int scores) {
        return scores / 3;
    }

    private static int minResult(int[] scores) {
        return Math.min(scores[0], Math.min(scores[1], scores[2]));
    }

    private static int maxResult(int[] scores) {
        return Math.max(scores[0], Math.max(scores[1], scores[2]));
    }

    private static void sortPlayers(Player[] players) {
        Arrays.sort(players);
    }

    private static void showResults(Player[] players) {
        sortPlayers(players);

        for (Player player : players) {
            System.out.println("Sum of results for player number " + player.getId() + ": " + player.getSumOfResults() + " " + addStar(player));
            System.out.println("Average for player number " + player.getId() + ": " + getAverageResult(player.getSumOfResults()));
            System.out.println("Min result for player number " + player.getId() + ": " + minResult(player.getResults()));
            System.out.println("Max result for player number " + player.getId() + ": " + maxResult(player.getResults()));
            System.out.println(" ");
        }
    }

    private static String addStar(Player player) {
        return player.isWinner() ? "*" : "";
    }
}