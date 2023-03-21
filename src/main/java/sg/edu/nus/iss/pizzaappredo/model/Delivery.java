package sg.edu.nus.iss.pizzaappredo.model;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Delivery implements Serializable  {
    private static final long serialVersionID = 1L;

    @NotNull(message = "Please provide a name")
    @Size(min=3, message="Minimum of 3 characters")
    private String name;

    @NotNull(message = "Please provide an address")
    @NotBlank(message = "Please provide an address")
    private String address;

    @NotNull(message = "Please select a phone number")
    @Size(min=8, max=8, message="Has to be 8 digits")
    private String phoneNumber;
    
    private boolean rush = false;
    private String comments;
    
    public Delivery() {}

    public Delivery(String name, String address,String phoneNumber,
            boolean rush, String comments) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.rush = rush;
        this.comments = comments;
    }



    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public boolean isRush() {return rush;}
    public void setRush(boolean rush) {this.rush = rush;}

    public String getComments() {return comments;}
    public void setComments(String comments) {this.comments = comments;}

}
