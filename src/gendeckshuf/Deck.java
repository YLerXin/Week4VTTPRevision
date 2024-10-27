package gendeckshuf;

import java.util.Random;

public class Deck {

    public static final String[] SUIT = {"Diamond","Spade","Hearts","Clubs"};
    public static final String[] NAMES = {"Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King"};
    public static final int[] VALUES = {1,2,3,4,5,6,7,8,9,10,10,10,10,};
    
    protected Card[] cards;

    public Deck(){
        int idx = 0;
        cards = new Card[52];
        for (int s=0;s<SUIT.length;s++){
            String suit = SUIT[s];
            for(int n=0;n<NAMES.length;n++){
                String name = NAMES[n];
                int value = VALUES[n];
                Card card = new Card(suit,name,value);
                cards[idx] = card;
                idx++;

            }
        }

    }
    public Card[] shuffle(){

        Random rand = new Random();
        for (int i = 0; i < cards.length; i++) {
                int randomIndexToSwap = rand.nextInt(52); // choose a rand num from 0 to 51 i guess
                Card temp = cards[randomIndexToSwap];
                cards[randomIndexToSwap] = cards[i];
                cards[i] = temp;
            }
        return cards;
    }
    public Card get(){
        return this.cards[0];
    }
    public Card get(int pos){
        return this.cards[pos];
    }
    public Card[] getDeck() {
        return this.cards;  // Returns the deck (the array of cards)
    }
    @Override
    public String toString(){
        return "Number of cards: %d".formatted(cards.length);
    }

}
