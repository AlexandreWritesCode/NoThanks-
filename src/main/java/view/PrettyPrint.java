package view;

import model.Player;

import java.util.Scanner;

public class PrettyPrint {
    public final static int setNumberOfPlayers(Scanner k){
        System.out.print("Number of players: ");
        int num = 0;
        num = k.nextInt();
        k.nextLine();
        while (num < 3 || num > 7) {
            System.out.print("No Thanks! is played with 3 to 7 players.\nNew number of players: ");
            num = k.nextInt();
            k.nextLine();
        }
        return num;
    }
    public static int setEndGame(Scanner k){
        System.out.print("Play first to: ");
        int num = k.nextInt();
        k.nextLine();
        while (num < 1) {
            System.out.print("You need to play at least one game. How many rounds would you like to play: ");
            num = k.nextInt();
            k.nextLine();
        }
        return num;
    }
    public static String setName(Scanner k, int user){
        System.out.print("Player "+ user + "'s name: ");
        return k.nextLine();
    }
    public static int displayChoicesAndGetResponse(int card, boolean hasChips, Scanner k, String name, int coins){
        System.out.println(name + "'s "+ "turn...");
        String output = "1) Take the gift ("+card+") and "+coins+" coin";
        if (coins == 0 || coins > 1) output+="s";
        int choice;
        if (hasChips){
            output += "\n2) No Thanks!";
            System.out.println(output);
            System.out.print("Choose: ");
            choice = k.nextInt();
            k.nextLine();
            while (choice!=1 && choice!=2){
                System.out.println();
                System.out.println("Invalid input. Pick '1' or '2'.");
                System.out.println(output);
                System.out.print("Choose again: ");
                choice = k.nextInt();
                k.nextLine();
            }
        } else {
            System.out.println(output);
            System.out.println("You have no coins so you take the gift.");
            choice = 1;
        }
        System.out.println();
        return choice;
    }
    public static void displayPlayerPoints(String name, int points){
        System.out.println(name + " has "+points+" points.");
    }
    public static void displayPlayerHands(String hand, String name, int tokens){
        System.out.println(name + "'s "+ "("+tokens+") hand: " +hand);
    }
    public static void displayWinners(String name, int score){
        System.out.println(name + " wins with " + score + " points!");
    }
    public static int displayWins(Player wins){
        int numberOfWins = wins.winner();
        String s = wins.getName() + " has won " + numberOfWins + " time";
        if (numberOfWins > 1) s+="s";
        s+="!";
        System.out.println(s);
        return numberOfWins;
    }
    public static void displayGameWinners(String s, int size){
        if (size == 1) {
            System.out.println(s + " wins!");
        } else {
            System.out.println(s+" won!");
        }
    }
}
