package nju.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Srf on 2017/3/6
 */

@Entity
@Table(name="card")
public class Card implements Serializable{

    @Id
    private int id;
    @Column(name="identitynum")
    private String identitynum;
    @Column(name="bankcnum")
    private String bankcnum;
    @Column(name="money")
    private int money;
    @Column(name="level")
    private int level;
    @Column(name="costcount")
    private int costcount;
    @Column(name="score")
    private int score;
    @Column(name="createdate")
    private Date createdate;
    @Column(name="suspenddate")
    private Date suspenddate;
    @Column(name="terminatedate")
    private Date terminatedate;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="mid")
    private Member member;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentitynum() {
        return this.identitynum;
    }

    public void setIdentitynum(String identitynum) {
        this.identitynum = identitynum;
    }

    public String getBankcnum() {
        return this.bankcnum;
    }

    public void setBankcnum(String bankcnum) {
        this.bankcnum = bankcnum;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCostcount() {
        return this.costcount;
    }

    public void setCostcount(int costcount) {
        this.costcount = costcount;
    }

    public Date getCreatedate() {
        return this.createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getSuspenddate() {
        return this.suspenddate;
    }

    public void setSuspenddate(Date suspenddate) {
        this.suspenddate = suspenddate;
    }

    public Date getTerminatedate() {
        return this.terminatedate;
    }

    public void setTerminatedate(Date terminatedate) {
        this.terminatedate = terminatedate;
    }

    public Member getMember() {
        return this.member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

}
