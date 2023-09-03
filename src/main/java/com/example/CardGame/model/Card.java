package com.example.CardGame.model;


public record Card(Suit suit, Rank rank) {

    public int getCardStrength() {
        return this.rank.getStrength();
    }
}
