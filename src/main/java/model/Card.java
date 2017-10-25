package model;

public class Card {
    private int number;
    public Card(int threeThrough35){
        this.number = threeThrough35;
    }

    /**
     * @return number on the card
     */
    public int getNumber(){
        return this.number;
    }
}
