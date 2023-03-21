package sg.edu.nus.iss.pizzaappredo.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import sg.edu.nus.iss.pizzaappredo.model.Delivery;
import sg.edu.nus.iss.pizzaappredo.model.Order;
import sg.edu.nus.iss.pizzaappredo.model.Pizza;
import sg.edu.nus.iss.pizzaappredo.repo.PizzaRepository;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepo;

    public static final String[] PIZZA_NAMES = {
        "bella", "margherita", "marinara", "spianatacalabrese", "trioformaggio"
    };

    public static final String[] PIZZA_SIZES = { "sm", "md", "lg"};

    private final Set<String> pizzaNames;
    private final Set<String> pizzaSizes;
    
    public PizzaService(){
        pizzaNames = new HashSet<String>(Arrays.asList(PIZZA_NAMES));
        pizzaSizes = new HashSet<String>(Arrays.asList(PIZZA_SIZES));
    }
    
    public List<ObjectError> validatePizzaOrder(Pizza p){

        List<ObjectError> errors = new LinkedList<>();
        FieldError error;

        if(!pizzaNames.contains(p.getPizzaName().toLowerCase())){
            error = new FieldError("pizzaName", "pizzaName", 
                    "We do not have the %s pizza. ".formatted(p.getPizzaName()));
            errors.add(error);
        }

        if(!pizzaSizes.contains(p.getSize().toLowerCase())){
            error = new FieldError("size", "size", 
                    "We do not have the %s size. ".formatted(p.getSize()));
            errors.add(error);
        }

        return errors;

    }

    public Order savePizzaOrder(Pizza p, Delivery d){
        Order o = createPizzaOrder(p,d);
        pizzaRepo.save(o);
        return o;
    }

    private Order createPizzaOrder(Pizza p, Delivery d) {
        String orderId = UUID.randomUUID().toString().substring(0,8);
        Order o = new Order(p,d);
        o.setPizzaCost(pizzaCost(o));
        o.setOrderId(orderId);
        

        return o;
    }

    public float pizzaCost(Order o){

        float total=0.2f;
        switch(o.getPizzaName()){
            case "margherita":
                total+=22;
                break;

            case "trioformaggio":
                total+=25;
                break;

            case "bella", "" , "marinara", "spianatacalabrese":
                total+=30;
                break;
        }

        switch(o.getSize()){
            case "sm":
                total *=1;
                break;
            case "md":
                total *=1.2;
                break;
            case "lg":
                total *=1.5;
                break;
        }

        total = total*o.getQuantity();
        
        return total;
    }
    
}
