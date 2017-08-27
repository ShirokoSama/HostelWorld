package nju.dao;

import nju.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Srf on 2017/3/11
 */

public interface RoomDao extends JpaRepository<Room, Integer> {

//    public Room save(Room a);
//
//    public List<Room> save(List<Room> rooms);
//
//    public Room saveAndFlush(Room r);
//
//    public List<Room> findAll();
//
//    public Room findOne(Integer id);
//
//    public void delete(Integer id);
//
//    public void delete(Room a);

    @Query("select r from Room r where r.hotel.id = ?1 and r.roomnum = ?2")
    public List<Room> findRoomByRoomNum(int hid, String roomnum);

    @Query("select r from Room r where r.hotel.id = ?1 order by r.roomnum")
    public List<Room> findAllHotelRooms(int id);

    @Query("select count(r) from Room r where r.status = 'empty' and r.hotel.id = ?1 and r.type = ?2")
    public int countEmptyNum(int hid, String type);

    @Query("select count(r) from Room r where r.hotel.id = ?1 and r.type = ?2")
    public int countTotalNum(int hid, String type);

    @Query("select r from  Room r where r.hotel.id = ?1 and r.type = ?2 and r.status = 'empty'")
    public List<Room> findAllHotelEmptyRoomByType(int hid, String type);

}
