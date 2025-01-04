package ma.oumaimaezafa.E_com_App.repositoriy;

import ma.oumaimaezafa.E_com_App.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepository  extends JpaRepository<Product,String> {
}
