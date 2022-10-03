package com.tictactoe.game.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tictactoe.game.dto.GameDTO;
import com.tictactoe.game.dto.StatusEnum;

@Component
@Scope("singleton")
public class GameData {
	
	private Map<String, GameDTO> data = new HashMap<>();
	
	public StatusEnum getGameStatus(String gameId) {
		return data.get(gameId).getStatus();
	}
	
	public boolean isGameExists(String gameId) {
		return data.containsKey(gameId);
	}

	public Map<String, GameDTO> getData() {
		return data;
	}

}
