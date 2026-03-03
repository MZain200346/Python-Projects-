import java.io.Serializable;

public abstract class Pet implements Serializable {
    private String name;
    private int age;
    private String color;
    private double weight;

    public Pet(String name, int age, String color, double weight) {
        setName(name);
        setAge(age);
        setColor(color);
        setWeight(weight);
    }

    // Getters and setters with validation
    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }
// Setters with basic validation
    public int getAge() { return age; }
    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        this.age = age;
    }

    public String getColor() { return color; }
    public void setColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Color cannot be empty");
        }
        this.color = color;
    }

    public double getWeight() { return weight; }
    public void setWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        this.weight = weight;
    }
// subclasses must implement thier own sound
    public abstract String speak();
}