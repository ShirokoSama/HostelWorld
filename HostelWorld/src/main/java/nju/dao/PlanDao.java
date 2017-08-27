package nju.dao;

import nju.entity.Plan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Srf on 2017/3/11
 */

public interface PlanDao extends Repository<Plan, Integer>{

    public Plan save(Plan a);

    public List<Plan> findAll();

    public Plan findOne(Integer id);

    public void delete(Integer id);

    public void delete(Plan a);

    @Query("select p from Plan p where p.hotel.id = ?1 and p.type = ?2")
    public List<Plan> findByHidAndType(int hid, String type);

}
