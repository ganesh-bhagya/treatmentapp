import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

public class DentalClinicApp {

    // patent list
    private static List<Patient> patients = new ArrayList<>();
    private static List<Doctor> doctors = new ArrayList<>();
     private static List<Appointment> appointments = new ArrayList<>();

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Login credentials
        String username = "admin";
        String password = "123";

        boolean isLoggedIn = false;
        int attempts = 0;

        //adding doc infos
        Doctor doctor1 = new Doctor("Dr. Smith");
        Doctor doctor2 = new Doctor("Dr. Johnson");
        doctors.add(doctor1);
        doctors.add(doctor2);

        while (true) {
            if (attempts >= 3) {
                System.out.println("Exceeded maximum login attempts. Exiting the program.");
                break;
            }

            if (!isLoggedIn) {
                System.out.println("Welcome to Dental Clinic");
                System.out.print("Enter username: ");
                String enteredUsername = scanner.nextLine();

                System.out.print("Enter password: ");
                String enteredPassword = scanner.nextLine();

                if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
                    System.out.println("Login successful!");
                    isLoggedIn = true;
                    attempts = 0; // Reset attempts on successful login
                } else {
                    System.out.println("Username or password is incorrect. Login failed.");
                    attempts++;
                }
            } else {
                System.out.println("\nSelect an option:");
                System.out.println("1. Add Appointment");
                System.out.println("2. View Appointments");
                System.out.println("3. Pay for Appointment");
                System.out.println("4. Logout");

                int choice;
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character after reading the int
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // Consume the newline character to avoid infinite loop
                    continue;
                }

