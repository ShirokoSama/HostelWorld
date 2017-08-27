package nju.service.impl;

import nju.dao.HotelDao;
import nju.dao.PlanDao;
import nju.entity.Hotel;
import nju.entity.Plan;
import nju.service.HotelBasicInfoService;
import nju.vo.HotelInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Srf on 2017/3/13
 */

@Service
@Transactional
public class HotelBasicInfoServiceImpl implements HotelBasicInfoService{

    @Resource
    private HotelDao hotelDao;
    @Resource
    private PlanDao planDao;

    public HotelInfo getHotelInfo(int hotel_id) {
        return new HotelInfo(hotelDao.findOne(hotel_id));
    }

    public String editHotelInfo(int hotel_id, String name, String address, String description) {
        Hotel new_hotel = new Hotel();
        Hotel current_hotel = hotelDao.findOne(hotel_id);
        new_hotel.setPassword(current_hotel.getPassword());
        new_hotel.setIdentitynum(current_hotel.getIdentitynum());
        new_hotel.setName(name);
        new_hotel.setDescription(description);
        new_hotel.setAddress(address);
        new_hotel.setStatus("edit");
        hotelDao.save(new_hotel);
        return "success";
    }

    public String modifyHotelPassword(int hotel_id, String old_password, String new_password) {
        Hotel hotel = hotelDao.findOne(hotel_id);
        if(old_password.equals(hotel.getPassword())){
            hotel.setPassword(new_password);
            hotelDao.save(hotel);
            return "success";
        }
        else{
            return "error";
        }
    }

    public String modifyPlan(int hotel_id, String type, int plannum, int price) {
        Plan plan = planDao.findByHidAndType(hotel_id,type).get(0);
        if(plannum<=plan.getTotalnum()-plan.getOrdernum()-plan.getStaynum()){
            plan.setPlannum(plannum);
            plan.setPrice(price);
            planDao.save(plan);
            return "success";
        }
        else{
            return "error";
        }
    }

    public String modifyPlanDate(int hid, Date date) {
        Hotel hotel = hotelDao.findOne(hid);
        hotel.setPlanDate(new java.sql.Date(date.getTime()));
        hotelDao.save(hotel);
        return "success";
    }

}
