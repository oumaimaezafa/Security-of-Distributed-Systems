package ma.oumaimaezafa.E_com_App.web;


import ma.oumaimaezafa.E_com_App.entities.Product;
import ma.oumaimaezafa.E_com_App.repositoriy.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRestController {
    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/products")
    public List<Product> productList(){
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Product productById(@PathVariable String id){
        return  productRepository.findById(id).get();
    }


}
