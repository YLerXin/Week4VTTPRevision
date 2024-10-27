package gendeckshuf;

public class Main {

    public static void main(String[] args) {
        Deck deck = new Deck();
        
        deck.shuffle();
        Card[] shuffledDeck = deck.getDeck();  // Get the shuffled deck

        for (int i = 0; i < shuffledDeck.length; i++) {
            System.out.println(shuffledDeck[i]);  // Assuming 'Card' has a proper toString() method to display the card
        }
    }
    
}
