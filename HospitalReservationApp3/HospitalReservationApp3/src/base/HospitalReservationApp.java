package base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class HospitalReservationApp {
    private static Connection conn;
    
    public HospitalReservationApp(){
        conn = SQLConnector.getConnection();
    }

    private static final Map<String, Doctor> doctors = new HashMap<>();
    private static final Map<String, Patient> patients = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeSampleData();
        
        
       

        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void login() {
        System.out.println("\n--- Login ---");
        System.out.print("Enter your role (doctor/patient): ");
        String role = scanner.next();

        if ("doctor".equalsIgnoreCase(role)) {
            doctorLogin();
        } else if ("patient".equalsIgnoreCase(role)) {
            patientLogin();
        } else {
            System.out.println("Invalid role. Please try again.");
        }
    }

    private static void doctorLogin() {
        System.out.print("Enter your username: ");
        String username = scanner.next();

        Doctor doctor = doctors.get(username);

        if (doctor != null) {
            System.out.println("Login successful, Dr. " + doctor.getName());
            doctorMenu(doctor);
        } else {
            System.out.println("Please register yourself.");
        }
    }

    private static void patientLogin() {
        System.out.print("Enter your username: ");
        String username = scanner.next();

        Patient patient = patients.get(username);

        if (patient != null) {
            System.out.println("Login successful, " + patient.getName());
            patientMenu(patient);
        } else {
            System.out.println("Please register yourself.");
        }
    }

    private static void register() {
        System.out.println("\n--- Registration ---");
        System.out.print("Enter your role (doctor/patient): ");
        String role = scanner.next();

        System.out.print("Enter your username: ");
        String username = scanner.next();

        if ("doctor".equalsIgnoreCase(role)) {
            if (!doctors.containsKey(username)) {
                System.out.print("Enter your name: ");
                String name = scanner.next();
                doctors.put(username, new Doctor(name));
                System.out.println("Registration successful, Dr. " + name + "!");
            } else {
                System.out.println("Username already exists. Please choose a different username.");
            }
        } else if ("patient".equalsIgnoreCase(role)) {
            if (!patients.containsKey(username)) {
                System.out.print("Enter your name: ");
                String name = scanner.next();
                patients.put(username, new Patient(name));
                System.out.println("Registration successful, " + name + "!");
            } else {
                System.out.println("Username already exists. Please choose a different username.");
            }
        } else {
            System.out.println("Invalid role. Please try again.");
        }
    }

    private static void doctorMenu(Doctor doctor) {
        while (true) {
            System.out.println("\n--- Doctor Menu ---");
            System.out.println("1. Set Availability");
            System.out.println("2. View Reservations");
            System.out.println("3. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    DoctorMenu.setAvailability(doctor);
                    break;
                case 2:
                    DoctorMenu.viewReservations(doctor);
                    break;
                case 3:
                    System.out.println("Exiting Doctor Menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void patientMenu(Patient patient) {
        while (true) {
            System.out.println("\n--- Patient Menu ---");
            System.out.println("1. View Doctors");
            System.out.println("2. Make Reservation");
            System.out.println("3. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    PatientMenu.viewDoctors(doctors);
                    break;
                case 2:
                    PatientMenu.makeReservation(patient, doctors);
                    break;
                case 3:
                    System.out.println("Exiting Patient Menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Sample Data Initialization
    private static void initializeSampleData() {
        doctors.put("DrSmith", new Doctor("Dr. Smith"));
        doctors.put("DrJohnson", new Doctor("Dr. Johnson"));

        patients.put("JohnDoe", new Patient("John Doe"));
        patients.put("JaneDoe", new Patient("Jane Doe"));
    }
}

class Human {
    public String name;
}

class Doctor extends Human{
    
    private List<String> availability;
    private List<String> reservations;

    public Doctor(String name) {
        this.name = name;
        this.availability = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<String> getAvailability() {
        return availability;
    }

    public List<String> getReservations() {
        return reservations;
    }

    public void setAvailability(List<String> availability) {
        this.availability = availability;
    }
}

class Patient extends Human{
    

    public Patient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class DoctorMenu {

    public static void setAvailability(Doctor doctor) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter available start time (hh:mm): ");
        String startTime = scanner.next();
        System.out.print("Enter available end time (hh:mm): ");
        String endTime = scanner.next();

        // Assume each session is 30 minutes
        int sessions = calculateSessions(startTime, endTime);

        List<String> availability = new ArrayList<>();
        for (int i = 0; i < sessions; i++) {
            availability.add(addMinutes(startTime, i * 30));
        }

        doctor.setAvailability(availability);
        System.out.println("Availability set successfully.");
    }

    public static void viewReservations(Doctor doctor) {
        System.out.println("\n--- View Reservations ---");
        System.out.printf("%-20s %-20s\n", "Patient Name", "Reserved Session");

        for (String reservation : doctor.getReservations()) {
            System.out.println(reservation);
        }

        if (doctor.getReservations().isEmpty()) {
            System.out.println("No reservations yet.");
        }
    }

    private static int calculateSessions(String startTime, String endTime) {
        int startMinutes = getMinutes(startTime);
        int endMinutes = getMinutes(endTime);

        return (endMinutes - startMinutes) / 30;
    }

    private static int getMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    private static String addMinutes(String time, int minutesToAdd) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        int totalMinutes = hours * 60 + minutes + minutesToAdd;

        return String.format("%02d:%02d", totalMinutes / 60, totalMinutes % 60);
    }
}

class PatientMenu {

    public static void viewDoctors(Map<String, Doctor> doctors) {
        System.out.println("\n--- View Doctors ---");
        for (String doctorName : doctors.keySet()) {
            System.out.println(doctorName);
        }
    }

    public static void makeReservation(Patient patient, Map<String, Doctor> doctors) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter doctor's name: ");
        String doctorName = scanner.nextLine();

        Doctor doctor = doctors.get(doctorName);

        if (doctor != null) {
            System.out.println("Doctor: " + doctor.getName());
            System.out.println("Availability: " + doctor.getAvailability());

            System.out.print("Choose a session: ");
            String session = scanner.nextLine();

            // Update doctor's reservations or handle reservation as needed
            doctor.getReservations().add(patient.getName() + " - " + session);

            System.out.println("Reservation made successfully!");
        } else {
            System.out.println("Doctor not found.");
        }
    }
}

