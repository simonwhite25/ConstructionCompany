import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);
    private static ConstructionCompany company = new ConstructionCompany("Fantasy Houses", "London");
    private static WaitingList waitingList = new WaitingList();
    private static int nextAppointmentID = 1;

    public static void main(String[] args) {
        loadHouses();

        boolean running = true;

        while (running) {
            displayMenu();

            int option = getIntInput("Choose an option: ");

            switch (option) {
                case 1:
                    registerCustomer();
                    break;
                case 2:
                    company.displayAllCustomers();
                    break;
                case 3:
                    company.displayAvailableHouses();
                    break;
                case 4:
                    searchHouseByStyle();
                    break;
                case 5:
                    purchaseHouse();
                    break;
                case 6:
                    scheduleAppointment();
                    break;
                case 7:
                    waitingList.displayWaitingList();
                    break;
                case 8:
                    running = false;
                    System.out.println("Thank you for choosing Fantasy Houses!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        input.close();
    }

    private static void displayMenu() {
        System.out.println("\n========== Fantasy Houses ==========");
        System.out.println("1. Register Customer");
        System.out.println("2. View All Customers");
        System.out.println("3. Display Available Houses");
        System.out.println("4. Search House by Style");
        System.out.println("5. Purchase House");
        System.out.println("6. Schedule Appointment");
        System.out.println("7. View Waiting List");
        System.out.println("8. Exit");
        System.out.println("====================================");
    }

    private static void loadHouses() {
        House house1 = company.addHouse(new DetachedHouse("Detached", 220000, "Kilkenny", "20% Complete", 3, true, "Started"));
        House house2 = company.addHouse(new DetachedHouse("Detached", 300000, "Kildare", "Not Started", 2, false, "Nothing Started"));
        House house3 = company.addHouse(new SemiDetachedHouse("Semi-detached", 120000, "Lisburn", "90% Completed", 2, true, "Nearly Complete"));
        House house4 = company.addHouse(new SemiDetachedHouse("Semi-detached", 150000, "Glin", "50% Complete", 3, true, "Half build completed"));

        house1.setQuantityAvailable(7);
        house2.setQuantityAvailable(8);
        house3.setQuantityAvailable(5);
        house4.setQuantityAvailable(10);
    }

    private static void registerCustomer() {
        System.out.println("\n--- Register Customer ---");

        System.out.print("Customer Name: ");
        String name = input.nextLine();

        System.out.print("Customer Email: ");
        String email = input.nextLine();

        System.out.print("Date Registered: ");
        String date = input.nextLine();

        Customer customer = new Customer(name, email, date, null);
        company.addCustomer(customer);

        System.out.println("Customer registered successfully.");
        System.out.println("Customer ID: " + customer.getCustomerID());
    }

    private static void searchHouseByStyle() {
        System.out.println("\n--- Search House by Style ---");
        System.out.print("Enter style, for example Kilkenny, Kildare, Lisburn, Glin: ");
        String style = input.nextLine();

        ArrayList<House> matchingHouses = company.findHousesByStyle(style);

        if (matchingHouses.isEmpty()) {
            System.out.println("No houses found for that style.");
        } else {
            for (House house : matchingHouses) {
                house.displayHouseDetails();
                System.out.println("Quantity Available: " + house.getQuantityAvailable());
                System.out.println("----------------------");
            }
        }
    }

    private static void purchaseHouse() {
        System.out.println("\n--- Purchase House ---");

        company.displayAvailableHouses();

        int customerID = getIntInput("Enter Customer ID: ");
        Customer customer = company.findCustomerByID(customerID);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        int houseID = getIntInput("Enter House ID to purchase: ");
        House house = company.findHouseById(houseID);

        if (house == null) {
            System.out.println("House not found.");
            return;
        }

        if (house.isAvailableForPurchase()) {
            company.purchaseHouse(customer, house);
        } else {
            System.out.println("House is not currently available.");
            waitingList.addCustomer(customer);
        }
    }

    private static void scheduleAppointment() {
        System.out.println("\n--- Schedule Appointment ---");

        int customerID = getIntInput("Enter Customer ID: ");
        Customer customer = company.findCustomerByID(customerID);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        if (!customer.hasSelectedHouse()) {
            System.out.println("Customer has not selected/purchased a house yet.");
            return;
        }

        System.out.print("Enter appointment date: ");
        String date = input.nextLine();

        System.out.print("Enter appointment time: ");
        String time = input.nextLine();

        Appointment appointment = new Appointment(nextAppointmentID++, date, time, false);
        appointment.scheduleAppointment(customer);
        appointment.displayAppointmentDetails();
    }

    private static int getIntInput(String message) {
        while (true) {
            System.out.print(message);

            if (input.hasNextInt()) {
                int number = input.nextInt();
                input.nextLine();
                return number;
            } else {
                System.out.println("Please enter a valid number.");
                input.nextLine();
            }
        }
    }
}