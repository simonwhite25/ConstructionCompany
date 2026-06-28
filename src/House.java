public abstract class House {
    private String type;
    private String state;
    private double price;
    private String style;
    private int numberOfBedrooms;
    private int houseID;
    private boolean designModified;
    private boolean isAvailable;
    private int quantityAvailable;
    private int quantityTotal;
    private static int nextHouseID = 1;

    public House() {
        this.type = null;
        this.price = 150000;
        this.style = null;
        this.numberOfBedrooms = 1;
        this.state = null;
        this.houseID = 0;
        this.quantityAvailable = 0;
        this.quantityTotal = 0;
        this.isAvailable = false;
        this.designModified = false;
    }

    public House(String type, double price, String style, String state, int numberOfBedrooms) {
        this.type = type;
        this.price = price;
        this.style = style;
        this.numberOfBedrooms = numberOfBedrooms;
        this.state = state;
        this.quantityAvailable = 0;
        this.quantityTotal = 0;
        this.isAvailable = false;
        this.designModified = false;
        this.houseID = nextHouseID++;
    }

    public double calculatePrice() {
        if (designModified) {
            return price * 1.35; // 35% increase for approved personalisation
        }
        return price;
    }

    public boolean canModifyDesign() {
        return state != null && state.equalsIgnoreCase("Not Started");
    }

    public boolean modifyDesign(String newStyle) {
        if (newStyle != null && !newStyle.isEmpty() && canModifyDesign()) {
            this.style = newStyle;
            this.designModified = true;
            System.out.println("House design modified successfully.");
            return true;
        }
        System.out.println("House design cannot be modified because construction has already started.");
        return false;
    }

    public void displayHouseDetails() {
        System.out.println("House ID: " + houseID);
        System.out.println("Type: " + type);
        System.out.println("Bedrooms: " + numberOfBedrooms);
        System.out.println("Style: " + style);
        System.out.println("Construction Status: " + state);
        System.out.println("Base Price: £" + price);
        System.out.println("Final Price: £" + calculatePrice());
        System.out.println("Quantity Available: " + quantityAvailable);
    }

    public boolean isAvailableForPurchase() {
        return quantityAvailable > 0;
    }

    public void decrementQuantity() {
        if (quantityAvailable > 0) {
            quantityAvailable--;
            isAvailable = quantityAvailable > 0;
        }
    }

    public String getType() { return type; }
    public double getPrice() { return price; }
    public String getStyle() { return style; }
    public String getState() { return state; }
    public int getNumberOfBedrooms() { return numberOfBedrooms; }
    public int getHouseID() { return houseID; }
    public boolean getIsAvailable() { return isAvailable; }
    public int getQuantityAvailable() { return quantityAvailable; }
    public int getQuantityTotal() { return quantityTotal; }
    public boolean isDesignModified() { return designModified; }

    public void setType(String type) { this.type = type; }
    public void setPrice(double price) { this.price = price; }
    public void setStyle(String style) { this.style = style; }
    public void setState(String state) { this.state = state; }
    public void setNumberOfBedrooms(int numberOfBedrooms) { this.numberOfBedrooms = numberOfBedrooms; }
    public void setHouseID(int houseID) { this.houseID = houseID; }
    public void setIsAvailable(boolean isAvailable) { this.isAvailable = isAvailable; }
    public void setDesignModified(boolean designModified) { this.designModified = designModified; }

    public void setQuantityAvailable(int quantity) {
        this.quantityAvailable = quantity;
        this.quantityTotal = quantity;
        this.isAvailable = quantity > 0;
    }
}
