package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.check.Check;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Users;
import model.client.Client;

public class AuthorizationController {
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
    private PasswordField Password;
    @FXML
    private Button SignIn;
    @FXML
    private Button SignUp;


    @FXML
    void initialize() {
        SignIn.setOnAction(event -> {
            String loginText=Login.getText().trim();
            String passwordText=Password.getText().trim();
            if(loginText.equals("")||passwordText.equals(""))
                OpenWindow("Поля не заполнены");
            else
                if(Check.checkLog(Login.getText())&&Check.checkLog(Password.getText()))
                     Authorize(loginText,passwordText);
                else
                    OpenWindow("Неправильный формат данных");
        });
        SignUp.setOnAction(event ->
                {
                    Stage dialog=new Stage();
                    Stage stage = (Stage) SignIn.getScene().getWindow();
                    //stage.hide();
                    try {
                        Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("view/registration.fxml"));
                        stage.setScene(new Scene(root, 614, 500));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage.show();
                });
        Exit.setOnAction(event ->
        {
            client=new Client();
            client.sendMarker("exit");
            Stage stage = (Stage) Exit.getScene().getWindow();
            stage.close();
        });
    }
    public void Authorize(String loginText,String passwordText)  {
        System.out.println("Вход");
        client = new Client();
        Users user = new Users();
        user.setLogin(loginText);
        user.setPassword(passwordText);

        client.sendMarker("start");
        client.sendObject(user);

        String message=new String();
        try {
            message=client.readMarker();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(message.equals("error"))
        {
            OpenWindow("Такой пользователь не зарегистрирован");
        }
        else
        {
            Users users=(Users)client.readObject();
            int msg=users.getRole();
            switch(msg)
            {
                case 0:
                {
                    Stage stage = (Stage) SignUp.getScene().getWindow();
                    //stage.hide();
                    try {
                        FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().
                                getResource("view/admin.fxml"));
                        stage.setScene(new Scene(loader.load(), 800, 500));
                        AdminController controllerAdmin=loader.<AdminController>getController();
                        controllerAdmin.initAdmin(users);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage.show();
                }break;
                case 1:
                {
                    Stage stage = (Stage) SignUp.getScene().getWindow();
                    try {
                        FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().
                                getResource("view/customer.fxml"));
                        stage.setScene(new Scene(loader.load(), 800, 500));
                        CustomerController controllerCustomer=loader.<CustomerController>getController();
                        controllerCustomer.initData(users);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage.show();
                }break;
                case 2:
                {
                    Stage stage = (Stage) SignUp.getScene().getWindow();
                    try {
                        FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().
                                getResource("view/appraiser.fxml"));
                        stage.setScene(new Scene(loader.load(), 800, 500));
                        AppraiserController controllerAppraiser=loader.<AppraiserController>getController();
                        controllerAppraiser.initAppraiser(users);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage.show();
                }break;
                case -1:
                {
                    OpenWindow("Ваш аккаунт заблокирован");
                }break;
                case -2:
                {
                    OpenWindow("Ваш аккаунт заблокирован");
                }break;
            }
        }
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
}

