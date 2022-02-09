package strategies;

public interface PriceCodeStrategy {
    double applyPolicy(int daysRented, double eachCharge);
    int getPriceCode();
}
