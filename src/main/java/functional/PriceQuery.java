package functional;

public class PriceQuery {
    private ItemReference[] itemReferences;

    public PriceQuery(ItemReference... itemReferences) {
        this.itemReferences = itemReferences;
    }

    public Result findPrice(String itemCode) {
        for (ItemReference itemReference: itemReferences) {
           if(itemReference.matcheSoughtItemCode(itemCode)) {
               return Result.found(itemReference.getUnitPrice());
           }
        }
        return Result.notFound(itemCode);
    }
}
