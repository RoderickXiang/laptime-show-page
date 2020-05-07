package com.roderick.service;

import com.roderick.dao.VehicleDao;
import com.roderick.pojo.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    VehicleDao vehicleDao;

    @Autowired
    public void setVehicleDao(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    public List<Vehicle> listVehicle() {
        return vehicleDao.listVehicleByOrder();
    }

    public Vehicle getVehicleById() {
        return null;
    }
}
