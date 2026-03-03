import java.util.Scanner;

/**
 * Main entry point for Pet Clinic Management System
 * Handles user interaction and menu navigation
 */
public class Main {
    public static void main(String[] args) {
        PetClinicManager manager = new PetClinicManager();
        Scanner scanner = new Scanner(System.in);

        // Main application loop
        while (true) {
            printMenu();
            int choice = getValidInt(scanner, 1, 7);  // Get validated menu choice
            scanner.nextLine(); // Clear input buffer

            // Process user selection
            switch (choice) {
                case 1 -> addPet(scanner, manager, "Cat");
                case 2 -> addPet(scanner, manager, "Dog");
                case 3 -> manager.displayAllPets();
                case 4 -> {
                    System.out.print("Enter pet name or color to search: ");
                    manager.searchPet(scanner.nextLine());
                }
                case 5 -> deletePet(scanner, manager);
                case 6 -> manager.generateReport();
                case 7 -> {
                    manager.saveToFile();
                    System.out.println("Data saved. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    // Displays the main menu options
    private static void printMenu() {
        System.out.println("\n=== Pet Clinic Management System ===");
        System.out.println("1. Add a Cat");
        System.out.println("2. Add a Dog");
        System.out.println("3. View All Pets");
        System.out.println("4. Search for a Pet");
        System.out.println("5. Delete a Pet");
        System.out.println("6. Generate Clinic Report");
        System.out.println("7. Exit");
        System.out.print("Select an option: ");
    }

    // Handles pet addition workflow
    private static void addPet(Scanner scanner, PetClinicManager manager, String type) {
        System.out.println("\nAdding a new " + type);

        // Collect and validate pet details
        System.out.print("Name: ");
        String name = scanner.nextLine();
        if (name.trim().isEmpty()) {
            System.out.println("❌ Error: Pet name cannot be empty!");
            return;
        }

        System.out.print("Age: ");
        int age = getValidInt(scanner, 0, 30);
        scanner.nextLine();

        System.out.print("Color: ");
        String color = scanner.nextLine();

        System.out.print("Weight (kg): ");
        double weight = getValidDouble(scanner);
        scanner.nextLine();

        System.out.print("Breed: ");
        String breed = scanner.nextLine();

        // Create appropriate pet type
        try {
            Pet pet = type.equals("Cat")
                    ? new Cat(name, age, color, weight, breed)
                    : new Dog(name, age, color, weight, breed);
            manager.addPet(pet);
        } catch (IllegalArgumentException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    // Handles pet deletion and workflow
    private static void deletePet(Scanner scanner, PetClinicManager manager) {
        manager.displayAllPets();
        if (manager.getPetCount() > 0) {
            System.out.print("Enter pet number to delete: ");
            int indexToDelete = getValidInt(scanner, 1, manager.getPetCount()) - 1;
            scanner.nextLine();
            manager.deletePet(indexToDelete);
        }
    }

    // Validates intger input within range
    private static int getValidInt(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int input = scanner.nextInt();
                if (input >= min && input <= max) return input;
                System.out.printf("Input must be between %d and %d: ", min, max);
            } catch (Exception e) {
                System.out.print("Invalid number. Try again: ");
                scanner.nextLine();
            }
        }
    }

    // Validates posiive double input
    private static double getValidDouble(Scanner scanner) {
        while (true) {
            try {
                double input = scanner.nextDouble();
                if (input > 0) return input;
                System.out.print("Weight must be positive. Try again: ");
            } catch (Exception e) {
                System.out.print("Invalid number. Try again: ");
                scanner.nextLine();
            }
        }
    }
}