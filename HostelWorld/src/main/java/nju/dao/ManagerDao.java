package nju.dao;

import nju.entity.Manager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Srf on 2017/3/11
 */

public interface ManagerDao extends Repository<Manager, Integer> {

    public List<Manager> findAll();

    public Manager findOne(Integer id);

    public long count();

    @Query("select m from Manager m where m.name = ?1")
    public List<Manager> findByName(String name);

}
