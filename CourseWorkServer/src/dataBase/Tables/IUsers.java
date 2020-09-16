package dataBase.Tables;

import model.Users;

import java.util.ArrayList;

public interface IUsers {
    public Users findUser(Users obj);
    public String findUserID(int id);
    public int findIDUser(Users obj);
    public boolean isLoginUnique(String login);
    public void insertUser(Users obj);
    public ArrayList<Users> selectUsers(int role);
    public ArrayList<Users> selectallUsers();
    public void changeRole(Users user);
    public String updateUser(Users user);
    public void deleteUser(int number);
}
