package model;
import java.io.Serializable;
import java.util.Objects;
public class Customer extends Users implements Serializable{
    private int idCustomer;
    private String Email;
    private String PassportSeria;
    private int PassportNo;

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassportSeria() {
        return PassportSeria;
    }

    public void setPassportSeria(String passportSeria) {
        PassportSeria = passportSeria;
    }

    public int getPassportNo() {
        return PassportNo;
    }

    public void setPassportNo(int passportNo) {
        PassportNo = passportNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return getIdCustomer() == customer.getIdCustomer() &&
                getPassportNo() == customer.getPassportNo() &&
                getEmail().equals(customer.getEmail()) &&
                getPassportSeria().equals(customer.getPassportSeria());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdCustomer(), getEmail(), getPassportSeria(), getPassportNo());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "idCustomer=" + idCustomer +
                ", Email='" + Email + '\'' +
                ", PassportSeria='" + PassportSeria + '\'' +
                ", PassportNo=" + PassportNo +
                '}';
    }
}
