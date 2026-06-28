import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConstructionCompany {
    private ArrayList<Customer> customers;
    private ArrayList<House> houses;
    private String companyName;
    private String companyLocation;
    private Map<String, Map<String, ArrayList<House>>> housesByStyleAndType;

    public ConstructionCompany(String companyName, String companyLocation) {
        this.companyName = companyName;
        this.companyLocation = companyLocation;
        this.customers = new ArrayList<>();
        this.houses = new ArrayList<>();
        this.housesByStyleAndType = new HashMap<>();
    }

    public void addCustomer(Customer customer) {
        if (customer != null && validateCustomer(customer)) {
            customers.add(customer);
            System.out.println("Customer " + customer.getName() + " added successfully");
        } else {
            System.out.println("Invalid customer");
        }
    }

    public boolean validateCustomer(Customer customer) {
        return customer != null
                && customer.getName() != null && !customer.getName().isEmpty()
                && customer.getEmail() != null && customer.getEmail().contains("@")
                && customer.getDateRegistered() != null && !customer.getDateRegistered().isEmpty();
    }

    public House addHouse(House house) {
        if (validateHouse(house)) {
            houses.add(house);
            organizeHouseByStyleAndType(house);
            return house;
        }
        System.out.println("House validation failed");
        return null;
    }

    private void organizeHouseByStyleAndType(House house) {
        String style = house.getStyle();
        String houseType = house.getType();
        housesByStyleAndType.putIfAbsent(style, new HashMap<>());
        housesByStyleAndType.get(style).putIfAbsent(houseType, new ArrayList<>());
        housesByStyleAndType.get(style).get(houseType).add(house);
    }

    public boolean validateHouse(House house) {
        return house != null
                && house.getPrice() > 0
                && house.getStyle() != null && !house.getStyle().isEmpty()
                && house.getType() != null && !house.getType().isEmpty()
                && house.getNumberOfBedrooms() > 0;
    }

    public void purchaseHouse(Customer customer, House house) {
        if (customer == null || house == null) {
            System.out.println("Invalid customer or house");
            return;
        }
        if (!houses.contains(house)) {
            System.out.println("House not available in inventory");
            return;
        }
        if (!house.isAvailableForPurchase()) {
            System.out.println("House is out of stock. Customer should be added to waiting list.");
            return;
        }
        customer.setSelectedHouse(house);
        customer.selectHousePreference(house.getType(), house.getStyle(), house.getNumberOfBedrooms());
        house.decrementQuantity();
        System.out.println(customer.getName() + " has purchased the house");
        System.out.println("Remaining Quantity: " + house.getQuantityAvailable());
    }

    public boolean changeDesign(Customer customer, String newStyle) {
        if (customer == null || customer.getSelectedHouse() == null) {
            System.out.println("Customer has no selected house to modify.");
            return false;
        }
        return customer.getSelectedHouse().modifyDesign(newStyle);
    }

    public void displayAvailableHouses() {
        if (houses.isEmpty()) {
            System.out.println("No houses available");
            return;
        }
        System.out.println("====== AVAILABLE HOUSES ======");
        for (House house : houses) {
            house.displayHouseDetails();
            if (house instanceof DetachedHouse) {
                DetachedHouse detached = (DetachedHouse) house;
                System.out.println("Private Garden: " + (detached.isPrivateGarden() ? "Yes" : "No"));
                System.out.println("Construction Progress: " + detached.getConstructionProgress());
            } else if (house instanceof SemiDetachedHouse) {
                SemiDetachedHouse semiDetached = (SemiDetachedHouse) house;
                System.out.println("Shared Wall: " + (semiDetached.isSharedWall() ? "Yes" : "No"));
                System.out.println("Construction Progress: " + semiDetached.getConstructionProgress());
            }
            System.out.println("-----------------------------");
        }
    }

    public void displayAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers registered");
            return;
        }
        System.out.println("====== CUSTOMER RECORDS ======");
        for (Customer customer : customers) {
            customer.displayCustomerRecord();
            System.out.println("-----------------------------");
        }
    }

    public Customer findCustomerByID(int customerID) {
        for (Customer customer : customers) {
            if (customer.getCustomerID() == customerID) {
                return customer;
            }
        }
        return null;
    }

    public House findHouseById(int houseID) {
        for (House house : houses) {
            if (house.getHouseID() == houseID) {
                return house;
            }
        }
        return null;
    }

    public ArrayList<House> findHousesByStyle(String style) {
        ArrayList<House> result = new ArrayList<>();
        for (House house : houses) {
            if (house.getStyle().equalsIgnoreCase(style)) {
                result.add(house);
            }
        }
        return result;
    }

    public ArrayList<House> findHousesByStyleAndType(String style, String houseType) {
        ArrayList<House> result = new ArrayList<>();
        for (House house : houses) {
            if (house.getStyle().equalsIgnoreCase(style) && house.getType().equalsIgnoreCase(houseType)) {
                result.add(house);
            }
        }
        return result;
    }

    public House findHouseByRequirements(String type, String style, int bedrooms) {
        for (House house : houses) {
            if (house.getType().equalsIgnoreCase(type)
                    && house.getStyle().equalsIgnoreCase(style)
                    && house.getNumberOfBedrooms() == bedrooms) {
                return house;
            }
        }
        return null;
    }

    public void allocateCustomerByPreference(Customer customer, WaitingList waitingList) {
        if (customer == null) {
            System.out.println("Invalid customer.");
            return;
        }

        if (customer.getPreferredHouseType() == null || customer.getPreferredDesignStyle() == null) {
            System.out.println("Customer does not have a stored house preference.");
            return;
        }

        if (customer.hasSelectedHouse()) {
            System.out.println(customer.getName() + " has already been allocated a house.");
            return;
        }

        House preferredHouse = findHouseByRequirements(
                customer.getPreferredHouseType(),
                customer.getPreferredDesignStyle(),
                customer.getPreferredBedrooms()
        );

        if (preferredHouse == null) {
            System.out.println("No matching house found for this customer's preference.");
            return;
        }

        System.out.println("Preferred house found: "
                + preferredHouse.getType() + ", "
                + preferredHouse.getNumberOfBedrooms() + " bedroom, "
                + preferredHouse.getStyle());

        if (preferredHouse.isAvailableForPurchase()) {
            purchaseHouse(customer, preferredHouse);
            System.out.println("ALLOCATED: " + customer.getName());
        } else {
            waitingList.addCustomer(customer);
            System.out.println("No houses currently available for this preference.");
            System.out.println("WAITING LIST: " + customer.getName());
        }
    }

    public void processAllocationQueue(WaitingList waitingList) {
        if (waitingList == null || waitingList.isEmpty()) {
            System.out.println("Allocation queue is empty.");
            return;
        }

        int customersToProcess = waitingList.getSize();
        ArrayList<Customer> stillWaiting = new ArrayList<>();

        System.out.println("====== ALLOCATION QUEUE PROCESSING ======");
        System.out.println("Customers are processed in date registered order.");

        for (int i = 0; i < customersToProcess; i++) {
            Customer customer = waitingList.removeCustomer();

            if (customer == null) {
                continue;
            }

            if (customer.hasSelectedHouse()) {
                System.out.println("SKIPPED: " + customer.getName() + " already has a house allocated.");
                continue;
            }

            House preferredHouse = findHouseByRequirements(
                    customer.getPreferredHouseType(),
                    customer.getPreferredDesignStyle(),
                    customer.getPreferredBedrooms()
            );

            if (preferredHouse != null && preferredHouse.isAvailableForPurchase()) {
                purchaseHouse(customer, preferredHouse);
                System.out.println("ALLOCATED BY QUEUE: " + customer.getName()
                        + " - Registered: " + customer.getDateRegistered());
            } else {
                stillWaiting.add(customer);
                System.out.println("STILL WAITING: " + customer.getName()
                        + " - Registered: " + customer.getDateRegistered());
            }
        }

        for (Customer customer : stillWaiting) {
            waitingList.addCustomerByDateRegistered(customer);
        }

        System.out.println("====== QUEUE PROCESSING COMPLETE ======");
    }

    public void displayMostPreferredDesignStyles() {
        System.out.println("====== MOST PREFERRED DESIGN STYLES ======");
        displayMostPreferredStyleForType("Detached");
        displayMostPreferredStyleForType("Semi-detached");
    }

    private void displayMostPreferredStyleForType(String houseType) {
        Map<String, Integer> styleCounts = new HashMap<>();

        for (Customer customer : customers) {
            if (customer.getPreferredHouseType() != null && customer.getPreferredHouseType().equalsIgnoreCase(houseType)) {
                String style = customer.getPreferredDesignStyle();
                styleCounts.put(style, styleCounts.getOrDefault(style, 0) + 1);
            }
        }

        String mostPreferredStyle = "None";
        int highestCount = 0;

        for (String style : styleCounts.keySet()) {
            if (styleCounts.get(style) > highestCount) {
                highestCount = styleCounts.get(style);
                mostPreferredStyle = style;
            }
        }

        System.out.println(houseType + ": " + mostPreferredStyle + " (" + highestCount + " selections)");
    }

    public ArrayList<Customer> getCustomers() { return customers; }
    public ArrayList<House> getHouses() { return houses; }
    public String getCompanyName() { return companyName; }
    public String getCompanyLocation() { return companyLocation; }

    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public void setCompanyLocation(String companyLocation) { this.companyLocation = companyLocation; }
}
