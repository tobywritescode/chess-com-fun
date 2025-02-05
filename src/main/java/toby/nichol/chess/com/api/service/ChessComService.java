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

    public Player getPlayer(String player){
        String url = getPlayerUrl(player);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json"); //<- accept the CSV file format
        HttpEntity<String> headersEntity = new HttpEntity<>(headers);
        ResponseEntity<Player> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                headersEntity,
                Player.class
        );

        if(response.getStatusCode().value() == 200){
            return response.getBody();
        }else{
            throw new RuntimeException("Could not retrieve player.");
        }
    }

    public GameStats getPlayerGamesForDate(String player, String year, String month){
        String url = getGamesUrl(player, year, month);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json"); //<- accept the CSV file format
        HttpEntity<String> headersEntity = new HttpEntity<>(headers);
        ResponseEntity<GameStats> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                headersEntity,
                GameStats.class
        );

        if(response.getStatusCode().value() == 200){
            return response.getBody();
        }else{
            throw new RuntimeException("Could not retrieve games.");
        }
    }


    public String getPlayerStatsForDate(String player, String year, String month) {
        GameStats gameStats = getPlayerGamesForDate(player, year, month);

        Map<String, Long> wls = gameStats.getGames().stream()
                .filter(game -> game.getWhite().getUsername().equalsIgnoreCase("tobyChezz"))
                .collect(Collectors.groupingBy(g -> g.getWhite().getResult().equalsIgnoreCase("win") ? "win" : "loss", Collectors.counting()));

        int wins = wls.getOrDefault("win", 0L).intValue();
        int losses = wls.getOrDefault("loss", 0L).intValue();

        String ratio  = calculateRatio(wins, losses);

        return "In the month "+month+"/"+year+ " you won " + wins + " and lost " + losses + " this is a ratio of " + ratio;

    }
}
