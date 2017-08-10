package functional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static functional.ItemReference.Builder.anItemReference;

public class CashRegisterTest {
    private CashRegister cashRegister;
    private PriceQuery priceQuery;

    @Before
    public void setUp() {
        priceQuery = new PriceQuery(
                anItemReference().withItemCode("APPLE").withUnitPrice(1.20).build(),
                anItemReference().withItemCode("BANANA").withUnitPrice(1.90).build());
        cashRegister = new CashRegister();
    }

    @Test
    public void should_calculate_price_for_one_item() {
        final Quantity quantity = Quantity.valueOf(1);
        final Price totalPrice = Price.valueOf(1.20);

        Result total = cashRegister.total(priceQuery.findPrice("APPLE"), quantity);
        Assertions.assertThat(total).isEqualTo(Result.found(totalPrice));

    }

    @Test
    public void should_calculate_price_for_two_item() {
        final Price unitPrice = Price.valueOf(2.55);
        final Quantity quantity = Quantity.valueOf(2);
        final Price totalPrice = Price.valueOf(5.10);
        Assertions.assertThat(cashRegister.total(unitPrice, quantity)).isEqualTo(totalPrice);
    }
}
