package model;

import java.io.Serializable;
import java.util.Objects;

public class Flat extends Realty implements Serializable {
    private  String Registration_No;
    private boolean isLast_FirstFloor;
    private boolean isCorner;
    private boolean isRepair;
    private boolean isLoggia;
    private boolean isGreenZone;
    private boolean isDormitoryArea;

    public String getRegistration_No() {
        return Registration_No;
    }

    public void setRegistration_No(String registration_No) {
        Registration_No = registration_No;
    }

    public boolean isLast_FirstFloor() {
        return isLast_FirstFloor;
    }

    public void setLast_FirstFloor(boolean last_FirstFloor) {
        isLast_FirstFloor = last_FirstFloor;
    }

    public boolean isCorner() {
        return isCorner;
    }

    public void setCorner(boolean corner) {
        isCorner = corner;
    }

    public boolean isRepair() {
        return isRepair;
    }

    public void setRepair(boolean repair) {
        isRepair = repair;
    }

    public boolean isLoggia() {
        return isLoggia;
    }

    public void setLoggia(boolean loggia) {
        isLoggia = loggia;
    }

    public boolean isGreenZone() {
        return isGreenZone;
    }

    public void setGreenZone(boolean greenZone) {
        isGreenZone = greenZone;
    }

    public boolean isDormitoryArea() {
        return isDormitoryArea;
    }

    public void setDormitoryArea(boolean dormitoryArea) {
        isDormitoryArea = dormitoryArea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return isLast_FirstFloor() == flat.isLast_FirstFloor() &&
                isCorner() == flat.isCorner() &&
                isRepair() == flat.isRepair() &&
                isLoggia() == flat.isLoggia() &&
                isGreenZone() == flat.isGreenZone() &&
                isDormitoryArea() == flat.isDormitoryArea() &&
                Objects.equals(getRegistration_No(), flat.getRegistration_No());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegistration_No(), isLast_FirstFloor(), isCorner(), isRepair(), isLoggia(), isGreenZone(), isDormitoryArea());
    }

    @Override
    public String toString() {
        return "Flat{" +
                "Registration_No='" + Registration_No + '\'' +
                ", isLast_FirstFloor=" + isLast_FirstFloor +
                ", isCorner=" + isCorner +
                ", isRepair=" + isRepair +
                ", isLoggia=" + isLoggia +
                ", isGreenZone=" + isGreenZone +
                ", isDormitoryArea=" + isDormitoryArea +
                '}';
    }
}
