    package com.inventory.Service;

    import com.inventory.Exception.DuplicateProductCodeException;
    import com.inventory.Model.Product;
    import com.inventory.Repository.ArrayListInventoryRepository;
    import com.inventory.Repository.InventoryRepository;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.DisplayName;
    import org.junit.jupiter.params.ParameterizedTest;
    import org.junit.jupiter.params.provider.ValueSource;

    import java.math.BigDecimal;
    import java.util.List;

    import static org.junit.jupiter.api.Assertions.*;

    public class InventoryTest {

        //                                INVENTORY SETUP
        private InventoryService inventoryService;
        @BeforeEach
        void setup(){
            InventoryRepository inventoryRepository = new ArrayListInventoryRepository();

            inventoryService = new InventoryService(inventoryRepository);
        }

        //                               FIND PRODUCT TESTS
        @Test
        @DisplayName("Should find product by code")
        void shouldFindProductByCode() {


            Product product = new Product("Keyboard", 100, new BigDecimal("150.00"), 10);

            inventoryService.registerProduct(product);
            Product foundProduct = inventoryService.findByCode(100);

            assertNotNull(foundProduct);
            assertEquals("Keyboard", foundProduct.getName());
            assertEquals(100, foundProduct.getCode());
            assertEquals(new BigDecimal("150.00"), foundProduct.getPrice());
            assertEquals(10, foundProduct.getQuantity());
        }

        @Test
        @DisplayName("Should return null when product does not exist")
        void shouldReturnNullWhenProductDoesNotExist() {

            Product foundProduct = inventoryService.findByCode(999);
            assertNull(foundProduct);
        }


        @Test
        @DisplayName("Should return null when no product matches the code")
        void shouldReturnNullWhenNoProductMatchesCode() {

            Product keyboard = new Product(
                    "Keyboard", 100, new BigDecimal("150.00"), 10
            );

                inventoryService.registerProduct(keyboard);

            Product foundProduct = inventoryService.findByCode(999);

            assertNull(foundProduct);
        }


        //                             PRODUCT EXISTENCE TESTS
        @Test
        @DisplayName("Should return true when product exists")
        void shouldReturnTrueWhenProductExists() {

            Product product = new Product("Mouse", 200, new BigDecimal("80.00"), 15);

            inventoryService.registerProduct(product);
            assertTrue(inventoryService.existsByCode(200));
        }

        @Test
        @DisplayName("Should return false when product does not exist")
        void shouldReturnFalseWhenProductDoesNotExist() {

            assertFalse(inventoryService.existsByCode(999));
        }

        //                              ADD PRODUCT TESTS
        @Test
        @DisplayName("Should throw exception when adding duplicate product code")
        void shouldThrowExceptionWhenAddingDuplicateProductCode() {

            Product firstProduct = new Product("Keyboard", 100, new BigDecimal("150.00"), 10);
            Product secondProduct = new Product("Mouse", 100, new BigDecimal("80.00"), 15);

            inventoryService.registerProduct(firstProduct);
            assertThrows(
                    DuplicateProductCodeException.class, () -> inventoryService.registerProduct(secondProduct)
            );
        }

        @Test
        @DisplayName("Should preserve inventory when adding duplicate product code")
        void shouldPreserveInventoryWhenAddingDuplicateProduct() {

            Product firstProduct = new Product("Keyboard", 100, new BigDecimal("150.00"), 10);
            Product secondProduct = new Product("Mouse", 100, new BigDecimal("80.00"), 15);

            inventoryService.registerProduct(firstProduct);
            assertThrows(
                    DuplicateProductCodeException.class, () -> inventoryService.registerProduct(secondProduct)
            );
            assertEquals(1, inventoryService.getAllProducts().size());
            assertTrue(inventoryService.existsByCode(100));
            assertEquals("Keyboard", inventoryService.findByCode(100).getName());
        }

        @Test
        @DisplayName("Should reject null product")
        void shouldRejectNullProduct(){

            assertThrows(NullPointerException.class, () -> {
                inventoryService.registerProduct(null);
            });
        }

        //                               UPDATE QUANTITY TESTS
        @Test
        @DisplayName("Should update product quantity")
        void shouldUpdateProductQuantity() {

            Product product = new Product("Keyboard", 100, new BigDecimal("150.00"), 10);

            inventoryService.registerProduct(product);
            boolean wasUpdated = inventoryService.updateQuantity(100, 25);

            assertTrue(wasUpdated);
            Product foundProduct = inventoryService.findByCode(100);
            assertEquals(25, foundProduct.getQuantity());
        }

        @Test
        @DisplayName("Should return false when updating nonexistent product")
        void shouldReturnFalseWhenUpdatingNonexistentProduct() {

            boolean wasUpdated = inventoryService.updateQuantity(999, 10);
            assertFalse(wasUpdated);
        }

        @Test
        @DisplayName("Should reject negative quantity when updating")
        void shouldRejectNegativeQuantityWhenUpdatingStock(){

            Product keyboard = new Product("keyboard", 10, new BigDecimal("10.00"), 10);
            inventoryService.registerProduct(keyboard);

            assertThrows(IllegalArgumentException.class, () ->{
                inventoryService.updateQuantity(10, -5);
            });
            assertEquals(10, keyboard.getQuantity());
        }

            @Test
            @DisplayName("Should accept zero quantity when updating stock")
            void shouldAcceptZeroQuantityWhenUpdatingStock(){

                Product keyboard = new Product("keyboard", 10, new BigDecimal("10.00"), 10);
                inventoryService.registerProduct(keyboard);

                boolean wasZero = inventoryService.updateQuantity(10, 0);
                assertTrue(wasZero);
                assertEquals(0, keyboard.getQuantity());
            }

            @ParameterizedTest
            @ValueSource(ints  = {0, 1, 10, 100})
            @DisplayName("Multiple quantity value test")
            void multipleQuantityValueTest(int quantity){

                Product keyboard = new Product("keyboard", 10, new BigDecimal("10.00"), 5);
                inventoryService.registerProduct(keyboard);

                boolean wasAccepted = inventoryService.updateQuantity(10, quantity);
                assertTrue(wasAccepted);
                assertEquals(quantity, keyboard.getQuantity());
            }

        //                                  REMOVE PRODUCT TESTS
        @Test
        @DisplayName("Should remove product successfully")
        void shouldRemoveProductSuccessfully() {

            Product product = new Product("Mouse", 200, new BigDecimal("80.00"), 15);

            inventoryService.registerProduct(product);
            boolean wasRemoved = inventoryService.removeProduct(200);

            assertTrue(wasRemoved);
            assertFalse(inventoryService.existsByCode(200));
        }

        @Test
        @DisplayName("Should return false when removing nonexistent product")
        void shouldReturnFalseWhenRemovingNonexistentProduct() {

            boolean wasRemoved = inventoryService.removeProduct(999);
            assertFalse(wasRemoved);
        }


        //                                      PRODUCT LIST TESTS
        @Test
        @DisplayName("Should return all products")
        void shouldReturnAllProducts() {

            Product first = new Product("Keyboard", 100, new BigDecimal("150.00"), 10);

            Product second = new Product("Mouse", 200, new BigDecimal("80.00"), 15);

            Product third = new Product("Monitor", 300, new BigDecimal("900.00"), 5);

            inventoryService.registerProduct(first);
            inventoryService.registerProduct(second);
            inventoryService.registerProduct(third);

            List<Product> products = inventoryService.getAllProducts();
            assertEquals(3, products.size());
        }

        @Test
        @DisplayName("Should return empty list when inventory is empty")
        void shouldReturnEmptyListWhenInventoryIsEmpty() {

            assertEquals(0, inventoryService.getAllProducts().size());
        }

        //                                       LIST PROTECTION TESTS
        @Test
        @DisplayName("Should prevent external addition to product list")
        void shouldPreventExternalAdditionToProductList() {

            Product first = new Product("Keyboard", 100, new BigDecimal("150.00"), 10);

            Product second = new Product("Mouse", 200, new BigDecimal("80.00"), 15);

            inventoryService.registerProduct(first);
            List<Product> products = inventoryService.getAllProducts();
            assertThrows(
                    UnsupportedOperationException.class, () -> products.add(second)
            );
        }

        @Test
        @DisplayName("Should prevent external removal from product list")
        void shouldPreventExternalRemovalFromProductList() {

            Product product = new Product("Keyboard", 100, new BigDecimal("150.00"), 10);

            inventoryService.registerProduct(product);
            List<Product> products = inventoryService.getAllProducts();
            assertThrows(
                    UnsupportedOperationException.class, () -> products.remove(product)
            );
        }
    }
