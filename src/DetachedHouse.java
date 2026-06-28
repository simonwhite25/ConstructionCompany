public class DetachedHouse extends House {
    private boolean privateGarden;
    private String constructionProgress;

    public DetachedHouse(String type, double price, String style, String state, int numberOfBedrooms, boolean privateGarden, String constructionProgress) {
        super(type, price, style, state, numberOfBedrooms);
        this.privateGarden = privateGarden;
        this.constructionProgress = constructionProgress;
    }

    public void displayDetachedFeatures() {
        displayHouseDetails();
        System.out.println("Private Garden: " + (privateGarden ? "Yes" : "No"));
        System.out.println("Construction Progress: " + constructionProgress);
    }

    @Override
    public double calculatePrice() {
        return super.calculatePrice();
    }

    public boolean isPrivateGarden() { return privateGarden; }
    public String getConstructionProgress() { return constructionProgress; }
    public void setPrivateGarden(boolean privateGarden) { this.privateGarden = privateGarden; }
    public void setConstructionProgress(String constructionProgress) { this.constructionProgress = constructionProgress; }
}
