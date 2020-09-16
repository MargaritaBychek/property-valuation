package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InfoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ok;

    @FXML
    private Label FIO;

    @FXML
    private BarChart<String, Number> StatisticsRB;

    @FXML
    private CategoryAxis StateRB;

    @FXML
    private NumberAxis Amount;

    @FXML
    void initialize() {

    }
    void show(int minsk,int br,int v,int gom,int gr,int m)
    {
        Stage stage1 = (Stage)ok.getScene().getWindow();
        stage1.initModality(Modality.APPLICATION_MODAL);
        ok.setOnAction(event -> stage1.close());

        XYChart.Series data = new XYChart.Series();
        data.getData().add(new XYChart.Data("Минская", minsk));
        data.getData().add(new XYChart.Data("Брестская", br));
        data.getData().add(new XYChart.Data("Витебская", v));
        data.getData().add(new XYChart.Data("Гомельская", gom));
        data.getData().add(new XYChart.Data("Гродненская", gr));
        data.getData().add(new XYChart.Data("Могилевская", m));

        StatisticsRB.getData().add(data);
    }
}
