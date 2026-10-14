// Rover extends Spacecraft class
public class Rover extends spacecraft {
    private String terrain;

    public Rover(String name, int capacity, String terrain) {
        super(name, capacity);
        this.terrain = terrain;
    }

    public String getTerrain() { return terrain; }

    @Override
    public String getType() { return "Rover"; }

    @Override
    public void launch() {
        System.out.println(getName() + " (Rover) deploys and begins exploring " + terrain + " terrain!");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("  Terrain: " + terrain);
    }
}
