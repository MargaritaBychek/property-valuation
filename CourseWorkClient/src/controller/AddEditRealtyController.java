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
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import model.client.Client;

public class AddEditRealtyController {
    private Client client;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Title;

    @FXML
    private TextField Number;

    @FXML
    private TextField Square;

    @FXML
    private TextField Thick;

    @FXML
    private TextField Hight;

    @FXML
    private TextField Rooms;

    @FXML
    private TextField City;

    @FXML
    private TextField Address;

    @FXML
    private ComboBox<String> State;

    @FXML
    private ComboBox<String> Material;

    @FXML
    private TextField Age;

    @FXML
    private Tab tab_House;

    @FXML
    private TextField NumberFloor;

    @FXML
    private TextField SquareLand;

    @FXML
    private CheckBox CHS;

    @FXML
    private CheckBox Water;

    @FXML
    private Tab tab_Flat;

    @FXML
    private CheckBox First_LastFloor;

    @FXML
    private CheckBox GrenZone;

    @FXML
    private CheckBox Logia;

    @FXML
    private CheckBox Repair;

    @FXML
    private CheckBox Corner;

    @FXML
    private CheckBox Dormitory;

    @FXML
    private Tab tab_Office;

    @FXML
    private TextField Metro;

    @FXML
    private TextField Distant;

    @FXML
    private CheckBox Parking;

    @FXML
    private TextField Bus;

    @FXML
    private CheckBox Center;

    @FXML
    private CheckBox Loft;

    @FXML
    private Button OK;

    @FXML
    private Button Exit;

    @FXML
    void initialize() {  }
    void addObject( Realty realty)
    {
        ObservableList<String> langs = FXCollections.observableArrayList
                ( "Брестская", "Витебская", "Гомельская", "Гродненская","Минская","Могилевская");
        State.setItems(langs);
        State.setValue("Минская");
        ObservableList<String> langs1 = FXCollections.observableArrayList
                ( "Кирпич","Панель", "Моноблок", "Бетон", "Дерево");
        Material.setItems(langs1);
        Material.setValue("Панель");
        Stage stage1 = (Stage) OK.getScene().getWindow();
        stage1.initModality(Modality.APPLICATION_MODAL);
        Exit.setOnAction(event -> stage1.close());
        OK.setOnAction(event ->
        {
            if(tab_House.isSelected())
                insertHouse(realty);
            else if(tab_Flat.isSelected())
                insertFlat(realty);
            else if(tab_Office.isSelected())
                insertOffice(realty);
        });
    }
    void updateObject(Realty realty)
    {
        client=new Client();
        String id=realty.getRegistration_No();
        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(tab_House,tab_Flat,tab_Office);
        ObservableList<String> langs = FXCollections.observableArrayList
                ( "Брестская", "Витебская", "Гомельская", "Гродненская","Минская","Могилевская");
        State.setItems(langs);
        ObservableList<String> langs1 = FXCollections.observableArrayList
                ( "Кирпич","Панель", "Моноблок", "Бетон", "Дерево");
        Material.setItems(langs1);
        Stage stage1 = (Stage) OK.getScene().getWindow();
        stage1.initModality(Modality.APPLICATION_MODAL);
        Title.setText("Редактирование элемента");
        Exit.setOnAction(event -> stage1.close());
        Number.setText(realty.getRegistration_No());
        State.setValue(realty.getState());
        City.setText(realty.getCity());
        Address.setText(realty.getAddress());
        Rooms.setText(String.valueOf(realty.getRooms()));
        Square.setText(String.valueOf(realty.getSquareFloor()));
        Hight.setText(String.valueOf(realty.getHighCeil()));
        Thick.setText(String.valueOf(realty.getThickWall()));
        Material.setValue(realty.getMaterial());
        Age.setText(String.valueOf(realty.getAge()));
        String type=realty.getType();
        switch (type)
        {
            case "Квартира":
            {
                client.sendMarker("findFlat");
                client.sendObject(realty.getRegistration_No());
                Flat flat=(Flat)client.readObject();
                System.out.println(flat);
                tabPane.getSelectionModel().select(tab_Flat);
                First_LastFloor.setSelected(flat.isLast_FirstFloor());
                GrenZone.setSelected(flat.isGreenZone());
                Logia.setSelected(flat.isLoggia());
                Repair.setSelected(flat.isRepair());
                Corner.setSelected(flat.isCorner());
                Dormitory.setSelected(flat.isDormitoryArea());
            }break;
            case "Дом":
            {
                client.sendMarker("findHouse");
                client.sendObject(realty.getRegistration_No());
                House house=(House)client.readObject();
                tabPane.getSelectionModel().select(tab_House);
                NumberFloor.setText(String.valueOf(house.getNumberFloors()));
                SquareLand.setText(String.valueOf(house.getSquareLand()));
                CHS.setSelected(house.isCHS());
                Water.setSelected(house.isAqueduct());
            }break;
            case "Офис":
            {
                client.sendMarker("findOffice");
                client.sendObject(realty.getRegistration_No());
                Office office=(Office)client.readObject();
                tabPane.getSelectionModel().select(tab_Office);
                Metro.setText(office.getMetroStation());
                Distant.setText(String.valueOf(office.getMetresToRoad()));
                Parking.setSelected(office.isParking());
                Bus.setText(office.getBusStation());
                Center.setSelected(office.isCenter());
                Loft.setSelected(office.isLoft());
            }break;
        }
        OK.setOnAction(event ->
        {
            if(tab_House.isSelected())
                updateHouse(realty,id);
            else if(tab_Flat.isSelected())
                updateFlat(realty,id);
            else if(tab_Office.isSelected())
                updateOffice(realty,id);
        });
    }

