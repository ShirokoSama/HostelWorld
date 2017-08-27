package nju.vo;

import nju.entity.Room;
import nju.util.RoomTypeFilter;

/**
 * Created by Srf on 2017/3/15
 */

public class RoomInfo {

    private int id;
    private String type;
    private String status;
    private String roomNum;
    private String hotelName;

    public RoomInfo(Room room) {
        this.id = room.getId();
        this.type = RoomTypeFilter.enToCh(room.getType());
        this.status = room.getStatus();
        this.roomNum = room.getRoomnum();
        this.hotelName = room.getHotel().getName();
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public String getHotelName() {
        return hotelName;
    }
}
