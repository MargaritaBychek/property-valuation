package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MessagesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label message_text;

    @FXML
    private Button OK;

    @FXML
    void initialize() {    }

    void setText(String txt)
    {
        message_text.setText(txt);
        Stage stage1 = (Stage) OK.getScene().getWindow();
        stage1.initModality(Modality.APPLICATION_MODAL);
        OK.setOnAction(event -> stage1.close());

    }

}

