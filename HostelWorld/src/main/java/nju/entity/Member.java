package nju.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Srf on 2017/3/6
 */

@Entity
@Table(name="member")
public class Member implements Serializable{

    @Id
    private int id;
    @Column(name="membername")
    private String membername;
    @Column(name="password")
    private String password;
    @Column(name="status")
    private String status;
    @OneToOne(mappedBy="member")
    private Card card;
    @OneToMany(mappedBy="member")
    private Set<Appointment> appointments = new HashSet<>();
    @OneToMany(mappedBy="member")
    private Set<Accomodation> accomodations = new HashSet<>();
    @OneToMany(mappedBy="member")
    private Set<Account> accounts = new HashSet<>();

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMembername() {
        return this.membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Card getCard() {
        return this.card;
    }

    public void setCard(Card card) {
        this.card = card;
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
            appointment.setMember(this);
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
            accomodation.setMember(this);
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
            account.setMember(this);
        }
    }

    public void removeAccount(Account account) {
        account.setHotel(null);
        this.accounts.remove(account);
    }

}
