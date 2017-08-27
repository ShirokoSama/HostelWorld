package nju.service.impl;

import nju.dao.*;
import nju.entity.*;
import nju.service.HotelBusinessService;
import nju.vo.AccomodationInfo;
import nju.vo.AccountInfo;
import nju.vo.AppointmentInfo;
import nju.vo.RoomInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Srf on 2017/3/15
 */

@Service
@Transactional
public class HotelBusinessServiceImpl implements HotelBusinessService{

    @Resource
    private MemberDao memberDao;
    @Resource
    private HotelDao hotelDao;
    @Resource
    private AppointmentDao appointmentDao;
    @Resource
    private AccomodationDao accomodationDao;
    @Resource
    private RoomDao roomDao;
    @Resource
    private PlanDao planDao;
    @Resource
    private AccountDao accountDao;

    public List<AppointmentInfo> getAllHotelAppointmentInfos(int hid) {
        List<AppointmentInfo> appointmentInfos = new ArrayList<>();
        for(Appointment appointment: hotelDao.findOne(hid).getAppointments()){
            appointmentInfos.add(new AppointmentInfo(appointment));
        }
        return appointmentInfos;
    }

    public List<AppointmentInfo> getAllHotelOrderingAppointmentInfos(int hid) {
        List<AppointmentInfo> appointmentInfos = new ArrayList<>();
        for(Appointment appointment: hotelDao.findOne(hid).getAppointments()){
            if(appointment.getStatus().equals("ordering"))
                appointmentInfos.add(new AppointmentInfo(appointment));
        }
        return appointmentInfos;
    }

    public List<AccomodationInfo> getAllHotelAccomodationInfos(int hid) {
        List<AccomodationInfo> accomodationInfos = new ArrayList<>();
        for(Accomodation accomodation: hotelDao.findOne(hid).getAccomodations()){
            accomodationInfos.add(new AccomodationInfo(accomodation));
        }
        return accomodationInfos;
    }

    public List<AccomodationInfo> getAllHotelStayingAccomodationInfos(int hid) {
        List<AccomodationInfo> accomodationInfos = new ArrayList<>();
        for(Accomodation accomodation: hotelDao.findOne(hid).getAccomodations()){
            if(accomodation.getLeavedate()==null)
                accomodationInfos.add(new AccomodationInfo(accomodation));
        }
        return accomodationInfos;
    }

    public List<RoomInfo> getAllHotelRoomInfos(int hid) {
        List<RoomInfo> roomInfos = new ArrayList<>();
        for(Room room: roomDao.findAllHotelRooms(hid)){
            roomInfos.add(new RoomInfo(room));
        }
        return roomInfos;
    }

    public List<AccountInfo> getAllHotelAccountInfos(int hid) {
        List<AccountInfo> accountInfos = new ArrayList<>();
        for(Account account: hotelDao.findOne(hid).getAccounts())
            accountInfos.add(new AccountInfo(account));
        return accountInfos;
    }

    public AppointmentInfo getOneAppointmentInfo(int aid){
        return new AppointmentInfo(appointmentDao.findOne(aid));
    }

    public AccomodationInfo getOneAccomodationInfo(int aid){
        return new AccomodationInfo(accomodationDao.findOne(aid));
    }

    public String settleAppointment(int aid) {
        Appointment appointment = appointmentDao.findOne(aid);
        appointment.setStatus("settled");
        Plan plan = planDao.findByHidAndType(appointment.getHotel().getId(),appointment.getType()).get(0);
        plan.setOrdernum(plan.getOrdernum()-appointment.getNum());
        appointmentDao.save(appointment);
        planDao.save(plan);
        Account account = new Account();
        account.setMember(appointment.getMember());
        account.setHotel(appointment.getHotel());
        account.setStatus("apply");
        account.setCost(appointment.getCost());
        account.setType("settle");
        account.setDate(new java.sql.Date(new Date().getTime()));
        accountDao.save(account);
        Member member = memberDao.findOne(appointment.getMember().getId());
        member.getCard().setScore(member.getCard().getScore()+appointment.getCost()/10);
        member.getCard().setCostcount(member.getCard().getCostcount()+appointment.getCost());
        member.getCard().setLevel(member.getCard().getCostcount()/1000+1);
        memberDao.save(member);
        return "success";
    }

    private String setRoomEmpty(Room room) {
        room.setStatus("empty");
        roomDao.save(room);
        return "success";
    }

    private String setRoomUsing(Room room) {
        room.setStatus("using");
        roomDao.save(room);
        return "success";
    }

