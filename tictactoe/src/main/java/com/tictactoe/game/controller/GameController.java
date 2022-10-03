package com.tictactoe.game.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tictactoe.game.dto.BoardDTO;
import com.tictactoe.game.dto.GameStartDTO;
import com.tictactoe.game.dto.GridDTO;
import com.tictactoe.game.service.GameService;

@RestController
@RequestMapping("/tictactoe")
public class GameController {
	
	@Autowired
	private GameService gameService;
	
	@PostMapping("/game")
	public BoardDTO startGame(@RequestBody @Valid GameStartDTO gameStartDTO) {
		return gameService.startGame(gameStartDTO.getPlayerToken());
	}
	
	@GetMapping("/game/{gameId}")
	public BoardDTO getBoard(@PathVariable String gameId) {
		return gameService.getBoard(gameId);
	}
	
	@PostMapping("/game/{gameId}/move")
	public BoardDTO move(@PathVariable String gameId, @RequestBody @Valid GridDTO grid) {
		return gameService.move(gameId, grid);
	}
}
