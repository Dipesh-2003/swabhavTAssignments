package com.aurionpro.test;

import com.aurionpro.model.BankAccount;
import com.aurionpro.model.InsufficientFundsException;
import com.aurionpro.model.NegativeAmountException;

public class BankAccountTest {
public static void main(String[] args) {
	BankAccount account = new BankAccount(1000);
    System.out.println("Initial Balance: " + account.getBalance());
    try {
        account.deposit(500);
        System.out.println("After deposit: " + account.getBalance());
        account.withdraw(200);
        System.out.println("After withdrawal: " + account.getBalance());
        account.withdraw(2000); // Should throw InsufficientFundsException
    } catch (NegativeAmountException | InsufficientFundsException e) {
        System.out.println("Exception: " + e.getMessage());
    }
    try {
        account.deposit(-100); // Should throw NegativeAmountException
    } catch (NegativeAmountException e) {
        System.out.println("Exception: " + e.getMessage());
    }
    
}
}
