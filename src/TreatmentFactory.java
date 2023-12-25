public class TreatmentFactory {
    public static Treatment createTreatment(TreatmentType type) {
        return new ConcreteTreatment(type);
    }
    
    // Concrete treatment class implementing the Treatment interface
    private static class ConcreteTreatment implements Treatment {
        private TreatmentType type;

        public ConcreteTreatment(TreatmentType type) {
            this.type = type;
        }

        @Override
        public TreatmentType getType() {
            return type;
        }

        @Override
        public double getPrice() {
            return type.getPrice();
        }
    }
}
