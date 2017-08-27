package nju.service.impl;

import nju.dao.AccountDao;
import nju.dao.HotelDao;
import nju.entity.Hotel;
import nju.service.ManagerHotelService;
import nju.vo.HotelInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Srf on 2017/3/15
 */

@Service
@Transactional
public class ManagerHotelServiceImpl implements ManagerHotelService{

    @Resource
    private HotelDao hotelDao;
    @Resource
    private AccountDao accountDao;

    public List<HotelInfo> getAllRegisterHotelInfo() {
        List<HotelInfo> hotelInfos = new ArrayList<>();
        for(Hotel hotel: hotelDao.findAllRegister())
            hotelInfos.add(new HotelInfo(hotel));
        return hotelInfos;
    }

    public List<HotelInfo> getAllEditHotelInfo() {
        List<HotelInfo> hotelInfos = new ArrayList<>();
        for(Hotel hotel: hotelDao.findAllEdit())
            hotelInfos.add(new HotelInfo(hotel));
        return hotelInfos;
    }

    public List<HotelInfo> getAllHotelInfo() {
        List<HotelInfo> hotelInfos = new ArrayList<>();
        for(Hotel hotel: hotelDao.findAllWorking())
            hotelInfos.add(new HotelInfo(hotel));
        return hotelInfos;
    }

    public String approveHotelRegister(int hid) {
        Hotel hotel = hotelDao.findOne(hid);
        hotel.setStatus("working");
        hotelDao.save(hotel);
        return "success";
    }

    public String abandonHotelRegister(int hid) {
        Hotel hotel = hotelDao.findOne(hid);
        hotelDao.delete(hotel);
        return "success";
    }

    public String approveHotelEdit(int hid) {
        Hotel editHotel = hotelDao.findOne(hid);
        String identitynum = editHotel.getIdentitynum();
        List<Hotel> hotels = hotelDao.findByIdentitynum(identitynum);
        Hotel originHotel = hotels.get(0);
        originHotel.setName(editHotel.getName());
        originHotel.setAddress(editHotel.getAddress());
        originHotel.setDescription(editHotel.getDescription());
        hotelDao.save(originHotel);
        hotelDao.delete(editHotel);
        return "success";
    }

    public String abandonHotelEdit(int hid) {
        hotelDao.delete(hid);
        return "success";
    }



}
