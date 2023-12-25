import java.util.ArrayList;
import java.util.List;

public class TreatmentManager {
    public static List<Treatment> getAvailableTreatments() {
        List<Treatment> availableTreatments = new ArrayList<>();

        for (TreatmentType type : TreatmentType.values()) {
            Treatment treatment = TreatmentFactory.createTreatment(type);
            availableTreatments.add(treatment);
        }

        return availableTreatments;
    }
}