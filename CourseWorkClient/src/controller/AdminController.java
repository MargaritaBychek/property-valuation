package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Commission;
import model.Users;
import model.client.Client;

public class AdminController {
    private Client client;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Exit;

    @FXML
    private Tab tab_user;

    @FXML
    private TableView<Users> TableUsers;

    @FXML
    private TableColumn<Users, Integer> Table_id_user;

    @FXML
    private TableColumn<Users, String> Table_login_user;

    @FXML
    private TableColumn<Users, String> Table_password_user;

    @FXML
    private TableColumn<Users, Integer> Table_role_user;

    @FXML
    private TableColumn<Users, String> Table_surname_user;

    @FXML
    private TableColumn<Users, String> Table_name_user;

    @FXML
    private TableColumn<Users, String> Table_patronymic_user;

    @FXML
    private TableColumn<Users, String> Table_state_user;

    @FXML
    private TableColumn<Users, String> Table_city_user;

    @FXML
    private TableColumn<Users, String> Table_address_user;

    @FXML
    private TableColumn<Users, String> Table_phone_user;

    @FXML
    private Button Block;

    @FXML
    private Button details;

    @FXML
    private TextField Search;

    @FXML
    private Tab tab_commission;

    @FXML
    private TableView<Commission> Table_Commission;

    @FXML
    private TableColumn<?, ?> Table_id_commission;

    @FXML
    private TableColumn<?, ?> Table_date;

    @FXML
    private TableColumn<?, ?> Table_time;

    @FXML
    private TableColumn<?, ?> Table_appraiser;

    @FXML
    private TableColumn<?, ?> Table_object;

    @FXML
    private TableColumn<?, ?> Table_status;

    @FXML
    private Button Reject;

    @FXML
    private Button Statistics;

    @FXML
    private Tab tab_objects;

    @FXML
    private Tab PersonalCabinet;

    @FXML
    private Label PersonalLogin;

    @FXML
    private Label PersonalPassword;

    @FXML
    private Label PersonalSurname;

    @FXML
    private Label PersonalName;

    @FXML
    private Label PersonalPatronymic;

    @FXML
    private Label PersonalState;

    @FXML
    private Label PersonalCity;

    @FXML
    private Label PersonalAddress;

    @FXML
    private Label PersonalPhone;

    @FXML
    private Button CorrectPersonal;


    @FXML
    void initialize() {}

