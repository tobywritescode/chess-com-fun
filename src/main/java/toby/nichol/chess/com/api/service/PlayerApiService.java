package toby.nichol.chess.com.api.service;

import toby.nichol.chess.com.api.model.GameStats;
import toby.nichol.chess.com.api.model.Player;

public interface PlayerApiService {

    Player  getPlayer(String player);

    GameStats getPlayerGamesForDate(String player, String year, String month);
}
