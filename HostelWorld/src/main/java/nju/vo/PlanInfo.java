package nju.vo;

import nju.entity.Plan;
import nju.util.RoomTypeFilter;

/**
 * Created by Srf on 2017/3/13
 */

public class PlanInfo {

    private String type;
    private int totalnum;
    private int staynum;
    private int ordernum;
    private int plannum;
    private int price;
    private String hotelName;
    private String hotelAddress;
    private String hotelDescription;

    public PlanInfo(Plan plan) {
        this.type = RoomTypeFilter.enToCh(plan.getType());
        this.plannum = plan.getPlannum();
        this.totalnum = plan.getTotalnum();
        this.staynum = plan.getStaynum();
        this.ordernum = plan.getOrdernum();
        this.price = plan.getPrice();
        this.hotelName = plan.getHotel().getName();
        this.hotelAddress = plan.getHotel().getAddress();
        this.hotelDescription = plan.getHotel().getDescription();
    }

    public String getType() {
        return this.type;
    }

    public int getTotalnum() {
        return this.totalnum;
    }

    public int getStaynum() {
        return this.staynum;
    }

    public int getOrdernum() {
        return this.ordernum;
    }

    public int getPlannum() {
        return this.plannum;
    }

    public int getPrice() {
        return this.price;
    }

    public String getHotelName() {
        return this.hotelName;
    }

    public String getHotelAddress() {
        return this.hotelAddress;
    }

    public String getHotelDescription() {
        return this.hotelDescription;
    }

}
