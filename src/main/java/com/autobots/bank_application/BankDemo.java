package com.autobots.bank_application;

public class BankDemo {
    public static void main(String[] args) throws Exception {

        MBank aliyaMBank = new MBank(123456789012L,123456789);


        aliyaMBank.deposit(100000);

        Optima ulugbekOptima = new Optima(123456789013L, 123456788);


        Bank.transferFunds(aliyaMBank, ulugbekOptima, 500);
        System.out.println(aliyaMBank.getBalance());
        System.out.println(ulugbekOptima.getBalance());

        Demir zinaidaDemir = new Demir(123456790123L,666666666);

        System.out.println(zinaidaDemir.getBalance());

        BankBase.allBankRecords.stream().filter(n->n.getBalance()>100).forEach(System.out::println);

        double allSumOfBanks = BankBase.allBankRecords.stream().mapToDouble(Bank::getBalance).sum();
        System.out.println(allSumOfBanks);
    }
}
