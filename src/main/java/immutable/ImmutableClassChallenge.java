package immutable;


import immutable.sealed.FinalKid;
import immutable.sealed.NonSealedKid;
import immutable.sealed.SealedKid;
import immutable.sealed.SpecialAbstractClass;
import immutable.sealed.iface.StringsChecker;

import java.math.BigDecimal;
import java.util.List;

public class ImmutableClassChallenge {

    public static void main(String[] args) {
        immutableClassesDesign();

        sealedKeyword();
    }

    private static void immutableClassesDesign() {
        System.out.println("=> Immutable Classes Design <=");
        BankAccount acc1 = new BankAccount(BigDecimal.ONE, BankAccount.AccountType.CHECKING);
        BankAccount acc2 = new BankAccount(BigDecimal.TEN, BankAccount.AccountType.SAVINGS);
        BankCustomer custo1 = new BankCustomer("Bobby", 123L, List.of(acc1, acc2));

        BankAccount acc3 = new BankAccount(BigDecimal.ZERO, BankAccount.AccountType.CHECKING);
        BankAccount acc4 = new BankAccount(BigDecimal.TWO, BankAccount.AccountType.SAVINGS);
        BankCustomer custo2 = new BankCustomer("Dolly", 456L, List.of(acc3, acc4));

        System.out.println(custo1);
        System.out.println(custo2);

        // Not possible - immutable
//        custo1.getAccounts().add(new BankAccount(new BigDecimal(1_000_000), BankAccount.AccountType.SAVINGS));
//        System.out.println(custo1);

        System.out.println("-= Before =-");
        custo1.getAccounts().forEach(account -> {
            BigDecimal balance = account.getBalance();
            System.out.println("Account type = " + account.getType());
            System.out.println("Balance = " + balance);
            balance = balance.add(new BigDecimal(1_000_000));
        });

        System.out.println("-= After =-");
        custo1.getAccounts().forEach(account -> {
            System.out.println("Account type = " + account.getType());
            System.out.println("Balance = " + account.getBalance());
        });

        // Better to use unmodifiable collections on class,
        // then unwanted manipulation over them is not possible from outside
//        System.out.printf("%s accounts before cleanup", custo1.getName());
//        System.out.println(custo1.getAccounts());
//        custo1.getAccounts().clear();
//        System.out.printf("%s accounts after cleanup", custo1.getName());
//        System.out.println(custo1.getAccounts());

        BankCustomerExt custoExt = new BankCustomerExt("Bobby EXT", 789, List.of(acc1, acc2, acc3));
        System.out.println(custoExt);
        System.out.println(custoExt.getAccounts());
    }

    /**
     * Sealed classes and interfaces.
     * Since Java 17
     */
    private static void sealedKeyword() {
        // Sealed class
        System.out.println("\n=> Sealed Class <=");
        SpecialAbstractClass.Kid kid = new SpecialAbstractClass.Kid();
        System.out.println(kid.aboutMe());
        FinalKid finalKid = new FinalKid();
        System.out.println(finalKid.aboutMe());
        SealedKid sealedKid = new SealedKid();
        System.out.println(sealedKid.aboutMe());
        NonSealedKid nonSealedKid = new NonSealedKid();
        System.out.println(nonSealedKid.aboutMe());
        SealedKid.GrandKid grandKid = new SealedKid.GrandKid();
        System.out.println(grandKid.aboutMe());

        System.out.println("------------");

        // Sealed interface
        System.out.println("=> Sealed Interface <=");
        StringsChecker sc = new StringsChecker();
        System.out.println("Sealed interface check1=" + sc.testData(String::isBlank, "gog", "fuf", "lol"));
        System.out.println("Sealed interface check2=" + sc.testData(String::isBlank, "tyt", "pop", " "));
    }

}
