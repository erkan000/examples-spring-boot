package com.tictactoe.game.service;

import java.util.ArrayList;

import javax.management.RuntimeErrorException;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
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
		if(gameData.isGameExists(gameId)) {
			return (BoardDTO)gameData.getData().get(gameId);
		}else {
			throw new RuntimeException("gameiddd  yokkk");
		}
		
	}

	public BoardDTO move(String gameId, @Valid GridDTO grid) {
		if(gameData.isGameExists(gameId)) {
			GameDTO game = gameData.getData().get(gameId);
			GridDTO ggg = game.getGridPosition(grid);		
			ggg.setPlayer(game.getPlayer());			
			return (BoardDTO)gameData.getData().get(gameId);
		}else {
			return null;
		}
	}

}
