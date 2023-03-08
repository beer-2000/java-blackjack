package domain;

import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Stack;

public class ShuffledDeck implements Deck {
    private final Stack<Card> deck = new Stack<>();

    public ShuffledDeck() {
        generateCards();
    }

    private void generateCards() {
        for (Suit suit : Suit.values()){
            addCardsWithSymbolOf(suit);
        }
        Collections.shuffle(this.deck);
    }

    private void addCardsWithSymbolOf(Suit suit){
        for (Rank rank : Rank.values()){
            this.deck.push(new Card(suit, rank));
        }
    }

    @Override
    public Card draw() {
        try {
            return this.deck.pop();
        } catch (EmptyStackException e) {
            throw new IllegalStateException("덱이 비었습니다.");
        }
    }
}
