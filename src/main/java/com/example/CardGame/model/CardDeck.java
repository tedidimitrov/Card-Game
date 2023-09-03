package com.example.CardGame.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Data
public class CardDeck {

    private List<Card> cards = new ArrayList<>(52);

    public CardDeck() {
        initialize();
    }

    public void initialize() {
        for(Suit suit: Suit.values()) {
            for(Rank rank: Rank.values()) {
                Card card = new Card(suit, rank);
                cards.add(card);
            }
        }
    }

    public Card draw() {
        if(!this.cards.isEmpty()) {
            return this.cards.remove(this.cards.size() - 1);
        } else {
            throw new RuntimeException("The deck ran out of cards! Please shuffle the deck again!");
        }
    }

    public void createWholeDeck(List<Card> playedCards) {
        this.cards.addAll(playedCards);
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }
}
