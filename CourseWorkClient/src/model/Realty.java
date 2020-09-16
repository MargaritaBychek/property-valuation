package model;
import java.io.Serializable;
import java.util.Objects;
public class Realty implements Serializable{
    private String Registration_No;
    private int IDOwner;
    public String Type;
    private String State;
    private String City;
    private String Address;
    private int Rooms;
    private double StateCoefficient;
    private double MaterialCoefficient;
    private double SquareFloor;
    private double HighCeil;
    private double ThickWall;
    private String Material;
    private  int Age;

    public String getRegistration_No() {
        return Registration_No;
    }

    public void setRegistration_No(String registration_No) {
        Registration_No = registration_No;
    }

    public int getIDOwner() {
        return IDOwner;
    }

    public void setIDOwner(int IDOwner) {
        this.IDOwner = IDOwner;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getRooms() {
        return Rooms;
    }

    public void setRooms(int rooms) {
        Rooms = rooms;
    }

    public double getSquareFloor() {
        return SquareFloor;
    }

    public void setSquareFloor(double squareFloor) {
        SquareFloor = squareFloor;
    }

    public double getHighCeil() {
        return HighCeil;
    }

    public void setHighCeil(double highCeil) {
        HighCeil = highCeil;
    }

    public double getThickWall() {
        return ThickWall;
    }

    public void setThickWall(double thickWall) {
        ThickWall = thickWall;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public double getStateCoefficient() {
        return StateCoefficient;
    }

    public void setStateCoefficient(double stateCoefficient) {
        StateCoefficient = stateCoefficient;
    }

    public double getMaterialCoefficient() {
        return MaterialCoefficient;
    }

    public void setMaterialCoefficient(double materialCoefficient) {
        MaterialCoefficient = materialCoefficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Realty realty = (Realty) o;
        return getIDOwner() == realty.getIDOwner() &&
                getRooms() == realty.getRooms() &&
                Double.compare(realty.getStateCoefficient(), getStateCoefficient()) == 0 &&
                Double.compare(realty.getMaterialCoefficient(), getMaterialCoefficient()) == 0 &&
                Double.compare(realty.getSquareFloor(), getSquareFloor()) == 0 &&
                Double.compare(realty.getHighCeil(), getHighCeil()) == 0 &&
                Double.compare(realty.getThickWall(), getThickWall()) == 0 &&
                getAge() == realty.getAge() &&
                Objects.equals(getRegistration_No(), realty.getRegistration_No()) &&
                Objects.equals(getType(), realty.getType()) &&
                Objects.equals(getState(), realty.getState()) &&
                Objects.equals(getCity(), realty.getCity()) &&
                Objects.equals(getAddress(), realty.getAddress()) &&
                Objects.equals(getMaterial(), realty.getMaterial());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegistration_No(), getIDOwner(), getType(), getState(), getCity(), getAddress(), getRooms(), getStateCoefficient(), getMaterialCoefficient(), getSquareFloor(), getHighCeil(), getThickWall(), getMaterial(), getAge());
    }

    @Override
    public String toString() {
        return "Realty{" +
                "Registration_No='" + Registration_No + '\'' +
                ", IDOwner=" + IDOwner +
                ", Type='" + Type + '\'' +
                ", State='" + State + '\'' +
                ", City='" + City + '\'' +
                ", Address='" + Address + '\'' +
                ", Rooms=" + Rooms +
                ", StateCoefficient=" + StateCoefficient +
                ", MaterialCoefficient=" + MaterialCoefficient +
                ", SquareFloor=" + SquareFloor +
                ", HighCeil=" + HighCeil +
                ", ThickWall=" + ThickWall +
                ", Material='" + Material + '\'' +
                ", Age=" + Age +
                '}';
    }
}
