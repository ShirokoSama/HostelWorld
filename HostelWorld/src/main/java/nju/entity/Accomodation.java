package nju.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Srf on 2017/3/6
 */

@Entity
@Table(name="accomodation")
public class Accomodation {

    @Id
    @GeneratedValue
    private int id;
    @Column(name="arrivedate")
    private Date arrivedate;
    @Column(name="leavedate")
    private Date leavedate;
    @Column(name="cost")
    private int cost;
    @Column(name="roomtype")
    private String roomtype;
    @Column(name="membername")
    private String membername;
    //"plan","card","cash"
    @Column(name="paytype")
    private String paytype;
    @ManyToOne
    @JoinColumn(name="mid")
    private Member member;
    @ManyToOne
    @JoinColumn(name="hid")
    private Hotel hotel;
    @ManyToOne
    @JoinColumn(name="rid")
    private Room room;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getArrivedate() {
        return this.arrivedate;
    }

    public void setArrivedate(Date arrivedate) {
        this.arrivedate = arrivedate;
    }

    public Date getLeavedate() {
        return this.leavedate;
    }

    public void setLeavedate(Date leavedate) {
        this.leavedate = leavedate;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
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

    public Room getRoom() {
        return this.room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

}
