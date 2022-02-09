package strategies;

public interface PriceCodeStrategy {
    double applyPolicy(int daysRented);
    int getPriceCode();
    int getPoint();
}
