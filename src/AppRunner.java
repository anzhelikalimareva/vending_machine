import enums.ActionLetter;
import model.*;
import paymentMethods.CardAcceptor;
import paymentMethods.CoinAcceptor;
import paymentMethods.MoneyReceiver;
import util.UniversalArray;
import util.UniversalArrayImpl;

import java.util.Scanner;

public class AppRunner {
    private final int MIN_AMOUNT = 20;
    private final int INCREASE_AMOUNT = 100;

    private final UniversalArray<Product> products = new UniversalArrayImpl<>();

    private MoneyReceiver moneyReceiver;

    private static boolean isExit = false;

    private AppRunner() {
        products.addAll(new Product[]{
                new Water(ActionLetter.B, 20),
                new CocaCola(ActionLetter.C, 50),
                new Soda(ActionLetter.D, 30),
                new Snickers(ActionLetter.E, 80),
                new Mars(ActionLetter.F, 80),
                new Pistachios(ActionLetter.G, 130)
        });
        chooseAcceptor();
    }

    private void chooseAcceptor() {
        print("Выберите деньгоприменик:");
        print("а - Картоприменик");
        print("b - Монетоприменик");
        String action = fromConsole().substring(0, 1);

        try {
            if ("a".equalsIgnoreCase(action)) {
                print("Вы выбрали - Картоприменик");
                moneyReceiver = new CardAcceptor(100);
            }
            else if ("b".equalsIgnoreCase(action)) {
                print("Вы выбрали - Монетоприменик");
                moneyReceiver = new CoinAcceptor(100);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            print("Недопустимая буква. Попрбуйте еще раз.");
            chooseAcceptor();
        }

    }



    public static void run() {
        AppRunner app = new AppRunner();
        while (!isExit) {
            app.startSimulation();
        }
    }

    private void startSimulation() {
        moneyReceiver.simulate();

        print("В автомате доступны:");
        showProducts(products);


        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        allowProducts.addAll(getAllowedProducts().toArray());
        chooseAction(allowProducts);

    }

    private UniversalArray<Product> getAllowedProducts() {
        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        for (int i = 0; i < products.size(); i++) {
            if (moneyReceiver.getAmount() >= products.get(i).getPrice()) {
                allowProducts.add(products.get(i));
            }
        }

        if (moneyReceiver.getAmount() < MIN_AMOUNT) {
            print("У вас недостаточно денег, пополните баланс!");
        }
        return allowProducts;
    }

    private void chooseAction(UniversalArray<Product> products) {
        print(" а - Пополнить баланс");
        showActions(products);
        print(" h - Выйти");


        try {
            String action = fromConsole().substring(0, 1);

            if (action.isBlank()) {
                throw new StringIndexOutOfBoundsException();
            }

            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getActionLetter().equals(ActionLetter.valueOf(action.toUpperCase()))) {
                    moneyReceiver.setAmount(moneyReceiver.getAmount() - products.get(i).getPrice());
                    print("Вы купили " + products.get(i).getName());
                    break;
                }
            }

            if ("a".equalsIgnoreCase(action)) {
                moneyReceiver.setAmount(moneyReceiver.getAmount() + INCREASE_AMOUNT);
                print("Вы пополнили баланс на " + INCREASE_AMOUNT);
            } else if ("h".equalsIgnoreCase(action)) {
                isExit = true;
                print("Вы завершили работу программы");
            }

        } catch (StringIndexOutOfBoundsException | IllegalArgumentException s) {
            print("Недопустимая буква. Попрбуйте еще раз.");
            chooseAction(products);
        }
    }

    private void showActions(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            print(String.format(" %s - %s", products.get(i).getActionLetter().getValue(), products.get(i).getName()));
        }
    }

    private String fromConsole() {
        return new Scanner(System.in).nextLine();
    }

    private void showProducts(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            print(products.get(i).toString());
        }
    }

    private void print(String msg) {
        System.out.println(msg);
    }
}
