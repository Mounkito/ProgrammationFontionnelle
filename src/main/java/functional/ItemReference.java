package functionnal;

public class ItemReference {

    private final String itemCode;
    private final Price unitPrice;

    public ItemReference(String itemCode , Price unitPrice) {
        this.itemCode = itemCode;
        this.unitPrice = unitPrice;
    }

    public Price getUnitPrice() {
        return unitPrice;
    }

    public boolean matcheSoughtItemCode(String itemCode) {
        return itemCode.equals(this.itemCode);
    }


    public static final class Builder {
        private String itemCode;
        private Price unitPrice;

        private Builder() {
        }

        public static Builder anItemReference() {
            return new Builder();
        }

        public Builder withItemCode(String itemCode) {
            this.itemCode = itemCode;
            return this;
        }

        public Builder withUnitPrice(Price unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public Builder withUnitPrice(double unitPrice){
            return withUnitPrice(Price.valueOf(unitPrice));
        }

        public ItemReference build() {
            ItemReference itemReference = new ItemReference(itemCode, unitPrice);
            return itemReference;
        }
    }
}
