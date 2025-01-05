package ma.oumaimaezafa.orderservice.feign;

import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor @Getter @Setter @AllArgsConstructor @Builder @ToString
public class Product {
    private String id;
    private  String name;
    private Double price ;
    private  int quantity;
}
