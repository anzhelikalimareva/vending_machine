package paymentMethods;

import java.util.Scanner;

public class CardAcceptor extends MoneyReceiver {
    private Scanner sc = new Scanner(System.in);


    public CardAcceptor(int amount) {
        super(amount, "CardAcceptor");
    }


    @Override
    public void startSimulation() {
        System.out.println("Картоприемник: ");
        System.out.println("Введите номер карты");
        String numberCard = sc.nextLine().strip();

        System.out.println("Введите пароль от карты");
        String password = sc.nextLine().strip();

        System.out.println("Вы успешно прошли авторизацию " + numberCard + password );
        System.out.println("Баланс на сумму: " + this.getAmount());

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
