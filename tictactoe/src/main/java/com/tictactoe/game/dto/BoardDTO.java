package com.tictactoe.game.dto;

import java.util.List;

public class BoardDTO {
	
	private String gameId;
	private StatusEnum status;
	private List<GridDTO> grid;
	
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	public List<GridDTO> getGrid() {
		return grid;
	}
	public void setGrid(List<GridDTO> grid) {
		this.grid = grid;
	}


}
