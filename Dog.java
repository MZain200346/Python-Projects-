/**
 * Dog implementation of the Pet class with breed-specific functionality
 */
public class Dog extends Pet {
    private String breed;  // Dog's breed (e.g., "Labrador", "Poodle")

    /**
     * Creates a new Dog instance
     * @param breed Must be non-empty (validated in setter)
     */
    public Dog(String name, int age, String color, double weight, String breed) {
        super(name, age, color, weight);
        setBreed(breed);  // Validates breed through setter
    }

    @Override
    public String speak() {
        return "Woof! I'm " + getName() + ", a " + getAge() + " year old " + breed;
    }

    // Breed accessor with validation
    public String getBreed() { return breed; }
    public void setBreed(String breed) {
        if (breed == null || breed.trim().isEmpty()) {
            throw new IllegalArgumentException("Breed cannot be empty");
        }
        this.breed = breed;
    }
}