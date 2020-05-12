package com.roderick.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ImageDao {

    void insertImage(@Param("vehicle_id") int vehicle_id, @Param("image_path") String image_path);

/*
    Integer getImageIdByVehicleId(@Param("vehicle_id") int vehicle_id);
*/

    String getImagePathByVehicleId(@Param("vehicle_id") int vehicle_id);

    List<String> getImagePathListByVehicleId(int vehicle_id);

    void deleteImageByVehicleId(@Param("vehicle_id") int vehicle_id);
}
