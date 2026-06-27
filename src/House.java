public abstract class House {
    private String type;
    private String state;
    private double price;
    private String style;
    private int numberOfBedrooms;
    private int houseID;
    private boolean modifyDesign;
    private boolean isAvailable;
    private int quantityAvailable;
    private int quantityTotal;
    private static int nextHouseID = 1;

    //default constructor
    public House(){
        this.type = null;
        this.price = 150000;
        this.style = null;
        this.numberOfBedrooms = 1;
        this.state = null;
        this.houseID = 0;
        this.quantityAvailable = 0;
        this.quantityTotal = 0;
    }

    //parameterized constructor
    public House(String type, double price, String style, String state, int numberOfBedrooms){
        this.type = type;
        this.price = price;
        this.style = style;
        this.numberOfBedrooms = numberOfBedrooms;
        this.state = state;
        this.quantityAvailable = 0;
        this.quantityTotal = 0;
        this.houseID = nextHouseID++;
    }

    public double calculatePrice(){
        if (style != null && style.equals("Kildare")) {
            if(modifyDesign){
                return price * 1.35;
            }
        }
        return price;
    }

    public void displayHouseDetails(){
        System.out.println("House ID: " + houseID);
        System.out.println("Price: " + price);
        System.out.println("Style: " + style);
        System.out.println("Number of Bedrooms: " + numberOfBedrooms);
    }

    public void modifyDesign(String style){
        if (style != null && style.equals("Kildare")){
            System.out.println("House Design Modified");
        } else {
            System.out.println("House Design Not Modified");
        }
    }

    public boolean isAvailableForPurchase(){
        return quantityAvailable > 0;
    }

    public void decrementQuantity(){
        if(quantityAvailable > 0){
            quantityAvailable--;
        }
    }


    //setting up getters
    public String getType(){
        return type;
    }

    public double getPrice(){
        return price;
    }

    public String getStyle(){
        return style;
    }

    public String getState(){
        return state;
    }

    public int getNumberOfBedrooms(){
        return numberOfBedrooms;
    }

    public int getHouseID(){
        return houseID;
    }

    public boolean getIsAvailable(){
        return isAvailable;
    }

    public int getQuantityAvailable(){
        return quantityAvailable;
    }

    public int getQuantityTotal(){
        return quantityTotal;
    }

    //setting up setters
    public void setType(String type){
        this.type = type;
    }

    public void setPrice(double price){
        this.price = price;
    }
    public void setStyle(String style){
        this.style = style;
    }
    public void setState(String state){
        this.state = state;
    }
    public void setNumberOfBedrooms(int numberOfBedrooms){
        this.numberOfBedrooms = numberOfBedrooms;
    }
    public void setHouseID(int houseID){
        this.houseID = houseID;
    }

    public void setIsAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    public void setQuantityAvailable(int quantity){
        this.quantityAvailable = quantity;
        this.quantityTotal = quantity;
    }


}
