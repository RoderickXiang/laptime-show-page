package com.roderick.service;

import com.roderick.dao.VehicleDao;
import com.roderick.pojo.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


public interface VehicleService {

    /**
     * 按照圈速时间顺序返回结果
     *
     * @return 车辆列表
     */
    List<Vehicle> listVehicle();

    Vehicle getVehicleById(int id);

    void deleteVehicleById(int id);

    /**
     * 添加车辆图片
     *
     * @param file 文件上传对象
     */
    void addVehicleImage(MultipartFile file, HttpServletRequest request) throws IOException;

    void insertVehicle(Vehicle vehicle);
}
