import com.roderick.StartApplication;
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
    DataSource dataSource;

    @Test
    public void daoTest() {
        /*List<Vehicle> vehicles = vehicleDao.listVehicle();
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }*/
        Vehicle vehicle = vehicleDao.getVehicleById(6);
        System.out.println(vehicle.getLap_time_str());
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
