package domain.user;

import domain.Result;
import domain.card.Deck;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class Participants {
    private final int INITIAL_CARDS_COUNT = 2;

    private final Players players;
    private final Dealer dealer;

    public Participants(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public static Participants from(Map<String, Integer> playerBettingAmountTable) {
        return new Participants(Players.from(playerBettingAmountTable), new Dealer());
    }

    public void drawInitialCardsEachParticipant(Deck deck) {
        drawInitialCardsBy(dealer, deck);
        players.getPlayers().forEach(player -> drawInitialCardsBy(player, deck));
    }

    private void drawInitialCardsBy(User user, Deck deck) {
        for (int i = 0; i < INITIAL_CARDS_COUNT; i++) {
            user.addCard(deck.draw());
        }
    }

    public AllWinningAmountDto calculateWinningAmountOfAllPlayers() {
        int winningAmountOfDealer= 0;
        Map<String, Integer> playerWinningAmounts = new LinkedHashMap<>();
        for (Player player : this.getPlayers()) {
            int winningAmountOfPlayer = calculateWinningAmount(player);
            playerWinningAmounts.put(player.getNameValue(), winningAmountOfPlayer);
            winningAmountOfDealer -= winningAmountOfPlayer;

        }
        return new AllWinningAmountDto(winningAmountOfDealer, playerWinningAmounts);
    }

    private int calculateWinningAmount(Player player) {
        Result result = calculateResult(player);
        return result.calculateWinningAmount(player.getBettingAmountValue());
    }

    private Result calculateResult(Player player) {
        if (player.isBust()) {
            return Result.LOSE;
        }
        if (dealer.isBust() || player.calculateScore() > dealer.calculateScore()) {
            return Result.WIN;
        }
        if (player.calculateScore() == dealer.calculateScore()) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }

    public boolean hitOrStayByDealer(Deck deck) {
        if (this.dealer.canAdd()) {
            this.dealer.addCard(deck.draw());
            return true;
        }
        return false;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
