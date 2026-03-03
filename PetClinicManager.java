import java.io.*;
import java.util.*;

/**
 * Core manager class handling pet storage, file operations, and reporting
 * Uses array-based storage with serialization for persistence
 */
public class PetClinicManager {
    // Configuration constants
    private static final int MAX_PETS = 100;  // Fixed capacity limit
    private static final String PET_FILE = "PetDetails.txt";  // Binary pet data
    private static final String CLINIC_FILE = "ClinicsDetails.txt";  // Text report

    // Instance fields
    private Pet[] pets = new Pet[MAX_PETS];  // Primary storage array
    private int petCount = 0;  // Current number of pets
    private final String clinicName = "Ulster Pet Clinic";  // Constant clinic identifier

    public PetClinicManager() {
        loadFromFile();  // Initialize with saved data if available
    }

    /**
     * Adds a new pet if capacity allows
     * @param pet The pet to add (Cat or Dog)
     */
    public void addPet(Pet pet) {
        if (petCount < MAX_PETS) {
            pets[petCount++] = pet;
            System.out.println(pet.speak() + " added successfully!");
        } else {
            System.out.println("Clinic is at full capacity!");
        }
    }

    /**
     * Removes pet at specified index and compacts array
     * @param index Position in pets array (0-based)
     */
    public void deletePet(int index) {
        if (index >= 0 && index < petCount) {
            System.arraycopy(pets, index + 1, pets, index, petCount - index - 1);
            pets[--petCount] = null;  // Clear reference and decrement count
            System.out.println("Pet deleted successfully!");
        }
    }

    // Dual-file persistence system
    public void saveToFile() {
        try (ObjectOutputStream petOut = new ObjectOutputStream(new FileOutputStream(PET_FILE));
             PrintWriter clinicOut = new PrintWriter(new FileWriter(CLINIC_FILE))) {

            // Save binary pet objects
            for (int i = 0; i < petCount; i++) {
                petOut.writeObject(pets[i]);
            }

            // Save human-readable report
            clinicOut.println("Clinic Name: " + clinicName);
            clinicOut.println("Total Pets: " + petCount);
            clinicOut.println("Cats: " + countPetsByType("Cat"));
            clinicOut.println("Dogs: " + countPetsByType("Dog"));
            clinicOut.println("Dominant Color: " + getDominantColor());

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Loads pets from binary file (this bascially silently handles the missing file)
    private void loadFromFile() {
        try (ObjectInputStream petIn = new ObjectInputStream(new FileInputStream(PET_FILE))) {
            while (true) {  // Read until EOFException
                pets[petCount++] = (Pet) petIn.readObject();
            }
        } catch (EOFException e) {
            // Normal termination - end of file reached
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing data found or error loading: " + e.getMessage());
        }
    }

    // Reporting methods
    public void generateReport() {
        System.out.println("\n--- " + clinicName + " Report ---");
        System.out.println("Total Pets: " + petCount);
        System.out.println("Cats: " + countPetsByType("Cat"));
        System.out.println("Dogs: " + countPetsByType("Dog"));
        System.out.println("Dominant Color: " + getDominantColor());
    }

    // Helper methods
    private int countPetsByType(String type) {
        int count = 0;
        for (int i = 0; i < petCount; i++) {
            if (pets[i].getClass().getSimpleName().equals(type)) {
                count++;
            }
        }
        return count;
    }

    private String getDominantColor() {
        Map<String, Integer> colorCounts = new HashMap<>();
        for (int i = 0; i < petCount; i++) {
            String color = pets[i].getColor();
            colorCounts.put(color, colorCounts.getOrDefault(color, 0) + 1);
        }
        return Collections.max(colorCounts.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    // Search and display methods
    public void searchPet(String query) {
        boolean found = false;
        for (int i = 0; i < petCount; i++) {
            Pet pet = pets[i];
            if (pet.getName().equalsIgnoreCase(query) || pet.getColor().equalsIgnoreCase(query)) {
                System.out.println("\nFound Pet:");
                System.out.println("Type: " + pet.getClass().getSimpleName());
                System.out.println("Name: " + pet.getName());
                System.out.println("Age: " + pet.getAge());
                System.out.println("Color: " + pet.getColor());
                System.out.println("Sound: " + pet.speak());
                found = true;
            }
        }
        if (!found) System.out.println("No pets found matching: " + query);
    }

    public int getPetCount() { return petCount; }

    public void displayAllPets() {
        if (petCount == 0) {
            System.out.println("No pets registered yet.");
            return;
        }
        System.out.println("\n--- All Registered Pets ---");
        for (int i = 0; i < petCount; i++) {
            System.out.println((i+1) + ". " + pets[i].speak());
        }
    }
}