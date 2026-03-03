// Concrete class representing a Cat, extends base Pet class
public class Cat extends Pet {
    private String breed;  // Specific attribute for cats

    // Constructor initialies both Pet properties and cat-specific breed
    public Cat(String name, int age, String color, double weight, String breed) {
        super(name, age, color, weight);  // Initialize base Pet properties
        setBreed(breed);  // Validate and set cat breed
    }

    @Override
    // Implements Pets abstract method with cat specific sound
    public String speak() {
        return "Meow! I'm " + getName() + ", a " + getAge() + " year old " + breed;
    }

    // Standard getter/setter with validation for breedd
    public String getBreed() { return breed; }
    public void setBreed(String breed) {
        if (breed == null || breed.trim().isEmpty()) {
            throw new IllegalArgumentException("Breed cannot be empty");
        }
        this.breed = breed;
    }
}