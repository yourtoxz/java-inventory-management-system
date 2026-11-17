import java.math.BigDecimal;
import java.math.RoundingMode;
public class Product {
    private String name;
    private final int code;
    private BigDecimal price;
    private int quantity;
    //                                Constructor
    public Product(String name, int code, BigDecimal price, int quantity) {
        if (name == null) {
            throw new NullPointerException(
                    "Product name cannot be null."
            );
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException(
                    "Product name cannot be blank."
            );
        }
        this.name = name;
        if (code <= 0) {
            throw new IllegalArgumentException(
                    "Product code must be greater than zero."
            );
        }
        this.code = code;
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Product price must be greater than zero."
            );
        }
        price = price.setScale(2, RoundingMode.HALF_UP);
        this.price = price;
        if (quantity < 0) {
            throw new IllegalArgumentException(
                    "Product quantity cannot be negative."
            );
        }
        this.quantity = quantity;
    }
    //                                  Getters
    public String getName() {
        return this.name;
    }
    public int getCode() {
        return this.code;
    }
    public BigDecimal getPrice() {
        return this.price;
    }
    public int getQuantity() {
        return this.quantity;
    }
    // Setters
    public void setName(String newName) {
        if (newName == null) {
            throw new NullPointerException(
                    "Product name cannot be null."
            );
        }
        if (newName.isBlank()) {
            throw new IllegalArgumentException(
                    "Product name cannot be blank."
            );
        }
        this.name = newName;
    }
    public void setPrice(BigDecimal newPrice) {
        if (newPrice == null || newPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Product price must be greater than zero."
            );
        }
        newPrice = newPrice.setScale(2, RoundingMode.HALF_UP);
        this.price = newPrice;
    }
    public void setQuantity(int newQuantity) {
        if (newQuantity < 0) {
            throw new IllegalArgumentException(
                    "Product quantity cannot be negative."
            );
        }
        this.quantity = newQuantity;
    }
    // Product Information
    public void displayProd() {
        System.out.println("========= PRODUCT INFORMATION =========");
        System.out.println();
        System.out.println("Name: " + getName());
        System.out.println("Code: " + getCode());
        System.out.println("Price: " + getPrice());
        System.out.println("Quantity: " + getQuantity() + " units");
    }
}