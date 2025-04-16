package paymentMethods;

public class CoinAcceptor  extends  MoneyReceiver{


    public CoinAcceptor(int amount) {
        super(amount, "CoinAcceptor");
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean pay(int sum) {
        if (sum <= amount) {
            amount -= sum;
            return true;
        }
        return false;
    }
}
