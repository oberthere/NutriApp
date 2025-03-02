package edu.rit.swen262;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import edu.rit.swen262.ui.PageRunner;

@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

    // Define PageRunner manually as a Spring Bean
    @Bean
    public PageRunner pageRunner() {
        return new PageRunner();
    }

    @Bean
    CommandLineRunner runPageRunner(PageRunner pageRunner) {
        return args -> {
            pageRunner.startUp();
            pageRunner.runPage();
        };
    }
}
