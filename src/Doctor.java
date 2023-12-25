import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Doctor {
    private String name;
    private Map<String, List<String>> schedule;

    public Doctor(String name) {
        this.name = name;
        this.schedule = new HashMap<>();
        // Initialize the doctor's schedule
        // ...
    }

    public String getName() {
        return name;
    }

    public Map<String, List<String>> getSchedule() {
        return schedule;
    }
}
