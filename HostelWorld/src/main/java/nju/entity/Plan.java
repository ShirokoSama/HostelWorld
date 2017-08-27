package nju.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Srf on 2017/3/6
 */

@Entity
@Table(name="plan")
public class Plan implements Serializable{

    @Id
    @GeneratedValue
    private int id;
    @Column(name="type")
    private String type;
    @Column(name="totalnum")
    private int totalnum;
    @Column(name="staynum")
    private int staynum;
    @Column(name="ordernum")
    private int ordernum;
    @Column(name="plannum")
    private int plannum;
    @Column(name="price")
    private int price;
    @ManyToOne
    @JoinColumn(name="hid")
    private Hotel hotel;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotalnum() {
        return this.totalnum;
    }

    public void setTotalnum(int totalnum) {
        this.totalnum = totalnum;
    }

    public int getStaynum() {
        return this.staynum;
    }

    public void setStaynum(int staynum) {
        this.staynum = staynum;
    }

    public int getOrdernum() {
        return this.ordernum;
    }

    public void setOrdernum(int ordernum) {
        this.ordernum = ordernum;
    }

    public int getPlannum() {
        return this.plannum;
    }

    public void setPlannum(int plannum) {
        this.plannum = plannum;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Hotel getHotel() {
        return this.hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

}
