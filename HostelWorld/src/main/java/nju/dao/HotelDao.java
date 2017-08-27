package nju.dao;

import nju.entity.Hotel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Srf on 2017/3/10
 */

public interface HotelDao extends Repository<Hotel, Integer> {

    public Hotel save(Hotel h);

    public List<Hotel> findAll();

    public Hotel findOne(Integer id);

    public long count();

    public void delete(Integer id);

    public void delete(Hotel h);

    @Query("select h from Hotel h where h.name = ?1 and h.status = 'working'")
    public List<Hotel> findByName(String name);

    @Query("select h from Hotel h where h.identitynum = ?1 and h.status = 'working'")
    public List<Hotel> findByIdentitynum(String identitynum);

    @Query("select h from Hotel h where h.name = ?1")
    public List<Hotel> findAllByName(String name);

    @Query("select h from Hotel h where h.identitynum = ?1")
    public List<Hotel> findAllByIdentitynum(String identitynum);

    @Query("select h from Hotel h where h.status = 'working' order by h.id")
    public List<Hotel> findAllWorking();

    @Query("select h from Hotel h where h.status = 'register'")
    public List<Hotel> findAllRegister();

    @Query("select h from Hotel h where h.status = 'edit'")
    public List<Hotel> findAllEdit();

}
