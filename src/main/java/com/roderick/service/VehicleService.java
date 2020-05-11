package com.roderick.service;

import com.roderick.pojo.Vehicle;
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
     * @param file 文件储存对象
     * @return imageName 车辆图片文件的名字
     */
    String addVehicleImage(MultipartFile file) throws IOException;

    int insertVehicle(Vehicle vehicle);

    /**
     * 添加车辆和车辆图片以及在数据库中记录图片的名称
     *
     * @param vehicle 新的车辆实体类
     * @param file 文件储存对象
     */
    void insertVehicle(Vehicle vehicle, MultipartFile file) throws IOException;
}
