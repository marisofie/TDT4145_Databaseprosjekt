package ui;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

public class HovedmenyController implements Initializable{

    public void goToOvelser() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        URL resource = getClass().getResource("Ovelser.fxml");
        loader.setLocation(resource);
        Parent root = loader.load();

        Stage primaryStage = (Stage) ovelserButton.getScene().getWindow();

        primaryStage.setScene(new Scene(root,1280,720));
        primaryStage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            goToOvelser();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

