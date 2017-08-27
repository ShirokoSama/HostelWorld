package nju.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Srf on 2017/3/6
 */

@Entity
@Table(name="appointment")
public class Appointment {

    @Id
    @GeneratedValue
    private int id;
    @Column(name="identitynum")
    private String identitynum;
    @Column(name="type")
    private String type;
    @Column(name="num")
    private int num;
    @Column(name="days")
    private int days;
    @Column(name="cost")
    private int cost;
    //"ordering","canceled","settled"
    @Column(name="status")
    private String status;
    @Column(name="date")
    private Date date;
    @ManyToOne
    @JoinColumn(name="hid")
    private Hotel hotel;
    @ManyToOne
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

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getDays() {
        return this.days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Hotel getHotel() {
        return this.hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Member getMember() {
        return this.member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

}
