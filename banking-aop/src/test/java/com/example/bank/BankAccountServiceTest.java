package com.example.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountServiceTest {
    private BankAccountService bank;

    @BeforeEach
    void setUp() { bank = new BankAccountService(); }

    @Test
    void startsWithOneThousand() { assertEquals(1000.0, bank.getBalance(), 0.001); }

    @Test
    void depositUpdatesBalance() { assertEquals(1500.0, bank.deposit(500), 0.001); }

    @Test
    void withdrawalUpdatesBalance() { assertEquals(700.0, bank.withdraw(300), 0.001); }

    @Test
    void insufficientFundsDoesNotChangeBalance() {
        assertThrows(InsufficientBalanceException.class, () -> bank.withdraw(1001));
        assertEquals(1000.0, bank.getBalance(), 0.001);
    }

    @Test
    void rejectsZeroOrNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> bank.deposit(0));
        assertThrows(IllegalArgumentException.class, () -> bank.withdraw(-10));
        assertEquals(1000.0, bank.getBalance(), 0.001);
    }
}
