package com.roderick.service.Impl;

import com.roderick.dao.VehicleDao;
import com.roderick.pojo.Vehicle;
import com.roderick.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    VehicleDao vehicleDao;

    @Autowired
    public void setVehicleDao(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    @Override
    public List<Vehicle> listVehicle() {
        return vehicleDao.listVehicleByOrder();
    }

    @Override
    public Vehicle getVehicleById(int id) {
        return vehicleDao.getVehicleById(id);
    }

    @Override
    public void deleteVehicleById(int id) {
        vehicleDao.deleteVehicleById(id);
    }
}
