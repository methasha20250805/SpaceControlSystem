// Child class: crew transport
public class Shuttle extends spacecraft {
    private int cargoCapacityKg;

    public Shuttle(String name, int capacity, int cargoCapacityKg) {
        super(name, capacity);              // call parent constructor
        this.cargoCapacityKg = cargoCapacityKg;
    }

    public int getCargoCapacityKg() { return cargoCapacityKg; }

    @Override
    public String getType() { return "Shuttle"; }

    @Override
    public void launch() {
        System.out.println(getName() + " (Shuttle) lifts off, carrying crew to orbit!");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("  Cargo capacity: " + cargoCapacityKg + " kg");
    }
}
