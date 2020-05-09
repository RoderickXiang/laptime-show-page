package com.roderick.service;

import com.roderick.dao.VehicleDao;
import com.roderick.pojo.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface VehicleService {

    void setVehicleDao(VehicleDao vehicleDao);

    /**
     * 按照圈速时间顺序返回结果
     * @return 车辆列表
     */
    List<Vehicle> listVehicle();

    Vehicle getVehicleById(int id);

    void deleteVehicleById(int id);
}
