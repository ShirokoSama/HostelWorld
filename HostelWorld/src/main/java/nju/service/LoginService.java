package nju.service;

import nju.dao.HotelDao;
import nju.dao.ManagerDao;
import nju.dao.MemberDao;
import nju.entity.Hotel;
import nju.entity.Manager;
import nju.entity.Member;
import nju.util.VerifyResult;
import nju.vo.LoginVerifyResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Srf on 2017/3/11
 */

public interface LoginService {

    public LoginVerifyResult memberVerify(String username, String password);

    public LoginVerifyResult hotelVerify(String name, String password);

    public LoginVerifyResult managerVerify(String name, String password);

}
