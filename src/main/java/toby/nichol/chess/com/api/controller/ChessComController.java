package toby.nichol.chess.com.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toby.nichol.chess.com.api.model.Player;

@RestController
@RequestMapping("/chess-com")
@AllArgsConstructor
public class ChessComController {

    @GetMapping("get-player")
    public ResponseEntity<Player> getChessComPlayer(){
        Player player = Player.builder().name("hikaru").build();
        return ResponseEntity.ok(player);
    }

}
