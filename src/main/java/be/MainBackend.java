package be;

import javafx.application.Application;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class MainBackend {

    private static final Logger logger = Logger.getLogger(Application.class);

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        logger.info("Entering application.");
        SpringApplication.run(MainBackend.class, args);
        logger.info("Exiting application.");
    }
}
