package nju.service;

import nju.dao.AccountDao;
import nju.dao.HotelDao;
import nju.entity.Account;
import nju.entity.Hotel;
import nju.vo.HotelInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Srf on 2017/3/15
 */

public interface ManagerHotelService {

    public List<HotelInfo> getAllRegisterHotelInfo();

    public List<HotelInfo> getAllEditHotelInfo();

    public List<HotelInfo> getAllHotelInfo();

    public String approveHotelRegister(int hid);

    public String abandonHotelRegister(int hid);

    public String approveHotelEdit(int hid);

    public String abandonHotelEdit(int hid);

}
