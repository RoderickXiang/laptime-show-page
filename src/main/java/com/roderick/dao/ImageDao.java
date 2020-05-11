package com.roderick.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ImageDao {

//    @Select("insert into image (vehicle_id,image_path) value (#{vehicle_id},#{image_path})")
    void insertImage(@Param("vehicle_id") int vehicle_id, @Param("image_path") String image_path);

    String getImageByVehicleId(int vehicle_id);

    List<String> getImageListByVehicleId(int vehicle_id);
}
