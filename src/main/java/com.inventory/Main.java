    package com.inventory;

    import com.inventory.Exception.DuplicateProductCodeException;
    import com.inventory.Model.Product;
    import com.inventory.Service.InventoryService;


    import java.math.BigDecimal;
    import java.util.List;
    import java.util.Scanner;

    public class Main{

        static Scanner scanner = new Scanner(System.in);
        static AppConfig appConfig = new AppConfig();
       private static final InventoryService inventoryService = appConfig.createInventoryService();
    //                             Validation Section
    static String validateProductName(){
        System.out.println("Give a name for the new product: ");
        while(true) {
           String prodName = scanner.nextLine();
            if (prodName == null || prodName.isBlank()) {
                System.out.println("Don't leave the name empty.");
                continue;
            }
            return prodName;
        }
    }
    static int validateProductCode(){
        System.out.println("Give a code for the new product: ");

        while(true){
            String code = scanner.nextLine();
            int prodCode;
            try{
                prodCode = Integer.parseInt(code);
            } catch(NumberFormatException e){
                System.out.println("Please, insert numbers to set code.");
                continue;
            }
            if(prodCode <= 0){
                System.out.println("Please insert a valid code.");
                continue;
            }
            boolean productExists = inventoryService.existsByCode(prodCode);
            if(productExists){
                System.out.println("Already have a product with this code." +
                        " Please, try another code.");
                continue;
            }
            return prodCode;
        }
    }
    static BigDecimal validateProductPrice(){
        System.out.println("Set the price of the product: ");
        BigDecimal prodPrice;
        while(true){
            String price = scanner.nextLine();
            try{
                prodPrice = new BigDecimal(price);
            } catch(NumberFormatException e){
                System.out.println("Please, insert numbers to set price.");
                continue;
            }
            if(prodPrice.compareTo(BigDecimal.ZERO) <= 0){
                System.out.println("Insert a valid price.");
                continue;
            }
            return prodPrice;
        }
    }
    static int validateProductQuantity(){
        System.out.println("Set the quantity: ");
        int prodQuantity;
        while(true){
            String quantity = scanner.nextLine();
            try{
                prodQuantity = Integer.parseInt(quantity);
            } catch (NumberFormatException e){
                System.out.println("Please, insert numbers to set quantity.");
                continue;
            }
            if(prodQuantity < 0){
                System.out.println("Please, insert a valid quantity.");
                continue;
            }
            return prodQuantity;
        }
    }
    //                         Product Registration
    static void registerProduct(){
        String prodName = validateProductName();
        int prodCode = validateProductCode();
        BigDecimal prodPrice = validateProductPrice();
        int prodQuantity = validateProductQuantity();

        Product product = new Product(prodName, prodCode, prodPrice, prodQuantity);
        try {
            inventoryService.registerProduct(product);
            System.out.println("Product has been added in the inventory!");
        } catch (DuplicateProductCodeException e) {
            System.out.println(e.getMessage());
        }
    }
    //                                  Product Listing
    static void listProducts(){
       List<Product> products = inventoryService.getAllProducts();
        if(products.isEmpty()){
            System.out.println("The list is empty!");
            return;
        }
        System.out.println("======= PRODUCTS =======");
        for(Product product : products){
            System.out.printf("name: %s | code: %d | price: %s | quantity: %d%n",
                    product.getName(), product.getCode(), product.getPrice(), product.getQuantity());
            System.out.println("--------------------------------");
        }
    }
    //                          Product Search
    static void findProduct(){
        System.out.println("put de code of the product you want to find: ");
        int findProdCode;
        while(true) {
            String code = scanner.nextLine();
            try {
                findProdCode = Integer.parseInt(code);
            } catch (NumberFormatException e) {
                System.out.println("Please, insert numbers to search products correctly.");
                continue;
            }
            if(findProdCode <= 0){
                System.out.println("Insert a valid code.");
                continue;
            }
            Product product = inventoryService.findByCode(findProdCode);
            if(product == null){
                System.out.println("A product with this code not exist.");
                continue;
            }
            System.out.printf("name: %s | code: %d | price: %s | quantity: %d%n",
                    product.getName(), product.getCode(),
                    product.getPrice(), product.getQuantity());
            return;
        }
    }
    //                              Stock Update
    static void updateStock() {
        System.out.println("\n===== STOCK UPDATE =====");
        while (true) {
            System.out.print("Enter the product code: ");
            String code = scanner.nextLine();
            int prodCode;
            try {
                prodCode = Integer.parseInt(code);
            } catch (NumberFormatException e) {
                System.out.println("Invalid product code. Please enter another code.\n");
                continue;
            }
            if (prodCode <= 0) {
                System.out.println("Product code must be greater than zero.\n");
                continue;
            }
            Product product = inventoryService.findByCode(prodCode);
            if (product == null) {
                System.out.println("Product not found. Please try again.\n");
                continue;
            }
            System.out.println("\nProduct found: " + product.getName());
            System.out.println("Current quantity: " + product.getQuantity());
            int oldQuantity = product.getQuantity();
            int newQuantity = validateProductQuantity();
            boolean updated = inventoryService.updateQuantity(prodCode, newQuantity);
            if (updated) {
                System.out.println("\n===== UPDATE SUCCESSFUL =====");
                System.out.printf("Product: %s%n", product.getName());
                System.out.printf("Previous quantity: %d%n",
                        oldQuantity);
                System.out.printf("New quantity: %d%n", newQuantity);
                System.out.println("=============================\n");
                break;
            }
            System.out.println("Unable to update product quantity. Please try again.\n");
        }
    }
    //                              Report Generation
    static void displayReport(){
        System.out.println("here is your report!");
        List<Product> products = inventoryService.getAllProducts();
        if(products.isEmpty()){
            System.out.println("There are no products to report.");
            return;
        }
        Product mostExpensive = products.get(0);
        Product mostStocked = products.get(0);
        BigDecimal productValue;
        BigDecimal totalValue = BigDecimal.ZERO;
        int totalUnits = 0;
        for(Product product : products) {
            if(product.getPrice().compareTo(mostExpensive.getPrice()) > 0){
                mostExpensive = product;
            }
            if(product.getQuantity() > mostStocked.getQuantity()){
                mostStocked = product;
            }
            totalUnits += product.getQuantity();
            productValue = product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity()));
            totalValue = totalValue.add(productValue);
        }
        System.out.println("Total inventory value: " + totalValue + " | total inventory units: " + totalUnits);
        System.out.println("Most expensive product: " + mostExpensive.getName() + " | price: " + mostExpensive.getPrice());
        System.out.println("Most stocked product: " + mostStocked.getName() + " | total units: " + mostStocked.getQuantity());
    }
        //                            Product Removal
        static void removeProduct(){
            System.out.println("insert product code you want to remove.");
            int prodCode;
            while (true){
                String code = scanner.nextLine();
                try{
                    prodCode = Integer.parseInt(code);
                } catch (NumberFormatException e){
                    System.out.println("Invalid product code. Please enter an integer.\n");
                    continue;
                }
                if(prodCode <= 0){
                    System.out.println("Product code must be greater than zero.\n");
                    continue;
                }
                Product product = inventoryService.findByCode(prodCode);
                if(product == null){
                    System.out.println("Product not found. Please try again.\n");
                    continue;
                }
                System.out.printf("\n Product found: %s%n", product.getName());
                System.out.println("Do you really want to remove this product?(y/n)");
                String option = scanner.nextLine();
                switch (option){
                    case "y":
                        boolean removed = inventoryService.removeProduct(prodCode);
                        if (removed) {
                            System.out.println("Product has been removed.");
                            return;
                        } else{
                            System.out.println("Not even possible remove this product.");
                        }
                    case "n":
                        System.out.println("Product removal cancelled.");
                        return;
                    default:
                        System.out.println("Please, insert 'y' or 'n' to continue.");
                }
            }
        }
    public static void main(String[] args){
        String option;
        boolean running = true;
        while(running) {
            System.out.println("===== INVENTORY MANAGEMENT ====");
            System.out.println();
            System.out.println("1 - Register Product");
            System.out.println("2 - List Products");
            System.out.println("3 - Find Product");
            System.out.println("4 - Update Stock");
            System.out.println("5 - Remove Product");
            System.out.println("6 - Display Report");
            System.out.println("0 - Exit");
            System.out.println();
            System.out.println("Choose an option: ");
            option = scanner.nextLine().trim().toLowerCase();
            switch (option){
                case "1": {
                    registerProduct();
                    break;
                } case "2": {
                    listProducts();
                    break;
                } case "3": {
                    findProduct();
                    break;
                } case "4": {
                    updateStock();
                    break;
                } case "5": {
                    removeProduct();
                    break;
                } case "6": {
                    displayReport();
                    break;
                } case "0": {
                    running = false;
                    System.out.println("Shutting down the system...");
                    break;
                } default: {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}