package nju.service.impl;

import nju.dao.AccountDao;
import nju.dao.HotelDao;
import nju.dao.ManagerDao;
import nju.dao.MemberDao;
import nju.entity.Account;
import nju.entity.Hotel;
import nju.entity.Member;
import nju.service.ManagerAccountService;
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

@Service
@Transactional
public class ManagerAccountServiceImpl implements ManagerAccountService{

    @Resource
    private AccountDao accountDao;
    @Resource
    private MemberDao memberDao;
    @Resource
    private ManagerDao managerDao;
    @Resource
    private HotelDao hotelDao;

    public ManagerInfo getManagerInfo(int id) {
        return new ManagerInfo(managerDao.findOne(id));
    }

    public List<MemberInfo> getAllMemberInfo() {
        List<MemberInfo> memberInfos = new ArrayList<>();
        for(Member member: memberDao.findAll())
            memberInfos.add(new MemberInfo(member));
        return memberInfos;
    }

    public List<AccountInfo> getAllAccountInfo() {
        List<AccountInfo> accountInfos = new ArrayList<>();
        for(Account account: accountDao.findAll())
            accountInfos.add(new AccountInfo(account));
        return accountInfos;
    }

    public List<AccountInfo> getAllMemberAccountInfo(int mid) {
        List<AccountInfo> accountInfos = new ArrayList<>();
        for(Account account: memberDao.findOne(mid).getAccounts())
            accountInfos.add(new AccountInfo(account));
        return accountInfos;
    }

    public List<AccountInfo> getAllApplyAccountInfo() {
        List<AccountInfo> accountInfos = new ArrayList<>();
        for(Account account: accountDao.findAllApplyAccount())
            accountInfos.add(new AccountInfo(account));
        return accountInfos;
    }

    public String settleAccount(int aid) {
        Account account = accountDao.findOne(aid);
        account.setStatus("approve");
        accountDao.save(account);
        return "success";
    }

    public String settleAllAccount() {
        List<Account> accounts = accountDao.findAllApplyAccount();
        for(Account account: accounts){
            account.setStatus("approve");
        }
        accountDao.save(accounts);
        return "success";
    }

    public String[] getHotelNames() {
        List<Hotel> hotels = hotelDao.findAllWorking();
        String names[] = new String[hotels.size()];
        for(int i=0;i<hotels.size();i++)
            names[i] = hotels.get(i).getName();
        return names;
    }

    public long[] getHotelAccounts() {
        List<Hotel> hotels = hotelDao.findAllWorking();
        long accounts[] = new long[hotels.size()];
        for(int i=0;i<hotels.size();i++) {
            long sum = 0;
            for(Account account: accountDao.findHotelAccountSum(hotels.get(i).getId()))
                sum += account.getCost();
            accounts[i] = sum;
        }
        return accounts;
    }

    public String[] getDateStrings() {
        String[] dates = new String[7];
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        for(int i=0;i<7;i++)
            dates[i] = month+"-"+(day-i);
        return dates;
    }

    public long[] getDateAccounts() throws ParseException {
        long accounts[] = new long[7];
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date date = new java.sql.Date(new Date().getTime());
        String dates[] = getDateStrings();
        for(int i=0;i<7;i++) {
            long sum = 0;
            for(Account account: accountDao.findDayAcountSum(new java.sql.Date(format.parse("2017-" + dates[i]).getTime()))) {
                System.out.println(new java.sql.Date(format.parse("2017-" + dates[i]).getTime()));
                System.out.println(account.getCost());
                sum += account.getCost();
            }
            accounts[i] = sum;
        }
        return accounts;
    }

}
