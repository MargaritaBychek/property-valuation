package model;

import java.io.Serializable;
import java.util.Objects;

public class Office implements Serializable {
    private String Registration_No;
    private int MetresToRoad;
    private String MetroStation;
    private String BusStation;
    private boolean isLoft;
    private boolean isParking;
    private boolean isCenter;

    public String getRegistration_No() {
        return Registration_No;
    }

    public void setRegistration_No(String registration_No) {
        Registration_No = registration_No;
    }

    public int getMetresToRoad() {
        return MetresToRoad;
    }

    public void setMetresToRoad(int metresToRoad) {
        MetresToRoad = metresToRoad;
    }

    public String getMetroStation() {
        return MetroStation;
    }

    public void setMetroStation(String metroStation) {
        MetroStation = metroStation;
    }

    public String getBusStation() {
        return BusStation;
    }

    public void setBusStation(String busStation) {
        BusStation = busStation;
    }

    public boolean isLoft() {
        return isLoft;
    }

    public void setLoft(boolean loft) {
        isLoft = loft;
    }

    public boolean isParking() {
        return isParking;
    }

    public void setParking(boolean parking) {
        isParking = parking;
    }

    public boolean isCenter() {
        return isCenter;
    }

    public void setCenter(boolean center) {
        isCenter = center;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Office office = (Office) o;
        return getMetresToRoad() == office.getMetresToRoad() &&
                isLoft() == office.isLoft() &&
                isParking() == office.isParking() &&
                isCenter() == office.isCenter() &&
                Objects.equals(getRegistration_No(), office.getRegistration_No()) &&
                Objects.equals(getMetroStation(), office.getMetroStation()) &&
                Objects.equals(getBusStation(), office.getBusStation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegistration_No(), getMetresToRoad(), getMetroStation(), getBusStation(), isLoft(), isParking(), isCenter());
    }

    @Override
    public String toString() {
        return "Office{" +
                "Registration_No='" + Registration_No + '\'' +
                ", MetresToRoad=" + MetresToRoad +
                ", MetroStation='" + MetroStation + '\'' +
                ", BusStation='" + BusStation + '\'' +
                ", isLoft=" + isLoft +
                ", isParking=" + isParking +
                ", isCenter=" + isCenter +
                '}';
    }
}
