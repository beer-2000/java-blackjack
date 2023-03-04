package controller;

import domain.BlackjackGame;
import domain.user.Player;
import ui.InputView;
import ui.OutputView;

public class BlackjackController {
    public void run() {
        BlackjackGame blackjackGame = new BlackjackGame(InputView.readPlayersName());
        blackjackGame.initializeGame();
        OutputView.printCardsStatus(blackjackGame.getDealer(), blackjackGame.getPlayers());
        blackjackGame.getPlayers().forEach(player -> giveCardUntilImpossible(player, blackjackGame));
        addCardToDealerIfPossible(blackjackGame);
        OutputView.printCardsStatusWithScore(blackjackGame.getDealer(), blackjackGame.getPlayers());
        OutputView.printResults(blackjackGame.calculateAllResults());
    }

    private void addCardToDealerIfPossible(BlackjackGame blackjackGame) {
        if (blackjackGame.getDealer().canAdd()) {
            OutputView.announceAddCardToDealer();
            blackjackGame.addCardToDealerIfPossible();
        }
    }

    private void giveCardUntilImpossible(Player player, BlackjackGame blackjackGame) {
        while (judgeWhetherDrawCard(player)){
            blackjackGame.addCardTo(player);
            OutputView.printCardsStatusOfUser(player);
        }
        if (player.canAdd()) {
            OutputView.printCardsStatusOfUser(player);
        }
    }

    private static boolean judgeWhetherDrawCard(Player player) {
        return player.canAdd() && InputView.readWhetherDrawCardOrNot(player);
    }
}
