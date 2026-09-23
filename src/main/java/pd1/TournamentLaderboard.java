package pd1;

import java.util.*;
import java.util.stream.IntStream;

public class TournamentLaderboard {
    public static void main(String[] args) {
        System.out.println("Insert number of Players (2-10): ");
        Scanner sn = new Scanner(System.in);

        int playersNumber = sn.nextInt();

        if (playersNumber > 10 || playersNumber < 2) {
            throw new Error("Invalid number of Players");
        }

        Player[] players = new Player[playersNumber];

        for (int i = 0; i < playersNumber; i++) {
            int[] wynik = new int[3];
            for (int k = 0; k < 3; k++) {
                System.out.println("Enter your " + (k + 1) + "result for player number " + i + ":");
                wynik[k] = sn.nextInt();
            }
            players[i] = new Player(i, wynik);
        }

        showResults(players);
        sn.close();
    }

    private static int sumResults(Player player) {
        return IntStream.of(player.getResults()).sum();
    }

    private static OptionalDouble averageOfResults(Player player) {
        return IntStream.of(player.getResults()).average();
    }

    private static OptionalInt minResult(Player player) {
        return IntStream.of(player.getResults()).min();
    }

    private static OptionalInt maxResult(Player player) {
        return IntStream.of(player.getResults()).max();
    }

    private static Player[] sortPlayers(Player[] players) {
        for (int i = 0; i < players.length; i++) {
            players[i].setSumOfResults(sumResults(players[i]));
        }

        System.out.println(players);

        //tu sortowanie i ustawenie Winner = true

        return players;//Arrays.sort(players);

    }

    private static void showResults(Player[] players) {
        for (Player player : players) {
            System.out.println("Sum of results for player number " + player.getId() + ": " + sumResults(player) + " " + isWinner(player));
            System.out.println("Average for player number " + player.getId() + ": " + averageOfResults(player));
            System.out.println("Min result for player number " + player.getId() + ": " + minResult(player));
            System.out.println("Max result for player number " + player.getId() + ": " + maxResult(player));
            System.out.println(" ");
        }
    }

    private static String isWinner(Player player) {
        if (player.isWinner()) {
            return "*";
        } else
            return "";
    }
}