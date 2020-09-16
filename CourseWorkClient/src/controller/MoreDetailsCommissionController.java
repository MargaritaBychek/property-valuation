package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.SVGPath;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Commission;
import model.Realty;
import model.client.Client;

public class MoreDetailsCommissionController {
private Client client;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Title;

    @FXML
    private Label Date;

    @FXML
    private Label Time;

    @FXML
    private Label Number;

    @FXML
    private Label Parameters;

    @FXML
    private Label Material;

    @FXML
    private Label Age;

    @FXML
    private Label Type;

    @FXML
    private Label FIO;

    @FXML
    private Label Owner;

    @FXML
    private Label Status;

    @FXML
    private Button Vote;

    @FXML
    private Button Report;

    @FXML
    private Button OK;

    @FXML
    private SVGPath V5;

    @FXML
    private SVGPath V4;

    @FXML
    private SVGPath V3;

    @FXML
    private SVGPath V2;

    @FXML
    private SVGPath V1;


    @FXML
    void initialize() { }

    void initData(Commission commission1)
    {
        Stage stage1 = (Stage) OK.getScene().getWindow();
        stage1.initModality(Modality.APPLICATION_MODAL);
        OK.setOnAction(event -> stage1.close());
        client=new Client();
        client.sendMarker("moreDetailsCommission");
        client.sendObject(commission1);
        Commission commission=(Commission) client.readObject();
        Realty realty=(Realty)client.readObject();
        String parameters=realty.getSquareFloor()+"/"+realty.getHighCeil()+"/"+realty.getThickWall();
        String fioAppraiser=(String)client.readObject();
        String fioOwner=(String)client.readObject();
        Title.setText(String.valueOf(commission.getIdCommission()));
        FIO.setText(fioAppraiser);
        Owner.setText(fioOwner);
        Date.setText(commission.getDate());
        Time.setText(commission.getTime());
        Number.setText(commission.getRegistration_No());
        Parameters.setText(parameters);
        Type.setText(realty.getType());
        Material.setText(realty.getMaterial());
        Age.setText(String.valueOf(realty.getAge()));
        Status.setText(commission.getStatus());
        if(commission.getStatus().equals("Завершена")&&!commission.isVoted()) {
            int[] V = new int[1];
            V1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    V1.setStyle("-fx-fill: #ff0000;");
                    V2.setStyle("-fx-fill: #0056ff;");
                    V3.setStyle("-fx-fill: #0056ff;");
                    V4.setStyle("-fx-fill: #0056ff;");
                    V5.setStyle("-fx-fill: #0056ff;");
                    V[0] = 1;
                }
            });
            V2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    V1.setStyle("-fx-fill: #ff0000;");
                    V2.setStyle("-fx-fill: #ff0000;");
                    V3.setStyle("-fx-fill: #0056ff;");
                    V4.setStyle("-fx-fill: #0056ff;");
                    V5.setStyle("-fx-fill: #0056ff;");
                    V[0] = 2;
                }
            });
            V3.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    V1.setStyle("-fx-fill: #ff0000;");
                    V2.setStyle("-fx-fill: #ff0000;");
                    V3.setStyle("-fx-fill: #ff0000;");
                    V4.setStyle("-fx-fill: #0056ff;");
                    V5.setStyle("-fx-fill: #0056ff;");

                    V[0] = 3;
                }
            });
            V4.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    V1.setStyle("-fx-fill: #ff0000;");
                    V2.setStyle("-fx-fill: #ff0000;");
                    V3.setStyle("-fx-fill: #ff0000;");
                    V4.setStyle("-fx-fill: #ff0000;");
                    V5.setStyle("-fx-fill: #0056ff;");
                    V[0] = 4;
                }
            });
            V5.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    V1.setStyle("-fx-fill: #ff0000;");
                    V2.setStyle("-fx-fill: #ff0000;");
                    V3.setStyle("-fx-fill: #ff0000;");
                    V4.setStyle("-fx-fill: #ff0000;");
                    V5.setStyle("-fx-fill: #ff0000;");
                    V[0] = 5;
                }
            });
            Report.setOnAction(event ->
            {
                System.out.println("111");
                Stage stage = new Stage();
                try {
                    FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().
                            getResource("view/assessReport.fxml"));
                    stage.setScene(new Scene(loader.load(), 600, 560));
                    AssessReportController controllerDetail=loader.<AssessReportController>getController();
                    controllerDetail.initData(commission);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.show();
            });
            Vote.setOnAction(event ->
            {
                if(V[0]==0)
                {
                    Stage stage = new Stage();
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().
                                getResource("view/messages.fxml"));
                        stage.setScene(new Scene(loader.load(), 350, 200));
                        MessagesController controllerDetail = loader.<MessagesController>getController();
                        controllerDetail.setText("Выберите оценку!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage.show();
                }
                else {
                    client.sendMarker("vote");
                    client.sendObject(commission);
                    client.sendObject(V[0]);
                    client.sendObject(commission.getId_appraiser());
                    String ms = (String) client.readObject();
                    if (ms.equals("ok")) {
                        OpenWindow("Операция прошла успешно!");
                    } else {
                        OpenWindow("Что-то пошло не так!");
                    }
                }
            });
        }
        else if(commission.getStatus().equals("Завершена")&&commission.isVoted())
        {
            Vote.setVisible(false);
            V1.setVisible(false);
            V2.setVisible(false);
            V3.setVisible(false);
            V4.setVisible(false);
            V5.setVisible(false);
            Report.setOnAction(event ->
            {
                System.out.println("111");
                Stage stage = new Stage();
                try {
                    FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().
                            getResource("view/assessReport.fxml"));
                    stage.setScene(new Scene(loader.load(), 600, 560));
                    AssessReportController controllerDetail=loader.<AssessReportController>getController();
                    controllerDetail.initData(commission);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.show();
            });
        }
        else
        {
            Vote.setVisible(false);
            Report.setVisible(false);
            V1.setVisible(false);
            V2.setVisible(false);
            V3.setVisible(false);
            V4.setVisible(false);
            V5.setVisible(false);
        }
    }
    void OpenWindow(String txt)
    {
        Stage stage1 = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().
                    getResource("view/messages.fxml"));
            stage1.setScene(new Scene(loader.load(), 350, 200));
            MessagesController controllerDetail = loader.<MessagesController>getController();
            controllerDetail.setText(txt);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage1.show();
    }
}
