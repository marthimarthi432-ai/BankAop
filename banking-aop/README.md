# Banking Application using Spring AOP

A simple Spring Boot banking example that starts with a balance of 1000 and supports deposits and withdrawals. Transaction logging is separated from banking logic using Spring AOP.

## Requirements
- Java 17+
- Maven 3.6+

## Run the application
From this folder, run:

```bash
mvn spring-boot:run
```

The demo deposits 500, withdraws 200, then attempts an invalid withdrawal to demonstrate error handling and AOP logging.

## Run tests

```bash
mvn test
```

## AOP annotations
- `@Before`: logs before deposit/withdraw methods execute.
- `@AfterReturning`: logs successful transactions and the resulting balance.
- `@AfterThrowing`: logs transaction failures.

## Project structure
- `BankingAopApplication.java` — application entry point and demo runner.
- `BankAccountService.java` — deposit, withdrawal, validation, and balance state.
- `InsufficientBalanceException.java` — exception for insufficient funds.
- `TransactionLoggingAspect.java` — separate transaction logging aspect.
- `BankAccountServiceTest.java` — tests for initial balance, successful transactions, and failure cases.

Note: This is an educational demo using an in-memory balance and `double` for simplicity; it does not persist accounts or handle real banking transactions.
