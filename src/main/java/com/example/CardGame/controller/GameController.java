package com.example.CardGame.controller;

import com.example.CardGame.model.Card;
import com.example.CardGame.service.GameService;
import jakarta.validation.constraints.DecimalMin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(path = "/start{balance}")
    public ResponseEntity<Card> start(@PathVariable @DecimalMin("1") Double balance) {
        return new ResponseEntity<>(gameService.start(balance), HttpStatus.OK);
    }

    @GetMapping(path = "/shuffle")
    public ResponseEntity<Card> shuffle() {
        return new ResponseEntity<>(gameService.shuffle(), HttpStatus.OK);
    }

    @GetMapping(path = "/bet{bet}")
    public ResponseEntity<String> bet(@PathVariable Double bet) {
        return new ResponseEntity<>(gameService.bet(bet), HttpStatus.OK);
    }

}
