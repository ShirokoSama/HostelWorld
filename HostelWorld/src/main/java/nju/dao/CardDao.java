package nju.dao;

import nju.entity.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Srf on 2017/3/11
 */

public interface CardDao extends Repository<Card, Integer> {

    public Card save(Card a);

    public List<Card> findAll();

    public Card findOne(Integer id);

    public void delete(Integer id);

    public void delete(Card a);

    public void flush();

    public Card saveAndFlush(Card c);

    @Query("select c from Card c where c.identitynum = ?1")
    public List<Card> findByIdentityNum(String identitynum);

}
