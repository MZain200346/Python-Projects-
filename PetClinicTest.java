import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PetClinicTest {
    @Test
    void testCatCreation() {
        Cat c = new Cat("Whiskers", 3, "Gray", 4.2, "Tabby");
        assertEquals("Whiskers", c.getName());
    }

    @Test
    void testInvalidAge() {
        assertThrows(IllegalArgumentException.class,
                () -> new Cat("Fluffy", -1, "White", 3.0, "Persian"));
    }

    @Test
    void testInvalidWeight() {
        assertThrows(IllegalArgumentException.class,
                () -> new Cat("Fluffy", 2, "White", -1.0, "Persian"));
    }

    @Test
    void testEmptyName() {
        assertThrows(IllegalArgumentException.class,
                () -> new Cat("", 2, "White", 3.0, "Persian"));
    }

    @Test
    void testEmptyBreed() {
        assertThrows(IllegalArgumentException.class,
                () -> new Cat("Fluffy", 2, "White", 3.0, ""));
    }

    @Test
    void testFilePersistence() {
        PetClinicManager manager = new PetClinicManager();
        int initialCount = manager.getPetCount(); // Get current count

        manager.addPet(new Cat("Test", 1, "Black", 2.0, "Siamese"));
        manager.saveToFile();

        PetClinicManager newManager = new PetClinicManager();
        assertEquals(initialCount + 1, newManager.getPetCount());
    }
}