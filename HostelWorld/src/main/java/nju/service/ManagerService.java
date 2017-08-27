package nju.service;

import nju.dao.AppointmentDao;
import nju.dao.HotelDao;
import nju.dao.ManagerDao;
import nju.dao.MemberDao;
import nju.entity.Appointment;
import nju.entity.Hotel;
import nju.entity.Manager;
import nju.entity.Member;
import nju.vo.AppointmentInfo;
import nju.vo.HotelInfo;
import nju.vo.ManagerInfo;
import nju.vo.MemberInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Srf on 2017/3/13
 */

public interface ManagerService {

    public ManagerInfo getManagerInfo(int manager_id);

    public List<HotelInfo> getAllHotelInfo();

    public List<MemberInfo> getAllMemberInfo();

    public List<AppointmentInfo> getAllMemberAppointmentInfo(int mid);

    public List<AppointmentInfo> getAllHotelAppointmentInfo(int hid);

}
