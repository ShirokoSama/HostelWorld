package nju.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Srf on 2017/3/6
 */

@Entity
@Table(name="hotel")
public class Hotel implements Serializable{

    @Id
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="password")
    private String password;
    @Column(name="identitynum")
    private String identitynum;
    @Column(name="address")
    private String address;
    @Column(name="description")
    private String description;
    @Column(name="status")
    private String status;
    @Column(name="plandate")
    private Date planDate;
    @OneToMany(mappedBy="hotel", cascade=CascadeType.ALL)
    private Set<Plan> plans = new HashSet<>();
    @OneToMany(mappedBy="hotel", cascade=CascadeType.ALL)
    private Set<Room> rooms = new HashSet<>();
    @OneToMany(mappedBy="hotel")
    private Set<Appointment> appointments = new HashSet<>();
    @OneToMany(mappedBy="hotel")
    private Set<Accomodation> accomodations = new HashSet<>();
    @OneToMany(mappedBy="hotel")
    private Set<Account> accounts = new HashSet<>();

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentitynum() {
        return this.identitynum;
    }

    public void setIdentitynum(String identitynum) {
        this.identitynum = identitynum;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }



    public Set<Plan> getPlans() {
        return this.plans;
    }

    public void setPlans(Set<Plan> plans) {
        this.plans = plans;
    }

    public void addPlan(Plan plan) {
        if(!this.plans.contains(plan)){
            this.plans.add(plan);
            plan.setHotel(this);
        }
    }

    public void removePlan(Plan plan) {
        plan.setHotel(null);
        this.plans.remove(plan);
    }

    public Set<Room> getRooms() {
        return this.rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        if(!this.rooms.contains(room)){
            this.rooms.add(room);
            room.setHotel(this);
        }
    }

    public void removeRoom(Room room) {
        room.setHotel(null);
        this.rooms.remove(room);
    }

    public Set<Appointment> getAppointments() {
        return this.appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void addAppointment(Appointment appointment) {
        if(!this.appointments.contains(appointment)){
            this.appointments.add(appointment);
            appointment.setHotel(this);
        }
    }

    public void removeAppoinment(Appointment appointment) {
        appointment.setHotel(null);
        this.appointments.remove(appointment);
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
            accomodation.setHotel(this);
        }
    }

    public void removeAccomodation(Accomodation accomodation) {
        accomodation.setHotel(null);
        this.accomodations.remove(accomodation);
    }

    public Set<Account> getAccounts() {
        return this.accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account) {
        if(!this.accounts.contains(account)){
            this.accounts.add(account);
            account.setHotel(this);
        }
    }

    public void removeAccount(Account account) {
        account.setHotel(null);
        this.accounts.remove(account);
    }

}
