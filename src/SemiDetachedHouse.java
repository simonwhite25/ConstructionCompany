public class SemiDetachedHouse extends House {
    private boolean sharedWall;
    private String constructionProgress;

    public SemiDetachedHouse(String type, double price, String style, String state, int numberOfBedrooms, boolean sharedWall, String constructionProgress) {
        super(type, price, style, state, numberOfBedrooms);
        this.sharedWall = sharedWall;
        this.constructionProgress = constructionProgress;
    }

    public void displaySemiDetachedFeatures() {
        displayHouseDetails();
        System.out.println("Shared Wall: " + (sharedWall ? "Yes" : "No"));
        System.out.println("Construction Progress: " + constructionProgress);
    }

    @Override
    public double calculatePrice() {
        return super.calculatePrice();
    }

    public boolean isSharedWall() { return sharedWall; }
    public String getConstructionProgress() { return constructionProgress; }
    public void setSharedWall(boolean sharedWall) { this.sharedWall = sharedWall; }
    public void setConstructionProgress(String constructionProgress) { this.constructionProgress = constructionProgress; }
}
