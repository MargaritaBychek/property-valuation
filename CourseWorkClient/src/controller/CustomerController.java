package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import model.*;
import model.client.Client;

public class CustomerController {
    private Client client;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Exit;

    @FXML
    private Tab Appraiser_tab;

    @FXML
    private TableView<Users> TableUsers;

    @FXML
    private TableColumn<Users, Integer> Table_id_user;

    @FXML
    private TableColumn<Users, String> Table_surname_user;

    @FXML
    private TableColumn<Users, String> Table_name_user;

    @FXML
    private TableColumn<Users, String> Table_patronymic_user;

    @FXML
    private TableColumn<Users, String> Table_state_user;

    @FXML
    private TableColumn<Users,String> Table_city_user;

    @FXML
    private TableColumn<Users, String> Table_address_user;

    @FXML
    private TableColumn<Users, String> Table_phone_user;
    @FXML
    private ComboBox<String> SearchState;

    @FXML
    private TextField SearchSurname;

    @FXML
    private Button Statistics;

    @FXML
    private Tab Objects_tab;

    @FXML
    private Button DeleteObject;

    @FXML
    private Button CorrectObject;

    @FXML
    private Button AddObject;

    @FXML
    private Button MyObjects;

    @FXML
    private Button More;

    @FXML
    private Button ReturnAllObjects;

    @FXML
    private TableView<Realty> TableRealty;

    @FXML
    private TableColumn<Realty, String> Registration_No;

    @FXML
    private TableColumn<Realty, String> TypeRealty;

    @FXML
    private TableColumn<Realty, String> StateRealty;

    @FXML
    private TableColumn<Realty, String> CityRealty;

    @FXML
    private TableColumn<Realty, String> AddressRealty;

    @FXML
    private TableColumn<Realty, Integer> NumberRooms;

    @FXML
    private TableColumn<Realty, Double> Square;

    @FXML
    private TableColumn<Realty, Double> High;

    @FXML
    private TableColumn<Realty, Double> Thick;

    @FXML
    private TableColumn<Realty, String> Material;

    @FXML
    private TableColumn<Realty, Integer> Age;

    @FXML
    private Tab Commission_tab;

    @FXML
    private Button MyCommissions;

    @FXML
    private Button ReturnAllCommissions;

    @FXML
    private Button AddCommission;

    @FXML
    private Button DeleteCommission;

    @FXML
    private Button CorrectCommission;

    @FXML
    private Button MoreCommission;

    @FXML
    private TableView<Commission> viewCommission;

    @FXML
    private TableColumn<Commission, Integer> id_commission;

    @FXML
    private TableColumn<Commission, String> date;

    @FXML
    private TableColumn<Commission, String> time;

    @FXML
    private TableColumn<Commission, Integer> appraiser;

    @FXML
    private TableColumn<Commission, String> object;

    @FXML
    private TableColumn<Commission, String> status;

    @FXML
    private ComboBox<String> searchStatus;

    @FXML
    private Tab PersonalCabinet;

    @FXML
    private Label PersonalSeries;

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
    private Label PersonalEmail;

    @FXML
    private Label PersonalNumber;

    @FXML
    private Button CorrectPersonal;

    @FXML
    private Button DeletePersonal;

