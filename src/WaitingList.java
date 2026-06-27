import java.util.Queue;
import java.util.LinkedList;

public class WaitingList {
    private Queue<Customer> waitingList;

    public WaitingList(){
        waitingList = new LinkedList<>();
    }

    public void addCustomer(Customer customer){
        if (customer != null) {
            waitingList.add(customer);
            System.out.println("Customer " + customer.getName() + " added to waiting list");
        } else {
            System.out.println("Invalid customer");
        }
    }

    public Customer removeCustomer(){
        if (!waitingList.isEmpty()) {
            Customer customer = waitingList.poll();
            System.out.println("Customer " + customer.getName() + " removed from waiting list");
            return customer;
        } else {
            System.out.println("Waiting list is empty");
            return null;
        }
    }

    public Customer viewNextCustomer(){
        if (!waitingList.isEmpty()) {
            return waitingList.peek();
        } else {
            System.out.println("Waiting list is empty");
            return null;
        }
    }

    public boolean isEmpty(){
        return waitingList.isEmpty();
    }

    public int getSize(){
        return waitingList.size();
    }

    public void displayWaitingList(){
        if (waitingList.isEmpty()) {
            System.out.println("Waiting list is empty");
            return;
        }
        System.out.println("Customers in Waiting List:");
        int position = 1;
        for (Customer customer : waitingList) {
            System.out.println(position + ". " + customer.getName() + " (ID: " + customer.getCustomerID() + ")");
            position++;
        }
    }

    public boolean removeSpecificCustomer(Customer customer){
        if (customer != null && waitingList.remove(customer)) {
            System.out.println("Customer " + customer.getName() + " removed from waiting list");
            return true;
        } else {
            System.out.println("Customer not found in waiting list");
            return false;
        }
    }
}
