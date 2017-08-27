package nju.service;

import nju.dao.*;
import nju.entity.*;
import nju.vo.AccomodationInfo;
import nju.vo.AccountInfo;
import nju.vo.AppointmentInfo;
import nju.vo.HotelInfo;
import org.omg.PortableInterceptor.ACTIVE;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Srf on 2017/3/14
 */

public interface MemberBusinessService {

    public List<HotelInfo> getAllHotelInfos();

    public List<AppointmentInfo> getAllMemberAppointmentInfos(int mid);

    public List<AccomodationInfo> getAllMemberAccomodationInfos(int mid);

    public List<AccountInfo> getAllMemberAccountInfos(int mid);

    public String makeAppointment(int mid, int hid, String type, int num, int days);

    public String cancelAppointment(int aid);

}
