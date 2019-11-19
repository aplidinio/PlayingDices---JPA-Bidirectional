package com.ITAcademy.dices.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ITAcademy.dices.DAO.IPlayerDAO;
import com.ITAcademy.dices.domains.Player;
import com.ITAcademy.dices.exceptions.PlayerNotFoundException;
import com.ITAcademy.dices.repositories.IPlayerRepository;

@Service
public class PlayerService implements IPlayerDAO <Player> {
	
	@Autowired
	private final IPlayerRepository playerRepository;
	
	public PlayerService(IPlayerRepository repository) {
		this.playerRepository = repository;
	}
	
	//Get all players and their success rate    
	@Override
	public List <Player> getAllPlayers() {
		return playerRepository.findAll();
	}

	//Create a new player		
	@Override
	public Player addPlayer(Player newPlayer) {
		newPlayer.setDateRegister(Date.valueOf(LocalDate.now()));
		newPlayer.setRateSuccess(-1);
		if (newPlayer.getName()=="") newPlayer.setName("Anonymous");
		return playerRepository.save(newPlayer);
	}

	//Get a player	
	@Override
	public Player getPlayer(Integer idPlayer) {
		return playerRepository.findById(idPlayer)
				.orElseThrow(() -> new PlayerNotFoundException(Integer.toString(idPlayer))); 
	}
	
	// Delete a player
	@Override
	public void deletePlayer (Integer idPlayer) {
		try {
			playerRepository.deleteById(idPlayer);
		} catch (EmptyResultDataAccessException e) {
			throw new PlayerNotFoundException(Integer.toString(idPlayer));
			}
	}

	//Modify a player                    
	@Override
	public Player modifyPlayer(Player modifyPlayer, Integer idPlayer) {
		return playerRepository.findById(idPlayer)
				  .map(player -> {
					  player.setName(modifyPlayer.getName());
					  return playerRepository.save(player);
					})
				  .orElseGet(() -> {
				        modifyPlayer.setIdPlayer(idPlayer);
				        return playerRepository.save(modifyPlayer);
				    });
		
	}

	//Get success average from all players   			
	@Override
	public double getSuccessAverage() {
		int totalSumSuccess = 0, counter=0;
		for (Player e : playerRepository.findAll()) {
			totalSumSuccess += e.getRateSuccess();
			counter++;
		}
		return totalSumSuccess/counter;
	}

	//Get player with highest success rate
	@Override
	public Player getTopPlayer() {
		Collection <Player> players = (Collection<Player>) getAllPlayers();
		 
		Player topPlayer = Collections.max(players, Comparator.comparing(Player::getRateSuccess));
		  		  	  
		return playerRepository.findById(topPlayer.getIdPlayer())
				.orElseThrow(() -> new PlayerNotFoundException("Fatal error"));
	}

	//Get player with lowest success rate
	@Override
	public Player getBottomPlayer() {
		double minSuccess = 100;
		int playerId = 0;
		Collection <Player> players = (Collection<Player>) getAllPlayers();
		for (Player e : players) {
			if (minSuccess > e.getRateSuccess() && e.getRateSuccess() >= 0) {
				playerId = e.getIdPlayer();	
				minSuccess = e.getRateSuccess();		
			}
		}
		return playerRepository.findById(playerId)
				.orElseThrow(() -> new PlayerNotFoundException("Fatal error"));
	}

}
