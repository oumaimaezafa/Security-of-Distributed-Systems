package ma.oumaimaezafa.E_com_App;

import ma.oumaimaezafa.E_com_App.entities.Product;
import ma.oumaimaezafa.E_com_App.repositoriy.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class EComAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EComAppApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository){
		return args->{
           productRepository.save(Product.builder().id("P01").name("Computer").price(23000.0).quantity(12).build());
		   productRepository.save(Product.builder().id("P02").name("Printer").price(1200.0).quantity(7).build());
		   productRepository.save(Product.builder().id("P03").name("Smart Phone").price(4300.0).quantity(5).build());
		};
	}
}
