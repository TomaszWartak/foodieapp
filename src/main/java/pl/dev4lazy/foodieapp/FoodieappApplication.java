package pl.dev4lazy.foodieapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.dev4lazy.foodieapp.order.Order;

@SpringBootApplication
public class FoodieappApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodieappApplication.class, args);
    }

    /*@Bean
    public Order order() {
        return new Order();
    }*/

}
