package strategies.price_codes;

import strategies.PriceCodeStrategy;

public class RegularType implements PriceCodeStrategy {

    private final int priceCode;

    public RegularType(int priceCode){
        this.priceCode = priceCode;
    }

    @Override
    public int getPriceCode() {
        return priceCode;
    }

    @Override
    public double applyPolicy(int daysRented, double eachCharge) {
        double tempCharge = eachCharge + 2;
        return daysRented > 2 ? tempCharge + ((daysRented - 2) * 1.5) : tempCharge;
    }

}
