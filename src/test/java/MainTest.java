import com.roderick.StartApplication;
import com.roderick.dao.ImageDao;
import com.roderick.dao.VehicleDao;
import com.roderick.pojo.Vehicle;
import com.roderick.service.ImageService;
import com.roderick.utils.VehicleIdentification;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.io.File;
import java.text.ParseException;

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
    @Value("${file.uploadFolder}")
    String uploadFolder;
    @Value("${spring.datasource.username}")
    String dataBaseUsername;


    @Test
    public void insertTest() throws ParseException {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(11);
        vehicle.setModel_name("修改测试2");
        vehicleDao.updateVehicle(vehicle);
    }

    @Test
    public void encryptTest() {
        /*String admin = stringEncryptor.encrypt("admin");    //加密
        System.out.println(stringEncryptor.decrypt(admin)); //解密*/
        System.out.println(dataBaseUsername);
    }

    @Test
    public void deleteTest() {
        File file = new File("F:/学习/上吊教程/Project/圈速展示/src/main/resources/static/images/vehicle/");
        System.out.println(file);
    }

    @Test
    public void imageDaoTest() {
        System.out.println(uploadFolder);
    }
}
