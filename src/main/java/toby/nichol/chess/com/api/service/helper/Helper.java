package toby.nichol.chess.com.api.service.helper;

public class Helper {

    public static String getPlayerUrl(String player){

        return "https://api.chess.com/pub/player/"+player;
    }

    public static String getGamesUrl(String player, String year, String month){
        return "https://api.chess.com/pub/player/"+player+"/games/"+year+"/"+month;
    }

}
