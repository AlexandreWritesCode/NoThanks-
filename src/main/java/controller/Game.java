package controller;

import model.Card;
import model.Player;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

import static view.PrettyPrint.*;

public class Game {
    private static LinkedList<Card> deck;
    private static LinkedList<Player> turn;
    private static int setTokens = 0;
    public Game() {
        deck = new LinkedList<Card>(); // create cards 3 - 35
        for(int counter = 3; counter <= 35; counter++){
            deck.add(new Card (counter));
        }
        Collections.shuffle(deck);
        for (int counter = 0; counter < 9; counter++) { // remove first ten cards
            deck.remove();
        }
    }
    public static void main (String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int numberOfPlayers = setNumberOfPlayers(keyboard);
        if (numberOfPlayers < 6){
            setTokens = 11;
        } else if (numberOfPlayers == 6){
            setTokens = 9;
        } else {
            setTokens = 7;
        }
        turn = new LinkedList<Player>();
        for (int counter = 1; counter < numberOfPlayers+1; counter++) {
            turn.add(new Player(setTokens, setName(keyboard, counter)));
        }
        int firstTo = setEndGame(keyboard);
        int maxNumberOfWins = 0;
        do {
            new Game();
            while (deck.size() > 0) {
                Card faceUp = deck.remove(); // the gift for this round
                int currentPool = 0;
                boolean someoneTookTheCard = false;
                while (!someoneTookTheCard) {
                    Player playersTurn = turn.remove();
                    turn.add(playersTurn);
                    for (Player p : turn) {  // display player's hand
                        displayPlayerHands(p.displayHand(), p.getName(), p.getTokens());
                    }
                    int choice = displayChoicesAndGetResponse(faceUp.getNumber(), playersTurn.hasChips(), keyboard, playersTurn.getName(), currentPool);
                    if (choice == 1) {
                        playersTurn.addCard(faceUp);
                        playersTurn.takeChips(currentPool);
                        displayPlayerPoints(playersTurn.getName(), playersTurn.calculatePoints());
                        someoneTookTheCard = true;
                    } else {
                        currentPool += playersTurn.noThanks();
                    }
                }
            }
            Player bobsMisfortune = turn.removeLast();
            int winnerScore = 1000;
            String winnerName = "";
            LinkedList<Player> winners = new LinkedList<Player>();
            for (Player p : turn) {
                int points = p.calculatePoints();
                displayPlayerPoints(p.getName(), points); // bob exists
                if (points < winnerScore) {
                    winnerName = p.getName();
                    winnerScore = points;
                    winners.clear();
                    winners.add(p);
                } else if (points == winnerScore) {
                    winnerName += " and " + p.getName();
                    winners.add(p);
                }
                p.newRound(setTokens); // bob doesn't exist
            }
            turn.add(bobsMisfortune);
            bobsMisfortune.newRound(setTokens);
            displayWinners(winnerName, winnerScore);
            maxNumberOfWins = 0;
            for (Player winner : winners) {
                int numOfWins = displayWins(winner);
                if (maxNumberOfWins < numOfWins) {
                    maxNumberOfWins = numOfWins;
                }
            }
            System.out.println();
        } while (maxNumberOfWins != firstTo);
        int wonRounds = 0;
        String winnerName = "";
        LinkedList<Player> winners = new LinkedList<Player>();
        for (Player p : turn) {
            if (p.numberOfRoundsWon() > wonRounds) {
                winners.clear();
                winners.add(p);
                winnerName = p.getName();
                wonRounds = p.numberOfRoundsWon();
            } else if (p.numberOfRoundsWon() == wonRounds) {
                winners.add(p);
                winnerName += " and " + p.getName();
            }
        }
        displayGameWinners(winnerName, winners.size());
    }
}
