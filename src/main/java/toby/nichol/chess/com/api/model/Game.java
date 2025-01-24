package toby.nichol.chess.com.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Game {
    private String url;
    private String pgn;
    private String time_control;
    private Long end_time;
    private Boolean rated;
    private String tcn;
    private String uuid;
    private String initial_setup;
    private String fen;
    private String time_class;
    private String rules;
    private PlayerGame white;
    private PlayerGame black;
}