package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Appraiser;
import model.Commission;
import model.Users;
import model.client.Client;

public class InfoAppraiserController {
    private Client client;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ok;

    @FXML
    private Label FIO;

    @FXML
    private AnchorPane CommissionsStatistics;

    @FXML
    void initialize() {    }
    void showStatistics(Users user )
    {
        client=new Client();
        int otpr=0,prin=0,otkl=0,zaversh=0;
        String fio="Статистика по оценщику "+user.getSurname()+" "+user.getName()+" "+user.getPatronymic();
        FIO.setText(fio);
        client.sendMarker("selectMyCommissions");
        client.sendObject(user.getIdUsers());
        List<Commission> ListCommission = (List<Commission>)client.readObject();
        for(int i=0;i<ListCommission.size();i++)
        {
            Commission u=ListCommission.get(i);
            String s=u.getStatus();
            switch(s)
            {
                case"Отправлена":otpr++;break;
                case"Принята":prin++;break;
                case"Отклонена":otkl++;break;
                case"Завершена":zaversh++;break;
            }
        }
        Stage stage1 = (Stage)ok.getScene().getWindow();
        stage1.initModality(Modality.APPLICATION_MODAL);
        ok.setOnAction(event -> stage1.close());
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                new PieChart.Data("Отклонена", otkl),
                new PieChart.Data("Отправлена", otpr),
                new PieChart.Data("Завершена", zaversh),
                 new PieChart.Data("Принята", prin));
        PieChart pieChart = new PieChart(pieChartData);
        CommissionsStatistics.getChildren().add(pieChart);

    }

}
