package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Card;
import domain.CardHand;
import domain.CardNumber;
import domain.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    @Test
    @DisplayName("점수를 계산한다.")
    void 점수_계산() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(Symbol.SPADE, CardNumber.TWO));
        cardHand.add(new Card(Symbol.CLOVER, CardNumber.KING));
        Dealer dealer = new Dealer(cardHand);
        assertThat(dealer.calculateScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("이름을 반환한다.")
    void 이름_반환() {
        String nameValue = "딜러";
        Dealer dealer = new Dealer();
        assertThat(dealer.getNameValue()).isEqualTo(nameValue);
    }

    @Test
    @DisplayName("카드를 더 받을 수 있는 경우 true를 반환한다.")
    void 카드_추가_가능() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(Symbol.SPADE, CardNumber.TWO));
        cardHand.add(new Card(Symbol.CLOVER, CardNumber.KING));
        Dealer dealer = new Dealer(cardHand);
        assertThat(dealer.canAdd()).isTrue();
    }

    @Test
    @DisplayName("카드를 더 받을 수 없는 경우 false를 반환한다.")
    void 카드_추가_불가능() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(Symbol.SPADE, CardNumber.SEVEN));
        cardHand.add(new Card(Symbol.CLOVER, CardNumber.KING));
        Dealer dealer = new Dealer(cardHand);
        assertThat(dealer.canAdd()).isFalse();
    }

    @Test
    @DisplayName("카드를 저장한다.")
    void 카드_저장() {
        Dealer dealer = new Dealer();
        assertThat(dealer.calculateScore()).isEqualTo(0);
        dealer.addCard(new Card(Symbol.SPADE, CardNumber.TWO));
        assertThat(dealer.calculateScore()).isEqualTo(2);
    }
}