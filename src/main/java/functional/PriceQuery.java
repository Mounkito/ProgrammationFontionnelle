package functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class PriceQuery {
    private final ItemReference[] itemReferences;

    public PriceQuery(ItemReference... itemReferences) {
        this.itemReferences = itemReferences;
    }

    public Result findPrice(String itemCode) {
        return reduce(Result.notFound(itemCode),
                (result, itemReference) -> Result.found(itemReference.getUnitPrice()),
                filter(itemReference -> itemReference.matchSoughtItemCode(itemCode),
                        Arrays.asList(itemReferences)));
    }

    private <T> List<T> filter(Predicate<T> predicate, List<T> itemList) {
        List<T> filteredList = new ArrayList<>();
        for (T item: itemList) {
            if (predicate.test(item)) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    private <T, R> R reduce(R result, BiFunction<R, T, R> biFunction,
                          List<T> itemReferences) {
        for (T itemReference: itemReferences) {
            result = biFunction.apply(result,itemReference);
        }
        return result;
    }
}
