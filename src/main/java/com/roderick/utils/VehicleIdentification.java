package com.roderick.utils;

import com.baidu.aip.imageclassify.AipImageClassify;
import org.jasypt.encryption.StringEncryptor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class VehicleIdentification {
    //设置APPID/AK/SK 使用Spring进行接管，必须从Spring容器中获取对象
    @Value("${baidu.APP_ID}")
    private String APP_ID;
    @Value("${baidu.API_KEY}")
    private String API_KEY;
    @Value("${baidu.SECRET_KEY}")
    private String SECRET_KEY;

    public static void main(String[] args) {
        VehicleIdentification vehicleIdentification = new VehicleIdentification();
        vehicleIdentification.identification("F:\\学习\\上吊教程\\Project\\圈速展示\\src\\main\\resources\\static\\images\\vehicle\\1200px-Festival_automobile_international_2014_-_Alfa_Romeo_4C_-_009.jpg");
    }

    public String identification(String imagePath) {// 参数为本地路径
        // 初始化一个AipImageClassify
        AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("top_num", "3");
        options.put("baike_num", "5");

        JSONObject res = client.carDetect(imagePath, options);
        return res.toString(2);
    }
}
