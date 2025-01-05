package ma.oumaimaezafa.orderservice;

import ma.oumaimaezafa.orderservice.entities.Order;
import ma.oumaimaezafa.orderservice.entities.OrderState;
import ma.oumaimaezafa.orderservice.entities.ProductItem;
import ma.oumaimaezafa.orderservice.feign.InventoryRestClient;
import ma.oumaimaezafa.orderservice.feign.Product;
import ma.oumaimaezafa.orderservice.repositories.OrderRepository;
import ma.oumaimaezafa.orderservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(OrderRepository orderRepository, ProductItemRepository productItemRepository, InventoryRestClient inventoryRestClient){
        return args ->{

        List<String> allProductsIds= List.of("P01","P02","P03");
            for (int i = 0; i < 5; i++) {
                Order order=Order.builder()
                        .id(UUID.randomUUID().toString())
                        .date(LocalDate.now())
                        .state(OrderState.PENDING)
                        .build();
                Order savedOrder=orderRepository.save(order);
                System.out.println("Saved Order: " + savedOrder);
                allProductsIds.forEach(p -> {
                    ProductItem productItem = ProductItem.builder()
                            .productId(p)
                            .quantity(new Random().nextInt(20))
                            .prix(Math.random() * 600 + 100)
                            .order(savedOrder)
                            .build();
                    ProductItem savedProductItem = productItemRepository.save(productItem);
                    System.out.println("Saved ProductItem: " + savedProductItem);
                });

            }

        };
    }
}