    @FXML
    void initialize() {    }
    void initData(Users user)
    {
        client = new Client();
        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(Appraiser_tab, Objects_tab, Commission_tab,PersonalCabinet);
        ObservableList<String> langs = FXCollections.observableArrayList
                ( " ","Брестская", "Витебская", "Гомельская", "Гродненская","Минская","Могилевская");
        SearchState.setItems(langs);
        int minsk=0,br=0,v=0,gom=0,gr=0,m=0;
        ObservableList<String> statuses = FXCollections.observableArrayList(" ","Отправлена","Принята","Завершена","Отклонена");
        searchStatus.setItems(statuses);
        ReturnAllObjects.setVisible(false);
        ReturnAllCommissions.setVisible(false);
        System.out.println("appraiser");
        client.sendMarker("selectUsers");
        client.sendObject(2);
        List<Users> listUser=(List<Users>)client.readObject();
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
        ObservableList<Users> people = FXCollections.observableArrayList(listUser);
        initAppraiser(people);


        Exit.setOnAction(event -> closeW());
        if(Appraiser_tab.isSelected()) {
            appraiserTabIsSelected(user,people,minsk,br,v,gom,gr,m);
        }
        Commission_tab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if (Commission_tab.isSelected())
                {
                    System.out.println("commission_tab");
                    client.sendMarker("selectCommissions");
                    List<Commission> ListCommission = (List<Commission>)client.readObject();
                    ObservableList<Commission> commissions = FXCollections.observableArrayList(ListCommission);
                    initCommission(commissions);
                    commissionTabIsSelected(user, commissions);
                }
            }
        });
        Objects_tab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if(Objects_tab.isSelected()) {
                    System.out.println("realty");
                    client.sendMarker("selectAllRealty");
                    List<Realty> listRealty = (List<Realty>) client.readObject();
                    ObservableList<Realty> realty = FXCollections.observableArrayList(listRealty);
                    initRealty(realty);
                    objectsTabIsSelected(user, realty);
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

    void initAppraiser(ObservableList<Users> people)
    {
        Table_id_user.setCellValueFactory(new PropertyValueFactory<>("idUsers"));
        Table_surname_user.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        Table_name_user.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Table_patronymic_user.setCellValueFactory(new PropertyValueFactory<>("Patronymic"));
        Table_state_user.setCellValueFactory(new PropertyValueFactory<>("State"));
        Table_city_user.setCellValueFactory(new PropertyValueFactory<>("City"));
        Table_address_user.setCellValueFactory(new PropertyValueFactory<>("Address"));
        Table_phone_user.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        TableUsers.setItems(people);
    }

    void initRealty(ObservableList<Realty> realty)
    {
        Registration_No.setCellValueFactory(new PropertyValueFactory<>("Registration_No"));
        TypeRealty.setCellValueFactory(new PropertyValueFactory<>("Type"));
        StateRealty.setCellValueFactory(new PropertyValueFactory<>("State"));
        CityRealty.setCellValueFactory(new PropertyValueFactory<>("City"));
        AddressRealty.setCellValueFactory(new PropertyValueFactory<>("Address"));
        NumberRooms.setCellValueFactory(new PropertyValueFactory<>("Rooms"));
        Square.setCellValueFactory(new PropertyValueFactory<>("SquareFloor"));
        High.setCellValueFactory(new PropertyValueFactory<>("HighCeil"));
        Thick.setCellValueFactory(new PropertyValueFactory<>("ThickWall"));
        Material.setCellValueFactory(new PropertyValueFactory<>("Material"));
        Age.setCellValueFactory(new PropertyValueFactory<>("Age"));
        TableRealty.setItems(realty);
    }

    void initCommission(ObservableList<Commission> commissions)
    {
        id_commission.setCellValueFactory(new PropertyValueFactory<>("idCommission"));
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        time.setCellValueFactory(new PropertyValueFactory<>("Time"));
        appraiser.setCellValueFactory(new PropertyValueFactory<>("id_appraiser"));
        object.setCellValueFactory(new PropertyValueFactory<>("Registration_No"));
        status.setCellValueFactory(new PropertyValueFactory<>("Status"));
        viewCommission.setItems(commissions);

    }

    void appraiserTabIsSelected(Users user,ObservableList<Users> people,int minsk,int br,int v,int gom,int gr,int m)
    {
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
        TableUsers.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                Users use=TableUsers.getSelectionModel().getSelectedItem();
                use.setRole(2);
                Stage stage1 = new Stage();
                try {
                    FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().
                            getResource("view/moreDetailsAppraiser.fxml"));
                    stage1.setScene(new Scene(loader.load(), 560, 300));
                    MoreDetailsAppraiserController controllerDetail=loader.<MoreDetailsAppraiserController>getController();
                    controllerDetail.initData(user,use);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage1.show();
            }
        });
        FilteredList<Users> filteredData = new FilteredList<>(people, p -> true);
        SearchSurname.textProperty().addListener((observable, oldValue, newValue) -> {
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
        SearchState.setOnAction(event ->
        {
            filteredData.setPredicate(Users -> {
                if (SearchState.getValue() == null || SearchState.getValue()==" ") {
                    return true;
                }
                String lowerCaseFilter =SearchState.getValue().toLowerCase();
                if (String.valueOf(Users.getState()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false; // Does not match.
            });
        });
        SortedList<Users> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TableUsers.comparatorProperty());
        TableUsers.setItems(sortedData);

    }

    void commissionTabIsSelected(Users user,ObservableList<Commission> commissions)
    {
        System.out.println("commission");
        FilteredList<Commission> filteredCommission = new FilteredList<>(commissions, p -> true);
        searchStatus.setOnAction(event6 ->
        {
            filteredCommission.setPredicate(Commission -> {
                if (searchStatus.getValue() == null || searchStatus.getValue()==" ") {
                    return true;
                }
                String lowerCaseFilter =searchStatus.getValue().toLowerCase();
                if (String.valueOf(Commission.getStatus()).toLowerCase().contains(lowerCaseFilter)) {
                    System.out.println("commission yes");
                    return true;
                }
                return false; // Does not match.
            });
        });
        AddCommission.setOnAction(event3 ->
        {
            Commission commissionObject=new Commission();
            commissionObject.setStatus("Отправлена");
            Stage stage1=new Stage();
            try {
                FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().
                        getResource("view/addEditCommission.fxml"));
                stage1.setScene(new Scene(loader.load(), 800, 600));
                AddEditCommissionController controllerDetail=loader.< AddEditCommissionController>getController();
                controllerDetail.addCommission(user,commissionObject);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage1.showAndWait();
            viewCommission.refresh();
        });
        final Commission[] comm = new Commission[1];
        comm[0]=new Commission();
        MyCommissions.setOnAction(event ->
        {
            filteredCommission.setPredicate(Commission -> {
                client.sendMarker("findMyRealty");
                client.sendObject(Commission.getRegistration_No());
                int id_own=(int)client.readObject();
                if (user.getIdUsers()==id_own) {
                    return true;
                }
                return false; // Does not match.
            });
            ReturnAllCommissions.setVisible(true);
            MyCommissions.setVisible(false);
        });
        ReturnAllCommissions.setOnAction(event ->
        {
            viewCommission.setItems(commissions);
            ReturnAllCommissions.setVisible(false);
            MyCommissions.setVisible(true);
        });
        SortedList<Commission> sortedCommission = new SortedList<>(filteredCommission);
        sortedCommission.comparatorProperty().bind(viewCommission.comparatorProperty());
        viewCommission.setItems(sortedCommission);
        viewCommission.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                comm[0] =viewCommission.getSelectionModel().getSelectedItem();
            }
        });
        CorrectCommission.setOnAction(event4 ->
        {
            if(comm[0].getIdCommission()==0)
                OpenWindow("Выберите элемент из списка!");
            else {
                client.sendMarker("findMyRealty");
                client.sendObject(comm[0].getRegistration_No());
                int id_own=(int)client.readObject();
                if(id_own!=user.getIdUsers())
                    OpenWindow("Операция над чужой заявкой!");
                else
                    if (comm[0].getStatus().equals("Завершена") || comm[0].getStatus().equals("Принята"))
                        OpenWindow("Невозможно редактировать принятую заявку!");
                    else {
                    Stage stage1 = new Stage();
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().
                                getResource("view/addEditCommission.fxml"));
                        stage1.setScene(new Scene(loader.load(), 800, 600));
                        AddEditCommissionController controllerDetail = loader.<AddEditCommissionController>getController();
                        controllerDetail.updateCommission(user, comm[0]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage1.showAndWait();
                    viewCommission.refresh();
                }
            }
        });
        DeleteCommission.setOnAction(event5 ->
        {
            if(comm[0].getIdCommission()==0)
            {
                OpenWindow("Выберите элемент из списка!");
            }
            else {
                final boolean[] yes = new boolean[1];
                yes[0]=false;
                Stage stage1 = new Stage();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().
                            getResource("view/areYouSure.fxml"));
                    stage1.setScene(new Scene(loader.load(), 440, 150));
                    AreYouSureController controllerDetail = loader.<AreYouSureController>getController();
                    controllerDetail.setText("удалить заявку?", yes);
                    stage1.showAndWait();
                    if (yes[0]) {
                client.sendMarker("findMyRealty");
                client.sendObject(comm[0].getRegistration_No());
                int id_own=(int)client.readObject();
                if(id_own!=user.getIdUsers())
                    OpenWindow("Операция над чужой заявкой!");
                else if ( comm[0].getStatus().equals("Принята"))
                    OpenWindow("Невозможно удалить принятую заявку!");
                     else if (comm[0].getStatus().equals("Завершена"))
                        OpenWindow("Невозможно удалить выполненную заявку!");
                     else {
                        client.sendMarker("deleteCommission");
                        client.sendObject(comm[0].getIdCommission());
                        String msg=(String)client.readObject();
                        if(msg.equals("ok"))
                            {OpenWindow("Операция прошла успешно");
                                viewCommission.refresh();}
                }
            }
            } catch (IOException e) {
            e.printStackTrace(); }
        }
        });
        MoreCommission.setOnAction(event ->
        {
            if(comm[0].getIdCommission()==0)
            {
                OpenWindow("Выберите элемент из списка!");
            }
            else
           { Stage stage1 = new Stage();
            try {
                FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().
                        getResource("view/moreDetailsCommission.fxml"));
                stage1.setScene(new Scene(loader.load(), 800, 600));
                MoreDetailsCommissionController controllerDetail=loader.<MoreDetailsCommissionController>getController();
                controllerDetail.initData(comm[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage1.show();}
        });
    }

    void objectsTabIsSelected(Users user,ObservableList<Realty> realty) {
        System.out.println("realty");
        FilteredList<Realty> filteredDataRealty = new FilteredList<>(realty, p -> true);
        MyObjects.setOnAction(event ->
        {
            filteredDataRealty.setPredicate(Realty -> {
                    if (Realty.getIDOwner() == user.getIdUsers()) {
                        return true;
                    }
                    return false; // Does not match.
                });
                SortedList<Realty> sortedDataRealty = new SortedList<>(filteredDataRealty);
                sortedDataRealty.comparatorProperty().bind(TableRealty.comparatorProperty());
                TableRealty.setItems(sortedDataRealty);
                ReturnAllObjects.setVisible(true);
                MyObjects.setVisible(false);
        });
        ReturnAllObjects.setOnAction(event ->
        {
            TableRealty.setItems(realty);
            ReturnAllObjects.setVisible(false);
            MyObjects.setVisible(true);
        });
        AddObject.setOnAction(event ->
                {
                    Realty realtyObject=new Realty();
                    realtyObject.setIDOwner(user.getIdUsers());
                   Stage stage1=new Stage();
                    try {
                        FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().
                                getResource("view/addEditRealty.fxml"));
                        stage1.setScene(new Scene(loader.load(), 800, 600));
                        AddEditRealtyController controllerDetail=loader.<AddEditRealtyController>getController();
                        controllerDetail.addObject(realtyObject);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage1.showAndWait();
                    client.sendMarker("selectAllRealty");
                    List<Realty> listRealty = (List<Realty>) client.readObject();
                    ObservableList<Realty> realty7 = FXCollections.observableArrayList(listRealty);
                    initRealty(realty7);
                    TableRealty.refresh();
                } );
        final Realty[] realty2 = new Realty[1];
        realty2[0]=new Realty();
        TableRealty.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event)
                {
                    realty2[0] =TableRealty.getSelectionModel().getSelectedItem();
                }
            });
        More.setOnAction(event -> {
            switch (realty2[0].getType())
            {
                case"Квартира":
                {
                    System.out.println(realty2[0].getType());
                    client.sendMarker("findFlat");
                    client.sendObject(realty2[0].getRegistration_No());
                    Flat flat=(Flat) client.readObject();
                    Stage stage1 = new Stage();
                    try {
                        FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().
                                getResource("view/moreDetailsRealty.fxml"));
                        stage1.setScene(new Scene(loader.load(), 800, 600));
                        MoreDetailsRealtyController controllerDetail=loader.<MoreDetailsRealtyController>getController();
                        controllerDetail.initFlat(realty2[0],flat);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage1.show();
                }break;
                case"Дом":
                {
                    System.out.println(realty2[0].getType());
                    client.sendMarker("findHouse");
                    client.sendObject(realty2[0].getRegistration_No());
                    House house=(House) client.readObject();
                    Stage stage1 = new Stage();
                    try {
                        FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().
                                getResource("view/moreDetailsRealty.fxml"));
                        stage1.setScene(new Scene(loader.load(), 800, 600));
                        MoreDetailsRealtyController controllerDetail=loader.<MoreDetailsRealtyController>getController();
                        controllerDetail.initHouse(realty2[0],house);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage1.show();
                }break;
                case"Офис":
                {
                    System.out.println(realty2[0].getType());
                    client.sendMarker("findOffice");
                    client.sendObject(realty2[0].getRegistration_No());
                    Office office=(Office) client.readObject();
                    Stage stage1 = new Stage();
                    try {
                        FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().
                                getResource("view/moreDetailsRealty.fxml"));
                        stage1.setScene(new Scene(loader.load(), 800, 600));
                        MoreDetailsRealtyController controllerDetail=loader.<MoreDetailsRealtyController>getController();
                        controllerDetail.initOffice(realty2[0],office);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage1.show();
                }break;
            }
        });
        CorrectObject.setOnAction(event9 ->
        {
            if(realty2[0].getIDOwner()==0)
            {
                Stage stage1 = new Stage();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().
                            getResource("view/messages.fxml"));
                    stage1.setScene(new Scene(loader.load(), 350, 200));
                    MessagesController controllerDetail = loader.<MessagesController>getController();
                    controllerDetail.setText("Выберите элемент из списка!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage1.showAndWait();
            }
            else {
                Stage stage1 = new Stage();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().
                            getResource("view/addEditRealty.fxml"));
                    stage1.setScene(new Scene(loader.load(), 800, 600));
                    AddEditRealtyController controllerDetail = loader.<AddEditRealtyController>getController();
                    controllerDetail.updateObject(realty2[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage1.show();
                client.sendMarker("selectAllRealty");
                List<Realty> listRealty = (List<Realty>) client.readObject();
                ObservableList<Realty> realty7 = FXCollections.observableArrayList(listRealty);
                initRealty(realty7);
                TableRealty.refresh();
            }
        });
        TableRealty.refresh();
        DeleteObject.setOnAction(event7 ->
        {
            if(realty2[0].getIDOwner()==0)
            {
                OpenWindow("Выберите элемент из списка!");
            }
            else {
                final boolean[] yes = new boolean[1];
                yes[0]=false;
                Stage stage1 = new Stage();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().
                            getResource("view/areYouSure.fxml"));
                    stage1.setScene(new Scene(loader.load(), 440, 150));
                    AreYouSureController controllerDetail = loader.<AreYouSureController>getController();
                    controllerDetail.setText("удалить объект?",yes);
                    stage1.showAndWait();
                    if (yes[0])
                    {
                        client.sendMarker("deleteRealty");
                        client.sendObject(realty2[0].getRegistration_No());
                        client.sendObject(realty2[0].getType());
                        String ms = (String) client.readObject();
                        if (ms.equals("ok")) {
                            client.sendMarker("selectAllRealty");
                            List<Realty> listRealty = (List<Realty>) client.readObject();
                            ObservableList<Realty> realty7 = FXCollections.observableArrayList(listRealty);
                            initRealty(realty7);
                            TableRealty.refresh();
                            OpenWindow("Операция прошла успешно!");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    void PersonalCabinetIsSelected(Users user)
    {
        System.out.println("account");
        client.sendMarker("findCustomer");
        int id=user.getIdUsers();
        client.sendObject(id);
        Customer customer=(Customer)client.readObject();
        PersonalLogin.setText(user.getLogin());
        PersonalPassword.setText(user.getPassword());
        PersonalSurname.setText(user.getSurname());
        PersonalName.setText(user.getName());
        PersonalPatronymic.setText(user.getPatronymic());
        PersonalState.setText(user.getState());
        PersonalCity.setText(user.getCity());
        PersonalAddress.setText(user.getAddress());
        PersonalPhone.setText(user.getPhone());
        PersonalEmail.setText(customer.getEmail());
        PersonalSeries.setText(customer.getPassportSeria());
        PersonalNumber.setText(String.valueOf(customer.getPassportNo()));
        CorrectPersonal.setOnAction(event ->  {
            Stage stage1 = new Stage();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().
                        getResource("view/editPersonal.fxml"));
                stage1.setScene(new Scene(loader.load(), 740, 500));
                EditPersonalController controllerDetail = loader.<EditPersonalController>getController();
                controllerDetail.editCustomer(user, customer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage1.show();
        });
        DeletePersonal.setOnAction(event -> {
            final boolean[] yes = new boolean[1];
            yes[0]=false;
            Stage stage1 = new Stage();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().
                        getResource("view/areYouSure.fxml"));
                stage1.setScene(new Scene(loader.load(), 440, 150));
                AreYouSureController controllerDetail = loader.<AreYouSureController>getController();
                controllerDetail.setText("удалить аккаунт?", yes);
                stage1.showAndWait();
                if (yes[0]) {
                    client.sendMarker("deleteCustomer");
                    client.sendObject(user.getIdUsers());
                    String ms = (String) client.readObject();
                    if (ms.equals("ok")) {
                        closeW();
                        OpenWindow("Операция прошла успешно!");
                    } else {
                        OpenWindow("Что-то пошло не так!");
                    }
                }
            } catch (IOException e) {
                    e.printStackTrace();
                }
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
