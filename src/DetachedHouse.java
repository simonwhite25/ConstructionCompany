public class DetachedHouse extends House{
    private boolean privateGarden;
    private String constructionProgress;

    //super method to call the parent class
    public DetachedHouse(String type, String style, double price, String state, int numberOfBedrooms, boolean privateGarden, String constructionProgress)
    {
        super(type, style, price, state, numberOfBedrooms);
        this.privateGarden = privateGarden;
        this.constructionProgress = constructionProgress;
    }

    public void displayDetachedFeatures(){
        System.out.println("Detached " + getHouseID() + " " + getNumberOfBedrooms() + "-Bedroom " + constructionProgress + " " +getPrice() + " " + getState());
        System.out.println("Private Garden: " + (privateGarden? "Yes" : "No"));
    }

    @Override
    public double calculatePrice(){
        if (getStyle() != null && getStyle().equals("Kildare")) {
            return getPrice() * 0.2;
        } else {
            return getPrice();
        }
    }

    //Getters
    public boolean isPrivateGarden(){
        return privateGarden;
    }

    public String getConstructionProgress(){
        return constructionProgress;
    }
    //Setters
    public void setPrivateGarden(boolean privateGarden){
        this.privateGarden = privateGarden;
    }

    public void setConstructionProgress(String constructionProgress){
        this.constructionProgress = constructionProgress;
    }
}
