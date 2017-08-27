package nju.dao;

import nju.entity.Accomodation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Srf on 2017/3/11
 */

public interface AccomodationDao extends Repository<Accomodation, Integer>{

    public Accomodation save(Accomodation a);

    public List<Accomodation> findAll();

    public Accomodation findOne(Integer id);

    public void delete(Integer id);

    public void delete(Accomodation a);

}
