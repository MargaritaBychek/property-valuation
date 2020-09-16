package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AreYouSureController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button OK;

    @FXML
    private Button Exit;

    @FXML
    private Label message_text;

    @FXML
    void initialize() {    }

    void setText(String txt,boolean []yes)
    {
        message_text.setText(txt);
        Stage stage1 = (Stage) OK.getScene().getWindow();
        stage1.initModality(Modality.APPLICATION_MODAL);

        Exit.setOnAction(event ->
        {
            yes[0] =false;
            closeW();
        });
        OK.setOnAction(event ->
        {
            yes[0]=true;
            closeW();
    });
    }
    void closeW()
    {
        Stage stage1 = (Stage) OK.getScene().getWindow();
        stage1.close();
    }
}
