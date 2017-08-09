package functional;

public class PriceQuery {
    private ItemReference[] itemReferences;

    public PriceQuery(ItemReference... itemReferences) {
        this.itemReferences = itemReferences;
    }

    public Price findPrice(String itemCode) {
        for (ItemReference itemReference: itemReferences) {
           if(itemReference.matcheSoughtItemCode(itemCode)) {
               return itemReference.getUnitPrice();
           }
        }
        return null;
    }
}
