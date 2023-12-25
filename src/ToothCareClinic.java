import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ToothCareClinic {
    private List<Doctor> doctors;
    private List<Patient> patients;
    private List<Appointment> appointments;

    public ToothCareClinic() {
        doctors = new ArrayList<>();
        patients = new ArrayList<>();
        appointments = new ArrayList<>();
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public boolean bookAppointment(Doctor doctor, Date date, String timeSlot, Patient patient,
            List<Treatment> treatments, double registrationFee) {
        // Check doctor availability, patient details, etc., and then create an
        // appointment
        Appointment newAppointment = new Appointment(doctor, date, timeSlot, patient, registrationFee);
        for (Treatment treatment : treatments) {
            newAppointment.addTreatment(treatment);
        }
        appointments.add(newAppointment);
        return true; // Return success/failure status
    }

    // Method to get appointments filtered by date
    public List<Appointment> getAppointmentsByDate(Date filterDate) {
        return appointments.stream()
                .filter(appointment -> appointment.getDate().equals(filterDate))
                .collect(Collectors.toList());
    }

    // Get all appointments list
    public List<Appointment> getAppointments() {
        return appointments;
    }

    // Search for an appointment using patient name
    public List<Appointment> getAppointmentsByPatientName(String patientName) {
        return appointments.stream()
                .filter(appointment -> appointment.getPatient().getName().equalsIgnoreCase(patientName))
                .collect(Collectors.toList());
    }

    // Search for an appointment using appointment ID
    public Appointment getAppointmentById(String appointmentId) {
        return appointments.stream()
                .filter(appointment -> appointment.getAppointmentId().equals(appointmentId))
                .findFirst()
                .orElse(null); // Return null if not found
    }

    public void viewAppointmentsAndHandlePayment() {
        if (!getAppointments().isEmpty()) {
            Appointment appointmentToPay = getAppointments().get(0); // For demonstration, assuming the first
                                                                     // appointment
            double totalFee = appointmentToPay.calculateTotalFee();
            System.out.println("Total Fee for Appointment ID " + appointmentToPay.getAppointmentId() + ": " + totalFee);

            // Assuming the payment was successful
            System.out
                    .println("Payment received successfully for Appointment ID " + appointmentToPay.getAppointmentId());
            // You might generate an invoice or update payment status in a real system
        } else {
            System.out.println("No appointments found.");
        }
    }
}
