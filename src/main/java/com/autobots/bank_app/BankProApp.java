package com.autobots.bank_app;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankProApp {

    static Scanner sc = new Scanner(System.in);
    static final Map<String, Client> clients = new HashMap<>();

    public static void main(String[] args) {


        Client asan = new Client("Asan Uson", "123");

        DepositAccount asanDepositAccount = new DepositAccount(asan, Currency.USD);
        CreditAccount asanCreditAccount = new CreditAccount(asan, Currency.EUR);

        asan.addAccount(asanDepositAccount);
        asan.addAccount(asanCreditAccount);
        System.out.println(asan.getClientID());

        clients.put(asan.getClientID(), asan);

        System.out.println("==========Welcome to MBank=============");
        System.out.print("Please enter client ID");
        String clientID = sc.nextLine();
        Client client = clients.get(clientID);
        if (client == null) {
            System.out.println("client was not found");
            return;
        }
        System.out.print("Please enter pin code:");
        String pinCode = sc.nextLine();
        if (!client.authenticate(pinCode)) {
            System.out.println("You have entered wrong pin code");
            return;
        }
        System.out.println(client.getFullName() + " Welcome to MBank");
        while (true) {
            System.out.println("1. View accounts");
            System.out.println("2. Transfer between accounts");
            System.out.println("3. View checks");
            System.out.println("4. Log out");

            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    for (BankAccount bankAccount : client.getAccounts()) {
                        System.out.printf("%s | %s | Баланс: %.2f %s\n",
                                bankAccount.getClass().getSimpleName(),
                                bankAccount.getAccountNumber(),
                                bankAccount.getBalance(), bankAccount.getCurrency());

                    }
                    break;
                case "2":
                    System.out.print("Enter the account number of the sender");
                    String fromID = sc.nextLine();
                    System.out.println("Enter the account number of the receiver");
                    String toID = sc.nextLine();
                    System.out.println("Sum: ");
                    double amount = Double.parseDouble(sc.nextLine());
                    BankAccount from = findAccount(fromID);
                    BankAccount to = findAccount(fromID);
                    if (from != null && to != null) {
                        boolean result = BankService.transfer(from, to, amount);
                        System.out.println(result ? "Операция прошла успешно" : "Ошибка перевода");
                    } else {
                        System.out.println("Счета не найдены");
                    }
                    break;
                case "3":
                    for (BankAccount bankAccount : client.getAccounts()) {
                        System.out.println("\n == чек по счету " + bankAccount.getAccountNumber());
                        for (Transaction transaction : bankAccount.getTransactions()) {
                            System.out.println(transaction);
                        }
                    }
                    break;

                case "0":
                    System.out.println("Выход...");
                    return;

                default:
                    System.out.println("invalid choice");
            }

        }
    }

        private static BankAccount findAccount (String accountNumber){
            for (Client client : clients.values()) {
                for (BankAccount bankAccount : client.getAccounts()) {
                    if (bankAccount.getAccountNumber().equals(accountNumber)) {
                        return bankAccount;
                    }
                }
            }
            return null;
        }
    }
