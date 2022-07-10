package jp.co.jeus.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Application Main classg
 */
@EnableAsync
@SpringBootApplication
public class Application {
    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
