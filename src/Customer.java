public class Customer {
    private int customerID;
    private String name;
    private String email;
    private String dateRegistered;
    private House selectedHouse;
    private String preferredHouseType;
    private String preferredDesignStyle;
    private int preferredBedrooms;
    private static int nextCustomerID = 1;

    public Customer() {
        this.customerID = nextCustomerID++;
        this.name = "John Doe";
        this.email = "Noemail@email.com";
        this.dateRegistered = "1990-01-01";
        this.selectedHouse = null;
        this.preferredHouseType = "Not selected";
        this.preferredDesignStyle = "Not selected";
        this.preferredBedrooms = 0;
    }

    public Customer(String name, String email, String dateRegistered, House selectedHouse) {
        this.customerID = nextCustomerID++;
        this.name = name;
        this.email = email;
        this.dateRegistered = dateRegistered;
        this.selectedHouse = selectedHouse;
        this.preferredHouseType = selectedHouse != null ? selectedHouse.getType() : "Not selected";
        this.preferredDesignStyle = selectedHouse != null ? selectedHouse.getStyle() : "Not selected";
        this.preferredBedrooms = selectedHouse != null ? selectedHouse.getNumberOfBedrooms() : 0;
    }

    public void selectHousePreference(String preferredHouseType, String preferredDesignStyle, int preferredBedrooms) {
        this.preferredHouseType = preferredHouseType;
        this.preferredDesignStyle = preferredDesignStyle;
        this.preferredBedrooms = preferredBedrooms;
    }

    public boolean hasSelectedHouse() {
        return selectedHouse != null;
    }

    public void requestModification(String newStyle) {
        if (selectedHouse != null) {
            selectedHouse.modifyDesign(newStyle);
        } else {
            System.out.println("Modification declined because no house has been selected.");
        }
    }

    public void scheduleAppointment() {
        if (selectedHouse != null) {
            System.out.println("Appointment Scheduled");
        } else {
            System.out.println("No House Selected");
        }
    }

    public void displayCustomerRecord() {
        System.out.println("Customer ID: " + customerID);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Date Registered: " + dateRegistered);
        System.out.println("Preferred House Type: " + preferredHouseType);
        System.out.println("Preferred Design Style: " + preferredDesignStyle);
        System.out.println("Preferred Bedrooms: " + preferredBedrooms);

        if (selectedHouse != null) {
            System.out.println("Selected House Type: " + selectedHouse.getType());
            System.out.println("Selected Design Style: " + selectedHouse.getStyle());
            System.out.println("Bedrooms: " + selectedHouse.getNumberOfBedrooms());
            System.out.println("Price: £" + selectedHouse.calculatePrice());
            System.out.println("Construction Status: " + selectedHouse.getState());
        } else {
            System.out.println("Selected House: None allocated yet");
        }
    }

    public int getCustomerID() { return customerID; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getDateRegistered() { return dateRegistered; }
    public House getSelectedHouse() { return selectedHouse; }
    public String getPreferredHouseType() { return preferredHouseType; }
    public String getPreferredDesignStyle() { return preferredDesignStyle; }
    public int getPreferredBedrooms() { return preferredBedrooms; }

    public void setCustomerID(int customerID) { this.customerID = customerID; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setDateRegistered(String dateRegistered) { this.dateRegistered = dateRegistered; }
    public void setSelectedHouse(House selectedHouse) { this.selectedHouse = selectedHouse; }
    public void setPreferredHouseType(String preferredHouseType) { this.preferredHouseType = preferredHouseType; }
    public void setPreferredDesignStyle(String preferredDesignStyle) { this.preferredDesignStyle = preferredDesignStyle; }
    public void setPreferredBedrooms(int preferredBedrooms) { this.preferredBedrooms = preferredBedrooms; }
}
