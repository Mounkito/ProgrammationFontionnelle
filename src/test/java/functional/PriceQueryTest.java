package functional;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static functional.ItemReference.Builder.anItemReference;

@RunWith(JUnitParamsRunner.class)
public class PriceQueryTest
{
    private PriceQuery priceQuery;

    @Before
    public void setUp() {
        priceQuery = new PriceQuery(
                anItemReference().withItemCode("APPLE").withUnitPrice(1.20).build(),
                anItemReference().withItemCode("BANANA").withUnitPrice(1.90).build());
    }

    @Parameters({"APPLE,1.20" , "BANANA , 1.90"})
    @Test
    public void find_the_price_given_an_item(String itemPrice, double unitPrice){
        Assertions.assertThat(priceQuery.findPrice(itemPrice)).isEqualTo(Result.found(Price.valueOf(unitPrice)));
    }

    @Test
    public void should_return_null_for_unknown_item() {
        Assertions.assertThat(priceQuery.findPrice("PEACH")).isEqualTo(Result.notFound("PEACH"));
    }
}
