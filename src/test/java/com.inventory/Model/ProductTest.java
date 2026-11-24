package com.inventory.Model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    //                                       VALID PRICE TESTS

    @ParameterizedTest
    @ValueSource(strings = {"10.00", "50.00", "99.90"})
    @DisplayName("Should accept valid prices")
    void shouldAcceptValidPrices(String price) {

        BigDecimal prodPrice = new BigDecimal(price);

        assertDoesNotThrow(() ->
                new Product("Keyboard", 100, prodPrice, 10)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"0.00", "-10.00", "-50.00"})
    @DisplayName("Should reject invalid prices")
    void shouldRejectInvalidPrices(String price) {

        BigDecimal prodPrice = new BigDecimal(price);

        assertThrows(IllegalArgumentException.class, () ->
                new Product("Keyboard", 100, prodPrice, 10)
        );
    }

    @Test
    @DisplayName("Should reject null price")
    void shouldRejectNullPrice() {

        assertThrows(IllegalArgumentException.class, () ->
                new Product("Keyboard", 100, null, 10)
        );
    }


    //                                  NAME VALIDATION TESTS

    @Test
    @DisplayName("Should reject null product name")
    void shouldRejectNullName() {

        assertThrows(NullPointerException.class, () ->
                new Product(null, 100, new BigDecimal("10.00"), 10)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    @DisplayName("Should reject blank product names")
    void shouldRejectBlankNames(String name) {

        assertThrows(IllegalArgumentException.class, () ->
                new Product(name, 100, new BigDecimal("10.00"), 10)
        );
    }

    //                                  NEW: SET NAME TESTS

    @Test
    @DisplayName("Should update product name")
    void shouldUpdateProductName() {

        Product product = new Product(
                "Keyboard", 100, new BigDecimal("10.00"), 10
        );

        product.setName("Mouse");

        assertEquals("Mouse", product.getName());
    }

    @Test
    @DisplayName("Should reject null name when updating")
    void shouldRejectNullNameWhenUpdating() {

        Product product = new Product(
                "Keyboard", 100, new BigDecimal("10.00"), 10
        );

        assertThrows(NullPointerException.class, () ->
                product.setName(null)
        );

        assertEquals("Keyboard", product.getName());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    @DisplayName("Should reject blank name when updating")
    void shouldRejectBlankNameWhenUpdating(String name) {

        Product product = new Product(
                "Keyboard", 100, new BigDecimal("10.00"), 10
        );

        assertThrows(IllegalArgumentException.class, () ->
                product.setName(name)
        );

        assertEquals("Keyboard", product.getName());
    }


    //                                  CODE VALIDATION TESTS

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -100})
    @DisplayName("Should reject invalid product codes")
    void shouldRejectInvalidCodes(int code) {

        assertThrows(IllegalArgumentException.class, () ->
                new Product("Keyboard", code, new BigDecimal("10.00"), 10)
        );
    }


    //                                   QUANTITY VALIDATION TESTS

    @ParameterizedTest
    @ValueSource(ints = {-1, -10, -100})
    @DisplayName("Should reject negative quantities")
    void shouldRejectNegativeQuantities(int quantity) {

        assertThrows(IllegalArgumentException.class, () ->
                new Product("Keyboard", 100, new BigDecimal("10.00"), quantity)
        );
    }

    @Test
    @DisplayName("Should accept zero quantity")
    void shouldAcceptZeroQuantity() {

        assertDoesNotThrow(() ->
                new Product("Keyboard", 100, new BigDecimal("10.00"), 0)
        );
    }


    //                                   PRICE ROUNDING TEST

    @Test
    @DisplayName("Should round price to two decimal places")
    void shouldRoundPriceToTwoDecimalPlaces() {

        Product product = new Product(
                "Keyboard", 100, new BigDecimal("10.555"), 10
        );

        assertEquals(new BigDecimal("10.56"), product.getPrice());
    }


    //                                   SET PRICE TESTS

    @Test
    @DisplayName("Should update product price")
    void shouldUpdateProductPrice() {

        Product product = new Product(
                "Keyboard", 100, new BigDecimal("10.00"), 10
        );

        product.setPrice(new BigDecimal("25.50"));

        assertEquals(new BigDecimal("25.50"), product.getPrice());
    }

    @Test
    @DisplayName("Should reject null price when updating")
    void shouldRejectNullPriceWhenUpdating() {

        Product product = new Product(
                "Keyboard", 100, new BigDecimal("10.00"), 10
        );

        assertThrows(IllegalArgumentException.class, () ->
                product.setPrice(null)
        );

        assertEquals(new BigDecimal("10.00"), product.getPrice());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0.00", "-10.00", "-50.00"})
    @DisplayName("Should reject invalid prices when updating")
    void shouldRejectInvalidPricesWhenUpdating(String price) {

        Product product = new Product(
                "Keyboard", 100, new BigDecimal("10.00"), 10
        );

        BigDecimal invalidPrice = new BigDecimal(price);

        assertThrows(IllegalArgumentException.class, () ->
                product.setPrice(invalidPrice)
        );

        assertEquals(new BigDecimal("10.00"), product.getPrice());
    }

    @Test
    @DisplayName("Should round updated price to two decimal places")
    void shouldRoundUpdatedPriceToTwoDecimalPlaces() {

        Product product = new Product(
                "Keyboard", 100, new BigDecimal("10.00"), 10
        );

        product.setPrice(new BigDecimal("25.555"));

        assertEquals(new BigDecimal("25.56"), product.getPrice());
    }


    //                                   DISPLAY PRODUCT TEST

    @Test
    @DisplayName("Should display product information")
    void shouldDisplayProductInformation() {

        Product product = new Product(
                "Keyboard", 100, new BigDecimal("150.00"), 10
        );

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(output));

            product.displayProd();

        } finally {
            System.setOut(originalOut);
        }

        String displayedInformation = output.toString();

        assertTrue(displayedInformation.contains("Name: Keyboard"));
        assertTrue(displayedInformation.contains("Code: 100"));
        assertTrue(displayedInformation.contains("Price: 150.00"));
        assertTrue(displayedInformation.contains("Quantity: 10 units"));
    }

}

