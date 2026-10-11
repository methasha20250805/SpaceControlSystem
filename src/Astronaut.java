
public class Astronaut {
    private String name;
    private String role;

    public Astronaut(String name, String role) {
        this.name = name;
        this.role = role;
    }

    // Getters
    public String getName() { return name; }
    public String getRole() { return role; }

    // Setters (with validation = controlled access)
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }

    public void setRole(String role) {
        if (role != null && !role.trim().isEmpty()) {
            this.role = role;
        } else {
            System.out.println("Invalid role. Role not changed.");
        }
    }

    public void displayInfo() {
        System.out.println("Astronaut: " + name + " | Role: " + role);
    }

    public static void main(String[] args) {
        Astronaut a = new Astronaut("Neil", "Commander");
        a.setRole("Mission Specialist");
        a.setRole("");                       // rejected
        System.out.println(a.getName() + " is now " + a.getRole());
    }
}