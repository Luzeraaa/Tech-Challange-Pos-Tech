package br.com.watchwatt.watchwatt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication()
public class WatchwattApplication {

  public static void main(String[] args) {
    SpringApplication.run(WatchwattApplication.class, args);
  }

}
