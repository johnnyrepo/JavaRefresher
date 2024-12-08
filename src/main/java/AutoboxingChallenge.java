import java.util.ArrayList;
import java.util.List;

public class AutoboxingChallenge {

    public static void main(String[] args) {
        Bank bank = new Bank("Silicon Valley Bank");
        bank.addCustomer("Bill Gates");
        bank.addCustomer("Jeff Bezos");
        bank.addCustomer("Larry Ellison");

        bank.printStatement("Vasja Pupkin");
        bank.printStatement("Bill Gates");
        bank.printStatement("Jeff Bezos");
        bank.printStatement("Larry Ellison");

        bank.addTransaction("Vasja Pupkin", 1_000_000_000);
        bank.addTransaction("Bill Gates", 10_000_000);
        bank.addTransaction("Bill Gates", 40_000_000);
        bank.addTransaction("Bill Gates", 50_000_000);
        bank.addTransaction("Jeff Bezos", 1_000_000);
        bank.addTransaction("Jeff Bezos", 2_000_000);
        bank.addTransaction("Jeff Bezos", -3_000_000);
        bank.addTransaction("Larry Ellison", 10);
        bank.addTransaction("Larry Ellison", 200);
        bank.addTransaction("Larry Ellison", -100);

        bank.printStatement("Bill Gates");
        bank.printStatement("Jeff Bezos");
        bank.printStatement("Larry Ellison");
    }

}

class Bank {

    private String name;

    private List<Customer> customers = new ArrayList<>();

    public Bank(String name) {
        this.name = name;
    }

    public boolean addCustomer(String name) {
        if (findCustomer(name) != null) {
            return false;
        }

        return customers.add(new Customer(name));
    }

    public boolean addTransaction(String name, double amount) {
        var customer = findCustomer(name);
        if (customer == null) {
            return false;
        }
        customer.addTransaction(amount);

        return  false;
    }

    public void printStatement(String name) {
        var customer = findCustomer(name);
        if (customer == null) {
            System.out.printf("No customer with name %s found.%n", name);
        } else {
            System.out.println(customer.getName() + " has following transactions: " + customer.getTransactions());
        }
    }

    private Customer findCustomer(String name) {
        for (var c : customers) {
            if (c.getName().equals(name)) {
                return c;
            }
        }

        return null;
    }

}

class Customer {

    private String name;

    private List<Double> transactions = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Double> getTransactions() {
        return transactions;
    }

    public void addTransaction(double amount) {
        transactions.add(amount);
    }
}

