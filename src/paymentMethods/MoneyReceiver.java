package paymentMethods;

public abstract class MoneyReceiver {
    protected int amount;
    protected String name;


    protected MoneyReceiver(int amount, String name) {
        this.amount = amount;
        this.name = name;
    }

    public abstract void simulate();

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
