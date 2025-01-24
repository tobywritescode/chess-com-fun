package toby.nichol.chess.com.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerGame {
    private Integer rating;
    private String result;
    @JsonProperty("@id")
    private String id;
    private String username;
    private String uuid;
}
