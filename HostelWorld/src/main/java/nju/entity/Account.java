package nju.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Srf on 2017/3/15
 */

@Entity
@Table(name="account")
public class Account {

    @Id
    @GeneratedValue
    private int id;
    @Column(name="cost")
    private int cost;
    //"apply","approve"
    @Column(name="status")
    private String status;
    //"recharge","appointment","cancel","settle","cardpay","cash"
    @Column(name="type")
    private String type;
    @Column(name="membername")
    private String membername;
    @Column(name="date")
    private Date date;
    @ManyToOne
    @JoinColumn(name="mid")
    private Member member;
    @ManyToOne
    @JoinColumn(name="hid")
    private Hotel hotel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
