package nju.util;

/**
 * Created by Srf on 2017/3/17
 */
public class AccountFilter {

    public static String filteStatus(String status) {
        if(status!=null)
            switch (status) {
                case "apply":
                    return "结算中";
                case "approve":
                    return "已结算";
                default:
                    return "未知";
            }
        else
            return "无状态";
    }

    public static String filteType(String type) {
        if(type!=null)
            switch (type) {
                case "recharge":
                    return "会员卡充值";
                case "appointment":
                    return "预约付款";
                case "cancel":
                    return "取消预约退款";
                case "settle":
                    return "预约入住转账";
                case "cardpay":
                    return "会员卡付款";
                case "cash":
                    return "现金支付";
                case "score":
                    return "积分兑换";
                default:
                    return "未知";
            }
        else
            return "未知";
    }

}
