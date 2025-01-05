package ma.oumaimaezafa.orderservice.entities;
//coresond a un produit

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import ma.oumaimaezafa.orderservice.feign.Product;

@Entity
@NoArgsConstructor
@AllArgsConstructor @Builder @ToString @Getter @Setter
public class ProductItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productId;
    private Double prix;
    private int quantity;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Order order;
    @Transient //car il n'apas une entitie jpa
    private Product product;

}
