import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[]args){
        boolean running = true;
        Scanner input = new Scanner(System.in);

        ConstructionCompany Company = new ConstructionCompany("Fantasy Houses", "London");

        var house1 = Company.addHouse(new DetachedHouse("Detached", "Kilkenny",220000,"20% Complete",3, true,"Started"));
        var house2 = Company.addHouse(new DetachedHouse("Detached","Kildare",300000,"Not Started", 2, false,"Nothing Started"));
        var house3 = Company.addHouse(new SemiDetachedHouse("Semi-detached",120000,"Lisburn","90% Completed", 2, true,"Nearly Complete"));
        var house4 = Company.addHouse(new SemiDetachedHouse("Semi-detached",150000,"Glin","50% Complete", 3,true,"Half buuild completed"));

        while (running) {
            System.out.println("Welcome to the Fantasy House building Company! " +
                    "Please provide your details to get started!");

            System.out.println("1. Register as a new customer");
            System.out.println("2. View all customers");
            System.out.println("3. Exit");

            int options = input.nextInt();
            input.nextLine();

            switch (options) {
                case 1:
                    System.out.println("Customer Name: ");
                    String name = input.nextLine();

                    System.out.println("Customer Email: ");
                    String email = input.nextLine();

                    System.out.print("Please input a date to be registered: ");
                    String date = input.nextLine();

                    Customer customer = new Customer(name, email, date, null);

                    Company.addCustomer(customer);

                    System.out.println("Customer ID: " +customer.getCustomerID());
                    System.out.println("Customer Name : " + customer.getName());
                    System.out.println("Customer Email : " + customer.getEmail());
                    System.out.println("Customer Date Registered : " + customer.getDateRegistered());

                    System.out.println("Customer Please select a house to purchase: ");
                    String style = input.nextLine();

                    ArrayList<House> matchingHouses = Company.findHousesByStyle(style);

                    for(House house : matchingHouses){
                        house.displayHouseDetails();
                    }



                    break;

                case 2:

                    break;

                case 3:
                    running = false;
                    break;

            }
            System.out.println("Thank you for choosing Fantasy Houses!");




        }



    }
}
