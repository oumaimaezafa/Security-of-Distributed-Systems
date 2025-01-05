package ma.oumaimaezafa.orderservice.repositories;

import ma.oumaimaezafa.orderservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,String> {
}
