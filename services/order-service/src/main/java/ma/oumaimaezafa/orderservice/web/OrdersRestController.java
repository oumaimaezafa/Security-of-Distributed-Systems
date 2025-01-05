package ma.oumaimaezafa.orderservice.web;

import ma.oumaimaezafa.orderservice.entities.Order;
import ma.oumaimaezafa.orderservice.feign.InventoryRestClient;
import ma.oumaimaezafa.orderservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrdersRestController {
    @Autowired
   private OrderRepository orderRepository;
    @Autowired
    private InventoryRestClient inventoryRestClient;


   @GetMapping("/orders")
   public List<Order> findAllOrders(){
      List<Order> orderList= orderRepository.findAll();
       orderList.forEach(order-> {
           order.getProductItems().forEach(pi -> {
               pi.setProduct(inventoryRestClient.findProductById(pi.getProductId()));

           });
       });
       return  orderList;
   }
   @GetMapping("/orders/{id}")
   public Order findOrderById(@PathVariable String id){
       Order order= orderRepository.findById(id).get();
       order.getProductItems().forEach(pi->{
           pi.setProduct(inventoryRestClient.findProductById(pi.getProductId()));
       });
       return order;
   }
}
