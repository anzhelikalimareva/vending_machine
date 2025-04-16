package paymentMethods;

public abstract class MoneyReceiver {
    protected int amount;
    protected String name;


    protected MoneyReceiver(int amount, String name) {
        this.amount = amount;
        this.name = name;
    }

    public abstract boolean pay(int sum);


}
