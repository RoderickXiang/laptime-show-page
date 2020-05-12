import com.roderick.StartApplication;
import com.roderick.dao.ImageDao;
import com.roderick.dao.VehicleDao;
import com.roderick.pojo.Vehicle;
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
    DataSource dataSource;

    @Test
    public void daoTest() throws ParseException {
        /*Vehicle vehicle = new Vehicle();
        vehicle.setModel_name("Id测试");
        Date parse = new SimpleDateFormat("mm:ss.SSS").parse("06:39.231");
        vehicle.setLap_time(parse);
        vehicleDao.insertVehicle(vehicle);*/
        File file = new File("F:\\学习\\上吊教程\\Project\\圈速展示\\src\\main\\resources\\static\\images\\vehicle\\" + "0cf6812a-78db-436f-a11e-7812f1d88a15.png");
        System.out.println(file.exists());
    }

    @Test
    public void insertTest() throws ParseException {
        Vehicle vehicle = new Vehicle();
        vehicle.setModel_name("audi rs6");
        Date parse = new SimpleDateFormat("mm:ss.SSS").parse("6:39.231");
        vehicle.setLap_time(parse);
        vehicle.setIntroduction("推头王");
        vehicleDao.insertVehicle(vehicle);
    }
}
