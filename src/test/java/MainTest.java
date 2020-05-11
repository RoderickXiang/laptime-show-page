import com.roderick.StartApplication;
import com.roderick.dao.ImageDao;
import com.roderick.dao.VehicleDao;
import com.roderick.pojo.Vehicle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
        imageDao.insertImage(43, "test");
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
