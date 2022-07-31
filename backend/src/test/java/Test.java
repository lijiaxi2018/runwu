import com.springstarter.pojo.*;
import com.springstarter.service.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;
import java.util.List;

public class Test {


    public static void main(String args[]) {
        FileSystemStorageService a = new FileSystemStorageService();
        try {
            a.copyFile("/backend/src/main/resources/uploads/users/lin/avatar.jpg", "/backend/src/main/resources/uploads/saber.jpg");
        } catch (IOException e) {
            System.out.println("failed");
        }

    }
}
