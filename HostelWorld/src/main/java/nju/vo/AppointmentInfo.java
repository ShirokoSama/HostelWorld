package nju.vo;

import nju.entity.Appointment;
import nju.util.RoomTypeFilter;

import java.util.Date;

/**
 * Created by Srf on 2017/3/14
 */

public class AppointmentInfo {

    private int id;
    private String identitynum;
    private String memberName;
    private String hotelName;
    private String type;
    private int num;
    private int days;
    private int cost;
    private String status;
    private Date date;

    public AppointmentInfo(Appointment appointment) {
        this.id = appointment.getId();
        this.identitynum = appointment.getIdentitynum();
        this.memberName = appointment.getMember().getMembername();
        this.hotelName = appointment.getHotel().getName();
        this.type = RoomTypeFilter.enToCh(appointment.getType());
        this.num = appointment.getNum();
        this.days = appointment.getDays();
        this.cost = appointment.getCost();
        this.date = appointment.getDate();
        switch (appointment.getStatus()) {
            case "ordering":
                this.status = "已预约";
                break;
            case "canceled":
                this.status = "已取消";
                break;
            case "settled":
                this.status = "已入住";
                break;
        }
    }

    public int getId() {
        return id;
    }

    public String getIdentitynum() {
        return identitynum;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getType() {
        return type;
    }

    public int getNum() {
        return num;
    }

    public int getDays() {
        return days;
    }

    public int getCost() {
        return cost;
    }

    public String getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

}
