import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//command line interface
public class Main {
    static List<Astronaut> astronauts = new ArrayList<>();
    static List<spacecraft> spacecrafts = new ArrayList<>();
    static List<mission> missions = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n===== Space Mission Control System =====");
            System.out.println("1. Register astronaut");
            System.out.println("2. Add spacecraft");
            System.out.println("3. Create mission (assign astronauts)");
            System.out.println("4. Launch mission");
            System.out.println("5. Display all missions");
            System.out.println("6. List astronauts / spacecraft");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            if (!sc.hasNextLine()) break;
            switch (sc.nextLine().trim()) {
                case "1": registerAstronaut(); break;
                case "2": addSpacecraft(); break;
                case "3": createMission(); break;
                case "4": launchMission(); break;
                case "5": displayMissions(); break;
                case "6": listAll(); break;
                case "0": running = false; System.out.println("Mission control offline. Goodbye!"); break;
                default: System.out.println("Invalid option.");
            }
        }
    }

    static void registerAstronaut() {
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Role: ");
        String role = sc.nextLine().trim();
        if (name.isEmpty() || role.isEmpty()) {
            System.out.println("Name and role cannot be empty.");
            return;
        }
        astronauts.add(new Astronaut(name, role));
        System.out.println("Registered " + name + ".");
    }

    static void addSpacecraft() {
        System.out.print("Type (1 = Shuttle, 2 = Rover): ");
        String type = sc.nextLine().trim();
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        int capacity = readInt("Crew capacity: ");
        if (name.isEmpty() || capacity <= 0) {
            System.out.println("Invalid name or capacity.");
            return;
        }
        if (type.equals("1")) {
            int cargo = readInt("Cargo capacity (kg): ");
            spacecrafts.add(new Shuttle(name, capacity, cargo));
        } else if (type.equals("2")) {
            System.out.print("Terrain (e.g. Lunar, Martian): ");
            spacecrafts.add(new Rover(name, capacity, sc.nextLine().trim()));
        } else {
            System.out.println("Unknown type.");
            return;
        }
        System.out.println("Spacecraft added.");
    }

    static void createMission() {
        if (astronauts.isEmpty() || spacecrafts.isEmpty()) {
            System.out.println("Register at least one astronaut and one spacecraft first.");
            return;
        }
        System.out.print("Mission name: ");
        String name = sc.nextLine().trim();

        for (int i = 0; i < spacecrafts.size(); i++)
            System.out.println((i + 1) + ". " + spacecrafts.get(i).getType() + " - " + spacecrafts.get(i).getName());
        int s = readInt("Choose spacecraft #: ") - 1;
        if (s < 0 || s >= spacecrafts.size()) { System.out.println("Invalid choice."); return; }

        mission m = new mission(name, spacecrafts.get(s));

        String more = "y";
        while (more.equalsIgnoreCase("y")) {
            for (int i = 0; i < astronauts.size(); i++)
                System.out.println((i + 1) + ". " + astronauts.get(i).getName() + " (" + astronauts.get(i).getRole() + ")");
            int a = readInt("Assign astronaut #: ") - 1;
            if (a >= 0 && a < astronauts.size()) m.addAstronaut(astronauts.get(a));
            else System.out.println("Invalid choice.");
            System.out.print("Add another? (y/n): ");
            more = sc.nextLine().trim();
        }
        missions.add(m);
        System.out.println("Mission '" + name + "' created.");
    }

    static void launchMission() {
        if (missions.isEmpty()) { System.out.println("No missions yet."); return; }
        for (int i = 0; i < missions.size(); i++)
            System.out.println((i + 1) + ". " + missions.get(i).getName());
        int i = readInt("Launch mission #: ") - 1;
        if (i < 0 || i >= missions.size()) { System.out.println("Invalid choice."); return; }
        missions.get(i).launch();
    }

    static void displayMissions() {
        if (missions.isEmpty()) System.out.println("No missions yet.");
        for (mission m : missions) m.displayInfo();
    }

    static void listAll() {
        System.out.println("Astronauts:");
        for (Astronaut a : astronauts) a.displayInfo();
        System.out.println("Spacecraft:");
        for (spacecraft s : spacecrafts) s.displayInfo();
    }

    // Safe integer input (avoids crashes on bad input)
    static int readInt(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
