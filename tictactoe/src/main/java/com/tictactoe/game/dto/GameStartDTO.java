package com.tictactoe.game.dto;

import javax.validation.constraints.NotNull;

public class GameStartDTO {
	
	@NotNull
	private PlayerEnum playerToken;

	public PlayerEnum getPlayerToken() {
		return playerToken;
	}

	public void setPlayerToken(PlayerEnum playerToken) {
		this.playerToken = playerToken;
	}

}
