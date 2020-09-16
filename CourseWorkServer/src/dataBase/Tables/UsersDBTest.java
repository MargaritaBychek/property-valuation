package dataBase.Tables;

import model.Users;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class UsersDBTest {
    UsersDB db;
    @BeforeAll
    public static void beforeAll() {
       System.out.println("Before Test");
    }
    @BeforeEach
    public void beforeEach() {
        db=new UsersDB();
    }
    @AfterEach
    public void AfterEach() {
        db=null;
    }
    @AfterAll
    public static void AfterAll() {
        System.out.println("After Test");
    }
    @org.junit.jupiter.api.Test
    void findUser() throws Exception{
        Users find=new Users();
        find.setLogin("new");
        find.setPassword("1234");
        Users user=db.findUser(find);
        assertEquals(user.getLogin(),"new");
    }

    @org.junit.jupiter.api.Test
    void isLoginUnique() {
        String login="hi";
        assertTrue(db.isLoginUnique(login));
    }
}