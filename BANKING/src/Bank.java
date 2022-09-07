import java.util.ArrayList;


public class Bank {
    private String name;
    // An arrayList of object class Branches
    private ArrayList<Branch> branches;

    public Bank(String name) {
        this.name = name;
        this.branches = new ArrayList<Branch>();
    }

    public boolean addBranch(String branchName) {
        if(findBranch(branchName) == null) {
            this.branches.add(new Branch(branchName));
            return true;
        }
        return false;
    }

    public boolean addCustomer(String branchName, String customerName, double initialAmount) {
        Branch branch = findBranch(branchName);
        if(branch != null) {
            return branch.newCustomer(customerName, initialAmount);
        }

        return false;
    }

    public boolean addCustomerTransaction(String branchName, String customerName, double amount) {
        Branch branch = findBranch(branchName);
        if(branch != null) {
            return branch.addCustomerTransaction(customerName, amount);
        }

        return false;
    }

    private Branch findBranch(String branchName) {
        for(int i=0; i<this.branches.size(); i++) {
            Branch checkedBranch = this.branches.get(i);
            if(checkedBranch.getName().equals(branchName)) {
                return checkedBranch;
            }
        }

        return null;
    }

    public boolean listCustomers(String branchName, boolean showTransactions) {
        Branch branch = findBranch(branchName);
        if(branch != null) {
            System.out.println("Customer details for branch " + branch.getName());
            //Next we need a list of customers that are part of the branch we have just received.
            ArrayList<Customer> branchCustomers = branch.getCustomers();
            //We need a forloop to print the list of customers that we have out.
            for(int i=0; i<branchCustomers.size(); i++) {
                Customer branchCustomer = branchCustomers.get(i);
                 // showing the name of the customer.
                System.out.println("Customer: " + branchCustomer.getName() + "[" + (i+1) + "]");
                //Now we have to get a list of transactions from those customers
                //This part of the code is going to execute only if show customers is true
                if(showTransactions) {
                    System.out.println("Transactions");
                    // (List of transactions for our customer)Now we need an ArrayList for our doubles (numbers) of the customers.
                    ArrayList<Double> transactions = branchCustomer.getTransactions();
                    for(int j=0; j<transactions.size(); j++) {
                        System.out.println("[" + (j+1) + "]  Amount "  + transactions.get(j));
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }}


