package functional;

import java.util.Objects;
import java.util.function.UnaryOperator;

public abstract class Result {

    public static Result found(Price price) {
        return new Found(price);
    }

    public static Result notFound(String itemCode) {
        return new NotFound(itemCode);
    }

    public abstract Result map(UnaryOperator<Price> unaryOperator);

    private static class Found extends Result {
        private final Price price;

        public Found(Price price) {
            this.price = price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Found found = (Found) o;
            return Objects.equals(price, found.price);
        }

        @Override
        public int hashCode() {
            return Objects.hash(price);
        }

        @Override
        public Result map(UnaryOperator<Price> unaryOperator) {
            return Result.found(unaryOperator.apply(price));
        }
    }

    private static class NotFound extends Result {
        private final String itemCode;

        public NotFound(String itemCode) {
            this.itemCode = itemCode;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NotFound notFound = (NotFound) o;
            return Objects.equals(itemCode, notFound.itemCode);
        }

        @Override
        public int hashCode() {
            return Objects.hash(itemCode);
        }

        @Override
        public Result map(UnaryOperator<Price> unaryOperator) {
            return null;
        }
    }
}
