package com.ITAcademy.dices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ITAcademy.dices.DAO.IGamesDAO;
import com.ITAcademy.dices.domains.Games;
import com.ITAcademy.dices.service.GamesService;

@RestController
@RequestMapping ("/dices/games")
public class GamesController {
	
	@Autowired
	private IGamesDAO <Games> gamesService;
	
	//to test service
	@GetMapping
	public String check() {
		return "You are in Games section!";
	}
	
	//Get all players 
	@GetMapping("/allgames")
	public List <Games> getAllPlayers() {
		return gamesService.getAllGames();
	}
	
	//Get all games from single player
	@GetMapping("/{playerIdPlayer}/allgames")
	public List <Games> getPlayerGames(@PathVariable Integer playerIdPlayer){  
		return gamesService.getPlayerGames(playerIdPlayer);	
	}
	
	//Making 2 dices game
	@PostMapping("/{playerIdPlayer}/twodices")
	public Games gambleTwoDices(@PathVariable Integer playerIdPlayer) {
		return gamesService.gambleDices(playerIdPlayer, 2);
	}
		
	//Making 6 dices game
	@PostMapping("/{playerIdPlayer}/sixdices")
	public Games gambleSixDices(@PathVariable Integer playerIdPlayer) {
		return gamesService.gambleDices(playerIdPlayer, 6);
	}
	
	//Delete all games from a player
	@DeleteMapping("/{playerIdPlayer}")
	public void deleteGames(@PathVariable Integer playerIdPlayer) {
		gamesService.deleteGames(playerIdPlayer);
	}
	
}
