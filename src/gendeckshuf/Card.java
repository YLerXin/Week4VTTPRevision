package gendeckshuf;

public class Card {
    private final String name;
    private final String suit;
    private final int value;

    public Card(String suit, String name,int value){
        this.suit = suit;
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }
    //overide the normal print method in the object class
    @Override
    public String toString(){
        return "Card{suit: %s, name: %s,value: %d}".formatted(suit,name,value);
    }
}
