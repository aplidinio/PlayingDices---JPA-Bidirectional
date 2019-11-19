package com.ITAcademy.dices.DAO;

import java.util.List;

import com.ITAcademy.dices.domains.Player;

public interface IPlayerDAO <T> {
	
	// Get all players and their success rate    
	public List <T> getAllPlayers();
		
	// Get a player						
	public Player getPlayer(Integer idPlayer);
	
	// Create a new player				
	public Player addPlayer(Player newPlayer); //it could be also public Object <T> newPlayer
	
	// Delete a player                                
	public void deletePlayer(Integer idPlayer);
	
	// Modify a player                    
	public Player modifyPlayer(Player modifyPlayer, Integer idPlayer);
	
	// Get success average from all players   			
	public double getSuccessAverage();
	
	// Get player with highest success rate
	public Player getTopPlayer();
		
	// Get player with lowest success rate
	public Player getBottomPlayer();

}
