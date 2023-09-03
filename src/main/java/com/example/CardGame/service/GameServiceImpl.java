package com.example.CardGame.service;


import com.example.CardGame.model.Card;
import com.example.CardGame.model.CardDeck;
import com.example.CardGame.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService{

    private CardDeck cardDeck;
    private List<Card> playedCards;
    private Player player;

    public static final String DRAW_MESSAGE = "You have drawn %s of %s";
    public static final String BALANCE_MESSAGE = "Your current balance is %s" ;

    @Autowired
    GameServiceImpl(CardDeck cardDeck, List<Card> playedCards, Player player) {
        this.cardDeck = cardDeck;
        this.playedCards = playedCards;
        this.player = player;
    }

    @Override
    public Card start(double balance) {
        this.player.setBalance(balance);
        return shuffle();
    }

    @Override
    public Card shuffle() {
        resetGame();
        return this.player.getPlayerCard();
    }

    @Override
    public String bet(double bet) {
        if(this.player.hasValidBalance(bet)) {
            return playGame(bet);
        } else {
            return "Your balance is not enough to cover this bet! Please deposit more money and start again!";
        }
    }

    private String playGame(double bet) {
        String result;
        this.player.subtractBet(bet);
        Card drawnCard = this.cardDeck.draw();
        this.playedCards.add(drawnCard);
        if (this.player.doesWin(drawnCard)) {
            this.player.doubleBet(bet);
            result = String.format(DRAW_MESSAGE, drawnCard.suit(), drawnCard.rank()) + ". You won! Your bet has doubled! " +
                    String.format(BALANCE_MESSAGE, this.player.getBalance());
        } else if (this.player.doesLose(drawnCard)) {
            result = String.format(DRAW_MESSAGE, drawnCard.suit(), drawnCard.rank()) + ". You lost the bet! " +
                    String.format(BALANCE_MESSAGE, this.player.getBalance());
        } else {
            this.player.returnBet(bet);
            result = String.format(DRAW_MESSAGE, drawnCard.suit(), drawnCard.rank()) + ". You have a push. The games goes on! " +
                     String.format(BALANCE_MESSAGE, this.player.getBalance());
        }
        return result;
    }

    private void resetGame() {
        this.cardDeck.createWholeDeck(this.playedCards);
        this.cardDeck.shuffleDeck();
        this.player.setPlayerCard(this.cardDeck.draw());
        this.playedCards.add(this.player.getPlayerCard());
    }
}
