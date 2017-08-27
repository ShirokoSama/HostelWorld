package nju.service;

import nju.dao.AccountDao;
import nju.dao.HotelDao;
import nju.dao.ManagerDao;
import nju.dao.MemberDao;
import nju.entity.Account;
import nju.entity.Hotel;
import nju.entity.Member;
import nju.vo.AccountInfo;
import nju.vo.ManagerInfo;
import nju.vo.MemberInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Srf on 2017/3/15
 */

public interface ManagerAccountService {

    public ManagerInfo getManagerInfo(int id);

    public List<MemberInfo> getAllMemberInfo();

    public List<AccountInfo> getAllAccountInfo();

    public List<AccountInfo> getAllMemberAccountInfo(int mid);

    public List<AccountInfo> getAllApplyAccountInfo();

    public String settleAccount(int aid);

    public String settleAllAccount();

    public String[] getHotelNames();

    public long[] getHotelAccounts();

    public String[] getDateStrings();

    public long[] getDateAccounts() throws ParseException;

}
