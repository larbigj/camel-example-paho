package example.camel.paho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamelApplication
{
    public static void main(String[] args) throws Exception
    {
        new SpringApplication(CamelApplication.class).run();
    }
}
