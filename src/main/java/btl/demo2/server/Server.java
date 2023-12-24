package btl.demo2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "btl.demo2")
@EnableJpaRepositories(basePackages = "btl.demo2.repository")
@EntityScan(basePackages = "btl.demo2.model")
public class Server {
    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
}
