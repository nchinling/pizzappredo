package sg.edu.nus.iss.pizzaappredo.model;

import java.io.Serializable;

import jakarta.json.JsonObject;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Pizza implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Please select a pizza")
    private String pizzaName;

    @NotBlank(message = "Please select a size")
    @NotNull(message = "Please select a size")
    private String size;


    @NotNull(message = "Please select a quantity")
    @Min(value = 1, message = "Minimum of 1 pizza")
    @Max(value=10, message="Max of 10 pizza")
    private int quantity;
    
    public Pizza() {}
    
    public Pizza(String pizzaName, String size, int quantity) {
        this.pizzaName = pizzaName;
        this.size = size;
        this.quantity = quantity;
    }

    public String getPizzaName() {return pizzaName;}
    public void setPizzaName(String pizzaName) {this.pizzaName = pizzaName;}

    public String getSize() {return size;}
    public void setSize(String size) {this.size = size;}

    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity;}


    public static Pizza createOrderObject(JsonObject o){
        Pizza p = new Pizza();
        p.setPizzaName(o.getString("pizza"));
        p.setSize(o.getString("size"));
        p.setQuantity(o.getInt("quantity"));
        return p;
    }

}
