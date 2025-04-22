// BloodBankApp.java
import java.util.Scanner;
import java.util.NoSuchElementException;

public class BloodBankApp {
    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\n=== Welcome to the Blood Bank Management System ===");
                System.out.println("1. Donor/Recipient");
                System.out.println("2. Staff Login");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                String input = sc.nextLine().trim();
                if (input.isEmpty()) continue;

                int choice = Integer.parseInt(input);

                if (choice == 1) {
                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter your blood type (e.g., A, B, AB, O): ");
                    String bloodType = sc.nextLine();

                    System.out.println("Select role:");
                    System.out.println("1. Donor");
                    System.out.println("2. Recipient");
                    System.out.print("Enter your choice: ");

                    int role = Integer.parseInt(sc.nextLine());

                    if (role == 1) {
                        Donor donor = new Donor(name, bloodType);
                        donor.showMenu(db,sc);
                    } else if (role == 2) {
                        Recipient recipient = new Recipient(name, bloodType);
                        recipient.showMenu(db, sc);
                    } else {
                        System.out.println("Invalid role selection.");
                    }

                } else if (choice == 2) {
                    System.out.print("Enter Staff ID: ");
                    String staffID = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();

                    Staff staff = new Staff(staffID, password);
                    if (staff.login(db)) {
                        System.out.println("Staff login successful.");
                        staff.showMenu(db);
                    } else {
                        System.out.println("Invalid staff credentials.");
                    }

                } else if (choice == 3) {
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (NoSuchElementException e) {
                System.out.println("No input available. Exiting...");
                break;
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }

        sc.close();
    }
}

