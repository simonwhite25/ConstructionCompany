public class SemiDetachedHouse extends House {
    private boolean sharedWall;
    private String constructionProgress;

    @Override
    public double calculatePrice() {
        return getPrice();
    }

    public SemiDetachedHouse(String type, double price, String style, String state, int numberOfBedrooms, boolean sharedWall, String constructionProgress) {
        super(type, price, style, state, numberOfBedrooms);
        this.sharedWall = sharedWall;
        this.constructionProgress = constructionProgress;
    }

    public void displaySemiDetachedFeatures(){
        System.out.println("Semi-Detached " + getHouseID() + " " + getNumberOfBedrooms() + "-Bedroom " + constructionProgress + " " +getPrice() + " " + getState());
        System.out.println("Shared Wall: " + sharedWall);
    }

    //Getters
    public boolean isSharedWall(){
        return sharedWall;
    }

    public String getConstructionProgress(){
        return constructionProgress;
    }

    //Setters
    public void setSharedWall(boolean sharedWall){
        this.sharedWall = sharedWall;
    }

    public void setConstructionProgress(String constructionProgress){
        this.constructionProgress = constructionProgress;
    }
}
