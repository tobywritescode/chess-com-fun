package toby.nichol.chess.com.api.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import toby.nichol.chess.com.api.model.GameStats;
import toby.nichol.chess.com.api.model.Player;

import java.util.Map;
import java.util.stream.Collectors;

import static toby.nichol.chess.com.api.service.helper.Helper.*;


@Service
@AllArgsConstructor
public class ChessComService {

    private final RestTemplate restTemplate;

    /**
     * Retrieves the player stats.
     * @param player String
     * @return ResponseEntity<Player>
     */
    public ResponseEntity<Player> getPlayer(String player) {
        String url = getPlayerUrl(player);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json"); //<- accept the CSV file format
        HttpEntity<String> headersEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                headersEntity,
                Player.class
        );
    }

    /**
     * Retrieves the player stats for a given date.
     * @param player String
     * @param year String
     * @param month String
     * @return ResponseEntity<GameStats>
     */
    public ResponseEntity<GameStats> getPlayerGamesForDate(String player, String year, String month) {
        String url = getGamesUrl(player, year, month);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json"); //<- accept the CSV file format
        HttpEntity<String> headersEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                headersEntity,
                GameStats.class
        );
    }

    public ResponseEntity<String> getPlayerStatsForDate(String player, String year, String month) {
        ResponseEntity<GameStats> gameStatsResponse = getPlayerGamesForDate(player, year, month);
        if (gameStatsResponse.getBody() == null || gameStatsResponse.getBody().getGames() == null) {
            return ResponseEntity.notFound().build();
        }
        Map<String, Long> wls = gameStatsResponse.getBody().getGames().stream()
                .filter(game -> game.getWhite().getUsername().equalsIgnoreCase("tobyChezz"))
                .collect(Collectors.groupingBy(g -> g.getWhite().getResult().equalsIgnoreCase("win") ? "win" : "loss", Collectors.counting()));

        int wins = wls.getOrDefault("win", 0L).intValue();
        int losses = wls.getOrDefault("loss", 0L).intValue();

        String ratio = calculateRatio(wins, losses);

        return ResponseEntity.ok().body("In the month " + month + "/" + year + " you won " + wins + " and lost " + losses + " this is a ratio of " + ratio);
    }
}
