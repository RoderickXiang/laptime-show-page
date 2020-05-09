package com.roderick.dao;

import com.roderick.pojo.Vehicle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface VehicleDao {
    List<Vehicle> listVehicle();

    List<Vehicle> listVehicleByOrder();

    Vehicle getVehicleById(int id);

    void insertVehicle(Vehicle vehicle);

    void deleteVehicleById(int id);
}
