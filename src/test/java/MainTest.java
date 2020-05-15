import com.roderick.StartApplication;
import com.roderick.dao.ImageDao;
import com.roderick.dao.VehicleDao;
import com.roderick.pojo.Vehicle;
import com.roderick.service.ImageService;
import com.roderick.utils.VehicleIdentification;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest(classes = StartApplication.class)
public class MainTest {
    @Autowired
    VehicleDao vehicleDao;
    @Autowired
    ImageDao imageDao;
    @Autowired
    ImageService imageService;
    @Autowired
    DataSource dataSource;
    @Autowired
    StringEncryptor stringEncryptor;
    @Autowired
    VehicleIdentification vehicleIdentification;


    @Test
    public void insertTest() throws ParseException {
        Vehicle vehicle = new Vehicle();
        vehicle.setModel_name("audi rs6");
        Date parse = new SimpleDateFormat("mm:ss.SSS").parse("6:39.231");
        vehicle.setLap_time(parse);
        vehicle.setIntroduction("推头王");
        vehicleDao.insertVehicle(vehicle);
    }

    @Test
    public void encryptTest() {
        String identification = vehicleIdentification.identification("F:\\学习\\上吊教程\\Project\\圈速展示\\src\\main\\resources\\static\\images\\vehicle\\1200px-Festival_automobile_international_2014_-_Alfa_Romeo_4C_-_009.jpg");
        System.out.println(identification);
    }

    @Test
    public void deleteTest() {
        File file = new File("F:\\学习\\上吊教程\\Project\\圈速展示\\src\\main\\resources\\static\\images\\temp");
        System.out.println(file.delete());  //要求递归删除
    }

}
