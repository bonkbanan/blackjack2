package com.example.demo.controller.ui;

import com.example.demo.model.Card;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/ui/board")
public class BoardUIController {

    @Autowired
    BoardService service;

    @GetMapping("/all")
    String getBoard(Model model){
        List<Card> cards = service.getBoard().getDeck();
        List<Card> myCards = service.getBoard().getGamerCards();
        List<Card> computerCards = service.getBoard().getDealerCards();
        model.addAttribute("computerCards",computerCards);
        model.addAttribute("cards",cards);
        model.addAttribute("gamerCards",myCards);
        model.addAttribute("message",service.getBoard().getMessage());
        return "board";
    }

    @GetMapping("/all/shuffle")
    String getShuffle(Model model){
        List<Card> cards = service.getBoard().getDeck();
        List<Card> myCards = service.getBoard().getGamerCards();
        Collections.shuffle(cards);
        List<Card> computerCards = service.getBoard().getDealerCards();
        model.addAttribute("computerCards",computerCards);
        model.addAttribute("cards",cards);
        model.addAttribute("gamerCards",myCards);
        model.addAttribute("message",service.getBoard().getMessage());
        return "board";
    }

    @GetMapping("/all/give/me")
    String getLastCard(Model model){
        service.giveCardToGamer();
        List<Card> cards = service.getBoard().getDeck();
        List<Card> myCards = service.getBoard().getGamerCards();
        List<Card> computerCards = service.getBoard().getDealerCards();
        model.addAttribute("computerCards",computerCards);
        model.addAttribute("cards",cards);
        model.addAttribute("gamerCards",myCards);
        model.addAttribute("message",service.getBoard().getMessage());
        return "board";
    }

    @GetMapping("/all/new/game")
    String getNewBoard(Model model){
        service.createNewGame();
        List<Card> cards = service.getBoard().getDeck();
        List<Card> myCards = service.getBoard().getGamerCards();
        List<Card> computerCards = service.getBoard().getDealerCards();
        model.addAttribute("computerCards",computerCards);
        model.addAttribute("cards",cards);
        model.addAttribute("gamerCards",myCards);
        model.addAttribute("message",service.getBoard().getMessage());
        return "board";
    }
    @GetMapping("/all/stop")
    String Stop(Model model){
        List<Card> cards = service.getBoard().getDeck();
        List<Card> myCards = service.getBoard().getGamerCards();
        service.getBoard().setTurn(false);
        service.giveCardToComputer();
        List<Card> computerCards = service.getBoard().getDealerCards();
        service.createMessage();
        model.addAttribute("computerCards",computerCards);
        model.addAttribute("cards",cards);
        model.addAttribute("gamerCards",myCards);
        model.addAttribute("message",service.getBoard().getMessage());
        return "board";
    }
}