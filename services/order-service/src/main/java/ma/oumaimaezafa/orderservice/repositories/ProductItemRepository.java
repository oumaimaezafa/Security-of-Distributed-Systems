package ma.oumaimaezafa.orderservice.repositories;

import ma.oumaimaezafa.orderservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<ProductItem,Long> {
}
