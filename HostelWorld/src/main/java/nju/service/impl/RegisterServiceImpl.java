package nju.service.impl;

import nju.dao.HotelDao;
import nju.dao.MemberDao;
import nju.dao.PlanDao;
import nju.dao.RoomDao;
import nju.entity.Hotel;
import nju.entity.Member;
import nju.entity.Plan;
import nju.entity.Room;
import nju.service.RegisterService;
import nju.util.IdentityNumCreator;
import nju.vo.HotelInfo;
import nju.vo.MemberInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Srf on 2017/3/11
 */

@Service
@Transactional
public class RegisterServiceImpl implements RegisterService{

    @Resource
    private MemberDao memberDao;
    @Resource
    private HotelDao hotelDao;
    @Resource
    private RoomDao roomDao;
    @Resource
    private PlanDao planDao;

    public MemberInfo memberRegister(String membername, String password) {
        List<Member> currentMembers = memberDao.findAll();
        boolean exist = false;
        for(Member member: currentMembers){
            if(member.getMembername().equals(membername)) {
                exist = true;
                break;
            }
        }
        if(exist){
            return null;
        }
        else{
            Member member = new Member();
            member.setMembername(membername);
            member.setPassword(password);
            member.setStatus("not active");
            memberDao.save(member);
            return null;
        }
    }

    public HotelInfo hotelRegister(String hotelname, String password, String address, String description) {
        List<Hotel> currentHotels = hotelDao.findAll();
        List<String> identityNums = new ArrayList<>();
        boolean exist = false;
        for(Hotel hotel: currentHotels){
            identityNums.add(hotel.getIdentitynum());
            if(hotel.getName().equals(hotelname)) {
                exist = true;
                break;
            }
        }
        if(exist){
            return new HotelInfo(hotelDao.findAllByName(hotelname).get(0));
        }
        else{
            Hotel hotel = new Hotel();
            hotel.setName(hotelname);
            hotel.setPassword(password);
            hotel.setAddress(address);
            hotel.setDescription(description);
            hotel.setStatus("register");
            hotel.setIdentitynum(IdentityNumCreator.createNextIdentityNum(identityNums));
            return new HotelInfo(hotelDao.save(hotel));
        }
    }

    public int createHotelRooms(String identitynum, String type, int num, int roomNumCount, int roomNumPerFloor) {
        List<Room> rooms = new ArrayList<>();
        for(int i=0;i<num;i++){
            if(roomNumCount%100>=roomNumPerFloor)
                roomNumCount = (roomNumCount/100+1)*100;
            roomNumCount++;
            Room room = new Room();
            room.setHotel(hotelDao.findAllByIdentitynum(identitynum).get(0));
            room.setType(type);
            room.setStatus("empty");
            room.setRoomnum(roomNumCount+"");
            rooms.add(room);
        }
        roomDao.save(rooms);
        return roomNumCount;
    }

    public void createHotelPlans(String identitynum, String type ,int num) {
        Plan plan = new Plan();
        plan.setType(type);
        plan.setTotalnum(num);
        plan.setStaynum(0);
        plan.setOrdernum(0);
        plan.setPlannum(0);
        plan.setHotel(hotelDao.findAllByIdentitynum(identitynum).get(0));
        planDao.save(plan);
    }

}
