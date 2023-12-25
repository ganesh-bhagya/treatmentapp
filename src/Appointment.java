import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Appointment {
    private double registrationFee;
    private static int appointmentCounter = 1000;

    private String appointmentId;
    private Doctor doctor;
    private Date date;
    private String timeSlot;
    private Patient patient;
    private List<Treatment> treatments;
    private boolean paid;

    public Appointment(Doctor doctor, Date date, String timeSlot, Patient patient,double registrationFee) {
        this.appointmentId = "A" + appointmentCounter++;
        this.registrationFee = registrationFee;
        this.doctor = doctor;
        this.date = date;
        this.timeSlot = timeSlot;
        this.patient = patient;
        this.paid = false;
        this.treatments = new ArrayList<>();
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getDate() {
        return date;
    }
    
    public double getRegistrationFee() {
        return registrationFee;
    }

    public boolean getPaid(){
        return paid;
    }

    public void setPaid(boolean paymentStatus){
        this.paid = paymentStatus;
    }

    // Getter for patient name
    public String getPatientName() {
        return patient.getName();
    }

    public void setRegistrationFee(double registrationFee) {
        this.registrationFee = registrationFee;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }

    public void addTreatment(Treatment treatment) {
        treatments.add(treatment);
    }

    public double calculateTotalFee() {
        double total = 0.0;
        for (Treatment treatment : treatments) {
            total += treatment.getPrice();
        }
        return total;
    }
}
