package paymentMethods;

import java.util.Scanner;

public class CardAcceptor extends MoneyReceiver {
    private final Scanner sc = new Scanner(System.in);


    public CardAcceptor(int amount) {
        super(amount, "CardAcceptor");
    }


    @Override
    public void simulate() {
        String numberCard = " ";
        String password = " ";
        try {
            System.out.println("Для покупки товара введите номер карты. Пример: 123456");
            numberCard = fromConsole();
            if (!numberCard.matches(("\\d+"))) {
                throw new IllegalArgumentException("Номер карты может быть только из чисел. Необходимо снова ввести номер карты");
            }

            try {
                System.out.println("Введите пароль от карты, он может состоять только из чисел");
                password = fromConsole();
                if (!password.matches(("\\d+"))) {
                    throw new IllegalArgumentException("Проверочный код может быть только из чисел. Нужно снова ввести данные.");
                }

                System.out.println("Вы успешно прошли авторизацию!");
                System.out.println("Доступный баланс: " + this.getAmount());

            } catch (IllegalArgumentException ile) {
                System.out.println(ile.getMessage());
                simulate();
            }

        } catch (IllegalArgumentException ile) {
            System.out.println(ile.getMessage());
            simulate();
        }



    }


    private String fromConsole() {
        return new Scanner(System.in).nextLine();
    }

}
