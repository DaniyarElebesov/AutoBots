package com.autobots.bank_application;

public interface Bank {

    double getBalance();
    void deposit(double amount);
    void withdraw(double amount);

    static void transferFunds(Bank sender, Bank recipient, double transferAmount){
        sender.withdraw(transferAmount);
        recipient.deposit(transferAmount);
    }

}
