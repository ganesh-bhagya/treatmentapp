// create enum class for treatment with prices
enum TreatmentType {
    CLEANINGS(500.0),
    WHITENING(1000.0),
    FILLING(800.0),
    NERVE_FILLING(1200.0),
    ROOT_CANAL_THERAPY(1500.0);

    private final double price;

    TreatmentType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
