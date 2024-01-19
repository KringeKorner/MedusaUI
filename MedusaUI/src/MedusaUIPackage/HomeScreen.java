package MedusaUIPackage;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.paint.*;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.*;

public class HomeScreen {
    public static Scene create(Stage stage){
        //Initialize all entities
        Label titleLabel = new Label("Medusa UI");
        Label versionLabel = new Label("Version V2.04");
        Label versionFeatures = new Label("V2.04 features a new analytics feature for users, where they can view various statistics regarding their profile's histories including the chat and change logs. Additional data provided is in the form of word with the most tags, the last successfully closed session, and the longest chat session for a profile. A small change was also made to the config section of profiles, where the data is now compared against the previously entered value for a modification. Unfortunately, no data saving was completed, so all of the changes are still only local!");
        Button createSession = new Button();
        Button openSession = new Button();
        Button viewProfiles = new Button();
        Button exitProgram = new Button();
        Button dataLogs = new Button();
        VBox versionInfoContent = new VBox();
        ScrollPane versionInfo = new ScrollPane();
        Ellipse dividerLine = new Ellipse();
        //Define base screen
        titleLabel.setFont(new Font("Century Gothic", 32));
        titleLabel.setTranslateX(25);
        titleLabel.setTranslateY(20);
        //Buttons
        //Create session
        createSession.setText("Create Session");
        createSession.setStyle("-fx-text-size: 13");
        createSession.setMinSize(120, 30);
        createSession.setLayoutX(25);
        createSession.setLayoutY(100);
        createSession.setOnAction((ActionEvent e) -> {
            stage.setScene(CreateSessionScene.create(stage));
        });
        //Open session
        openSession.setText("Open Session");
        openSession.setStyle("-fx-text-size: 13");
        openSession.setMinSize(120, 30);
        openSession.setLayoutX(25);
        openSession.setLayoutY(150);
        openSession.setOnAction((ActionEvent e) -> {
            stage.setScene(OpenSession.create(stage));
        });
        //View profiles
        viewProfiles.setText("View Profiles");
        viewProfiles.setStyle("-fx-text-size: 13");
        viewProfiles.setMinSize(120, 30);
        viewProfiles.setLayoutX(25);
        viewProfiles.setLayoutY(200);
        viewProfiles.setOnAction((ActionEvent e) -> {
            stage.setScene(ViewProfiles.create(stage));
        });
        //data logs
        dataLogs.setText("Statistics and Logs");
        dataLogs.setStyle("-fx-text-size: 13");
        dataLogs.setMinSize(120, 30);
        dataLogs.setLayoutX(25);
        dataLogs.setLayoutY(250);
        dataLogs.setOnAction((ActionEvent e) -> {
            stage.setScene(StatisticsAndLogs.create(stage));
        });
        //Exit program
        exitProgram.setText("Exit Medusa UI");
        exitProgram.setStyle("-fx-text-size: 13");
        exitProgram.setMinSize(120, 30);
        exitProgram.setLayoutX(25);
        exitProgram.setLayoutY(300);
        exitProgram.setOnAction((ActionEvent e) -> {
            Platform.exit();
        });
        //Side Panel
        dividerLine.setCenterX(430);
        dividerLine.setCenterY(200);
        dividerLine.setRadiusX(1);
        dividerLine.setRadiusY(195);
        dividerLine.setFill(Color.web("#D3D3D3"));
        //VBoxes
        versionInfoContent.setMinHeight(250);
        versionInfoContent.setPadding(new Insets(10));
        versionInfoContent.setSpacing(5);
        versionInfoContent.getChildren().add(versionFeatures);
        //Scroll pane
        versionInfo.setPrefSize(150, 260);
        versionInfo.setLayoutX(445);
        versionInfo.setLayoutY(70);
        versionInfo.setContent(versionInfoContent);
        //Version label
        versionLabel.setFont(new Font("Century Gothic", 12));
        versionLabel.setTranslateX(479);
        versionLabel.setTranslateY(30);
        //Version text
        versionFeatures.setFont(new Font("Century Gothic", 12));
        versionFeatures.setWrapText(true);
        versionFeatures.setMaxWidth(110);
        versionFeatures.setMinHeight(200);
        Group versionLabels = new Group(versionLabel, versionInfo);
        Group buttons = new Group(createSession, openSession, viewProfiles, dataLogs, exitProgram);
        Group root = new Group(titleLabel, buttons, dividerLine, versionLabels);
        Scene homeScreen = new Scene(new Group(versionLabels, buttons, root), 600, 350);
        return homeScreen;
    }
}
