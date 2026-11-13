import java.math.BigDecimal;
public  class InventoryTest{
public static void main (String[] args){
    Inventory inventory = new Inventory();

    Product aura = new Product("Furycube", 300, new BigDecimal("200.00"), 10);
    inventory.addProduct(aura);

    Product foundProduct = inventory.findByCode(300);
    if(foundProduct != null) {
        System.out.println(foundProduct.getName());
        System.out.println(foundProduct.getCode());
        System.out.println(foundProduct.getPrice());
        System.out.println(foundProduct.getQuantity());

        foundProduct.setQuantity(25);
        inventory.findByCode(30).setQuantity(999);
        inventory.removeProduct(25);
        inventory.removeProduct(25);
    } else {
        System.out.println("Produto não encontrado");
    }
}
}