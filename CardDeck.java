import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bwgarret on 5/22/2017.
 */
public class CardDeck {
    private List<Card> deck = new ArrayList<>();
    private List<Card> cardsInHands = new ArrayList<>();

    public CardDeck(){
        for(Rank r : Rank.values()){
            for(Suit s : Suit.values())
                deck.add(new Card(s, r));
        }
    }

    public void shuffle(){
        Collections.shuffle(this.deck);
    }

    public void printDeck(){
        for (Card c : deck){
            System.out.println("TEST RANK: " + c.getRank() + " TEST SUIT: " + c.getSuit());
        }
    }

    public Card drawCard(){
        Card c = deck.remove(0);
        cardsInHands.add(c);
        return c;
    }
    public static void main(String args[]){
        CardDeck myDeck = new CardDeck();
        myDeck.printDeck();
        myDeck.shuffle();
        myDeck.printDeck();
        System.out.println("Pulled Card: " + myDeck.drawCard().getRank() + " of " + myDeck.drawCard().getSuit());
        System.out.println("Pulled Card: " + myDeck.drawCard().getRank() + " of " + myDeck.drawCard().getSuit());
        System.out.println("Pulled Card: " + myDeck.drawCard().getRank() + " of " + myDeck.drawCard().getSuit());
        System.out.println("Pulled Card: " + myDeck.drawCard().getRank() + " of " + myDeck.drawCard().getSuit());
        //myDeck.deck.forEach(System.out::println);

    }
}
