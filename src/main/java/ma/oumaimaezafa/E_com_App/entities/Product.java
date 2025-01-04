package ma.oumaimaezafa.E_com_App.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity

@AllArgsConstructor  @NoArgsConstructor
@Getter @Setter @ToString
@Builder
public class Product {
    @Id
    private String id;
    private  String name;
    private Double price ;
    private  int quantity;
}
