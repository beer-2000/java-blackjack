public class Player {
    private static final int BLACKJACK_SCORE = 21;

    private final Name name;
    private final CardHand cardHand;

    public Player(String nameValue, CardHand cardHand) {
        this.cardHand = cardHand;
        this.name = new Name(nameValue);
    }

    public Player(String nameValue) {
        this.cardHand = new CardHand();
        this.name = new Name(nameValue);
    }

    public int calculateScore() {
        return this.cardHand.calculateScore();
    }

    public String getNameValue() {
        return this.name.getValue();
    }

    public boolean canAdd() {
        return cardHand.calculateScore() < BLACKJACK_SCORE;
    }

    public void addCard(Card card) {
        if (canAdd()) {
            this.cardHand.add(card);
            return;
        }
        throw new IllegalStateException("카드 추가가 불가능하여 실행되지 않았습니다.");
    }

    public boolean isBlackjack() {
        return cardHand.calculateScore() == BLACKJACK_SCORE;
    }
}
