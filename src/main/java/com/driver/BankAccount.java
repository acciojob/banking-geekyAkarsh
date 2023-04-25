package com.driver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter


public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {

        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception

        if(sum<0 || digits*9 < sum) throw new Exception("Account Number can not be generated");

        StringBuilder accNo = new StringBuilder();

        Random rand = new Random();
        int remSum = sum;

        for(int i=0;i<digits;i++){

            int max = Math.min(remSum+1,10);

            int n = rand.nextInt(max);
            accNo.append(String.valueOf(n));

            remSum -= n;
        }

        return accNo.toString();
    }

    public void deposit(double amount) {
        //add amount to balance

        this.setBalance(amount+this.getBalance());
        return;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance

        if(this.getBalance()-amount<this.getMinBalance())
            throw new Exception("Insufficient Balance");

        this.setBalance(this.getMinBalance()-amount);
        return;
    }
}