package domain.user;

import domain.Card;
import domain.Hand;
import java.util.List;

abstract public class User {
    protected static final int BLACKJACK_SCORE = 21;

    protected UserName userName;
    protected Hand hand;

    abstract public boolean canAdd();

    public String getNameValue() {
        return this.userName.getValue();
    }

    public int calculateScore() {
        return this.hand.calculateScore();
    }

    public boolean isBlackjack() {
        return hand.calculateScore() == BLACKJACK_SCORE;
    }

    public void addCard(Card card) {
        if (canAdd()) {
            hand.add(card);
            return;
        }
        throw new IllegalStateException("카드 추가가 불가능하여 실행되지 않았습니다.");
    }

    public List<Card> getCards() {
        return this.hand.getCards();
    }

    public boolean isBust() {
        return this.calculateScore() > BLACKJACK_SCORE;
    }
}
