package functional;

public class CashRegister {

    public Price total(Price price, Quantity quantity) {
        return price.multiplyBy(quantity);
    }
    public Result total(Result result, Quantity quantity) {
        return result.map(price -> price.multiplyBy(quantity));
    }
}
