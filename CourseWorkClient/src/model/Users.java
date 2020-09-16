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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Users that = (Users) obj;

        return Objects.equals(this.idUsers, that.idUsers) &&
                Objects.equals(this.Login, that.Login) &&
                Objects.equals(this.Password, that.Password) &&
                Objects.equals(this.Role, that.Role) &&
                Objects.equals(this.Name, that.Name) &&
                Objects.equals(this.Surname, that.Surname) &&
                Objects.equals(this.Patronymic, that.Patronymic) &&
                Objects.equals(this.State, that.State) &&
                Objects.equals(this.City, that.City) &&
                Objects.equals(this.Address, that.Address) &&
                Objects.equals(this.Phone, that.Phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idUsers,
                this.Login, this.Password, this.Role,
                this.Name, this.Surname, this.Patronymic,
                this.State,this.City,this.Address,
                this.Phone);
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
