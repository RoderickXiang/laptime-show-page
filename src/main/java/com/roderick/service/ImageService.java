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

    /**
     * 暂时存放图片用于车辆识别
     *
     * @param file 文件储存对象
     * @return 车辆图片文件的名字
     */
    String addTempVehicleImage(MultipartFile file) throws IOException;

    /**
     * 百度车辆识别
     *
     * @param imagePath 图片位置（/image/temp）
     * @return json结果
     */
    String vehicleIdentification(String imagePath);

    /**
     * 递归删除文件下的所有文件（车辆识别后调用此接口实现删除）
     *
     * @param absolutePath 文件或文件的绝对路径
     */
    void deleteTempImage(String absolutePath);
}
