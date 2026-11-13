import java.math.BigDecimal;
import java.math.RoundingMode;
public class Product {
    private String name;
    private final int code;
    private BigDecimal price;
    private int quantity;
    // construtor
    public Product(String name, int code, BigDecimal price, int quantity){
        if(name == null) throw new NullPointerException("nao deixe o campo vazio, re-insira o nome.");
        if(name.isBlank()) throw new IllegalArgumentException("nao deixe o campo em branco, re-insira o nome.");
        this.name = name;
        if(code <= 0 ){
            throw new IllegalArgumentException("digite um numero maior que 0");
        }   this.code = code;
        if(price == null || price.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("digite um valor maior que 0 para o produto.");
        }
        price = price.setScale(2, RoundingMode.HALF_UP );
        this.price = price;
        if(quantity < 0 ){
            throw new IllegalArgumentException("quantia invalida, re-insira uma quantidade valida");
        }   this.quantity = quantity;
    }
    //  SESSÃ0 GETTERS
    public String getName() {
        return this.name;
    } public int getCode(){
        return this.code;
    }
    public BigDecimal getPrice(){
        return this.price;
    }public int getQuantity(){
        return this.quantity;
    }
    // SESSÃO SETTERS
    public void setName(String newname) {
        if(newname == null) throw new NullPointerException("nao deixe o campo vazio, re-insira o nome.");
        if(newname.isBlank()) throw new IllegalArgumentException("nao deixe o campo em branco, re-insira o nome.");
        this.name = newname;
    }public void setPrice(BigDecimal newprice){
        if(newprice == null || newprice.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("valor invalido, insira um valor novamente.");
        }   newprice = newprice.setScale(2, RoundingMode.HALF_UP);
        this.price = newprice;
    }public void setQuantity(int newquantity){
        if(newquantity < 0){
            throw new IllegalArgumentException("quantidade invalida, insira o valor novamente.");
        }   this.quantity = newquantity;
    }
    // EXIBIÇÃO TOTAL
    public void displayProd(){
        System.out.println("========= INFORMAÇÕES DO PRODUTO =========");
        System.out.println();
        System.out.println("Nome: " + getName());
        System.out.println("Código: " + getCode());
        System.out.println("Preço: R$ " + getPrice());
        System.out.println("Quantidade: " + getQuantity() + " unidades");
    }
}