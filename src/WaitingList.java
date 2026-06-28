import java.util.Queue;
import java.util.LinkedList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class WaitingList {
    private Queue<Customer> waitingList;

    public WaitingList() {
        waitingList = new LinkedList<>();
    }

    // Adds customers in registration-date order so the queue follows the assignment rule.
    // Expected date format: YYYY-MM-DD, for example 2026-06-28.
    public void addCustomer(Customer customer) {
        addCustomerByDateRegistered(customer);
    }

    public void addCustomerByDateRegistered(Customer customer) {
        if (customer == null) {
            System.out.println("Invalid customer");
            return;
        }

        if (containsCustomer(customer)) {
            System.out.println("Customer " + customer.getName() + " is already in the waiting list");
            return;
        }

        LinkedList<Customer> orderedList = new LinkedList<>(waitingList);
        int insertPosition = orderedList.size();

        for (int i = 0; i < orderedList.size(); i++) {
            Customer currentCustomer = orderedList.get(i);

            if (isEarlierOrSameDate(customer.getDateRegistered(), currentCustomer.getDateRegistered())) {
                insertPosition = i;
                break;
            }
        }

        orderedList.add(insertPosition, customer);
        waitingList = orderedList;

        System.out.println("Customer " + customer.getName() + " added to allocation queue by date registered");
    }

    private boolean isEarlierOrSameDate(String newCustomerDate, String currentCustomerDate) {
        try {
            LocalDate newDate = LocalDate.parse(newCustomerDate);
            LocalDate currentDate = LocalDate.parse(currentCustomerDate);
            return !newDate.isAfter(currentDate);
        } catch (DateTimeParseException error) {
            return false;
        }
    }

    public boolean containsCustomer(Customer customer) {
        return waitingList.contains(customer);
    }

    public Customer removeCustomer() {
        if (!waitingList.isEmpty()) {
            Customer customer = waitingList.poll();
            System.out.println("Customer " + customer.getName() + " removed from waiting list");
            return customer;
        }
        System.out.println("Waiting list is empty");
        return null;
    }

    public Customer viewNextCustomer() {
        if (!waitingList.isEmpty()) {
            return waitingList.peek();
        }
        System.out.println("Waiting list is empty");
        return null;
    }

    public boolean isEmpty() { return waitingList.isEmpty(); }
    public int getSize() { return waitingList.size(); }

    public void displayWaitingList() {
        if (waitingList.isEmpty()) {
            System.out.println("Waiting list is empty");
            return;
        }
        System.out.println("Customers in Allocation Queue / Waiting List, ordered by date registered:");
        int position = 1;
        for (Customer customer : waitingList) {
            System.out.println(position + ". " + customer.getName()
                    + " (ID: " + customer.getCustomerID() + ")"
                    + " - Registered: " + customer.getDateRegistered()
                    + " - Preference: " + customer.getPreferredHouseType()
                    + ", " + customer.getPreferredBedrooms() + " bedroom, "
                    + customer.getPreferredDesignStyle());
            position++;
        }
    }

    public boolean removeSpecificCustomer(Customer customer) {
        if (customer != null && waitingList.remove(customer)) {
            System.out.println("Customer " + customer.getName() + " removed from waiting list");
            return true;
        }
        System.out.println("Customer not found in waiting list");
        return false;
    }
}
