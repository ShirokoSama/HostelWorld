package nju.util;

/**
 * Created by Srf on 2017/3/17
 */

public class RoomTypeFilter {

    public static String enToCh(String type) {
        switch (type) {
            case "single":
                return "单人间";
            case "double":
                return "双人间";
            case "flat":
                return "套间";
            case "deluxe":
                return "豪华间";
            default:
                return "";
        }
    }

    public static String chToEn(String type) {
        switch (type) {
            case "单人间":
                return "single";
            case "双人间":
                return "double";
            case "套间":
                return "flat";
            case "豪华间":
                return "deluxe";
            default:
                return "";
        }
    }

}
