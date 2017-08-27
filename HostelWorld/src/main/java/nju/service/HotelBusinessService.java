package nju.service;

import nju.dao.*;
import nju.entity.*;
import nju.util.RoomTypeFilter;
import nju.vo.AccomodationInfo;
import nju.vo.AccountInfo;
import nju.vo.AppointmentInfo;
import nju.vo.RoomInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Srf on 2017/3/15
 */

public interface HotelBusinessService {

    public List<AppointmentInfo> getAllHotelAppointmentInfos(int hid);

    public List<AppointmentInfo> getAllHotelOrderingAppointmentInfos(int hid);

    public List<AccomodationInfo> getAllHotelAccomodationInfos(int hid);

    public List<AccomodationInfo> getAllHotelStayingAccomodationInfos(int hid);

    public List<RoomInfo> getAllHotelRoomInfos(int hid);

    public List<AccountInfo> getAllHotelAccountInfos(int hid);

    public AppointmentInfo getOneAppointmentInfo(int aid);

    public AccomodationInfo getOneAccomodationInfo(int aid);

    public String settleAppointment(int aid);

    //"full","member not exist","no enough money","success"
    public String createNewAccomodation(int hid, String roomType, int cost, String memberName, String payType, String roomNum);

    public String makeAccomodationLeave(int aid);

}