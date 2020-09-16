package dataBase.Tables;

import model.Users;

import java.util.ArrayList;

public class UsersDB implements IUsers {
    private static UsersDB instance;
    private ConnectionDB dbConnection;

    public UsersDB() {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized UsersDB getInstance() {
        if (instance == null) {
            instance = new UsersDB();
        }
        return instance;
    }

    public Users findUser(Users obj)
    {
        String find = "SELECT * From users Where login = '" + obj.getLogin() +
                "' and password = '" + obj.getPassword() + "'";
        ArrayList<String[]> result = dbConnection.getArrayResult(find);
        Users users = new Users();
        for (String[] items : result) {
            users.setIdUsers(Integer.parseInt(items[0]));
            users.setLogin(items[1]);
            users.setPassword(items[2]);
            users.setRole(Integer.parseInt(items[3]));
            users.setSurname(items[4]);
            users.setName(items[5]);
            users.setPatronymic(items[6]);
            users.setState(items[7]);
            users.setCity(items[8]);
            users.setAddress(items[9]);
            users.setPhone(items[10]);
        }
        return users;
    }
    public String findUserID(int id)
    {
        String find = "SELECT * From users Where id_users =" + id;
        ArrayList<String[]> result = dbConnection.getArrayResult(find);
        Users users = new Users();
        for (String[] items : result) {
            users.setSurname(items[4]);
            users.setName(items[5]);
            users.setPatronymic(items[6]);
        }
        String s=users.getSurname()+" "+users.getName()+" "+users.getPatronymic();
        return s;
    }
    public int findIDUser(Users obj)
    {
        String find = "SELECT * From users Where login = '" + obj.getLogin() +
                "' and password = '" + obj.getPassword() + "'";
        ArrayList<String[]> result = dbConnection.getArrayResult(find);
       int id = 0;
        for (String[] item : result)
            id = Integer.valueOf(item[0]);
        return id;
    }
    public boolean isLoginUnique(String login)
    {
        String find = "SELECT * From users Where login = '" +login + "'";
        ArrayList<String[]> result = dbConnection.getArrayResult(find);
        String UserLogin=new String();
        for (String[] item : result)
            UserLogin = item[1];
        if(login.equals(UserLogin))
            return false;
        return true;
    }
    public void insertUser(Users obj)
    {
        String insert="INSERT INTO users" +
                "(id_users,login, password, role, surname,name, patronymic, state,city,address,phone) VALUES(null,'"+
                obj.getLogin()+"','"+obj.getPassword()+"','"+obj.getRole()+"','"+
                obj.getSurname()+"','"+obj.getName()+"','"+obj.getPatronymic()+"','"+
                obj.getState()+"','"+obj.getCity()+"','"+obj.getAddress()+"','"+obj.getPhone()+"')";
        dbConnection.execute(insert);
    }
    public ArrayList<Users> selectUsers(int role)
    {
        String str = "SELECT * From Users WHERE role="+role;
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Users> listUsers = new ArrayList<>();

        for (String[] items: result){
            Users users = new Users();
            users.setIdUsers(Integer.parseInt(items[0]));
            users.setSurname(items[4]);
            users.setName(items[5]);
            users.setPatronymic(items[6]);
            users.setState(items[7]);
            users.setCity(items[8]);
            users.setAddress(items[9]);
            users.setPhone(items[10]);
            listUsers.add(users);
        }
        return listUsers;
    }
    public ArrayList<Users> selectallUsers()
    {
        String str = "SELECT * From Users";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Users> listUsers = new ArrayList<>();

        for (String[] items: result){
            Users users = new Users();
            users.setIdUsers(Integer.parseInt(items[0]));
            users.setLogin(items[1]);
            users.setPassword(items[2]);
            users.setRole(Integer.parseInt(items[3]));
            users.setSurname(items[4]);
            users.setName(items[5]);
            users.setPatronymic(items[6]);
            users.setState(items[7]);
            users.setCity(items[8]);
            users.setAddress(items[9]);
            users.setPhone(items[10]);
            listUsers.add(users);
        }
        return listUsers;
    }
    public void changeRole(Users user)
    {
        String str = "UPDATE users SET users.role="+ user.getRole()+
                " WHERE users.id_users="+user.getIdUsers();
        dbConnection.execute(str);
    }
    public String updateUser(Users user)
    {
        String str = "UPDATE users SET " +
                "users.login='"+ user.getLogin()+
                "', users.password='"+user.getPassword()+
                "',users.surname='"+ user.getSurname()+
                "',users.name='" + user.getName()+
                "', users.patronymic='"+user.getPatronymic()+
                "', users.state='"+ user.getState()+
                "',users.city='"+user.getCity()+
                "',users.address='"+user.getAddress()+
                "',users.phone='" +user.getPhone()+ "' " +
                " WHERE users.id_users="+user.getIdUsers();
        dbConnection.execute(str);
        return "ok";
    }
    public void deleteUser(int number)
    {
        String delete="DELETE FROM users WHERE id_users="+number;
        dbConnection.execute(delete);
    }
}