package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Commission;
import model.client.Client;

public class AssessReportController {
private Client client;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ok;

    @FXML
    private TextArea Result;

    @FXML
    private Label Title;

    @FXML
    void initialize() { }
    void initData(Commission commission)
    {
        client=new Client();
        Stage stage1 = (Stage) ok.getScene().getWindow();
        stage1.initModality(Modality.APPLICATION_MODAL);
        ok.setOnAction(event -> stage1.close());
        Title.setText(String.valueOf(commission.getIdCommission()));
        String FileName="src/server/reports/commission"+commission.getIdCommission()+".txt";
        client.sendMarker("readReport");
        client.sendObject(FileName);
        String msg=(String)client.readObject();
        if(msg.equals("Error"))
            { Stage stage = new Stage();
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().
                                getResource("view/messages.fxml"));
                        stage.setScene(new Scene(loader.load(), 440, 150));
                        MessagesController controllerDetail = loader.<MessagesController>getController();
                        controllerDetail.setText("Отчет не найден!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage.show();
            }
        else
           {
               String str=(String)client.readObject();
               Result.setText(str);
           }

    }
}
