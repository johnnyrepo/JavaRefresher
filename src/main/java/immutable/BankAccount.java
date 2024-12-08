package immutable;

import java.math.BigDecimal;

public final class BankAccount {

    enum AccountType {
        CHECKING, SAVINGS
    }

    private final AccountType type;

    private final BigDecimal balance;

    public BankAccount(BigDecimal balance, AccountType type) {
        this.balance = balance;
        this.type = type;
    }

    public AccountType getType() {
        return type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "type=" + type +
                ", balance=" + balance +
                '}';
    }
}



