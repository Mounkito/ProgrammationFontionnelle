package functional;

public class CashRegister {

    public Result total(Result result, Quantity quantity) {
        return result.map(price -> price.multiplyBy(quantity));
    }
}
