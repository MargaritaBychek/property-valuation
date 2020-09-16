package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.check.Check;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appraiser;
import model.Customer;
import model.Users;
import model.client.Client;

public class RegistrationController {
    private static AuthorizationController instance;
    private Client client;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Exit;

    @FXML
    private Button SignUp;

    @FXML
    private TextField Login;

    @FXML
    private TextField Name;

    @FXML
    private TextField Surname;

    @FXML
    private TextField Patronymic;

    @FXML
    private TextField Password;

    @FXML
    private TextField City;

    @FXML
    private TextField Address;

    @FXML
    private ComboBox<String> State;

    @FXML
    private TextField Phone;

    @FXML
    private TextField Email;

    @FXML
    private TextField Number;

    @FXML
    private TextField Serya;

    @FXML
    private TextField Licence;

    @FXML
    private Tab tab_customer;

    @FXML
    private Tab tab_appraiser;

    @FXML
    void initialize() {
        ObservableList<String> langs = FXCollections.observableArrayList
                ( "Брестская", "Витебская", "Гомельская", "Гродненская","Минская","Могилевская");
        State.setItems(langs);
        //TabPane tabPane = new TabPane();

        SignUp.setOnAction(event -> {

                Users user = new Users();
                if (tab_appraiser.isSelected()) {
                    if (checkAll() && Check.checkRegister(Licence.getText())) {
                        fillUser(user);
                        user.setRole(2);
                        Appraiser appraiser = new Appraiser();
                        RegisterAppraiser(user, appraiser);
                    }
                    else {
                        OpenWindow("Неправильный формат данных!");
                    }
                }
                if (tab_customer.isSelected()) {
                    if (checkAll() && checkCustomer()) {
                        fillUser(user);
                        user.setRole(1);
                        Customer customer = new Customer();
                        RegisterCustomer(user, customer);
                    }
                    else {
                        OpenWindow("Неправильный формат данных!");
                    }
                }
        });
        Exit.setOnAction(event ->
        {
            Stage stage = (Stage) Exit.getScene().getWindow();
            //stage.hide();
            try {
                Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("view/authorization.fxml"));
                stage.setScene(new Scene(root, 500, 400));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.show();
        });
    }
        public void RegisterCustomer(Users user, Customer customer) {
            System.out.println("Вход");
            client = new Client();
            customer.setEmail(Email.getText());
            customer.setPassportSeria(Serya.getText());
            customer.setPassportNo(Integer.valueOf(Number.getText()));
            client.sendMarker("registerCustomer");
            client.sendObject(user);
            System.out.println(user.toString());
            client.sendObject(customer);
            System.out.println(customer.toString());
            String msg=(String)client.readObject();
            if(msg.equals("ok"))
            {int message =(int)client.readObject();
            user.setIdUsers(message);
                    Stage stage = (Stage) SignUp.getScene().getWindow();
                    try {
                        FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().
                                getResource("view/customer.fxml"));
                        stage.setScene(new Scene(loader.load(), 800, 500));
                        CustomerController controllerCustomer=loader.<CustomerController>getController();
                        controllerCustomer.initData(user);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage.show();
                }
            else
                OpenWindow("Логин не уникален!");
            }
    public void RegisterAppraiser(Users user, Appraiser appraiser) {
        client = new Client();
        appraiser.setNo_Licence(Licence.getText());
        client.sendMarker("registerAppraiser");
        client.sendObject(user);
        System.out.println(user.toString());

        client.sendObject(appraiser);
        System.out.println(appraiser.toString());
        String msg=(String)client.readObject();
        if(msg.equals("ok"))
       { int message =(int)client.readObject();
        user.setIdUsers(message);
                Stage stage = (Stage) SignUp.getScene().getWindow();
                try {
                    FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().
                            getResource("view/appraiser.fxml"));
                    stage.setScene(new Scene(loader.load(), 800, 500));
                    AppraiserController controllerAppraiser=loader.<AppraiserController>getController();
                    controllerAppraiser.initAppraiser(user);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.show();
            }
        else
            OpenWindow("Логин не уникален!");
    }
        void fillUser(Users user)
        {
            user.setLogin(Login.getText());
            user.setPassword(Password.getText());
            user.setName(Name.getText());
            user.setSurname(Surname.getText());
            user.setPatronymic(Patronymic.getText());
            user.setState(State.getValue());
            user.setCity(City.getText());
            user.setAddress(Address.getText());
            user.setPhone(Phone.getText());
        }
    boolean checkAll()
    {
        if(!Check.checkLog(Login.getText()))
            return false;
        if(!Check.checkLog(Password.getText()))
            return false;
        if(!Check.checkString(Surname.getText()))
            return false;
        if(!Check.checkString(Name.getText()))
            return false;
        if(!Check.checkString(Patronymic.getText()))
            return false;
        if(!Check.checkString(City.getText()))
            return false;
        if(!Check.checkAddress(Address.getText()))
            return false;
        if(!Check.checkPhone(Phone.getText()))
            return false;
        return true;
    }
    boolean checkCustomer()
    {
        if(!Check.checkEmail(Email.getText()))
            return false;
        if(!Check.checkPassport(Serya.getText()))
            return false;
        if(!Check.checkInt(Number.getText()))
            return false;
        return true;
    }
    void OpenWindow(String txt)
    {
        Stage stage1 = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().
                    getResource("view/messages.fxml"));
            stage1.setScene(new Scene(loader.load(), 350, 200));
            MessagesController controllerDetail = loader.<MessagesController>getController();
            controllerDetail.setText(txt);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage1.show();
    }
}


