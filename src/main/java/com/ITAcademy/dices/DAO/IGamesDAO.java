package com.ITAcademy.dices.DAO;

import java.util.List;

import com.ITAcademy.dices.domains.Games;

public interface IGamesDAO <T> {
		 	 	
	// List all games
	public List <T> getAllGames();
	
	// List all games from single player
	public List <T> getPlayerGames(Integer idPlayer);
	 	
	// Add game
	public Games gambleDices(Integer idPlayer, int numberDices);
			
	// Delete all games from a player
	public void deleteGames(Integer idPlayer);
		
	// Calculate player Success
	public double calculatePlayerSuccess(Integer idPlayer);
	
	// Set Player Success
	public void setPlayerSuccess (Integer playerIdPlayer, double success);

}
