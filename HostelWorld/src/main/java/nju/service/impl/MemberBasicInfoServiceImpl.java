package nju.service.impl;

import nju.dao.AccountDao;
import nju.dao.CardDao;
import nju.dao.MemberDao;
import nju.entity.Account;
import nju.entity.Card;
import nju.entity.Member;
import nju.service.MemberBasicInfoService;
import nju.util.IdentityNumCreator;
import nju.vo.MemberInfo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Srf on 2017/3/12
 */

@Service
@Transactional
public class MemberBasicInfoServiceImpl implements MemberBasicInfoService{

    @Resource
    private MemberDao memberDao;
    @Resource
    private CardDao cardDao;
    @Resource
    private AccountDao accountDao;

    public MemberInfo getMemberInfo(int id) {
        Member member = memberDao.findOne(id);
        return new MemberInfo(member);
    }

    public String createCard(int id, String banknum, int money) {
        if(money>=1000) {
            Member member = memberDao.findOne(id);
            List<Card> currentCards = cardDao.findAll();
            List<String> identityNums = new ArrayList<>();
            for (Card card : currentCards) {
                identityNums.add(card.getIdentitynum());
            }
            Card card = new Card();
            card.setBankcnum(banknum);
            card.setMoney(money);
            card.setCostcount(0);
            card.setScore(0);
            card.setLevel(1);
            card.setIdentitynum(IdentityNumCreator.createNextIdentityNum(identityNums));
            Date date = new Date();
            card.setCreatedate(new java.sql.Date(date.getTime()));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
            card.setSuspenddate(new java.sql.Date(calendar.getTime().getTime()));
            card.setTerminatedate(null);
            card.setMember(member);
            cardDao.save(card);
            member.setStatus("active");
            memberDao.save(member);
            Account account = new Account();
            account.setCost(money);
            account.setType("recharge");
            account.setMember(memberDao.findOne(id));
            account.setDate(new java.sql.Date(new Date().getTime()));
            accountDao.save(account);
            return "success";
        }
        else{
            return "no enough money";
        }
    }

    @Scheduled(cron = "0 0 0 365 *")
    private void suspendCard(int mid) {
        Member member = memberDao.findOne(mid);
        if(member.getCard().getMoney()<=500)
            member.setStatus("suspend");
    }

    public String addMoney(int money,int member_id) {
        Member member = memberDao.findOne(member_id);
        member.getCard().setMoney(member.getCard().getMoney()+money);
        memberDao.save(member);
        Account account = new Account();
        account.setCost(money);
        account.setType("recharge");
        account.setMember(memberDao.findOne(member_id));
        account.setDate(new java.sql.Date(new Date().getTime()));
        accountDao.save(account);
        return "success";
    }

    public MemberInfo editCardInfo(int member_id, String bankcnum) {
        Member member = memberDao.findOne(member_id);
        member.getCard().setBankcnum(bankcnum);
        return new MemberInfo(memberDao.save(member));
    }

    public String scoreToMoney(int member_id, int score) {
        if(score<=memberDao.findOne(member_id).getCard().getScore()) {
            Member member = memberDao.findOne(member_id);
            member.getCard().setScore(member.getCard().getScore() - score);
            member.getCard().setMoney(member.getCard().getMoney() + score / 100);
            memberDao.save(member);
            Account account = new Account();
            account.setType("score");
            account.setCost(score / 100);
            account.setMember(memberDao.findOne(member_id));
            account.setDate(new java.sql.Date(new Date().getTime()));
            accountDao.save(account);
            return "success";
        }
        else{
            return "no enough score";
        }
    }

    public String modifyMemberPassword(int member_id, String old_password, String new_password) {
        Member member = memberDao.findOne(member_id);
        if(member.getPassword().equals(old_password)){
            member.setPassword(new_password);
            memberDao.save(member);
            return "success";
        }
        else{
            return "error";
        }
    }

}