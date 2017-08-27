package nju.service;

import nju.dao.AccountDao;
import nju.dao.CardDao;
import nju.dao.MemberDao;
import nju.entity.Account;
import nju.entity.Card;
import nju.entity.Member;
import nju.util.IdentityNumCreator;
import nju.vo.MemberInfo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Srf on 2017/3/12
 */

public interface MemberBasicInfoService {

    public MemberInfo getMemberInfo(int id);

    public String createCard(int id, String banknum, int money);

    public String addMoney(int money,int member_id);

    public MemberInfo editCardInfo(int member_id, String bankcnum);

    public String scoreToMoney(int member_id, int score);

    public String modifyMemberPassword(int member_id, String old_password, String new_password);

}