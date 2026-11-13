import java.util.ArrayList;
    public class Inventory {
        private final ArrayList<Product> inventory = new ArrayList<>();
        public void addProduct(Product product) {
           if (existsByCode(product.getCode())){
               throw new IllegalArgumentException("essa porra já existe");
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
    }