    void insertObject(Realty realty)
    {
        realty.setRegistration_No(Number.getText());
        realty.setState(State.getValue());
        switch (realty.getState())
        {
            case"Брестская":realty.setStateCoefficient(0.9);break;
            case"Витебская":realty.setStateCoefficient(1.35);break;
            case"Гомельская":realty.setStateCoefficient(1.3);break;
            case"Гродненская":realty.setStateCoefficient(1.05);break;
            case"Минская":realty.setStateCoefficient(1.4);break;
            case"Могилевская":realty.setStateCoefficient(1.15);break;
        }
        realty.setCity(City.getText());
        realty.setAddress(Address.getText());
        realty.setRooms(Integer.parseInt(Rooms.getText()));
        realty.setSquareFloor(Double.parseDouble(Square.getText()));
        realty.setHighCeil(Double.parseDouble(Hight.getText()));
        realty.setThickWall(Double.parseDouble(Thick.getText()));
        realty.setAge(Integer.parseInt(Age.getText()));
        realty.setMaterial(Material.getValue());
        switch (realty.getMaterial())
        {
            case"Кирпич":realty.setMaterialCoefficient(1.15);break;
            case"Панель":realty.setMaterialCoefficient(1.4);break;
            case"Моноблок":realty.setMaterialCoefficient(1.6);break;
            case"Бетон":realty.setMaterialCoefficient(1);break;
            case"Дерево":realty.setMaterialCoefficient(0.85);break;
        }
        System.out.println(realty.toString());
    }

