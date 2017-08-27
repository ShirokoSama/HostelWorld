package nju.service;

import nju.vo.HotelInfo;
import java.util.Date;

/**
 * Created by Srf on 2017/3/13
 */

public interface HotelBasicInfoService {

    public HotelInfo getHotelInfo(int hotel_id);

    public String editHotelInfo(int hotel_id, String name, String address, String description);

    public String modifyHotelPassword(int hotel_id, String old_password, String new_password);

    public String modifyPlan(int hotel_id, String type, int plannum, int price);

    public String modifyPlanDate(int hid, Date date);

}
