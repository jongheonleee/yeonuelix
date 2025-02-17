package yeo.nuel.lix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class YeonuelixBatchApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(YeonuelixBatchApplication.class, args);
        SpringApplication.exit(run);
    }
}
