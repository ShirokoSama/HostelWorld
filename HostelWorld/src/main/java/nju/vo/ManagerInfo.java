package nju.vo;

import nju.entity.Manager;

/**
 * Created by Srf on 2017/3/12
 */
public class ManagerInfo {

    private int id;
    private String managerName;

    public ManagerInfo(Manager manager) {
        this.id = manager.getId();
        this.managerName = manager.getName();
    }

    public int getId() {
        return this.id;
    }

    public String getManagerName() {
        return this.managerName;
    }

}
