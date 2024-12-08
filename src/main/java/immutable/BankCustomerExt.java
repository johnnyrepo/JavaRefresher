package immutable;

import java.util.List;

public class BankCustomerExt extends BankCustomer {
    public BankCustomerExt(String name, long customerId, List<BankAccount> accounts) {
        super(name, customerId, accounts);
    }

//    @Override
//    public List<BankAccount> getAccounts() {
//        List<BankAccount> accounts = new ArrayList<>(getAccounts());
//        accounts.add(new BankAccount(new BigDecimal(1_000_000), BankAccount.AccountType.SAVINGS));
//
//        return accounts;
//    }
}
