import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConstructionCompany {
    private ArrayList<Customer> customers;
    private ArrayList<House> houses;
    private String companyName;
    private String companyLocation;
    private Map<String, Map<String, ArrayList<House>>> housesByStyleAndType;

    // Constructor
    public ConstructionCompany(String companyName, String companyLocation) {
        this.companyName = companyName;
        this.companyLocation = companyLocation;
        this.customers = new ArrayList<>();
        this.houses = new ArrayList<>();
        this.housesByStyleAndType = new HashMap<>();
    }

    // Add a new customer to the company
    public void addCustomer(Customer customer) {
        if (customer != null) {
            customers.add(customer);
            System.out.println("Customer " + customer.getName() + " added successfully");
        } else {
            System.out.println("Invalid customer");
        }
    }

    // Add a new house to the inventory
    public House addHouse(House house) {
        if (validateHouse(house)) {
            houses.add(house);
            organizeHouseByStyleAndType(house);
            return house;
        }
        System.out.println("House validation failed");
        return null;
    }

    // Organize house in the nested map structure
    private void organizeHouseByStyleAndType(House house){
        String style = house.getStyle();
        String houseType = house.getClass().getSimpleName();

        housesByStyleAndType.putIfAbsent(style, new HashMap<>());

        housesByStyleAndType.get(style).putIfAbsent(houseType, new ArrayList<>());

        housesByStyleAndType.get(style).get(houseType).add(house);
    }

    // Validate house before adding
    public boolean validateHouse(House house) {
        if (house == null) {
            return false;
        }
        // Check if price is valid (positive)
        if (house.getPrice() <= 0) {
            return false;
        }
        // Check if style is not null
        if (house.getStyle() == null || house.getStyle().isEmpty()) {
            return false;
        }
        return true;
    }

    // Purchase a house for a customer
    public void purchaseHouse(Customer customer, House house) {
        if (customer == null || house == null) {
            System.out.println("Invalid customer or house");
            return;
        }
        if (!houses.contains(house)) {
            System.out.println("House not available in inventory");
            return;
        }
        if (!house.isAvailableForPurchase()){
            System.out.println("House is out of stock. Will contact customer as soon as a house is available.");
            return;
        }
        customer.setSelectedHouse(house);
        house.decrementQuantity();
        System.out.println(customer.getName() + " has purchased the house");
        System.out.println("Remaining Quantity: " + house.getQuantityAvailable());
    }

    // Change design of a house
    public void changeDesign(House house, String style) {
        if (house != null && style != null && !style.isEmpty()) {
            house.setStyle(style);
            System.out.println("House design changed to " + style);
        } else {
            System.out.println("Invalid house or style");
        }
    }

    // Display all available houses organized by style and type
    public void displayAvailableHouses() {
        if (houses.isEmpty()) {
            System.out.println("No houses available");
            return;
        }
        System.out.println("====== AVAILABLE HOUSES ======");

        for (String style : housesByStyleAndType.keySet()) {
            System.out.println("\n--- Style: " + style + " ---");

            Map<String, ArrayList<House>> typeMap = housesByStyleAndType.get(style);
            for (String houseType : typeMap.keySet()) {
                System.out.println("\n  " + houseType + ":");
                ArrayList<House> housesOfType = typeMap.get(houseType);

                for (House house : housesOfType) {
                    System.out.println("    House ID: " + house.getHouseID());
                    System.out.println("    Bedrooms: " + house.getNumberOfBedrooms());
                    System.out.println("    Price: £" + house.getPrice());
                    System.out.println("    State: " + house.getState());

                    // Display type-specific details
                    if (house instanceof DetachedHouse) {
                        DetachedHouse detached = (DetachedHouse) house;
                        System.out.println("    Construction Progress: " + detached.getConstructionProgress());
                        System.out.println("    Private Garden: " + (detached.isPrivateGarden() ? "Yes" : "No"));
                    } else if (house instanceof SemiDetachedHouse) {
                        SemiDetachedHouse semiDetached = (SemiDetachedHouse) house;
                        System.out.println("    Construction Progress: " + semiDetached.getConstructionProgress());
                        System.out.println("    Shared Wall: " + (semiDetached.isSharedWall() ? "Yes" : "No"));
                    }

                    System.out.println("    Quantity Available: " + house.getQuantityAvailable());
                    System.out.println();
                }
            }
        }
    }

    // Display all customers
    public void displayAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers registered");
            return;
        }
        System.out.println("All Customers:");
        for (Customer customer : customers) {
            System.out.println("ID: " + customer.getCustomerID() +
                    ", Name: " + customer.getName() +
                    ", Registered: " + customer.getDateRegistered());
        }
    }

    // Find customer by ID
    public Customer findCustomerByID(int customerID) {
        for (Customer customer : customers) {
            if (customer.getCustomerID() == customerID) {
                return customer;
            }
        }
        return null;
    }

    //Find house by ID
    public House findHouseById(int houseID) {
        for (House house : houses){
            if (house.getHouseID() == houseID){
                return house;
            }
        }
        return null;
    }

    // Find houses by style
    public ArrayList<House> findHousesByStyle(String style) {
        ArrayList<House> result = new ArrayList<>();

        for (String storedStyle : housesByStyleAndType.keySet()) {
            if (storedStyle.equalsIgnoreCase(style)) {
                Map<String, ArrayList<House>> typeMap = housesByStyleAndType.get(storedStyle);

                for (ArrayList<House> houseList : typeMap.values()) {
                    result.addAll(houseList);
                }
            }
        }

        return result;
    }

    // Find houses by style and type
    public ArrayList<House> findHousesByStyleAndType(String style, String houseType) {
        if (housesByStyleAndType.containsKey(style)) {
            Map<String, ArrayList<House>> typeMap = housesByStyleAndType.get(style);
            if (typeMap.containsKey(houseType)) {
                return typeMap.get(houseType);
            }
        }
        return new ArrayList<>();
    }

    // Getters
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<House> getHouses() {
        return houses;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    // Setters
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }
}