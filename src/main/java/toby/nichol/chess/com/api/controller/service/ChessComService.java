package toby.nichol.chess.com.api.controller.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import toby.nichol.chess.com.api.model.Player;

import static toby.nichol.chess.com.api.config.Config.getPlayerUrl;

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

        if(response.getStatusCode().value() == 200){ //<- if 200 response we can get the statement as a string.
            return response.getBody();
        }else{
            throw new RuntimeException("Could not retrieve statement.");
        }
    }


}
