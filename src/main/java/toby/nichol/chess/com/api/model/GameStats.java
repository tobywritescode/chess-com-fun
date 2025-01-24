package toby.nichol.chess.com.api.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GameStats {
    private List<Game> games;
}
