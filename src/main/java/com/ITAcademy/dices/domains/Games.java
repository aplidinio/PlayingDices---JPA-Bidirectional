package com.ITAcademy.dices.domains;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Games {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	@Id
	private int idGame;
	@ElementCollection
	private List <Integer> dices = new ArrayList <Integer>();
	private boolean result;
	@ManyToOne (targetEntity = Player.class)
	@JoinColumn (name="id_player")
	private Player player;

	public Games() {
	}
		
	public Games(ArrayList <Integer> dices, boolean result, Integer idPlayer) {
		
		this.dices = dices;
		this.result = result;
		this.player = new Player (idPlayer);
	}
	
	public int getIdGame() {
		return idGame;
	}

	public void setIdGame(int idGame) {
		this.idGame = idGame;
	}

	public List<Integer> getDices() {
		return dices;
	}

	public void setDices(List<Integer> dices) {
		this.dices = dices;
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
		
	@Override
	public String toString() {
		return "Games [idGame=" + idGame + ", dices=" + dices + ", result=" + result + ", player=" + player + "]";
	}

}
