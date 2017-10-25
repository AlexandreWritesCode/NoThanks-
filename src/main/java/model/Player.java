package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Player {
    private int chips;
    private String name;
    private int roundsWon;
    private int points;
    private ArrayList <Card>hand;
    public Player (int tokens, String callMe){
        this.name = callMe;
        this.chips = tokens;
        this.hand = new ArrayList<Card>();
        this.roundsWon = 0;
        this.points = 0;
    }
    /**
     * @return name of user
     */
    public String getName(){
        return this.name;
    }

    /**
     * @param bet number of tokens in the pile
     * @return total number of tokens user has now
     */
    public int takeChips(int bet) {
        return this.chips += bet;
    }
    /**
     * @return whether player has chips
     */
    public boolean hasChips(){
        return this.chips!=0;
    }

    /**
     * @return number of tokens in hand
     */
    public int getTokens(){
        return this.chips;
    }
    /**
     * Toss a chip to avoid collecting card
     * @return 1
     */
    public int noThanks(){
        this.chips--;
        return 1;
    }

    /**
     * @param c the card that is being added
     */
    public void addCard(Card c){
        this.hand.add(c);
        Collections.sort(this.hand, new Comparator<Card>(){
            public int compare(Card o1, Card o2){
                return o1.getNumber() - o2.getNumber();
            }
        });
    }
    /**
     * @return user's hand formatted as a string
     */
    public String displayHand(){
        String output = "";
        int lastNumberException = 0;
        for(Card c : this.hand) {
            if (lastNumberException < this.hand.size()-1) {
                output += c.getNumber() + ", ";
            } else {
                output += c.getNumber();
            }
            lastNumberException++;
        }
        return output;
    }
    /**
     * @return number of wins
     */
    public int winner(){
        return ++this.roundsWon;
    }
    /**
     * @return number of points user has
     */
    public int calculatePoints(){
        this.points = 0;
        for (int counter = this.hand.size()-1;  0 <= counter; counter--) {
            int currentCard = this.hand.get(counter).getNumber();
            if (counter != 0) { // must be more cards in the deck
                if(currentCard - 1 != this.hand.get(counter- 1).getNumber()){ // the next card doesn't follow a sequence
                    this.points += currentCard;
                } // else let the next card go so we can truncate to the lowest in the sequence
            } else {
                this.points += currentCard;
            }
        }
        this.points -= this.chips;
        return this.points;
    }
    /**
     * @param tokens number of starting tokens based on number of players
     */
    public void newRound(int tokens){
        this.points = 0;
        this.chips=tokens;
        this.hand.clear();
    }

    /**
     * @return number of rounds won
     */
    public int numberOfRoundsWon(){
        return this.roundsWon;
    }
}
