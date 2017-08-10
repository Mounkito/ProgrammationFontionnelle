package functional;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static functional.ItemReference.Builder.anItemReference;

@RunWith(JUnitParamsRunner.class)
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
    @Parameters({"APPLE,1,1.20" , "BANANA,2,3.80"})
    public void should_calculate_price_for_each_item(String itemCode, int quantity , double totalPrice) {
        Result total = cashRegister.total(priceQuery.findPrice(itemCode), Quantity.valueOf(quantity));
        Assertions.assertThat(total).isEqualTo(Result.found(Price.valueOf(totalPrice)));
    }

    @Test
    public void should_work_when_item_is_not_found(){
        Result total = cashRegister.total(priceQuery.findPrice("PEACH"), Quantity.valueOf(1));
        Assertions.assertThat(total).isEqualTo(Result.notFound("PEACH"));

    }
}
