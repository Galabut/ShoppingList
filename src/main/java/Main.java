import com.shopping.JPAUtility;
import com.shopping.model.Item;
import com.shopping.model.ShoppingList;
import com.shopping.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Julia Galabut on 5/6/17.
 */
@SpringBootApplication
@ComponentScan({"com.shopping"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
