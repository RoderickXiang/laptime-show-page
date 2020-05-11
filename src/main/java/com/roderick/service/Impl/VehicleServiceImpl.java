package com.roderick.service.Impl;

import com.roderick.dao.ImageDao;
import com.roderick.dao.VehicleDao;
import com.roderick.pojo.Vehicle;
import com.roderick.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VehicleServiceImpl implements VehicleService {
    VehicleDao vehicleDao;
    ImageDao imageDao;

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

    @Override
    public String addVehicleImage(MultipartFile file) throws IOException {
        File realPath = new File(uploadFolder); //就怕没有目录
        if (!realPath.exists()) {
            boolean mkdir = realPath.mkdir();
        }
        String originalFilename = file.getOriginalFilename();
        String fileSuffix = null;
        //获取后缀
        if (originalFilename != null && !"".equals(originalFilename)) {
            fileSuffix = originalFilename.substring(originalFilename.lastIndexOf(".")); //"."也是要的
        }

        //通过UUID防止文件名重复
        String fileName = UUID.randomUUID().toString() + fileSuffix;
        file.transferTo(new File(uploadFolder + "/" + fileName));
        return fileName;
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
        System.out.println(vehicleId);
        String imageName = this.addVehicleImage(file); //返回文件的名字（UUID）
        imageDao.insertImage(vehicleId, imageName); //添加文件名到数据库
    }

}
