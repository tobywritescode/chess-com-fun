package toby.nichol.chess.com.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import toby.nichol.chess.com.api.model.GameStats;
import toby.nichol.chess.com.api.service.ChessComService;
import toby.nichol.chess.com.api.model.Player;

@Slf4j
@RestController
@RequestMapping("/chess-com")
@AllArgsConstructor
public class ChessComController {

    static final String INCORRECT_REQUEST = "Your request is incorrect.";
    static final String NO_GAMES_FOUND = "No games found.";
    static final String USERNAME_NOT_FOUND = "Username not found.";
    static final String NO_GAME_STATS_FOUND = "No game stats found.";
    private final ChessComService chessComService;

    /**
     * Takes the player name as a variable and retrieves data for the player as shown in
     * @<a href="https://www.chess.com/news/view/published-data-api#pubapi-endpoint-games">...</a>
     * When a HttpClientException is called it processes the status to give the correct response.
     *
     * @param player String
     * @return responseEntity
     */
    @GetMapping("/get-player/{player}")
    public ResponseEntity getChessComPlayer(@PathVariable String player) {
        ResponseEntity<Player> playerResponse;
        try {
            playerResponse = chessComService.getPlayer(player);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 404) {
                log.info(USERNAME_NOT_FOUND);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USERNAME_NOT_FOUND);
            } else if (e.getStatusCode().value() == 400) {
                log.info(INCORRECT_REQUEST);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INCORRECT_REQUEST);
            } else {
                log.info("Call to get-player failed.");
                return ResponseEntity.internalServerError().build();
            }
        }
        return playerResponse;
    }

    /**
     * Takes the player name and date as variables and retrieves data for the player as shown in
     * @<a href="https://www.chess.com/news/view/published-data-api#pubapi-endpoint-games">...</a>
     * When a HttpClientException is called it processes the status to give the correct response.
     * @param player String
     * @param year String (YYYY)
     * @param month String (MM)
     * @return ResponseEntity
     */
    @GetMapping("/get-player-games/{player}/{year}/{month}")
    public ResponseEntity getPlayerGames(@PathVariable String player, @PathVariable String year,
                                         @PathVariable String month) {
        ResponseEntity<GameStats> gameStatsResponse;
        try {
            gameStatsResponse = chessComService.getPlayerGamesForDate(player, year, month);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 404) {
                log.info(NO_GAMES_FOUND);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NO_GAMES_FOUND);
            } else if (e.getStatusCode().value() == 400) {
                log.info(INCORRECT_REQUEST);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INCORRECT_REQUEST);
            } else {
                log.info("Call to get-player-games failed.");
                return ResponseEntity.internalServerError().build();
            }
        }
        return ResponseEntity.ok(gameStatsResponse);
    }

    /**
     * Takes the player name and date as variables and retrieves data for the player as shown in
     * @<a href="https://www.chess.com/news/view/published-data-api#pubapi-endpoint-games">...</a>
     * filtering by date. When a HttpClientException is called it processes the status to give the correct response.
     * @param player String
     * @param year String (YYYY)
     * @param month String (MM)
     * @return ResponseEntity
     */
    @GetMapping("/get-player-stats-for-date/{player}/{year}/{month}")
    public ResponseEntity<String> getPlayerStatsForDates(@PathVariable String player, @PathVariable String year,
                                                         @PathVariable String month) {
        ResponseEntity<String> gameStatsResponse;
        try {
            gameStatsResponse = chessComService.getPlayerStatsForDate(player, year, month);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 404) {
                log.info(NO_GAME_STATS_FOUND);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NO_GAME_STATS_FOUND);
            } else if (e.getStatusCode().value() == 400) {
                log.info(INCORRECT_REQUEST);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INCORRECT_REQUEST);
            } else {
                log.info("Call to get-player-stats-for-date failed.");
                return ResponseEntity.internalServerError().build();
            }
        }
        return gameStatsResponse;
    }
}
