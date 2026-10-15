//Spacecraft class
public class spacecraft {
    private String name;
    private int capacity;

    public spacecraft(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }
//getters and setters
    public String getName() { return name; }
    public int getCapacity() { return capacity; }

    public String getType() { return "Spacecraft"; }

    public void launch() {
        System.out.println(name + " is launching...");
    }

    public void displayInfo() {
        System.out.println(getType() + ": " + name + " | Capacity: " + capacity);
    }
}