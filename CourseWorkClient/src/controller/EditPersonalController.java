package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.check.Check;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Appraiser;
import model.Customer;
import model.Users;
import model.client.Client;
import sun.rmi.runtime.Log;

public class EditPersonalController {
    private Client client;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Exit;

    @FXML
    private TextField Login;

    @FXML
    private TextField Name;

    @FXML
    private TextField Surname;

    @FXML
    private TextField Password;

    @FXML
    private TextField City;

    @FXML
    private TextField Address;

    @FXML
    private ComboBox<String> State;

    @FXML
    private TextField Patronymic;

    @FXML
    private TextField Phone;

    @FXML
    private Label SeriesHide;

    @FXML
    private TextField Email_Licence;

    @FXML
    private Label EmailLicence;

    @FXML
    private TextField Serya;

    @FXML
    private TextField Number;

    @FXML
    private Button OK;


    @FXML
    void initialize() {    }

    void editCustomer(Users user, Customer customer)
    {
        Stage stage1 = (Stage) OK.getScene().getWindow();
        stage1.initModality(Modality.APPLICATION_MODAL);
        Exit.setOnAction(event -> stage1.close());
        EditBase(user);
        Email_Licence.setText(customer.getEmail());
        Serya.setText(customer.getPassportSeria());
        Number.setText(String.valueOf(customer.getPassportNo()));
        OK.setOnAction(event ->
        {
            if (checkAll() && checkCustomer()) {
                initBase(user);
                customer.setEmail(Email_Licence.getText());
                customer.setPassportSeria(Serya.getText());
                customer.setPassportNo(Integer.parseInt(Number.getText()));
                client = new Client();
                client.sendMarker("updateCustomer");
                client.sendObject(user);
                client.sendObject(customer);
                String ms = (String) client.readObject();
                if (ms.equals("ok")) {
                    closeW();
                    OpenWindow("Операция прошла успешно!");
                }
            } else {
                OpenWindow("Неправильный формат данных!");
            }
        });
    }
    void editAppraiser(Users user, Appraiser appraiser)
    {
        Stage stage1 = (Stage) OK.getScene().getWindow();
        stage1.initModality(Modality.APPLICATION_MODAL);
        Exit.setOnAction(event -> stage1.close());
        EditBase(user);
        EmailLicence.setText("№ лицензии");
        Email_Licence.setText(appraiser.getNo_Licence());
        SeriesHide.setVisible(false);
        Serya.setVisible(false);
        Number.setVisible(false);
        OK.setOnAction(event ->
        {
            if (checkAll() && Check.checkRegister(Email_Licence.getText())) {
                initBase(user);
                appraiser.setNo_Licence(Email_Licence.getText());
                client = new Client();
                client.sendMarker("updateAppraiser");
                client.sendObject(user);
                client.sendObject(appraiser);
                String ms = (String) client.readObject();
                if (ms.equals("ok")) {
                    closeW();
                    OpenWindow("Операция прошла успешно!");
                }
            } else {
                OpenWindow("Неправильный формат данных!");
            }
        });
    }
    void editAdmin(Users user)
    {
        Stage stage1 = (Stage) OK.getScene().getWindow();
        stage1.initModality(Modality.APPLICATION_MODAL);
        Exit.setOnAction(event -> stage1.close());
        EditBase(user);
        EmailLicence.setVisible(false);
        Email_Licence.setVisible(false);
        SeriesHide.setVisible(false);
        Serya.setVisible(false);
        Number.setVisible(false);
        OK.setOnAction(event ->
        {
            if(checkAll())
            {
                initBase(user);
                client=new Client();
                client.sendMarker("updateAdmin");
                client.sendObject(user);
                String ms=(String)client.readObject();
                if(ms.equals("ok"))
                {closeW();
                    OpenWindow("Операция прошла успешно!");}
            }
            else{
                OpenWindow("Неправильный формат данных!");
            }
        });

    }

    void EditBase(Users user)
    {
        ObservableList<String> langs = FXCollections.observableArrayList
                ( "Брестская", "Витебская", "Гомельская", "Гродненская","Минская","Могилевская");
        State.setItems(langs);
        Login.setText(user.getLogin());
        Password.setText(user.getPassword());
        Surname.setText(user.getSurname());
        Name.setText(user.getName());
        Patronymic.setText(user.getPatronymic());
        State.setValue(user.getState());
        City.setText(user.getCity());
        Address.setText(user.getAddress());
        Phone.setText(user.getPhone());
    }
    void initBase(Users user)
    {
        user.setLogin(Login.getText());
        user.setPassword(Password.getText());
        user.setSurname(Surname.getText());
        user.setName(Name.getText());
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
        if(!Check.checkEmail(Email_Licence.getText()))
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
            stage1.setScene(new Scene(loader.load(), 440, 150));
            MessagesController controllerDetail = loader.<MessagesController>getController();
            controllerDetail.setText(txt);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage1.show();
    }
    void closeW()
    {
        Stage stage = (Stage) OK.getScene().getWindow();
        stage.close();
    }
}
