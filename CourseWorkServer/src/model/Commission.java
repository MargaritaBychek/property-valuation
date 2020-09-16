package model;

import java.io.Serializable;
import java.util.Objects;

public class Commission implements Serializable {
    private int idCommission;
    private String Date;
    private String Time;
    private int id_appraiser;
    private String Registration_No;
    private String Status;
    private boolean isVoted;

    public int getIdCommission() {
        return idCommission;
    }

    public void setIdCommission(int idComission) {
        this.idCommission = idComission;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getId_appraiser() {
        return id_appraiser;
    }

    public void setId_appraiser(int id_appraiser) {
        this.id_appraiser = id_appraiser;
    }

    public String getRegistration_No() {
        return Registration_No;
    }

    public void setRegistration_No(String registration_No) {
        Registration_No = registration_No;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public boolean isVoted() {
        return isVoted;
    }

    public void setVoted(boolean voted) {
        isVoted = voted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commission that = (Commission) o;
        return getIdCommission() == that.getIdCommission() &&
                getId_appraiser() == that.getId_appraiser() &&
                isVoted() == that.isVoted() &&
                Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getTime(), that.getTime()) &&
                Objects.equals(getRegistration_No(), that.getRegistration_No()) &&
                Objects.equals(getStatus(), that.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdCommission(), getDate(), getTime(), getId_appraiser(), getRegistration_No(), getStatus(), isVoted());
    }

    @Override
    public String toString() {
        return "Commission{" +
                "idCommission=" + idCommission +
                ", Date='" + Date + '\'' +
                ", Time='" + Time + '\'' +
                ", id_appraiser=" + id_appraiser +
                ", Registration_No='" + Registration_No + '\'' +
                ", Status='" + Status + '\'' +
                ", isVoted=" + isVoted +
                '}';
    }
}

