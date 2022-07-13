import com.springstarter.AccountManager;
import com.springstarter.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

public class Test {

    @Autowired
    private static MongoTemplate mongoTemplate;
    public static void main(String args[]) {
        List<User> list = mongoTemplate.findAll(User.class, "author");
        System.out.println(list.get(0));
    }
}
