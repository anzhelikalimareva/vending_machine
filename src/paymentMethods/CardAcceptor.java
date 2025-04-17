package paymentMethods;

import java.util.Scanner;

public class CardAcceptor extends MoneyReceiver {
    private final Scanner sc = new Scanner(System.in);


    public CardAcceptor(int amount) {
        super(amount, "CardAcceptor");
    }


    @Override
    public void startSimulation() {
        String numberCard = " ";
        String password = " ";
        try {
            System.out.println("Введите номер карты. Номер карты может состоять только из чисел");
            numberCard = fromConsole();
            if (!numberCard.matches(("\\d+"))) {
                throw new IllegalArgumentException();
            }

            try {
                System.out.println("Введите пароль от карты, он может состоять только из чисел");
                password = fromConsole();
                if (!password.matches(("\\d+"))) {
                    throw new IllegalArgumentException();
                }

                System.out.println("Вы успешно прошли авторизацию!");
                System.out.println("Баланс на сумму: " + this.getAmount());

            } catch (IllegalArgumentException e) {
                System.out.println("Проверочный код может быть только из чисел");
                startSimulation();
            }


        } catch (IllegalArgumentException e) {
            System.out.println("Номер карты может быть только из чисел. Необходимо снова ввести номер карты");
            startSimulation();
        }



    }


    private String fromConsole() {
        return new Scanner(System.in).nextLine();
    }

}
