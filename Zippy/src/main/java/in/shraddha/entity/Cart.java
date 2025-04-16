package in.shraddha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private Integer id;
    private String name;
    private String img;
    private double price;
    private int quantity;
    private double discount; // New field for discount percentage

    public double getDiscountedPrice() {
        return this.price - (this.price * (this.discount / 100)); // Price after discount
    }

    public double getTotalPrice() {
        return getDiscountedPrice() * this.quantity; // Total price for this item after discount
    }
}
