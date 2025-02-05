package toby.nichol.chess.com.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toby.nichol.chess.com.api.model.GameStats;
import toby.nichol.chess.com.api.service.PlayerApiService;
import toby.nichol.chess.com.api.model.Player;
import toby.nichol.chess.com.api.service.PlayerStatsService;

@RestController
@RequestMapping("/chess-com")
@AllArgsConstructor
public class ChessComController {

    private final PlayerApiService chessComService;
    private final PlayerStatsService statsService;

    @GetMapping("/get-player/{player}")
    public ResponseEntity<Player> getChessComPlayer(@PathVariable String player){
        Player playerResponse = chessComService.getPlayer(player);
        return ResponseEntity.ok(playerResponse);
    }

    @GetMapping("/get-player-games/{player}/{year}/{month}")
    public ResponseEntity<GameStats> getPlayerGames(@PathVariable String player, @PathVariable String year, @PathVariable String month){
        GameStats gameStats = chessComService.getPlayerGamesForDate(player, year, month);
        return ResponseEntity.ok(gameStats);
    }

    @GetMapping("/get-player-stats-for-date/{player}/{year}/{month}")
    public ResponseEntity<String> getPlayerStatsForDates(@PathVariable String player, @PathVariable String year, @PathVariable String month){
        String gameStats = statsService.getPlayerStatsForDate(player, year, month);
        return ResponseEntity.ok(gameStats);
    }

}
