package toby.nichol.chess.com.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
    public static String getPlayerUrl(String player){

        return "https://api.chess.com/pub/player/"+player;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
