package org.example.Amazon;

import org.example.Amazon.Cost.ItemType;
import org.example.Amazon.Cost.PriceRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AmazonIntegrationTest {


    static class TestCart implements ShoppingCart {
        private final List<Item> items = new ArrayList<>();

        @Override
        public void add(Item item) {
            items.add(item);
        }

        @Override
        public List<Item> getItems() {
            return items;
        }

        @Override
        public int numberOfItems() {
            return items.size();
        }
    }

    @Test
    @DisplayName("specification-based")
    void testFullCostCalculation_spec() {

        TestCart cart = new TestCart();

        List<PriceRule> rules = new ArrayList<>();

        rules.add(items -> {
            double sum = 0;
            for (Item i : items) {
                sum += i.getPricePerUnit() * i.getQuantity();
            }
            return sum;
        });

        Amazon amazon = new Amazon(cart, rules);

        Item laptop = new Item(
                ItemType.ELECTRONIC,
                "Laptop",
                1,
                1000
        );

        amazon.addToCart(laptop);

        double finalCost = amazon.calculate();
        assertTrue(finalCost == 1000);
    }

    @Test
    @DisplayName("structural-based")
    void testFullCostCalculation_structural() {

        TestCart cart = new TestCart();

        List<PriceRule> rules = new ArrayList<>();
        rules.add(items -> items.size() * 10); // structural rule: 10 per item

        Amazon amazon = new Amazon(cart, rules);

        Item book = new Item(
                ItemType.OTHER,
                "Book",
                1,
                20
        );

        amazon.addToCart(book);

        double finalCost = amazon.calculate();
        assertTrue(finalCost == 10);
    }

}
