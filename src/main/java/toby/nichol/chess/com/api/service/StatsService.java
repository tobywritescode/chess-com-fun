package toby.nichol.chess.com.api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import toby.nichol.chess.com.api.model.GameStats;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

import static toby.nichol.chess.com.api.service.helper.Helper.calculateRatio;

@Service
@AllArgsConstructor
public class StatsService implements PlayerStatsService {

    private final PlayerApiService chessComService;

    public String getPlayerStatsForDate(String player, String year, String month) {
        GameStats gameStats = chessComService.getPlayerGamesForDate(player, year, month);

        Map<String, Long> wls = gameStats.getGames().stream()
                .filter(game -> game.getWhite().getUsername().equalsIgnoreCase(player))
                .collect(Collectors.groupingBy(g -> g.getWhite().getResult().equalsIgnoreCase("win") ? "win" : "loss", Collectors.counting()));

        int wins = wls.getOrDefault("win", 0L).intValue();
        int losses = wls.getOrDefault("loss", 0L).intValue();

        BigDecimal ratio  = calculateRatio(wins, losses);

        return "In the month "+month+"/"+year+ " you won " + wins + " and lost " + losses + " this is a win/loss ratio of " + ratio;

    }
}
