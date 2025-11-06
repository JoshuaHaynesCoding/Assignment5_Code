package org.example.Amazon;

import org.example.Amazon.Cost.ItemType;
import org.example.Amazon.Cost.PriceRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AmazonUnitTest {

    @Test
    @DisplayName("specification-based")
    void testAddToCart() {

        ShoppingCart fakeCart = mock(ShoppingCart.class);
        List<PriceRule> emptyRules = new ArrayList<>();

        Amazon amazon = new Amazon(fakeCart, emptyRules);

        Item phone = new Item(
                ItemType.ELECTRONIC,
                "Phone",
                1,
                800
        );

        amazon.addToCart(phone);

        verify(fakeCart).add(phone);
    }

    @Test
    @DisplayName("structural-based")
    void testCalculate() {

        ShoppingCart cart = mock(ShoppingCart.class);
        List<Item> fakeItems = new ArrayList<>();
        fakeItems.add(new Item(ItemType.OTHER, "Book", 1, 20));

        when(cart.getItems()).thenReturn(fakeItems);

        PriceRule rule1 = mock(PriceRule.class);
        PriceRule rule2 = mock(PriceRule.class);

        when(rule1.priceToAggregate(fakeItems)).thenReturn(20.0);
        when(rule2.priceToAggregate(fakeItems)).thenReturn(5.0);

        List<PriceRule> rules = List.of(rule1, rule2);

        Amazon amazon = new Amazon(cart, rules);

        double result = amazon.calculate();
        assertEquals(25.0, result);
    }
}