    Flat insFlat(Realty realty)
    {
            insertObject(realty);
            realty.setType("Квартира");
            Flat flat = new Flat();
            flat.setRegistration_No(realty.getRegistration_No());
            if (First_LastFloor.isSelected())
                flat.setLast_FirstFloor(true);
            if (Corner.isSelected())
                flat.setCorner(true);
            if (Repair.isSelected())
                flat.setRepair(true);
            if (Logia.isSelected())
                flat.setLoggia(true);
            if (GrenZone.isSelected())
                flat.setGreenZone(true);
            if (Dormitory.isSelected())
                flat.setDormitoryArea(true);
            return flat;
    }
    House insHouse(Realty realty)
    {
        insertObject(realty);
        realty.setType("Дом");
        House house = new House();
        house.setRegistration_No(realty.getRegistration_No());
        house.setNumberFloors(Integer.parseInt(NumberFloor.getText()));
        house.setSquareLand(Double.parseDouble(SquareLand.getText()));
        if (CHS.isSelected())
            house.setCHS(true);
        if (Water.isSelected())
            house.setAqueduct(true);
        return house;
    }
    Office insOffice(Realty realty)
    {
        insertObject(realty);
        realty.setType("Офис");
        Office office = new Office();
        office.setRegistration_No(realty.getRegistration_No());
        office.setMetroStation(Metro.getText());
        office.setBusStation(Bus.getText());
        office.setMetresToRoad(Integer.parseInt(Distant.getText()));
        if (Center.isSelected())
            office.setCenter(true);
        if (Loft.isSelected())
            office.setLoft(true);
        if (Parking.isSelected())
            office.setParking(true);
        return office;
    }

    void insertFlat(Realty realty)
    {
        client=new Client();
        if(checkAll()) {
            Flat flat=insFlat(realty);
            client.sendMarker("addFlat");
            client.sendObject(realty);
            client.sendObject(flat);
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
    void insertHouse(Realty realty)
    {
        client=new Client();
        if (checkAll()&&checkHouse()) {
            House house=insHouse(realty);
            System.out.println(house.toString());
            client.sendMarker("addHouse");
            client.sendObject(realty);
            client.sendObject(house);
            System.out.println("house");
            String ms=(String)client.readObject();
            if(ms.equals("ok"))
            {closeW();
                OpenWindow("Операция прошла успешно!");}
        }
        else{
            OpenWindow("Неправильный формат данных!");
        }
    }

    void insertOffice(Realty realty)
    {
        client=new Client();
        if(checkAll()&&checkOffice()) {
            Office office=insOffice(realty);
            client.sendMarker("addOffice");
            client.sendObject(realty);
            client.sendObject(office);
            String ms=(String)client.readObject();
            if(ms.equals("ok"))
                {closeW();
                OpenWindow("Операция прошла успешно!");}
        }
        else{
            OpenWindow("Неправильный формат данных!");
        }
    }
    void updateFlat(Realty realty,String id)
    {
        client=new Client();
        if(checkAll()) {
            Flat flat=insFlat(realty);
            client.sendMarker("updateFlat");
            client.sendObject(id);
            client.sendObject(realty);
            client.sendObject(flat);
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
    void updateHouse(Realty realty,String id)
    {
        client=new Client();
        if(checkAll()) {
            House house=insHouse(realty);
            client.sendMarker("updateHouse");
            client.sendObject(id);
            client.sendObject(realty);
            client.sendObject(house);
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
    void updateOffice(Realty realty,String id)
    {
        client=new Client();
        if(checkAll()) {
            Office office=insOffice(realty);
            client.sendMarker("updateOffice");
            client.sendObject(id);
            client.sendObject(realty);
            client.sendObject(office);
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
    void closeW()
    {
        Stage stage = (Stage) OK.getScene().getWindow();
        stage.close();
    }

    boolean checkAll()
    {
        if(!Check.checkRegister(Number.getText()))
            return false;
        if(!Check.checkInt(Age.getText()))
            return false;
        if(!Check.checkInt(Rooms.getText()))
            return false;
       if(!Check.checkDouble(Square.getText()))
            return false;
        if(!Check.checkDouble(Hight.getText()))
            return false;
        if(!Check.checkDouble(Thick.getText()))
            return false;
        if(!Check.checkString(City.getText()))
            return false;
         if(!Check.checkAddress(Address.getText()))
            return false;
        return true;
    }
    boolean checkHouse()
    {
        if(!Check.checkDouble(SquareLand.getText()))
            return false;
        if(!Check.checkInt(NumberFloor.getText()))
            return false;
        return true;
    }
    boolean checkOffice()
    {
        if(!Check.checkString(Metro.getText()))
            return false;
        if(!Check.checkString(Bus.getText()))
            return false;
        if(!Check.checkInt(Distant.getText()))
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
        stage1.showAndWait();
    }
}