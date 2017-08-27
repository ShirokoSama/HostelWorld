package nju.util;

/**
 * Created by Srf on 2017/3/17
 */

public class PayTypeFilter {

    public static String chToEn(String type) {
        switch (type) {
            case "预约支付":
                return "plan";
            case "会员卡支付":
                return "card";
            case "现金支付":
                return "cash";
            default:
                return "";
        }
    }

    public static String enToCh(String type) {
        switch (type) {
            case "plan":
                return "预约支付";
            case "card":
                return "会员卡支付";
            case "cash":
                return "现金支付";
            default:
                return "";
        }
    }

}
