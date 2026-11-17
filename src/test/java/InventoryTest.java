import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    @Test
    void shouldFindProductByCode() {

        // Arrange


        // Act


        // Assert

    }
}

    /* validation control
    boolean nameValidation = false;
    boolean codeValidation = false;
    boolean priceValidation = false;
    boolean quantityValidation = false;
    // variable initialization
    String prodName ="";
    int prodCode = 0;
    int prodQuantity = 0;
    BigDecimal prodPrice = BigDecimal.ZERO;
    //  here the register begins
            while(!nameValidation){
        System.out.println("Give a name for the new product.");
        prodName = scanner.nextLine();
        if(!prodName.isBlank()){
            nameValidation = true;
        }
        if(prodName != null){
            nameValidation = true;
        } else{
            System.out.println("Don't leave the name empty");
        }
    }
            while(!codeValidation){
        System.out.println("Give a code for the new product.");
        String code = scanner.nextLine();
        try{
            prodCode = Integer.parseInt(code);
            if(prodCode <= 0){
                System.out.println("Please insert a valid code");
            } else if(inventory.existsByCode(prodCode)) {
                System.out.println("A product with this code already exists.");
            } else {
                codeValidation = true;
            }
        } catch(NumberFormatException e){
            System.out.println("Please, insert numbers to set that code.");
        }
    }
            while (!priceValidation) {
        System.out.println("Set the price of the product.");
        String price = scanner.nextLine();
        try {
            prodPrice = new BigDecimal(price);
            if (prodPrice.compareTo(BigDecimal.ZERO) <= 0) {
                System.out.println("Please insert a valid price");
            } else {
                priceValidation = true;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please, insert numbers to set the price.");
        }
    }
            while (!quantityValidation){
        System.out.println("Set the quantity.");
        String quantity = scanner.nextLine();
        try {
            prodQuantity = Integer.parseInt(quantity);
            if(prodQuantity <0){
                System.out.println("Please insert a valid quantity");
            } else {
                quantityValidation = true;
            }
        } catch (NumberFormatException e){
            System.out.println("Please, insert a valid quantity");
        }
    }
    Product product = new Product(prodName, prodCode, prodPrice, prodQuantity);
            inventory.addProduct(product);
            System.out.println("Register Completed!");*/
