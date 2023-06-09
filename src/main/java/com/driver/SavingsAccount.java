package com.driver;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavingsAccount extends BankAccount{
    double rate;
    double maxWithdrawalLimit;
    private  int nWithdraws;

    public SavingsAccount(String name, double balance, double maxWithdrawalLimit, double rate) {
        // minimum balance is 0 by default
        super(name,balance,0);
        this.rate = rate;
        this.maxWithdrawalLimit = maxWithdrawalLimit;
    }

    public void withdraw(double amount) throws Exception {
        // Might throw the following errors:
        // 1. "Maximum Withdraw Limit Exceed" : If the amount exceeds maximum withdrawal limit
        // 2. "Insufficient Balance" : If the amount exceeds balance

        if(nWithdraws>maxWithdrawalLimit)
            throw new Exception("Maximum Withdraw Limit Exceed");

        if(amount>super.getBalance())
            throw new Exception("Insufficient Balance");

        super.setBalance(super.getMinBalance()-amount);
        nWithdraws++;
        return;
    }

    public double getSimpleInterest(int years){
        // Return the final amount considering that bank gives simple interest on current amount

        return (rate*years*super.getBalance()) + super.getBalance();
    }

    public double getCompoundInterest(int times, int years){
        // Return the final amount considering that bank gives compound interest on current amount given times per year

        return super.getBalance()*Math.pow(1 + rate/times, years);
    }

}
