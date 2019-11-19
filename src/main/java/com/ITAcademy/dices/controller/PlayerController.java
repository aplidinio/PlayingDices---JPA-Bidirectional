package com.ITAcademy.dices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ITAcademy.dices.DAO.IPlayerDAO;
import com.ITAcademy.dices.domains.Player;
import com.ITAcademy.dices.service.PlayerService;

@RestController
@RequestMapping("/dices")
public class PlayerController{
	
	@Autowired
	private IPlayerDAO <Player> playerService;
	
	//to test service
	@GetMapping
	public String check() {
		return "You are in Players section!";
	}
	  	 	
	//Get all players 
	@GetMapping("/players")
	public List <Player> getAllPlayers() {
		return playerService.getAllPlayers();
	}
	  	 	  
	//Create a new player				
	@PostMapping("/player")
	public Player newPlayer(@RequestBody Player newPlayer) {
		return playerService.addPlayer(newPlayer);
	}
	  
	//Get a player						
	@GetMapping("/player/{idPlayer}")
	public Player getPlayer(@PathVariable Integer idPlayer) {
		return playerService.getPlayer(idPlayer);
	}
	  
	//Delete a player                                
	@DeleteMapping("/player/{idPlayer}")
	public void deletePlayer(@PathVariable Integer idPlayer) {
		playerService.deletePlayer(idPlayer);
	} 
	  
	//Modify a player                    
	@PutMapping("/player/{idPlayer}")
	public Player modifyPlayer(@RequestBody Player modifyPlayer, @PathVariable Integer idPlayer) {
		return playerService.modifyPlayer(modifyPlayer, idPlayer);
	}
	
	//Get success average from all players   			
	@GetMapping("/players/ranking")
	public double getSuccessAverage(){
		return playerService.getSuccessAverage();
	}
	
	//Get player with highest success rate
	@GetMapping("/players/ranking/winner")
	public Player getTopPlayer() {
		return playerService.getTopPlayer();		  			
	}
	
	//Get player with lowest success rate
	@GetMapping("/players/ranking/loser")
	public Player getBottomPlayer() {
		return playerService.getBottomPlayer();		  			
	}
	
}
