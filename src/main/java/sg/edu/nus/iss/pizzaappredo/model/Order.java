package sg.edu.nus.iss.pizzaappredo.model;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Order {
    
    private String orderId;
    private Delivery delivery;
    private Pizza pizza;
    private float totalCost;
    private float pizzaCost;
    
    public Order() {}

    public Order(Pizza pizza, Delivery delivery) {
        this.pizza = pizza;
        this.delivery = delivery;
    }

    public Order(String orderId, Delivery delivery, Pizza pizza) {
        this.orderId = orderId;
        this.delivery = delivery;
        this.pizza = pizza;
    }

    public Order(Delivery delivery, Pizza pizza, float totalCost) {
        this.delivery = delivery;
        this.pizza = pizza;
        this.totalCost = totalCost;
    }

    public Order(String orderId, Delivery delivery, Pizza pizza, float totalCost, float pizzaCost) {
        this.orderId = orderId;
        this.delivery = delivery;
        this.pizza = pizza;
        this.totalCost = totalCost;
        this.pizzaCost = pizzaCost;
    }

    public String getOrderId() {return orderId;}
    public void setOrderId(String orderId) {this.orderId = orderId;}

    public Delivery getDelivery() {return delivery;}
    public void setDelivery(Delivery delivery) {this.delivery = delivery;}

    public Pizza getPizza() {return pizza;}
    public void setPizza(Pizza pizza) {this.pizza = pizza;}

    public float getPizzaCost(){return pizzaCost;}
    public void setPizzaCost(float pizzaCost) {this.pizzaCost = pizzaCost;}
   
    public void setTotalCost(float totalCost) {this.totalCost = totalCost;}

    public float getTotalCost() {
        if (this.isRush() == true){
            totalCost = this.getPizzaCost()+2;
            return totalCost;
        }
        return this.getPizzaCost();
    }

 
    public String getName(){return this.getDelivery().getName();}
    public String getAddress(){return this.getDelivery().getAddress();}
    public String getPhoneNumber(){return this.getDelivery().getPhoneNumber();}
    public boolean isRush(){return this.getDelivery().isRush();}
    public String getComments(){return this.getDelivery().getComments();}
    public String getPizzaName(){return this.getPizza().getPizzaName();}
    public String getSize(){return this.getPizza().getSize();}
    public int getQuantity(){return this.getPizza().getQuantity();}
   

    

    public static JsonObject toJSON(String json){
        JsonReader r = Json.createReader(new StringReader(json));
        return r.readObject();
    }

    public JsonObject toJSON(){

        return Json.createObjectBuilder()
                .add("orderId", this.getOrderId())
                .add("name", this.getDelivery().getName())
                .add("address", this.getAddress())
                .add("phone", this.getPhoneNumber())
                .add("rush", this.isRush())
                .add("comments", this.getComments())
                .add("pizza", this.getPizzaName())
                .add("size", this.getSize())
                .add("quantity", this.getQuantity())
                .add("total", this.getTotalCost())
                .build();
    }


}
