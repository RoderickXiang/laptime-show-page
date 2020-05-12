package com.roderick.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface ImageService {

    /**
     * 添加车辆图片
     *
     * @param file 文件储存对象
     * @return imageName 车辆图片文件的名字
     */
    String addVehicleImage(MultipartFile file) throws IOException;

    /**
     * 删除车辆图片
     *
     * @param vehicleId 车辆id
     */
    void deleteImageByVehicleId(int vehicleId) throws IOException;
}
