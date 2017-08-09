package functional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class CashRegisterTest {
    private CashRegister cashRegister;

    @Before
    public void setUp() {
        cashRegister = new CashRegister();
    }

    @Test
    public void should_calculate_price_for_one_item() {
        final Price unitPrice = Price.valueOf(1.20);
        final Quantity quantity = Quantity.valueOf(1);
        final Price totalPrice = Price.valueOf(1.20);
        Assertions.assertThat(cashRegister.total(unitPrice, quantity)).isEqualTo(totalPrice);

    }

    @Test
    public void should_calculate_price_for_two_item() {
        final Price unitPrice = Price.valueOf(2.55);
        final Quantity quantity = Quantity.valueOf(2);
        final Price totalPrice = Price.valueOf(5.10);
        Assertions.assertThat(cashRegister.total(unitPrice, quantity)).isEqualTo(totalPrice);
    }
}
