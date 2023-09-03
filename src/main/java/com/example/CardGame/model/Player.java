package com.example.CardGame.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class Player {

    private double balance;
    private Card playerCard;

    public boolean hasValidBalance(double bet) {
        return this.balance >= bet;
    }

    public boolean doesWin(Card card) {
        return this.playerCard.getCardStrength() > card.getCardStrength();
    }

    public boolean doesLose(Card card) {
        return this.playerCard.getCardStrength() < card.getCardStrength();
    }

    public void doubleBet(double bet) {
        this.balance += bet * 2;
    }

    public void subtractBet(double bet) {
        this.balance -= bet;
    }

    public void returnBet(double bet) {
        this.balance += bet;
    }

}
