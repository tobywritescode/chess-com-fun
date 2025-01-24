package toby.nichol.chess.com.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class StreamingPlatform {
    private String type;
    private String channelUrl;
}
