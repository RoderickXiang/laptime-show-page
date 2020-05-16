package com.roderick.service.Impl;

import com.roderick.dao.ImageDao;
import com.roderick.service.ImageService;
import com.roderick.service.VehicleService;
import com.roderick.utils.VehicleIdentification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    ImageDao imageDao;
    VehicleService vehicleService;
    VehicleIdentification vehicleIdentification;

    @Value("${file.uploadFolder}")
    private String uploadFolder;    //车辆图片存放的位置

    @Value("${file.tempUploadFolder}")
    private String tempUploadFolder;    //车辆识别暂存位置

    @Autowired
    public void setImageDao(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    @Autowired
    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Autowired
    public void setVehicleIdentification(VehicleIdentification vehicleIdentification) {
        this.vehicleIdentification = vehicleIdentification;
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
    public void deleteImageByVehicleId(int id) throws IOException {
        String imagePath = imageDao.getImagePathByVehicleId(id);    //获取图片名称包含图片格式
        if (imagePath != null) {
            File file = new File(uploadFolder + "/" + imagePath);
            if (file.exists()) { //删除图片
                boolean delete = file.delete();
            }
            imageDao.deleteImageByVehicleId(id);
        }
    }

    @Override
    public String addTempVehicleImage(MultipartFile file) throws IOException {
        File realPath = new File(tempUploadFolder);
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
        file.transferTo(new File(tempUploadFolder + "/" + fileName));
        return fileName;
    }

    @Override
    public String vehicleIdentification(String imagePath) {
        imagePath = tempUploadFolder + "\\" + imagePath;
        String result_json = vehicleIdentification.identification(imagePath);
        this.deleteTempImage(tempUploadFolder);
        return result_json;
    }

    @Override
    public void deleteTempImage(String path) {
        File file = new File(path);
        if (file.exists()) {
            String[] list = file.list();
            if (list != null)
                for (String fileName : list) {
                    File tempFile = new File(path, fileName);
                    if (tempFile.isDirectory()) {
                        this.deleteTempImage(tempFile.getAbsolutePath());   //递归调用
                    }
                    boolean delete = tempFile.delete();
                }
        }
    }
}
