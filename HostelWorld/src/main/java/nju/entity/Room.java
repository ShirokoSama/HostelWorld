package nju.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Srf on 2017/3/6
 */

@Entity
@Table(name="room")
public class Room implements Serializable{

    @Id
    @GeneratedValue
    private int id;
    @Column(name="type")
    private String type;
    @Column(name="status")
    private String status;
    @Column(name="roomnum")
    private String roomnum;
    @ManyToOne
    @JoinColumn(name="hid")
    private Hotel hotel;
    @OneToMany(mappedBy="room")
    private Set<Accomodation> accomodations = new HashSet<>();

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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoomnum() {
        return this.roomnum;
    }

    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum;
    }

    public Hotel getHotel() {
        return this.hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Set<Accomodation> getAccomodations() {
        return this.accomodations;
    }

    public void setAccomodations(Set<Accomodation> accomodations) {
        this.accomodations = accomodations;
    }

    public void addAccomodation(Accomodation accomodation) {
        if(!this.accomodations.contains(accomodation)){
            this.accomodations.add(accomodation);
            accomodation.setRoom(this);
        }
    }

    public void removeAccomodation(Accomodation accomodation) {
        accomodation.setHotel(null);
        this.accomodations.remove(accomodation);
    }

}
