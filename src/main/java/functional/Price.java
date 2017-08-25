package functional;

import java.util.Objects;

class Price {

    private final double price;

    static Price valueOf(double value){
        return new Price(value);
    }

    private Price(double price) {
        this.price = price;
    }

    public Price multiplyBy(Quantity quantity) {
        return valueOf(quantity.multiplyBy(price));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price1 = (Price) o;
        return Double.compare(price1.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }

    @Override
    public String toString() {
        return "Price{" +
                "price=" + price +
                '}';
    }
}
