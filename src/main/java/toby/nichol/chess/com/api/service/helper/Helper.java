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

    public static String calculateRatio(int num1, int num2) {
        if (num2 == 0) {
            return "Undefined (division by zero)";
        }

        BigDecimal a = new BigDecimal(num1);
        BigDecimal b = new BigDecimal(num2);

        BigDecimal ratio = a.divide(b,2, RoundingMode.HALF_UP); // Calculate ratio with 2 decimal places


        return String.valueOf(ratio); // Format as 1:x
    }

}
