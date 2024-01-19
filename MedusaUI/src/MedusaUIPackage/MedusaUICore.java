package MedusaUIPackage;

import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import javafx.application.Application;  
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.paint.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import static javafx.scene.text.Font.getFontNames;
import javafx.stage.*;

public class MedusaUICore extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        MedusaDBCore.getDBData(1, "", "", 0);
        Profiles.getData();
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        //Configuration and display
        primaryStage.setTitle("MedusaUI");
        primaryStage.setScene(HomeScreen.create(primaryStage));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
