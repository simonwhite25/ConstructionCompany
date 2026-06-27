public class Appointment {
    private int appointmentID;
    private String appointmentDate;
    private String appointmentTime;
    private boolean appointmentBooked;

    public void scheduleAppointment(Customer customer){
        if (!appointmentBooked && customer != null && customer.hasSelectedHouse()) {
            appointmentBooked = true;
            System.out.println("Appointment Scheduled");
        } else {
            System.out.println("Appointment Not Scheduled");
        }
    }

    public void cancelAppointment(){
        if (appointmentBooked) {
            appointmentBooked = false;
            System.out.println("Appointment Cancelled");
        } else {
            System.out.println("Appointment Not Cancelled");
        }
    }

    public Appointment(int appointmentID, String appointmentDate, String appointmentTime, boolean appointmentBooked) {
        this.appointmentID = appointmentID;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.appointmentBooked = appointmentBooked;
    }

    public void confirmedAppointment(){
        appointmentBooked = true;
    }

    public void displayAppointmentDetails(){
        System.out.println("Appointment ID: " + appointmentID);
        System.out.println("Appointment Date: " + appointmentDate);
        System.out.println("Appointment Time: " + appointmentTime);
        System.out.println("Appointment Booked: " + appointmentBooked);
    }

    // Getters
    public int getAppointmentID(){
        return appointmentID;
    }

    public String getAppointmentDate(){
        return appointmentDate;
    }

    public String getAppointmentTime(){
        return appointmentTime;
    }

    public boolean isAppointmentBooked(){
        return appointmentBooked;
    }

    // Setters
    public void setAppointmentID(int appointmentID){
        this.appointmentID = appointmentID;
    }

    public void setAppointmentDate(String appointmentDate){
        this.appointmentDate = appointmentDate;
    }

    public void setAppointmentTime(String appointmentTime){
        this.appointmentTime = appointmentTime;
    }

    public void setAppointmentBooked(boolean appointmentBooked){
        this.appointmentBooked = appointmentBooked;
    }
}