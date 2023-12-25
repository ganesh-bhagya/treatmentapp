
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Doctor extends Person {

    private Map<String, List<String>> schedule;

    public Doctor(String name) {
        super(name);
        this.schedule = new HashMap<>();
        // Initialize the doctor's schedule
        // ...
    }

    public Map<String, List<String>> getSchedule() {
        return schedule;
    }
}
