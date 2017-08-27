package nju.dao;

import nju.entity.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Srf on 2017/3/7
 */

public interface MemberDao extends Repository<Member, Integer> {

    public Member save(Member m);

    public List<Member> findAll();

    public Member findOne(Integer id);

    public long count();

    public void delete(Integer id);

    public void delete(Member m);

    @Query("select m from Member m where m.membername = ?1")
    public List<Member> findByMembername(String membername);

}
