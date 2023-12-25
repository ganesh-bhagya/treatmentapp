
// public class Treatment {
//     private TreatmentType type;

//     public Treatment(TreatmentType type) {
//         this.type = type;
//     }

//     public TreatmentType getType() {
//         return type;
//     }

//     public double getPrice() {
//         return type.getPrice();
//     }
// }

//update for fatory design pattern
// Interface for treatment
interface Treatment {
    TreatmentType getType();
    double getPrice();
}
