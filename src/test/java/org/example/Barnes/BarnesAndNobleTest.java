package org.example.Barnes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BarnesAndNobleTest {


    static class TestBookDatabase implements BookDatabase {
        @Override
        public Book findByISBN(String ISBN) {
            // title ISBN price quantity
            return new Book("Test Book", 10, 5);
        }
    }


    static class TestBuyBookProcess implements BuyBookProcess {
        @Override
        public void buyBook(Book book, int amount) {

        }
    }

    @Test
    @DisplayName("specification-based")
    void specTest() {
        BookDatabase db = new TestBookDatabase();
        BuyBookProcess process = new TestBuyBookProcess();
        BarnesAndNoble store = new BarnesAndNoble(db, process);

        Map<String, Integer> cart = new HashMap<>();
        cart.put("12345", 2);

        PurchaseSummary summary = store.getPriceForCart(cart);

        assertNotNull(summary);
        assertEquals(20.0, summary.getTotalPrice()); // 2 × 10
    }

    @Test
    @DisplayName("structural-based")
    void structuralTest() {
        BookDatabase db = new TestBookDatabase();
        BuyBookProcess process = new TestBuyBookProcess();
        BarnesAndNoble store = new BarnesAndNoble(db, process);

        Map<String, Integer> cart = new HashMap<>();
        cart.put("99999", 10);

        PurchaseSummary summary = store.getPriceForCart(cart);

        assertEquals(50.0, summary.getTotalPrice());
        assertFalse(summary.getUnavailable().isEmpty());

    }
}
