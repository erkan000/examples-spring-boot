package com.tictactoe.game.service;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tictactoe.game.dto.BoardDTO;
import com.tictactoe.game.dto.GameDTO;
import com.tictactoe.game.dto.GridDTO;
import com.tictactoe.game.dto.PlayerEnum;
import com.tictactoe.game.dto.StatusEnum;

@Component
public class GameService {
	
	@Autowired private GameData gameData;
	
	private final static int gameIdLenght = 5;

	public BoardDTO startGame(PlayerEnum playerToken) {
		GameDTO board = new GameDTO();
		board.setPlayer(playerToken);
		board.setGameId(RandomStringUtils.randomAlphanumeric(gameIdLenght));
		board.setStatus(StatusEnum.OPEN);		
		board.setGrid(GameDTO.createInitialBoard());		
		gameData.getData().put(board.getGameId(), board);
		return board;
	}

	public BoardDTO getBoard(String gameId) {
		GameDTO game = validateGame(gameId);
		return game;
		
	}

	public BoardDTO move(String gameId, @Valid GridDTO grid) {
		GameDTO game = validateGame(gameId);
		if(game.getStatus() != StatusEnum.OPEN) {
			throw new RuntimeException(gameId + " game is finished. Result is:" + game.getStatus());
		}
		processMove(grid, game);
		return game;
	}

	private void processMove(GridDTO grid, GameDTO game) {
		GridDTO ggg = game.getGridPosition(grid);
		if(ggg.getPlayer() != null) {
			throw new RuntimeException("Can not move, filled with another player.");
		}
		ggg.setPlayer(game.getPlayer());
		checkGameStatus(game);
		
		if(game.getStatus() == StatusEnum.OPEN) {
			moveComputer(game);
			checkGameStatus(game);
		}
	}
	
	private void moveComputer(GameDTO game) {
		int move;
		do {
			move = RandomUtils.nextInt(0, 9);
		} while (game.getGrid().get(move).getPlayer() != null);
			
		if(game.getPlayer() == PlayerEnum.O) {
			game.getGrid().get(move).setPlayer(PlayerEnum.X);
		}else {
			game.getGrid().get(move).setPlayer(PlayerEnum.O);
		}		
	}

	private void checkGameStatus(GameDTO game) {
		List<GridDTO> grid = game.getGrid();
		if(grid.get(0).getPlayer() == PlayerEnum.O && grid.get(1).getPlayer() == PlayerEnum.O  && grid.get(2).getPlayer() == PlayerEnum.O) {
			game.setStatus(StatusEnum.PLAYER_O_WON);
		}else if(grid.get(3).getPlayer() == PlayerEnum.O && grid.get(4).getPlayer() == PlayerEnum.O  && grid.get(5).getPlayer() == PlayerEnum.O) {
			game.setStatus(StatusEnum.PLAYER_O_WON);
		}else if(grid.get(6).getPlayer() == PlayerEnum.O && grid.get(7).getPlayer() == PlayerEnum.O  && grid.get(8).getPlayer() == PlayerEnum.O) {
			game.setStatus(StatusEnum.PLAYER_O_WON);
		}else if(grid.get(0).getPlayer() == PlayerEnum.O && grid.get(3).getPlayer() == PlayerEnum.O  && grid.get(6).getPlayer() == PlayerEnum.O) {
			game.setStatus(StatusEnum.PLAYER_O_WON);
		}else if(grid.get(1).getPlayer() == PlayerEnum.O && grid.get(4).getPlayer() == PlayerEnum.O  && grid.get(7).getPlayer() == PlayerEnum.O) {
			game.setStatus(StatusEnum.PLAYER_O_WON);
		}else if(grid.get(2).getPlayer() == PlayerEnum.O && grid.get(5).getPlayer() == PlayerEnum.O  && grid.get(8).getPlayer() == PlayerEnum.O) {
			game.setStatus(StatusEnum.PLAYER_O_WON);
		}else if(grid.get(0).getPlayer() == PlayerEnum.O && grid.get(4).getPlayer() == PlayerEnum.O  && grid.get(8).getPlayer() == PlayerEnum.O) {
			game.setStatus(StatusEnum.PLAYER_O_WON);
		}else if(grid.get(2).getPlayer() == PlayerEnum.O && grid.get(4).getPlayer() == PlayerEnum.O  && grid.get(6).getPlayer() == PlayerEnum.O) {
			game.setStatus(StatusEnum.PLAYER_O_WON);
		}
		if(grid.get(0).getPlayer() == PlayerEnum.X && grid.get(1).getPlayer() == PlayerEnum.X  && grid.get(2).getPlayer() == PlayerEnum.X) {
			game.setStatus(StatusEnum.PLAYER_X_WON);
		}else if(grid.get(3).getPlayer() == PlayerEnum.X && grid.get(4).getPlayer() == PlayerEnum.X  && grid.get(5).getPlayer() == PlayerEnum.X) {
			game.setStatus(StatusEnum.PLAYER_X_WON);
		}else if(grid.get(6).getPlayer() == PlayerEnum.X && grid.get(7).getPlayer() == PlayerEnum.X  && grid.get(8).getPlayer() == PlayerEnum.X) {
			game.setStatus(StatusEnum.PLAYER_X_WON);
		}else if(grid.get(0).getPlayer() == PlayerEnum.X && grid.get(3).getPlayer() == PlayerEnum.X  && grid.get(6).getPlayer() == PlayerEnum.X) {
			game.setStatus(StatusEnum.PLAYER_X_WON);
		}else if(grid.get(1).getPlayer() == PlayerEnum.X && grid.get(4).getPlayer() == PlayerEnum.X  && grid.get(7).getPlayer() == PlayerEnum.X) {
			game.setStatus(StatusEnum.PLAYER_X_WON);
		}else if(grid.get(2).getPlayer() == PlayerEnum.X && grid.get(5).getPlayer() == PlayerEnum.X  && grid.get(8).getPlayer() == PlayerEnum.X) {
			game.setStatus(StatusEnum.PLAYER_X_WON);
		}else if(grid.get(0).getPlayer() == PlayerEnum.X && grid.get(4).getPlayer() == PlayerEnum.X  && grid.get(8).getPlayer() == PlayerEnum.X) {
			game.setStatus(StatusEnum.PLAYER_X_WON);
		}else if(grid.get(2).getPlayer() == PlayerEnum.X && grid.get(4).getPlayer() == PlayerEnum.X  && grid.get(6).getPlayer() == PlayerEnum.X) {
			game.setStatus(StatusEnum.PLAYER_X_WON);
		}
		if(game.getStatus() == StatusEnum.OPEN) {
			boolean hasEmptyCell = false;
			for (GridDTO gridDTO : grid) {
				if(gridDTO.getPlayer() == null) {
					hasEmptyCell = true;
					break;
				}
			}
			if(!hasEmptyCell) {
				game.setStatus(StatusEnum.TIE);
			}
		}
	}

	private GameDTO validateGame(String gameId) {
		if(!gameData.isGameExists(gameId)) {
			throw new RuntimeException("There is no game with ID:" + gameId);
		}
		return gameData.getData().get(gameId);
	}

}
