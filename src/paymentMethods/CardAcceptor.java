package paymentMethods;

public class CardAcceptor extends MoneyReceiver {


    public CardAcceptor(int amount) {
        super(amount, "CardAcceptor");
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
