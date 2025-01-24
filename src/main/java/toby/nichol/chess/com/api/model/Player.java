package toby.nichol.chess.com.api.model;

import lombok.*;

import java.util.List;

@Data
@Builder
public class Player {
    private String avatar;
    private Long playerId;
    private String id;
    private String url;
    private String name;
    private String username;
    private String title;
    private Long followers;
    private String country;
    private String location;
    private Long lastOnline;
    private Long joined;
    private String status;
    private boolean isStreamer;
    private String twitchUrl;
    private boolean verified;
    private String league;
    private List<StreamingPlatform> streamingPlatforms;
}
