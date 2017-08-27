package nju.dao;

import nju.entity.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Srf on 2017/3/11
 */

public interface AppointmentDao extends Repository<Appointment, Integer>{

    public Appointment save(Appointment a);

    public List<Appointment> findAll();

    public Appointment findOne(Integer id);

    public void delete(Integer id);

    public void delete(Appointment a);

    @Query("select max(a.identitynum) from Appointment a")
    public String findMaxIdentityNum();

}
