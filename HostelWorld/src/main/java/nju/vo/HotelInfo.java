package nju.vo;

import nju.entity.Hotel;
import nju.entity.Plan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Srf on 2017/3/12
 */

public class HotelInfo {

    private int id;
    private String name;
    private String identitynum;
    private String address;
    private String description;
    private Date planDate;
    private List<PlanInfo> planInfos;

    public HotelInfo(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.identitynum = hotel.getIdentitynum();
        this.address = hotel.getAddress();
        this.description = hotel.getDescription();
        this.planDate = hotel.getPlanDate();
        if(hotel.getPlans()!=null) {
            planInfos = new ArrayList<>();
            for (Plan plan : hotel.getPlans()) {
                planInfos.add(new PlanInfo(plan));
            }
        }
    }

    public int getId() {
        return this.id;
    }

    public String getHotelName() {
        return this.name;
    }

    public String getIdentitynum() {
        return this.identitynum;
    }

    public String getAddress() {
        return this.address;
    }

    public String getDescription() {
        return this.description;
    }

    public Date getPlanDate() {
        return this.planDate;
    }

    public List<PlanInfo> getPlanInfos() {
        return this.planInfos;
    }

    public int getSingleTotal() {
        PlanInfo info = getPlanInfo("单人间");
        if(info!=null)
            return info.getTotalnum();
        else
            return 0;
    }

    public int getDoubleTotal() {
        PlanInfo info = getPlanInfo("双人间");
        if(info!=null)
            return info.getTotalnum();
        else
            return 0;
    }

    public int getFlatTotal() {
        PlanInfo info = getPlanInfo("套间");
        if(info!=null)
            return info.getTotalnum();
        else
            return 0;
    }

    public int getDeluxeTotal() {
        PlanInfo info = getPlanInfo("豪华间");
        if(info!=null)
            return info.getTotalnum();
        else
            return 0;
    }

    public int getSingleRemain() {
        PlanInfo info = getPlanInfo("单人间");
        if(info!=null)
            return info.getPlannum()-info.getOrdernum()-info.getStaynum();
        else
            return 0;
    }

    public int getDoubleRemain() {
        PlanInfo info = getPlanInfo("双人间");
        if(info!=null)
            return info.getPlannum()-info.getOrdernum()-info.getStaynum();
        else
            return 0;
    }

    public int getFlatRemain() {
        PlanInfo info = getPlanInfo("套间");
        if(info!=null)
            return info.getPlannum()-info.getOrdernum()-info.getStaynum();
        else
            return 0;
    }

    public int getDeluxeRemain() {
        PlanInfo info = getPlanInfo("豪华间");
        if(info!=null)
            return info.getPlannum()-info.getOrdernum()-info.getStaynum();
        else
            return 0;
    }

    public int getSinglePrice() {
        PlanInfo info = getPlanInfo("单人间");
        if(info!=null)
            return info.getPrice();
        else
            return 0;
    }

    public int getDoublePrice() {
        PlanInfo info = getPlanInfo("双人间");
        if(info!=null)
            return info.getPrice();
        else
            return 0;
    }

    public int getFlatPrice() {
        PlanInfo info = getPlanInfo("套间");
        if(info!=null)
            return info.getPrice();
        else
            return 0;
    }

    public int getDeluxePrice() {
        PlanInfo info = getPlanInfo("豪华间");
        if(info!=null)
            return info.getPrice();
        else
            return 0;
    }

    private PlanInfo getPlanInfo(String type) {
        for(PlanInfo planInfo: planInfos){
            if(planInfo.getType().equals(type)){
                return planInfo;
            }
        }
        return null;
    }

}
