package sg.edu.nus.iss.pizzaappredo.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.pizzaappredo.model.Delivery;
import sg.edu.nus.iss.pizzaappredo.model.Order;
import sg.edu.nus.iss.pizzaappredo.model.Pizza;
import sg.edu.nus.iss.pizzaappredo.service.PizzaService;

@Controller
@RequestMapping
public class PizzaController implements Serializable {
    
    @Autowired
    private PizzaService pizzaSvc;


    
    @GetMapping(path={"/", "/index.html"})
    public String getIndex(Model m, HttpSession session){

        //HttpSession is not really required. But good to have to invalidate session. 
        session.invalidate();
        m.addAttribute("pizza", new Pizza());
        return "index";
    }

    @PostMapping(path="/pizza")
    public String postPizza(@Valid Pizza pizza, BindingResult bindings, 
    Model m, HttpSession session){

        if(bindings.hasErrors()){
            return "index";
        }

        List<ObjectError> errors = pizzaSvc.validatePizzaOrder(pizza);
        if(!errors.isEmpty()){
            for(ObjectError e:errors)
                bindings.addError(e);
                return "index";
        }

        session.setAttribute("pizza", pizza);
        m.addAttribute("delivery", new Delivery());
        return "delivery";

    }

    @PostMapping(path="/pizza/order")
    public String postPizza(@Valid Delivery delivery, BindingResult bindings, 
    Model m, HttpSession session){

        if(bindings.hasErrors()){
            return "delivery";
        }

        Pizza p = (Pizza) session.getAttribute("pizza");
        Order o = pizzaSvc.savePizzaOrder(p, delivery);
        m.addAttribute("order", o);
        return "order";

    }



}