    //"full","member not exist","no enough money","success"
    public String createNewAccomodation(int hid, String roomType, int cost, String memberName, String payType, String roomNum){
        boolean exist = true;
        if(memberDao.findByMembername(memberName)==null||memberDao.findByMembername(memberName).size()==0){
            exist = false;
        }
        Room room;
        if(roomNum==null||roomNum.equals("")) {
            List<Room> rooms = roomDao.findAllHotelEmptyRoomByType(hid, roomType);
            if (rooms == null || rooms.size() == 0) {
                return "full";
            }
            room = rooms.get(0);
        }
        else {
            List<Room> rooms = roomDao.findRoomByRoomNum(hid, roomNum);
            if (rooms == null || rooms.size() ==0 ) {
                return "error";
            }
            room = rooms.get(0);
        }
        switch (payType) {
            case "plan":
                if(exist){
                    this.addAccomodation(hid,roomType,cost,memberName,payType,room);
                    return "success";
                }
                else{
                    return "member not exist";
                }
            case "card":
                if(exist){
                    Member member = memberDao.findByMembername(memberName).get(0);
                    if(member.getCard().getMoney()>=cost) {
                        this.addAccomodation(hid, roomType, cost, memberName, payType, room);
                        member.getCard().setMoney(member.getCard().getMoney()-cost);
                        Account account = new Account();
                        account.setMember(memberDao.findByMembername(memberName).get(0));
                        account.setHotel(hotelDao.findOne(hid));
                        account.setStatus("apply");
                        account.setType("cardpay");
                        account.setCost(cost);
                        account.setDate(new java.sql.Date(new Date().getTime()));
                        accountDao.save(account);
                        member.getCard().setScore(member.getCard().getScore()+cost);
                        member.getCard().setCostcount(member.getCard().getCostcount()+cost);
                        member.getCard().setLevel(member.getCard().getCostcount()/1000+1);
                        memberDao.save(member);
                        return "success";
                    }
                    else
                        return "no enough money";
                }
                else
                    return "member not exist";
            case "cash":
                this.addAccomodationCash(hid,roomType,cost,memberName,payType,room);
                return "success";
            default:
                return "error";
        }
    }

    public String makeAccomodationLeave(int aid) {
        Accomodation accomodation = accomodationDao.findOne(aid);
        accomodation.setLeavedate(new java.sql.Date(new Date().getTime()));
        Plan plan = planDao.findByHidAndType(accomodation.getHotel().getId(), accomodation.getRoomtype()).get(0);
        plan.setStaynum(plan.getStaynum()-1);
        Room room = roomDao.findOne(accomodation.getRoom().getId());
        setRoomEmpty(room);
        accomodationDao.save(accomodation);
        planDao.save(plan);
        return "success";
    }

    private void addAccomodation(int hid, String roomType, int cost, String memberName, String payType, Room room) {
        Accomodation accomodation = new Accomodation();
        accomodation.setRoom(room);
        accomodation.setCost(cost);
        accomodation.setHotel(hotelDao.findOne(hid));
        accomodation.setMembername(memberName);
        accomodation.setPaytype(payType);
        accomodation.setRoomtype(roomType);
        accomodation.setArrivedate(new java.sql.Date(new Date().getTime()));
        accomodation.setMember(memberDao.findByMembername(memberName).get(0));
        accomodationDao.save(accomodation);
        Plan plan = planDao.findByHidAndType(hid,roomType).get(0);
        plan.setStaynum(plan.getStaynum()+1);
        planDao.save(plan);
        setRoomUsing(room);
    }

    private void addAccomodationCash(int hid, String roomType, int cost, String memberName, String payType, Room room) {
        Accomodation accomodation = new Accomodation();
        accomodation.setRoom(room);
        accomodation.setCost(cost);
        accomodation.setHotel(hotelDao.findOne(hid));
        accomodation.setMembername(memberName);
        accomodation.setPaytype(payType);
        accomodation.setRoomtype(roomType);
        accomodation.setArrivedate(new java.sql.Date(new Date().getTime()));
        accomodationDao.save(accomodation);
        Plan plan = planDao.findByHidAndType(hid,roomType).get(0);
        plan.setStaynum(plan.getStaynum()+1);
        planDao.save(plan);
        setRoomUsing(room);
        Account account = new Account();
        account.setHotel(hotelDao.findOne(hid));
        account.setCost(cost);
        account.setType("cash");
        account.setMembername(memberName);
        account.setDate(new java.sql.Date(new Date().getTime()));
        accountDao.save(account);
    }

}
