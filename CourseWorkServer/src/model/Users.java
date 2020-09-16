package model;

import java.io.Serializable;
import java.util.Objects;

public class Users implements Serializable {
    private int idUsers;
    private String Login;
    private String Password;
    private int Role;
    private String Name;
    private String Surname;
    private String Patronymic;
    private String State;
    private String City;
    private String Address;
    private String Phone;

    
    public int getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(int idUsers) {
        this.idUsers = idUsers;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int role) {
        Role = role;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getPatronymic() {
        return Patronymic;
    }

    public void setPatronymic(String patronymic) {
        Patronymic = patronymic;
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

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return getIdUsers() == users.getIdUsers() &&
                getRole() == users.getRole() &&
                Objects.equals(getLogin(), users.getLogin()) &&
                Objects.equals(getPassword(), users.getPassword()) &&
                Objects.equals(getName(), users.getName()) &&
                Objects.equals(getSurname(), users.getSurname()) &&
                Objects.equals(getPatronymic(), users.getPatronymic()) &&
                Objects.equals(getState(), users.getState()) &&
                Objects.equals(getCity(), users.getCity()) &&
                Objects.equals(getAddress(), users.getAddress()) &&
                Objects.equals(getPhone(), users.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdUsers(), getLogin(), getPassword(),
                getRole(), getName(), getSurname(), getPatronymic(),
                getState(), getCity(), getAddress(), getPhone());
    }

    @Override
    public String toString() {
        return "Users{" +
                "idUsers=" + idUsers +
                ", Login='" + Login + '\'' +
                ", Password='" + Password + '\'' +
                ", Role=" + Role +
                ", Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", Patronymic='" + Patronymic + '\'' +
                ", State='" + State + '\'' +
                ", City='" + City + '\'' +
                ", Address='" + Address + '\'' +
                ", Phone='" + Phone + '\'' +
                '}';
    }
}
