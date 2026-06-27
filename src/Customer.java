public class Customer {
    private int customerID;
    private String name;
    private String email;
    private String dateRegistered;
    private House selectedHouse;
    private static int nextCustomerID = 1;

    // default constructor
    public Customer(){
        this.name = "John Doe";
        this.dateRegistered = "1990-01-01";
        this.selectedHouse = null;
        this.email = "Noemail@email.com";
    }

    // parameterized constructors
    public Customer(String name, String email, String dateRegistered, House selectedHouse){
        this.name = name;
        this.email = email;
        this.dateRegistered = dateRegistered;
        this.selectedHouse = selectedHouse;
        this.customerID = nextCustomerID++;
    }

    public boolean hasSelectedHouse(){
        return selectedHouse != null;
    }

    public void requestModification() {
        if (selectedHouse != null && selectedHouse.getStyle() != null && selectedHouse.getStyle().equals("Kildare")) {
            System.out.println("House Modification Request Accepted");
        } else {
            System.out.println("House Modification Request Declined");
        }
    }

    public void scheduleAppointment(){
        if (selectedHouse != null) {
            System.out.println("Appointment Scheduled");
        } else {
            System.out.println("No House Selected");
        }
    }

    // setting up getters
    public int getCustomerID(){
        return customerID;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getDateRegistered(){
        return dateRegistered;
    }

    public House getSelectedHouse(){
        return selectedHouse;
    }

    // setting up setters
    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setDateRegistered(String dateRegistered){
        this.dateRegistered = dateRegistered;
    }

    public void setSelectedHouse(House selectedHouse){
        this.selectedHouse = selectedHouse;
    }
}