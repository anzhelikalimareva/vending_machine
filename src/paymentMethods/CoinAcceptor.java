package paymentMethods;

public class CoinAcceptor  extends  MoneyReceiver{


    public CoinAcceptor(int amount) {
        super(amount, "CoinAcceptor");
    }



    @Override
    public void startSimulation() {

        System.out.println("Монет на сумму: " + this.getAmount());

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
