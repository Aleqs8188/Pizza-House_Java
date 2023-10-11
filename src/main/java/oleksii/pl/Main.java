package oleksii.pl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        DataBaseSearchInClients dataBaseSearch = new DataBaseSearchInClients();
        ObjectWriter objectWriter = new ObjectWriter();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        DataBaseSearchInPizza dataBaseSearchInPizza = new DataBaseSearchInPizza();
        RewriterClientDB rewriterClientDB = new RewriterClientDB();
        while (true) {
            int choice;
            PrintSymbols();
            System.out.println("                 Hey, you are in PizzaHouse!");
            System.out.println("If you want to start, you need to register(1) or login(2).");
            choice = scan.nextInt();
            if (choice == 1) {
                PrintSymbols();
                PrintSymbols();
                System.out.println("Your email: ");
                String email = reader.readLine();
                System.out.println("Your password: ");
                String password = reader.readLine();
                Client searchSamePerson = dataBaseSearch.searchByParameter(email, password);
                if (searchSamePerson.getId() != 0) {
                    System.err.println("***Client with the same email and password also is***");
                    System.err.println("**********************Try again**********************");
                    continue;
                }
                System.out.println("Your name: ");
                String name = reader.readLine();
                System.out.println("Your surname: ");
                String surname = reader.readLine();
                System.out.println("Your number of card: ");
                int numberOfCard = scan.nextInt();
                System.out.println("Your pin of card: ");
                int pinOfCard = scan.nextInt();
                System.out.println("Your money on this card: ");
                BigDecimal money = scan.nextBigDecimal();
                System.out.println("Your username: ");
                String username = reader.readLine();
                Client registerClient = new Client(name, surname, email, numberOfCard, pinOfCard, money, username, password);
                objectWriter.writePersonToDatabase(registerClient);
                PrintSymbols();
                PrintSymbols();
            } else {
                PrintSymbols();
            }
            System.out.println("*****Log in to your account*****");
            System.out.println("Enter your email: ");
            String emailLog = reader.readLine();
            System.out.println("Enter your password: ");
            String passwordLog = reader.readLine();
            Client logInClient = dataBaseSearch.searchByParameter(emailLog, passwordLog);
            if (logInClient.getId() != 0) {
                PrintSymbols();
                System.out.println("****************Success******************");
            } else {
                PrintSymbols();
                System.err.println("****************Try again****************");
                continue;
            }
            PrintSymbols();
            while (true) {
                System.out.printf("*Hello " + logInClient.getUsername() + ", your balance is: \"%.2f\"" + " zł.%n", logInClient.getMoney());
                System.out.println("***Choose what do you want to do...");
                System.out.println("1) Menu || 2) Check an order list || 3) Make an order || 4) Exit");
                System.out.print("Enter your choice: ");
                choice = scan.nextInt();
                switch (choice) {
                    case 1 -> {
                        PrintSymbols();
                        dataBaseSearchInPizza.printMenuPizzas();
                        System.out.println("Choose what are you want: ");
                        choice = scan.nextInt();
                        switch (choice) {
                            case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 -> dataBaseSearchInPizza.getPizzaFromDbByParam(choice);
                        }
                    }
                    case 2 -> {
                        int number;
                        PrintSymbols();
                        double sum = dataBaseSearchInPizza.orderList.getTotalSumToPay();
                        System.out.printf("Total sum to pay: " + "%.2f" + " zl." + " Your order list: " + "%n", sum);
                        dataBaseSearchInPizza.orderList.orderList.forEach(System.out::println);
                        PrintSymbols();
                        System.out.println("***Choose what do you want to do...");
                        System.out.println("1) Delete something from list || 2) Full clear order list || 3) Go Back || 4) Exit");
                        System.out.print("Enter your choice: ");
                        choice = scan.nextInt();
                        switch (choice) {
                            case 1 -> {
                                System.out.print("Enter number of pizza to delete: ");
                                number = scan.nextInt();
                                dataBaseSearchInPizza.orderList.setTotalSumToPay(dataBaseSearchInPizza.orderList.getTotalSumToPay() - dataBaseSearchInPizza.deleteFromOrder(number));
                                dataBaseSearchInPizza.orderList.orderList.forEach(System.out::println);
                            }
                            case 2 -> {
                                dataBaseSearchInPizza.orderList.orderList.clear();
                                dataBaseSearchInPizza.orderList.setTotalSumToPay(0);
                                PrintSymbols();
                                System.out.println("Order list clear...");
                                PrintSymbols();
                            }
                            case 3 -> {
                                break;
                            }
                            case 4 -> {
                                return;
                            }
                        }
                    }
                    case 3 -> {
                        BigDecimal sumToPay = BigDecimal.valueOf(dataBaseSearchInPizza.orderList.getTotalSumToPay());
                        BigDecimal sum = logInClient.getMoney().subtract(sumToPay);
                        rewriterClientDB.updateMoneyForClient(logInClient, sum);
                        dataBaseSearchInPizza.orderList.orderList.clear();
                        dataBaseSearchInPizza.orderList.setTotalSumToPay(0);
                        PrintSymbols();
                        System.out.println("*******Success********");
                        System.out.println("Wait for your pizza...");
                        PrintSymbols();
                    }
                    case 4 -> {
                        return;
                    }
                }
            }
        }
    }

    public static void PrintSymbols() {
        System.out.println("****************************************");
    }
}

