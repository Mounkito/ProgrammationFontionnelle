package functionnal;

import functionnal.Quantity;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class QuantityTest {
    @Test
    public void should_return_true_for_same_quantity() {
        Assertions.assertThat(Quantity.valueOf(1)).isEqualTo(Quantity.valueOf(1));
    }
}
