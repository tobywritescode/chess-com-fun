package toby.nichol.chess.com.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toby.nichol.chess.com.api.controller.service.ChessComService;
import toby.nichol.chess.com.api.model.Player;

@RestController
@RequestMapping("/chess-com")
@AllArgsConstructor
public class ChessComController {

    private final ChessComService chessComService;

    @GetMapping("/get-player/{player}")
    public ResponseEntity<Player> getChessComPlayer(@PathVariable String player){
        Player playerResponse = chessComService.getPlayer(player);
        return ResponseEntity.ok(playerResponse);
    }

}
