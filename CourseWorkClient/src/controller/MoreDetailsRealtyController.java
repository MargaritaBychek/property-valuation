package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Flat;
import model.House;
import model.Office;
import model.Realty;

public class MoreDetailsRealtyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Title;

    @FXML
    private Label Address;

    @FXML
    private CheckBox is1;

    @FXML
    private CheckBox is2;

    @FXML
    private CheckBox is3;

    @FXML
    private CheckBox is4;

    @FXML
    private CheckBox is5;

    @FXML
    private CheckBox is6;

    @FXML
    private Label Square;

    @FXML
    private Label High;

    @FXML
    private Label Thick;

    @FXML
    private Label Material;

    @FXML
    private Label Age;

    @FXML
    private Label Floors;

    @FXML
    private Label SquareLand;

    @FXML
    private Label Floors_metresToroad;

    @FXML
    private Label SL;

    @FXML
    private Label Bus;

    @FXML
    private Label StationBus;

    @FXML
    private Button OK;

    @FXML
    void initialize() {    }

    void initData(Realty realty)
    {
        Stage stage1 = (Stage) OK.getScene().getWindow();
        stage1.initModality(Modality.APPLICATION_MODAL);
        OK.setOnAction(event -> stage1.close());
        Title.setText(realty.getRegistration_No());
        String str=realty.getState()+" обл.,"+realty.getCity()+", "+realty.getAddress();
        Address.setText(str);
        Square.setText(String.valueOf(realty.getSquareFloor()));
        High.setText(String.valueOf(realty.getHighCeil()));
        Thick.setText(String.valueOf(realty.getThickWall()));
        Material.setText(realty.getMaterial());
        Age.setText(String.valueOf(realty.getAge()));
    }

    void initFlat(Realty realty, Flat flat)
    {
        initData(realty);
        Floors.setVisible(false);
        SquareLand.setVisible(false);
        Floors_metresToroad.setVisible(false);
        SL.setVisible(false);
        Bus.setVisible(false);
        StationBus.setVisible(false);
        is1.setText("Первый или последний этаж" );
        is1.setSelected(flat.isLast_FirstFloor());
        is2.setText("Угловая планировка" );
        is2.setSelected(flat.isCorner());
        is3.setText("Евроремонт" );
        is3.setSelected(flat.isRepair());
        is4.setText("Есть балкон или лоджия" );
        is4.setSelected(flat.isLoggia());
        is5.setText("Рядом парк или зеленые насаждения" );
        is5.setSelected(flat.isGreenZone());
        is6.setText("Спальный район" );
        is6.setSelected(flat.isDormitoryArea());
    }

    void initHouse(Realty realty, House house)
    {
        initData(realty);
        is3.setVisible(false);
        is4.setVisible(false);
        is5.setVisible(false);
        is6.setVisible(false);
        Bus.setVisible(false);
        StationBus.setVisible(false);
        Floors_metresToroad.setText(String.valueOf(house.getNumberFloors()));
        SL.setText(String.valueOf(house.getSquareLand()));
        is1.setText("Отопительная система" );
        is1.setSelected(house.isCHS());
        is2.setText("Водопровод" );
        is2.setSelected(house.isAqueduct());
    }
    void initOffice(Realty realty, Office office)
    {
        initData(realty);
        is4.setVisible(false);
        is5.setVisible(false);
        is6.setVisible(false);
        Floors.setText("Расстояние до проезжей части");
        Floors_metresToroad.setText(String.valueOf(office.getMetresToRoad()));
        SquareLand.setText("ст. метро");
        SL.setText(office.getMetroStation());
        StationBus.setText(office.getBusStation());
        is1.setText("Центр города");
        is1.setSelected(office.isCenter());
        is2.setText("Парковка");
        is2.setSelected(office.isParking());
        is3.setText("Лифт в здании");
        is3.setSelected(office.isLoft());
    }
}
