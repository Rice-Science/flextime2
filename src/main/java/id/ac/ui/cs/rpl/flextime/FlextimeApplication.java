package id.ac.ui.cs.rpl.flextime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FlextimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlextimeApplication.class, args);
    }

}
