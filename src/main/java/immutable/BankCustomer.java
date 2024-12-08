package immutable;

import java.util.List;

public class BankCustomer {

    private final String name;
    private final long customerId;
    private final List<BankAccount> accounts;

    public BankCustomer(String name, long customerId, List<BankAccount> accounts) {
        this.name = name;
        this.customerId = customerId;
        this.accounts = accounts != null ? List.copyOf(accounts) : List.of();
    }

    public String getName() {
        return name;
    }

    public long getCustomerId() {
        return customerId;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    @Override
    public String toString() {
        return "BankCustomer{" +
                "name='" + name + '\'' +
                ", customerId=" + customerId +
                ", accounts=" + accounts +
                '}';
    }
}
