package com.tictactoe.game.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class GridDTO {
	
	@Min(0)
	@Max(2)
	private int row;
	
	@Min(0)
	@Max(2)
	private int col;
	
	private PlayerEnum player;
	
	public GridDTO() {
		super();
	}
	
	public GridDTO(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public PlayerEnum getPlayer() {
		return player;
	}
	public void setPlayer(PlayerEnum player) {
		this.player = player;
	}

}
