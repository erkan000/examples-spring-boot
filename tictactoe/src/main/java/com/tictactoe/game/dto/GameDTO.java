package com.tictactoe.game.dto;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

public class GameDTO extends BoardDTO{
	
	private PlayerEnum player;
	
	public GridDTO getGridPosition(GridDTO grid) {
		GridDTO position = null;
		for (int i = 0; i < this.getGrid().size(); i++) {
			GridDTO gridDTO = this.getGrid().get(i);
			if(gridDTO.getRow() == grid.getRow() && gridDTO.getCol() == grid.getCol()) {
				position = gridDTO;
				break;
			}
		}
		return position;
	}
	
	public static List<GridDTO> createInitialBoard(){
		List<GridDTO> gridList = new ArrayList<>();
		gridList.add(new GridDTO(0,0));
		gridList.add(new GridDTO(0,1));
		gridList.add(new GridDTO(0,2));
		gridList.add(new GridDTO(1,0));
		gridList.add(new GridDTO(1,1));
		gridList.add(new GridDTO(1,2));
		gridList.add(new GridDTO(2,0));
		gridList.add(new GridDTO(2,1));
		gridList.add(new GridDTO(2,2));
		return gridList;
	}

	@Transient
	public PlayerEnum getPlayer() {
		return player;
	}

	public void setPlayer(PlayerEnum player) {
		this.player = player;
	}
	
}
