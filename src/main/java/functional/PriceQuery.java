package functional;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class PriceQuery {
    private final ItemReference[] itemReferences;

    public PriceQuery(ItemReference... itemReferences) {
        this.itemReferences = itemReferences;
    }

    public Result findPrice(String itemCode) {
        return reduce(Result.notFound(itemCode),(Result result,ItemReference itemReference) -> {
            if(itemReference.matchSoughtItemCode(itemCode)) {
                return Result.found(itemReference.getUnitPrice());
            }else{
                return result;
            }
        } , Arrays.asList(itemReferences));
    }

    private <T, R> R reduce(R result, BiFunction<R, T, R> biFunction,
                          List<T> itemReferences) {
        for (T itemReference: itemReferences) {
            result = biFunction.apply(result,itemReference);
        }
        return result;
    }
}