                switch (choice) {
                    // Implement cases for appointment functionalities
                    case 1:
                        System.out.println("Redirect to add an appointment...");
                        handleAddAppointment(scanner);
                        break;
                    case 2:
                        System.out.println("Redirect to viewing appointments...");
                        handleViewAppointments();
                        break;
                    case 3:
                        System.out.println("Redirect to paying for an appointment...");
                        handlePayForAppointment();
                        break;
                    case 4:
                        System.out.println("Logged out.");
                        isLoggedIn = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            }
        }
    }

    // handel appointment sub menu
    private static void handleAddAppointment(Scanner scanner) {
        System.out.println("\nAdding an appointment...");

        System.out.println("Select an option:");
        System.out.println("1. Find patient by telephone number");
        System.out.println("2. Register new patient");

        int appointmentOption = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (appointmentOption) {
            case 1:
                System.out.print("Enter patient's telephone number: ");
                String phoneNumber = scanner.nextLine();
                Patient existingPatient = findPatientByTelephone(phoneNumber);
                if (existingPatient != null) {
                    // Proceed with adding appointment for the existing patient
                    addAppointmentForExistingPatient(existingPatient);
                } else {
                    System.out.println("Patient not found. Registering a new patient...");
                    registerNewPatient(scanner);
                }
                break;
            case 2:
                registerNewPatient(scanner);
                break;
            default:
                System.out.println("Invalid option. Please select again.");
                break;
        }
    }

    private static void addAppointmentForExistingPatient(Patient patient) {
        System.out.println("Enter doctor's name: ");
        String doctorName = scanner.nextLine();
        Doctor existingDoctor = findDoctorByName(doctorName);
        if (existingDoctor == null) {
            System.out.println("Doctor not found. Please register the doctor first.");
            registerNewDoctor(scanner);
            return;
        }

        System.out.println("Enter appointment date (YYYY-MM-DD): ");
        String dateString = scanner.nextLine();
        // Parse dateString to a Date object, for example: Date date =
        // parseDate(dateString);

        System.out.println("Enter time slot: ");
        String timeSlot = scanner.nextLine();

        System.out.println("Do you want to add treatments? (Y/N)");
        String addTreatments = scanner.nextLine();
        List<Treatment> treatments = new ArrayList<>();
        if (addTreatments.equalsIgnoreCase("Y")) {
            while (true) {
                System.out.println("Enter treatment type (or type 'exit' to finish adding treatments): ");
                String treatmentType = scanner.nextLine();
                if (treatmentType.equalsIgnoreCase("exit")) {
                    break;
                }
                // Search for treatment by type
                Treatment treatment = findTreatmentByType(treatmentType);
                if (treatment != null) {
                    treatments.add(treatment);
                } else {
                    System.out.println("Treatment not found.");
                }
            }
        }

        System.out.println("Enter registration fee: ");
        double registrationFee = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parsedDate = dateFormat.parse(dateString);
            Appointment appointment = new Appointment(existingDoctor, parsedDate, timeSlot, patient, registrationFee);
            appointment.setTreatments(treatments);
            displaySingleAppointmentDetails(appointment);
            appointments.add(appointment);
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }

        System.out.println("Appointment added for existing patient: " + patient.getName());
    }

    private static Patient findPatientByTelephone(String phoneNumber) {
        for (Patient patient : patients) {
            if (patient.getTelephone().equals(phoneNumber)) {
                return patient;
            }
        }
        return null;
    }

    private static void registerNewPatient(Scanner scanner) {
        // Logic to register a new patient
        // Example: Taking input for new patient details
        System.out.print("Enter patient's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patient's address: ");
        String address = scanner.nextLine();
        System.out.print("Enter patient's telephone number: ");
        String telephone = scanner.nextLine();

        Patient newPatient = new Patient(name, address, telephone);
        patients.add(newPatient);

        // After registration, proceed with adding appointment for the new patient
        addAppointmentForExistingPatient(newPatient);
    }

    private static void displaySingleAppointmentDetails(Appointment appointment) {
        // Display appointment details
        System.out.println("\nAppointment Details:");
        System.out.println("nAppointment ID: " + appointment.getAppointmentId());
        System.out.println("Patient Name: " + appointment.getPatientName());
        System.out.println("Registration Fee: " + appointment.getRegistrationFee());
        // Display other appointment details...
    }

    // find the doc list
    private static Doctor findDoctorByName(String doctorName) {
        for (Doctor doctor : doctors) {
            if (doctor.getName().equalsIgnoreCase(doctorName)) {
                System.out.println("Doctor Name: " + doctor.getName());
                return doctor;
            }
        }
        return null;
    }

    // Logic to register a new doctor...
    private static Doctor registerNewDoctor(Scanner scanner) {
        System.out.println("Enter doctor's name: ");
        String name = scanner.nextLine();
        Doctor newDoctor = new Doctor(name);
        doctors.add(newDoctor);

        System.out.println("Doctor registered successfully!");

        return newDoctor;
    }

    // Logic to search for treatment by type...
    private static Treatment findTreatmentByType(String treatmentType) {
        // List of predefined treatments
        List<Treatment> availableTreatments = TreatmentManager.getAvailableTreatments();

        // Iterate through the available treatments to find a match
        for (Treatment treatment : availableTreatments) {
            if (treatment.getType().name().equalsIgnoreCase(treatmentType)) {
                return treatment; // Return the treatment if found
            }
        }

        return null; // Return null if no matching treatment is found
    }

    //handel view appoinment details
    private static void handleViewAppointments() {
        System.out.println("Viewing appointments...");
        System.out.println("Appointment List:");
        System.out.println("ID | Doctor | Date | Time Slot | Patient | Total Fee");
    
        for (Appointment appointment : appointments) {
            System.out.println(appointment.getAppointmentId() + " | " +
                               appointment.getDoctor().getName() + " | " +
                               appointment.getDate() + " | " +
                               appointment.getTimeSlot() + " | " +
                               appointment.getPatient().getName() + " | " +
                               appointment.calculateTotalFee());
        }
    
        System.out.println("Enter the ID of the appointment to view details (or type 'exit' to go back):");
        String input = scanner.nextLine();
    
        if (!input.equalsIgnoreCase("exit")) {
            Appointment selectedAppointment = findAppointmentById(input);
            if (selectedAppointment != null) {
                displayAppointmentDetails(selectedAppointment);
            } else {
                System.out.println("Appointment not found.");
            }
        }
    }
    
    private static void displayAppointmentDetails(Appointment appointment) {
        System.out.println("\nAppointment Details:");
        System.out.println("ID: " + appointment.getAppointmentId());
        System.out.println("Doctor: " + appointment.getDoctor().getName());
        System.out.println("Date: " + appointment.getDate());
        System.out.println("Time Slot: " + appointment.getTimeSlot());
        System.out.println("Patient: " + appointment.getPatient().getName());
        System.out.println("Total Fee: " + appointment.calculateTotalFee());
        // Display other appointment details...
    }

    private static Appointment findAppointmentById(String id) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(id)) {
                return appointment;
            }
        }
        return null; // Return null if the appointment with the given ID is not found
    }

    //handle payemnt sections
    private static List<Appointment> getUnpaidAppointments() {
        List<Appointment> unpaidAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (!appointment.getPaid()) {
                unpaidAppointments.add(appointment);
            }
        }
        return unpaidAppointments;
    }

    private static void handlePayForAppointment() {
        List<Appointment> unpaidAppointments = getUnpaidAppointments();
    
        if (unpaidAppointments.isEmpty()) {
            System.out.println("No unpaid appointments.");
            return;
        }
    
        System.out.println("Unpaid Appointments:");
        System.out.println("ID | Doctor | Date | Time Slot | Patient | Total Fee");
    
        for (Appointment appointment : unpaidAppointments) {
            System.out.println(appointment.getAppointmentId() + " | " +
                               appointment.getDoctor().getName() + " | " +
                               appointment.getDate() + " | " +
                               appointment.getTimeSlot() + " | " +
                               appointment.getPatient().getName() + " | " +
                               appointment.calculateTotalFee());
        }
    
        System.out.println("Enter the ID of the appointment to pay for (or type 'exit' to cancel):");
        String input = scanner.nextLine();
    
        if (!input.equalsIgnoreCase("exit")) {
            Appointment selectedAppointment = findAppointmentById(input);
            if (selectedAppointment != null) {
                payForAppointment(selectedAppointment);
            } else {
                System.out.println("Appointment not found.");
            }
        }
    }

    private static void payForAppointment(Appointment appointment) {
        double totalFee = appointment.calculateTotalFee();
        System.out.println("Appointment details:");
        displayAppointmentDetails(appointment);
        System.out.println("Total Fee: " + totalFee);
        
        // Process payment logic
        // Example: Mark the appointment as paid
        appointment.setPaid(true);
        System.out.println("Payment successful for appointment ID: " + appointment.getAppointmentId());
    }

}
