package nju.vo;

import nju.entity.Account;
import nju.util.AccountFilter;

import java.util.Date;

/**
 * Created by Srf on 2017/3/15
 */

public class AccountInfo {

    private int id;
    private String status;
    private int cost;
    private String type;
    private String memberName;
    private String hotelName;
    private Date date;

    public AccountInfo(Account account) {
        this.id = account.getId();
        this.status = AccountFilter.filteStatus(account.getStatus());
        this.cost = account.getCost();
        this.type = AccountFilter.filteType(account.getType());
        if(account.getMember()==null)
            memberName = account.getMembername();
        else
            memberName = account.getMember().getMembername();
        if(account.getHotel()==null)
            hotelName = "无";
        else
            hotelName = account.getHotel().getName();
        this.date = account.getDate();
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public int getCost() {
        return cost;
    }

    public String getType() {
        return type;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public Date getDate() {
        return date;
    }

}
