package nju.service;

import nju.dao.*;
import nju.entity.*;
import nju.util.IdentityNumCreator;
import nju.vo.HotelInfo;
import nju.vo.ManagerInfo;
import nju.vo.MemberInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Srf on 2017/3/11
 */

public interface RegisterService {

    public MemberInfo memberRegister(String membername, String password);

    public HotelInfo hotelRegister(String hotelname, String password, String address, String description);

    public int createHotelRooms(String identitynum, String type, int num, int roomNumCount, int roomNumPerFloor);

    public void createHotelPlans(String identitynum, String type ,int num);

}
