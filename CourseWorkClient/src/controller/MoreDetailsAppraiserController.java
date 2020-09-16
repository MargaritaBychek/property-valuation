package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Appraiser;
import model.Customer;
import model.Users;
import model.client.Client;

public class MoreDetailsAppraiserController {
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
    private Label state;

    @FXML
    private Label city;

    @FXML
    private Label address;

    @FXML
    private Label phone;

    @FXML
    private Label licence;

    @FXML
    private Label PopularityPassport;

    @FXML
    private Label EmailLicence;

    @FXML
    private Label PassportLevel;

    @FXML
    private Label Series;

    @FXML
    private Button Statistics;

    @FXML
    void initialize() {

    }
    void initData(Users user,Users use)
    {
        Stage stage1 = (Stage) ok.getScene().getWindow();
        stage1.initModality(Modality.APPLICATION_MODAL);
        ok.setOnAction(event -> stage1.close());
       String str= use.getSurname()+" "+use.getName()+" "+use.getPatronymic();
        FIO.setText(str);
        state.setText(use.getState());
        city.setText(use.getCity());
        address.setText(use.getAddress());
        phone.setText(use.getPhone());
        System.out.println(use.toString());
        int role=use.getRole();
        switch (role)
        {
            case 1:{
                Statistics.setVisible(false);
                client = new Client();
                client.sendMarker("findCustomer");
                client.sendObject(use.getIdUsers());
                Customer customer=(Customer)client.readObject();
                EmailLicence.setText("Email");
                licence.setText(customer.getEmail());
                PassportLevel.setText("Серия и № пасспорта");
                Series.setText(customer.getPassportSeria());
                PopularityPassport.setText(String.valueOf(customer.getPassportNo()));
            }break;
            case 2:{
                client = new Client();
                client.sendMarker("findAppraiser");
                client.sendObject(use.getIdUsers());
                Appraiser appraiser=(Appraiser)client.readObject();
                licence.setText(appraiser.getNo_Licence());
                Series.setText(String.valueOf(appraiser.getPopularity()));
                PopularityPassport.setVisible(false);
                System.out.println(appraiser.toString());
                Statistics.setOnAction(event -> {
                    Stage stage2 = new Stage();
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().
                                getResource("view/infoAppraiser.fxml"));
                        stage2.setScene(new Scene(loader.load(), 600, 560));
                        InfoAppraiserController controlDetail = loader.<InfoAppraiserController>getController();
                        controlDetail.showStatistics(use);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage2.show();
                });
            }break;
            case 0:{
                Statistics.setVisible(false);
                EmailLicence.setVisible(false);
                PassportLevel.setVisible(false);
                Series.setVisible(false);
                PopularityPassport.setVisible(false);
                licence.setVisible(false);
            }break;
        }


    }
}
