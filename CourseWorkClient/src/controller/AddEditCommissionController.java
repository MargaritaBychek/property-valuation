package controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import controller.check.Check;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import model.Commission;
import model.Realty;
import model.Users;
import model.client.Client;

import static java.lang.Integer.valueOf;

public class AddEditCommissionController {
    private Client client;
    final int MIN_YEAR=2015;
    final int MAX_YEAR=2020;
    final int MIN_MONTH=1;
    final int MAX_MONTH=12;
    final int MIN_HOUR=0;
    final int MAX_HOUR=23;
    final int MIN_MINUTES=0;
    final int MAX_MINUTE=59;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Title;

    @FXML
    private TextField Number;


    @FXML
    private TextField IDappraiser;

    @FXML
    private TextField Hour;

    @FXML
    private TextField Minute;

    @FXML
    private TableView<Users> TableUsers;

    @FXML
    private TableColumn<Users, Integer> Table_id_appraiser;

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
    private TableView<Realty> TableRealty;

    @FXML
    private TableColumn<Realty, String> NumberRealty;

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
    private Button OK;

    @FXML
    private Button Exit;

    @FXML
    private DatePicker datePicker;


    @FXML
    void initialize() {

    }
    void addCommission(Users user, Commission commission)
    {
        //datePicker.setValue(LocalDate.of(2017, 7, 25));

        Stage stage1 = (Stage) OK.getScene().getWindow();
        stage1.initModality(Modality.APPLICATION_MODAL);
        Exit.setOnAction(event -> stage1.close());
        client = new Client();
        initAppraiser();
        initRealty(user);
        OK.setOnAction(event ->
        {
            addNewCommission(commission);
        });
    }
    void updateCommission(Users user, Commission commission)
    {
        System.out.println(commission.toString());
        Stage stage1 = (Stage) OK.getScene().getWindow();
        stage1.initModality(Modality.APPLICATION_MODAL);
        Title.setText("Рдактирование элемента");
        Exit.setOnAction(event -> stage1.close());
        client = new Client();
        initAppraiser();
        initRealty(user);
        Number.setText(commission.getRegistration_No());
        String date=commission.getDate();
        String[] words=date.split("-");
        int year=Integer.parseInt(words[0]);
        int month=Integer.parseInt(words[1]);
        int day=Integer.parseInt(words[2]);
        datePicker.setValue(LocalDate.of(year, month, day));
        String time=commission.getTime();
        String[] HHMM=time.split(":");
        Hour.setText(HHMM[0]);
        Minute.setText(HHMM[1]);
        IDappraiser.setText(String.valueOf(commission.getId_appraiser()));
        OK.setOnAction(event ->
        {
            updateMyCommission(commission);
        });

    }
    void initAppraiser()
    {
        datePicker.setShowWeekNumbers(false);
        Callback<DatePicker, DateCell> dayCellFactory= this.getDayCellFactory();
        datePicker.setDayCellFactory(dayCellFactory);
        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        datePicker.setConverter(converter);
        datePicker.setPromptText("yyyy-MM-dd");

        client.sendMarker("selectUsers");
        client.sendObject(2);
        List<Users> listUser=(List<Users>)client.readObject();
        ObservableList<Users> people = FXCollections.observableArrayList(listUser);
        Table_id_appraiser.setCellValueFactory(new PropertyValueFactory<>("idUsers"));
        Table_surname_user.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        Table_name_user.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Table_patronymic_user.setCellValueFactory(new PropertyValueFactory<>("Patronymic"));
        Table_state_user.setCellValueFactory(new PropertyValueFactory<>("State"));
        Table_city_user.setCellValueFactory(new PropertyValueFactory<>("City"));
        Table_address_user.setCellValueFactory(new PropertyValueFactory<>("Address"));
        Table_phone_user.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        TableUsers.setItems(people);
        TableUsers.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event) {
                Users use = TableUsers.getSelectionModel().getSelectedItem();
                IDappraiser.setText(String.valueOf(use.getIdUsers()));
            }
        });
    }
    void initRealty(Users user)
    {
        client.sendMarker("selectMyRealty");
        client.sendObject(user.getIdUsers());
        List<Realty> listRealty = (List<Realty>) client.readObject();
        ObservableList<Realty> realty = FXCollections.observableArrayList(listRealty);
        NumberRealty.setCellValueFactory(new PropertyValueFactory<>("Registration_No"));
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
        TableRealty.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
               Realty realty2 =TableRealty.getSelectionModel().getSelectedItem();
               Number.setText(realty2.getRegistration_No());
            }
        });
    }
    void newCommission(Commission commission)
    {
        String date=datePicker.getValue().toString();
        commission.setDate(date);
       // String newDate=Year.getText()+"-"+Month.getText()+"-"+Day.getText();
        String newTime=Hour.getText()+":"+Minute.getText();
        commission.setTime(newTime);
        commission.setId_appraiser(Integer.parseInt(IDappraiser.getText()));
        commission.setRegistration_No(Number.getText());
        commission.setStatus("Отправлена");
    }
    void addNewCommission(Commission commission)
    {
        if(check())
        {
        newCommission(commission);
        System.out.println(commission.toString());
        client.sendMarker("addCommission");
        client.sendObject(commission);
        String ms = (String) client.readObject();
        if (ms.equals("ok")) {
            closeW();
            OpenWindow("Операция прошла успешно!");
        }
    }
        else{
        OpenWindow("Неправильный формат данных!");
    }
    }
    void updateMyCommission(Commission commission) {
        if (check()) {
            newCommission(commission);
            System.out.println(commission.toString());
            client.sendMarker("updateCommission");
            client.sendObject(commission);
            String ms = (String) client.readObject();
            if (ms.equals("ok")) {
                closeW();
                OpenWindow("Операция прошла успешно!");
            }
        }
        else{
            OpenWindow("Неправильный формат данных!");}
    }

    public boolean check()
    {
        int y;
        if(!Check.checkInt(Hour.getText()))
            return false;
        else
        {
            y=Integer.parseInt(Hour.getText());
            if(y<MIN_HOUR||y>MAX_HOUR)
                return false;
        }
        if(!Check.checkInt(Minute.getText()))
            return false;
        else
        {
            y=Integer.parseInt(Minute.getText());
            if(y<MIN_MINUTES||y>MAX_MINUTE)
                return false;
        }
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
    private Callback<DatePicker, DateCell> getDayCellFactory() {
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        LocalDate today = LocalDate.now();
                        setDisable(empty || item.compareTo(today) > 0);
                    }
                };
            }
        };
        return dayCellFactory;
    }
}
