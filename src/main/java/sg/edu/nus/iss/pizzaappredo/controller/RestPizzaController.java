package sg.edu.nus.iss.pizzaappredo.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.pizzaappredo.model.Order;
import sg.edu.nus.iss.pizzaappredo.service.PizzaService;

@RestController
@RequestMapping
public class RestPizzaController {
    
    @Autowired
    private PizzaService pizzaSvc;
    
    @GetMapping(path="/order/{orderId}")
        public ResponseEntity<String> getOrder(@PathVariable String orderId) throws IOException{
        Optional<Order> order = pizzaSvc.getOrder(orderId);

        if(order.isEmpty()){
            JsonObject error = Json.createObjectBuilder()
                    .add("message", "Order %s not found".formatted(orderId))
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error.toString());
        }
        return ResponseEntity.ok(order.get().toJSON().toString());
        // return ResponseEntity.ok("All is well");
    
    }
}
