
public class Patient extends Person {

    private String address;
    private String telephone;

    public Patient(String name, String address, String telephone) {
        super(name);
        this.address = address;
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
