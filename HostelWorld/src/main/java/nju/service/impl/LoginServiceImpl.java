package nju.service.impl;

import nju.dao.HotelDao;
import nju.dao.ManagerDao;
import nju.dao.MemberDao;
import nju.entity.Hotel;
import nju.entity.Manager;
import nju.entity.Member;
import nju.service.LoginService;
import nju.util.VerifyResult;
import nju.vo.LoginVerifyResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Srf on 2017/3/11
 */

@Service
@Transactional
public class LoginServiceImpl implements LoginService{

    @Resource
    private MemberDao memberDao;
    @Resource
    private HotelDao hotelDao;
    @Resource
    private ManagerDao managerDao;

    public LoginVerifyResult memberVerify(String username, String password) {
        List<Member> members = memberDao.findByMembername(username);
        if(members!=null){
            if(members.size()!=0) {
                Member member = members.get(0);
                if (member.getPassword().equals(password))
                    return new LoginVerifyResult(member.getId(), VerifyResult.SUCCESS);
                else
                    return new LoginVerifyResult(0, VerifyResult.ERROR);
            }
            else
                return new LoginVerifyResult(0, VerifyResult.ERROR);
        }
        else{
            return new LoginVerifyResult(0, VerifyResult.NULL);
        }
    }

    public LoginVerifyResult hotelVerify(String name, String password) {
        List<Hotel> hotels = hotelDao.findByName(name);
        if(hotels!=null&&hotels.size()!=0){
            Hotel hotel = hotels.get(0);
            if(hotel.getPassword().equals(password))
                return new LoginVerifyResult(hotel.getId(), VerifyResult.SUCCESS);
            else
                return new LoginVerifyResult(0, VerifyResult.ERROR);
        }
        else{
            hotels = hotelDao.findByIdentitynum(name);
            if(hotels!=null&&hotels.size()!=0){
                Hotel hotel = hotels.get(0);
                if(hotel.getPassword().equals(password))
                    return new LoginVerifyResult(hotel.getId(), VerifyResult.SUCCESS);
                else
                    return new LoginVerifyResult(0, VerifyResult.ERROR);
            }
            else
                return new LoginVerifyResult(0, VerifyResult.NULL);
        }
    }

    public LoginVerifyResult managerVerify(String name, String password) {
        List<Manager> managers = managerDao.findByName(name);
        if(managers!=null&&managers.size()!=0){
            Manager manager = managers.get(0);
            if(manager.getPassword().equals(password))
                return new LoginVerifyResult(manager.getId(), VerifyResult.SUCCESS);
            else
                return new LoginVerifyResult(0, VerifyResult.ERROR);
        }
        else{
            return new LoginVerifyResult(0, VerifyResult.NULL);
        }
    }

}
