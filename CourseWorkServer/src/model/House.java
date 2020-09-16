package model;

import java.io.Serializable;
import java.util.Objects;

public class House extends Realty implements Serializable {
    private String Registration_No;
    private int NumberFloors;
    private  double SquareLand;
    private boolean isCHS;
    private boolean isAqueduct;

    public String getRegistration_No() {
        return Registration_No;
    }

    public void setRegistration_No(String registration_No) {
        Registration_No = registration_No;
    }

    public int getNumberFloors() {
        return NumberFloors;
    }

    public void setNumberFloors(int numberFloors) {
        NumberFloors = numberFloors;
    }

    public double getSquareLand() {
        return SquareLand;
    }

    public void setSquareLand(double squareLand) {
        SquareLand = squareLand;
    }

    public boolean isCHS() {
        return isCHS;
    }

    public void setCHS(boolean CHS) {
        isCHS = CHS;
    }

    public boolean isAqueduct() {
        return isAqueduct;
    }

    public void setAqueduct(boolean aqueduct) {
        isAqueduct = aqueduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return getNumberFloors() == house.getNumberFloors() &&
                Double.compare(house.getSquareLand(), getSquareLand()) == 0 &&
                isCHS() == house.isCHS() &&
                isAqueduct() == house.isAqueduct() &&
                Objects.equals(getRegistration_No(), house.getRegistration_No());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegistration_No(), getNumberFloors(), getSquareLand(), isCHS(), isAqueduct());
    }

    @Override
    public String toString() {
        return "House{" +
                "Registration_No='" + Registration_No + '\'' +
                ", NumberFloors=" + NumberFloors +
                ", SquareLand=" + SquareLand +
                ", isCHS=" + isCHS +
                ", isAqueduct=" + isAqueduct +
                '}';
    }
}
