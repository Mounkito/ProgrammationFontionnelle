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
        final Result price = priceQuery.findPrice(itemPrice);
        Assertions.assertThat(price).isEqualTo(Result.found(Price.valueOf(unitPrice)));
        price.ifFound(System.out::println);

    }

    @Test
    public void should_return_not_found_for_unknown_item() {
        final Result price = priceQuery.findPrice("PEACH");
        Assertions.assertThat(price).isEqualTo(Result.notFound("PEACH"));
        price.ifNotFound(System.err::println);
    }
}
