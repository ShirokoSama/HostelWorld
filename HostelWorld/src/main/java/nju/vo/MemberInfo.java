package nju.vo;

import nju.entity.Card;
import nju.entity.Member;

import java.util.Date;

/**
 * Created by Srf on 2017/3/12
 */

public class MemberInfo {

    private int id;
    private String name;
    private String status;
    private String identitynum;
    private String bankcnum;
    private int money;
    private int level;
    private int costcount;
    private int score;
    private Date createdate;
    private Date suspenddate;
    private Date terminatedate;

    public MemberInfo(Member member) {
        this.id = member.getId();
        this.name = member.getMembername();
        switch (member.getStatus()){
            case "not active":
                this.status = "未激活";
                break;
            case "active":
                this.status = "已激活";
                break;
            default:
                this.status = "暂停中";
        }
        Card card = member.getCard();
        if(card!=null){
            this.identitynum = card.getIdentitynum();
            this.bankcnum = card.getBankcnum();
            this.money = card.getMoney();
            this.level = card.getLevel();
            this.costcount = card.getCostcount();
            this.score = card.getScore();
            this.createdate = card.getCreatedate();
            this.suspenddate = card.getSuspenddate();
            this.terminatedate = card.getTerminatedate();
        }
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getStatus() {
        return this.status;
    }

    public String getIdentitynum() {
        return this.identitynum;
    }

    public String getBankcnum() {
        return this.bankcnum;
    }

    public int getMoney() {
        return this.money;
    }

    public int getLevel() {
        return this.level;
    }

    public int getCostcount() {
        return this.costcount;
    }

    public int getScore() {
        return this.score;
    }

    public Date getCreatedate() {
        return this.createdate;
    }

    public Date getSuspenddate() {
        return this.suspenddate;
    }

    public Date getTerminatedate() {
        return this.terminatedate;
    }

}