    void initAdmin(Users user)
    {
        client = new Client();
        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(tab_user,tab_commission,PersonalCabinet);
        int minsk=0,br=0,v=0,gom=0,gr=0,m=0;
        client.sendMarker("selectallUsers");
        List<Users> listUser=(List<Users>)client.readObject();
        ObservableList<Users> people = FXCollections.observableArrayList(listUser);
        initAppraiser(people);
        for(int i=0;i<listUser.size();i++)
        {
            Users u=listUser.get(i);
            String s=u.getState();
            switch(s)
            {
                case"Минская":minsk++;break;
                case"Брестская":br++;break;
                case"Витебская":v++;break;
                case"Гомельская":gom++;break;
                case"Гродненская":gr++;break;
                case"Могилевская":m++;break;
            }
        }
        client.sendMarker("selectCommissions");
        List<Commission> ListCommission = (List<Commission>)client.readObject();
        ObservableList<Commission> commissions = FXCollections.observableArrayList(ListCommission);
        initCommission(commissions);
        Exit.setOnAction(event -> closeW() );
        if(tab_user.isSelected()) {
            TabUserIsSelected(user,people,minsk,br,v,gom,gr,m);
        }
        tab_commission.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if(tab_commission.isSelected())
                {
                    final Commission[] commission = new Commission[1];
                    Table_Commission.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            commission[0] =Table_Commission.getSelectionModel().getSelectedItem();
                            System.out.println(commission[0].toString());

                        }
                    });
                    Reject.setOnAction(event1 ->
                    {
                        if(commission[0].getRegistration_No().equals(null))
                            OpenWindow("Выберите элемент из списка!");
                        else
                            if(commission[0].getStatus().equals("Завершена"))
                                OpenWindow("Заявка уже выполнена!");
                            else if(commission[0].getStatus().equals("Принята"))
                                OpenWindow("Заявка уже принята!");
                        else {
                            commission[0].setStatus("Отклонена");
                            System.out.println(commission[0].toString());
                            client.sendMarker("changeStatusCommission");
                            client.sendObject(commission[0]);
                            client.sendMarker("selectCommissions");
                            Table_Commission.refresh();
                        }
                    });
                }
            }
        });
        PersonalCabinet.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if(PersonalCabinet.isSelected())
                    PersonalCabinetIsSelected(user);
            }
        });

    }

    void TabUserIsSelected(Users user,ObservableList<Users> people,int minsk,int br,int v,int gom,int gr,int m)
    {
        final Users[] userAll = new Users[1];
        userAll[0]=new Users();
        TableUsers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                userAll[0] = TableUsers.getSelectionModel().getSelectedItem();

            }
        });
        details.setOnAction(event1 ->
        {
            Stage stage1 = new Stage();
            try {
                FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().
                        getResource("view/moreDetailsAppraiser.fxml"));
                stage1.setScene(new Scene(loader.load(), 560, 300));
                MoreDetailsAppraiserController controllerDetail=loader.<MoreDetailsAppraiserController>getController();
                controllerDetail.initData(user,userAll[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage1.show();
        });
        Block.setOnAction(event1 ->
        {
            int role = (userAll[0].getRole()) * (-1);
            userAll[0].setRole(role);
            System.out.println(userAll[0].toString());
            client.sendMarker("changeRole");
            client.sendObject(userAll[0]);
            System.out.println("select");
            //client.sendMarker("selectallUsers");
            TableUsers.refresh();
        });
        Statistics.setOnAction(event ->
        {
            Stage stage2 = new Stage();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().
                        getResource("view/info.fxml"));
                stage2.setScene(new Scene(loader.load(), 600, 560));
                InfoController controllDetail = loader.<InfoController>getController();
                controllDetail.show(minsk, br, v, gom, gr, m);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage2.show();
        });
        FilteredList<Users> filteredData = new FilteredList<>(people, p -> true);
        Search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Users -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(Users.getSurname()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Users> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TableUsers.comparatorProperty());
        TableUsers.setItems(sortedData);
    }
    void initAppraiser(ObservableList<Users> people)
    {
        Table_id_user.setCellValueFactory(new PropertyValueFactory<>("idUsers"));
        Table_login_user.setCellValueFactory(new PropertyValueFactory<>("Login"));
        Table_password_user.setCellValueFactory(new PropertyValueFactory<>("Password"));
        Table_role_user.setCellValueFactory(new PropertyValueFactory<>("Role"));
        Table_surname_user.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        Table_name_user.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Table_patronymic_user.setCellValueFactory(new PropertyValueFactory<>("Patronymic"));
        Table_state_user.setCellValueFactory(new PropertyValueFactory<>("State"));
        Table_city_user.setCellValueFactory(new PropertyValueFactory<>("City"));
        Table_address_user.setCellValueFactory(new PropertyValueFactory<>("Address"));
        Table_phone_user.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        TableUsers.setItems(people);
    }

    void initCommission(ObservableList<Commission> commissions)
    {
        Table_id_commission.setCellValueFactory(new PropertyValueFactory<>("idCommission"));
        Table_date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Table_time.setCellValueFactory(new PropertyValueFactory<>("Time"));
        Table_appraiser.setCellValueFactory(new PropertyValueFactory<>("id_appraiser"));
        Table_object.setCellValueFactory(new PropertyValueFactory<>("Registration_No"));
        Table_status.setCellValueFactory(new PropertyValueFactory<>("Status"));
        Table_Commission.setItems(commissions);
    }
    void PersonalCabinetIsSelected(Users user)
    {
        System.out.println("account");
        PersonalLogin.setText(user.getLogin());
        PersonalPassword.setText(user.getPassword());
        PersonalSurname.setText(user.getSurname());
        PersonalName.setText(user.getName());
        PersonalPatronymic.setText(user.getPatronymic());
        PersonalState.setText(user.getState());
        PersonalCity.setText(user.getCity());
        PersonalAddress.setText(user.getAddress());
        PersonalPhone.setText(user.getPhone());
        CorrectPersonal.setOnAction(event ->  {
            Stage stage1 = new Stage();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().
                        getResource("view/editPersonal.fxml"));
                stage1.setScene(new Scene(loader.load(), 740, 500));
                EditPersonalController controllerDetail = loader.<EditPersonalController>getController();
                controllerDetail.editAdmin(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage1.show();
        });

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
        Stage stage = (Stage) Exit.getScene().getWindow();
        try {
            Parent root= FXMLLoader.load(getClass().getClassLoader().getResource("view/authorization.fxml"));
            stage.setScene(new Scene(root, 500, 400));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }
}
