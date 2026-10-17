import java.util.ArrayList;
import java.util.List;

// A mission = a name + a spacecraft + the astronauts assigned to it
public class mission {
    private String name;
    private spacecraft spacecraft;
    private List<Astronaut> crew = new ArrayList<>();
    private boolean launched = false;

    public mission(String name, spacecraft spacecraft) {
        this.name = name;
        this.spacecraft = spacecraft;
    }

    public String getName() { return name; }
    public boolean isLaunched() { return launched; }
//adding an astronaut
    public boolean addAstronaut(Astronaut a) {
        if (crew.size() >= spacecraft.getCapacity()) {
            System.out.println("Cannot add " + a.getName() + ": " + spacecraft.getName() + " is full.");
            return false;
        }
        if (crew.contains(a)) {
            System.out.println(a.getName() + " is already on this mission.");
            return false;
        }
        crew.add(a);
        return true;
    }

    public void launch() {
        if (crew.isEmpty()) {
            System.out.println("Cannot launch: no astronauts assigned.");
            return;
        }
        spacecraft.launch();
        launched = true;
        System.out.println("Mission '" + name + "' is underway!");
    }

    public void displayInfo() {
        System.out.println("--- Mission: " + name + " [" + (launched ? "LAUNCHED" : "PLANNED") + "] ---");
        spacecraft.displayInfo();
        System.out.println("Crew (" + crew.size() + "/" + spacecraft.getCapacity() + "):");
        if (crew.isEmpty()) System.out.println("  (none)");
        for (Astronaut a : crew) System.out.println("  - " + a.getName() + " (" + a.getRole() + ")");
    }
}