package com.example.CardGame.service;

import com.example.CardGame.model.Card;

public interface GameService {

    Card start(double balance);

    Card shuffle();

    String bet(double bet);
}
