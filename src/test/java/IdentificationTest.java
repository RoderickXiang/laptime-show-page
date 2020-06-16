import com.fasterxml.jackson.databind.ObjectMapper;
import com.roderick.StartApplication;
import com.roderick.utils.VehicleIdentification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@SpringBootTest(classes = StartApplication.class)
public class IdentificationTest {
    @Autowired
    VehicleIdentification vehicleIdentification;

    @Test
    public void carIdentificationTest() throws JSONException {
        File file = new File("F:/Test");    //原文件夹
        String notCarPath = "F:/NotCar";    //不是车的文件夹
        File[] imageList = file.listFiles();
        for (File image : imageList) {
            if (image.isFile()) {
                String result = vehicleIdentification.identification(image.getAbsolutePath());
                JSONObject jsonObject = new JSONObject(result);
                try {
                    JSONObject json = (JSONObject) jsonObject.getJSONArray("result").get(0);
                    String name = json.getString("name");
                    if ("非车类".equals(name)) {
                        System.out.println(image.renameTo(new File(notCarPath + "/" + name
                                + "-"
                                + UUID.randomUUID().toString().split("-")[0]
                                + ".jpg")));    //移到不是车的文件夹
                    } else {
                        System.out.println(name);
                        System.out.println(image.renameTo(new File(image.getParent() + "/" + name
                                + "-"
                                + UUID.randomUUID().toString().split("-")[0]
                                + ".jpg")));  //图片重命名
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    @Test
    public void getTheRightImage() throws IOException {
        String destDir = "F:/Cars";
        String destPath;
        for (int i = 0; i <= 58; i++) {
            File file = new File("E:/test/名车/" + i);
            File[] imageList = file.listFiles();
            for (File image : imageList) {
                if (image.isFile()) {
                    try {
                        BufferedImage sourceImage = ImageIO.read(image);
                        sourceImage.getWidth();
                        destPath = destDir + "/" + UUID.randomUUID() + ".jpg";
                        Files.copy(image.toPath(), new File(destPath).toPath());
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("废除" + image.getAbsolutePath());
                    }
                    /*System.out.println(image.renameTo(new File("F:/Cars" + "/" + UUID.randomUUID() + ".jpg")));*/
                }
            }
        }
    }
}
