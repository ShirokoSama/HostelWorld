package nju.service.impl;

import nju.dao.HotelDao;
import nju.dao.ManagerDao;
import nju.dao.MemberDao;
import nju.entity.Appointment;
import nju.entity.Hotel;
import nju.entity.Manager;
import nju.entity.Member;
import nju.service.ManagerService;
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

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService{

    @Resource
    private ManagerDao managerDao;
    @Resource
    private HotelDao hotelDao;
    @Resource
    private MemberDao memberDao;

    public ManagerInfo getManagerInfo(int manager_id) {
        Manager manager = managerDao.findOne(manager_id);
        return new ManagerInfo(manager);
    }

    public List<HotelInfo> getAllHotelInfo() {
        List<HotelInfo> hotelInfos = new ArrayList<>();
        for(Hotel hotel: hotelDao.findAll())
            hotelInfos.add(new HotelInfo(hotel));
        return hotelInfos;
    }

    public List<MemberInfo> getAllMemberInfo() {
        List<MemberInfo> memberInfos = new ArrayList<>();
        for(Member member: memberDao.findAll())
            memberInfos.add(new MemberInfo(member));
        return memberInfos;
    }

    public List<AppointmentInfo> getAllMemberAppointmentInfo(int mid) {
        List<AppointmentInfo> appointmentInfos = new ArrayList<>();
        for(Appointment appointment: memberDao.findOne(mid).getAppointments())
            appointmentInfos.add(new AppointmentInfo(appointment));
        return appointmentInfos;
    }

    public List<AppointmentInfo> getAllHotelAppointmentInfo(int hid) {
        List<AppointmentInfo> appointmentInfos = new ArrayList<>();
        for(Appointment appointment: hotelDao.findOne(hid).getAppointments())
            appointmentInfos.add(new AppointmentInfo(appointment));
        return appointmentInfos;
    }

}
