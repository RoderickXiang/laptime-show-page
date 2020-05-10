package com.roderick.service.Impl;

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
import java.util.List;
import java.util.UUID;

@Service
public class VehicleServiceImpl implements VehicleService {
    VehicleDao vehicleDao;

    @Value("${file.uploadFolder}")
    private String uploadFolder;

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

    @Override
    public void addVehicleImage(MultipartFile file, HttpServletRequest request) throws IOException {
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
    }
}
