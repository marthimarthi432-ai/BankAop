package com.example.bank;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankingAopApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankingAopApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(BankAccountService bank) {
        return args -> {
            System.out.println("\n--- Banking AOP Demo ---");
            System.out.println("Initial balance: " + bank.getBalance());
            bank.deposit(500);
            bank.withdraw(200);
            try {
                bank.withdraw(2000);
            } catch (InsufficientBalanceException ex) {
                System.out.println("Transaction failed: " + ex.getMessage());
            }
            System.out.println("Final balance: " + bank.getBalance());
        };
    }
}
