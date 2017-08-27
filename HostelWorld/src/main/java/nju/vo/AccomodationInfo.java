package nju.vo;

import nju.entity.Accomodation;
import nju.util.PayTypeFilter;
import nju.util.RoomTypeFilter;

import java.util.Date;

/**
 * Created by Srf on 2017/3/15
 */

public class AccomodationInfo {

    private int id;
    private Date arriveDate;
    private Date leaveDate;
    private int cost;
    private String roomType;
    private String payType;
    private String memberName;
    private String hotelName;
    private String roomNum;

    public AccomodationInfo(Accomodation accomodation) {
        this.id = accomodation.getId();
        this.arriveDate = accomodation.getArrivedate();
        this.leaveDate = accomodation.getLeavedate();
        this.cost = accomodation.getCost();
        this.roomType = RoomTypeFilter.enToCh(accomodation.getRoomtype());
        this.payType = PayTypeFilter.enToCh(accomodation.getPaytype());
        this.memberName = accomodation.getMembername();
        this.hotelName = accomodation.getHotel().getName();
        this.roomNum = accomodation.getRoom().getRoomnum();
    }

    public int getId() {
        return id;
    }

    public Date getArriveDate() {
        return arriveDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public int getCost() {
        return cost;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getPayType() {
        return payType;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getRoomNum() {
        return roomNum;
    }
}
