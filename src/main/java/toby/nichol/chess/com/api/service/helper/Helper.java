package toby.nichol.chess.com.api.service.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Helper {

    public static String getPlayerUrl(String player){

        return "https://api.chess.com/pub/player/"+player;
    }

    public static String getGamesUrl(String player, String year, String month){
        return "https://api.chess.com/pub/player/"+player+"/games/"+year+"/"+month;
    }

    public static BigDecimal calculateRatio(int num1, int num2) {
        if (num2 == 0) {
            throw new RuntimeException("");
        }

        BigDecimal a = new BigDecimal(num1);
        BigDecimal b = new BigDecimal(num2);


        return a.divide(b,2, RoundingMode.HALF_UP);
    }

}
