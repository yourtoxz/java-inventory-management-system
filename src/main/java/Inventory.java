import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Inventory {
        private final ArrayList<Product> inventory = new ArrayList<Product>();
        public void addProduct(Product product) {
            if (product == null){
                throw new NullPointerException("Product cannot be null.");
            }
            if (existsByCode(product.getCode())){
               throw new DuplicateProductCodeException("A product with this code already exists.");
            }
             inventory.add(product);
        }
        public boolean existsByCode(int code) {
            for (Product product : inventory) {
                if (code == product.getCode()) {
                    return true;
                }
            }return false;
        }
        public Product findByCode(int code){
            for(Product product : inventory){
                if(product.getCode() == code){
                    return product;
                }
            }
            return null;
        }
        public boolean removeProduct(int code){
            Product productToRemove = findByCode(code);
            if(productToRemove != null){
                inventory.remove(productToRemove);
                return true;
            } else {
                return false;
            }
        }
        public boolean updateQuantity(int code, int newQuantity){
            Product quantityToSet = findByCode(code);
                if(quantityToSet == null){
                    return false;
                }
                quantityToSet.setQuantity(newQuantity);
            return true;
        }
        public List<Product> getProducts(){
        return Collections.unmodifiableList(inventory);
     }
    }