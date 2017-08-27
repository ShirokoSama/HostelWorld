package nju.dao;

import nju.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

/**
 * Created by Srf on 2017/3/15
 */
public interface AccountDao extends CrudRepository<Account, Integer>{

    @Query("select a from Account a where a.type = 'settle'")
    public List<Account> findAllSettleAccount();

    @Query("select a from Account a where a.status = 'apply'")
    public List<Account> findAllApplyAccount();

    @Query("select a from Account a where a.hotel.id = ?1")
    public List<Account> findAllHotelAccount(Integer hid);

    @Query("select a from Account a where a.member.id = ?1")
    public List<Account> findAllMemberAccount(Integer mid);

    @Query("select a from Account a where a.hotel.id = ?1")
    public List<Account> findHotelAccountSum(Integer hid);

    @Query("select a from Account a where a.date = ?1")
    public List<Account> findDayAcountSum(Date date);

}
