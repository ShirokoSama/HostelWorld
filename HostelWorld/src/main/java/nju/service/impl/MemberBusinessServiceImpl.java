package nju.service.impl;

import nju.dao.*;
import nju.entity.*;
import nju.service.MemberBasicInfoService;
import nju.service.MemberBusinessService;
import nju.vo.AccomodationInfo;
import nju.vo.AccountInfo;
import nju.vo.AppointmentInfo;
import nju.vo.HotelInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Srf on 2017/3/14
 */

@Service
@Transactional
public class MemberBusinessServiceImpl implements MemberBusinessService{

    @Resource
    private MemberDao memberDao;
    @Resource
    private HotelDao hotelDao;
    @Resource
    private AppointmentDao appointmentDao;
    @Resource
    private PlanDao planDao;
    @Resource
    private AccountDao accountDao;

    public List<HotelInfo> getAllHotelInfos() {
        List<Hotel> hotels = hotelDao.findAllWorking();
        List<HotelInfo> hotelInfos = new ArrayList<>();
        for(Hotel hotel: hotels){
            hotelInfos.add(new HotelInfo(hotel));
        }
        return hotelInfos;
    }

    public List<AppointmentInfo> getAllMemberAppointmentInfos(int mid) {
        List<AppointmentInfo> appointmentInfos = new ArrayList<>();
        for(Appointment appointment: memberDao.findOne(mid).getAppointments()){
            appointmentInfos.add(new AppointmentInfo(appointment));
        }
        return appointmentInfos;
    }

    public List<AccomodationInfo> getAllMemberAccomodationInfos(int mid) {
        List<AccomodationInfo> accomodationInfos = new ArrayList<>();
        for(Accomodation accomodation: memberDao.findOne(mid).getAccomodations()){
            accomodationInfos.add(new AccomodationInfo(accomodation));
        }
        return accomodationInfos;
    }

    public List<AccountInfo> getAllMemberAccountInfos(int mid) {
        List<AccountInfo> accountInfos = new ArrayList<>();
        for(Account account: memberDao.findOne(mid).getAccounts()){
            accountInfos.add(new AccountInfo(account));
        }
        return accountInfos;
    }

    public String makeAppointment(int mid, int hid, String type, int num, int days) {
        Plan plan = planDao.findByHidAndType(hid,type).get(0);
        if(plan.getPlannum()-plan.getOrdernum()-plan.getStaynum()>=num){
            Member member = memberDao.findOne(mid);
            if(member.getCard().getMoney()>=(1-(member.getCard().getLevel()-1)*0.05)*num*days*plan.getPrice()) {
                Appointment appointment = new Appointment();
                appointment.setMember(memberDao.findOne(mid));
                appointment.setHotel(hotelDao.findOne(hid));
                appointment.setType(type);
                appointment.setDays(days);
                appointment.setCost((int)(num * days * plan.getPrice()*(1-(member.getCard().getLevel()-1)*0.05)));
                appointment.setStatus("ordering");
                appointment.setNum(num);
                int currentMaxIdentityNum;
                if (appointmentDao.findMaxIdentityNum() == null)
                    currentMaxIdentityNum = 1000000;
                else
                    currentMaxIdentityNum = Integer.parseInt(appointmentDao.findMaxIdentityNum());
                appointment.setIdentitynum((currentMaxIdentityNum + 1) + "");
                appointment.setDate(new java.sql.Date(new Date().getTime()));
                appointmentDao.save(appointment);
                plan.setOrdernum(plan.getOrdernum() + num);
                planDao.save(plan);
                member.getCard().setMoney(member.getCard().getMoney()-appointment.getCost());
                memberDao.save(member);
                Account account = new Account();
                account.setCost(appointment.getCost());
                account.setType("appointment");
                account.setMember(member);
                account.setHotel(hotelDao.findOne(hid));
                account.setDate(new java.sql.Date(new Date().getTime()));
                accountDao.save(account);
                return "success";
            }
            else {
                return "no enough money";
            }
        }
        else {
            return "error";
        }
    }

    public String cancelAppointment(int aid) {
        Appointment appointment = appointmentDao.findOne(aid);
        appointment.setStatus("canceled");
        Member member = memberDao.findOne(appointment.getMember().getId());
        Plan plan = planDao.findByHidAndType(appointment.getHotel().getId(),appointment.getType()).get(0);
        member.getCard().setMoney(member.getCard().getMoney()+appointment.getCost());
        plan.setOrdernum(plan.getOrdernum() - appointment.getNum());
        appointmentDao.save(appointment);
        memberDao.save(member);
        planDao.save(plan);
        Account account = new Account();
        account.setCost(appointment.getCost());
        account.setType("cancel");
        account.setMember(member);
        account.setHotel(hotelDao.findOne(appointment.getHotel().getId()));
        account.setDate(new java.sql.Date(new Date().getTime()));
        accountDao.save(account);
        return "success";
    }

}
