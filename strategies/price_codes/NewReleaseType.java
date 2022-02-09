package strategies.price_codes;

import strategies.PriceCodeStrategy;

public class NewReleaseType implements PriceCodeStrategy {

    private final int priceCode;

    public NewReleaseType(int priceCode){
        this.priceCode = priceCode;
    }

    @Override
    public int getPriceCode() {
        return priceCode;
    }

    public int getPoint() {
        return 2;
    }

    @Override
    public double applyPolicy(int daysRented) {
        return daysRented *3;
    }
}
