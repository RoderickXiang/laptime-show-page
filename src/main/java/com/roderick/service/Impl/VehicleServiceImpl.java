package com.roderick.service.Impl;

import com.roderick.dao.ImageDao;
import com.roderick.dao.VehicleDao;
import com.roderick.pojo.Vehicle;
import com.roderick.service.ImageService;
import com.roderick.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    VehicleDao vehicleDao;
    ImageDao imageDao;
    ImageService imageService;

    @Value("${file.uploadFolder}")
    private String uploadFolder;    //车辆图片存放的位置

    @Autowired
    public void setVehicleDao(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    @Autowired
    public void setImageDao(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
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
    public void deleteVehicleById(int id) throws IOException {
        imageService.deleteImageByVehicleId(id);
        vehicleDao.deleteVehicleById(id);
    }

    @Override
    public int insertVehicle(Vehicle vehicle) {
        String lap_time_str = vehicle.getLap_time_str();
        Date lap_time = null;
        if (lap_time_str != null) { //时间转换
            try {
                lap_time = new SimpleDateFormat("mm:ss.SSS").parse(lap_time_str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        vehicle.setLap_time(lap_time);
        vehicleDao.insertVehicle(vehicle);
        return vehicle.getId();
    }

    @Override
    public void insertVehicle(Vehicle vehicle, MultipartFile file) throws IOException {
        int vehicleId = this.insertVehicle(vehicle);
        String imageName = imageService.addVehicleImage(file); //返回文件的名字（UUID）
        imageDao.insertImage(vehicleId, imageName); //添加文件名到数据库
    }

}
