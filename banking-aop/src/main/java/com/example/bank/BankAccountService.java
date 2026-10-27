package com.example.bank;

import org.springframework.stereotype.Service;

@Service
public class BankAccountService {
    private double balance = 1000.0;

    public synchronized double deposit(double amount) {
        validateAmount(amount);
        balance += amount;
        System.out.printf("Deposited: %.2f | Balance: %.2f%n", amount, balance);
        return balance;
    }

    public synchronized double withdraw(double amount) {
        validateAmount(amount);
        if (amount > balance) {
            throw new InsufficientBalanceException(
                    String.format("Insufficient balance. Available: %.2f, requested: %.2f", balance, amount));
        }
        balance -= amount;
        System.out.printf("Withdrawn: %.2f | Balance: %.2f%n", amount, balance);
        return balance;
    }

    public synchronized double getBalance() {
        return balance;
    }

    private void validateAmount(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Transaction amount must be greater than zero.");
        }
    }
}
