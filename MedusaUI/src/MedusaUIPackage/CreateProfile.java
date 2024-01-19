package MedusaUIPackage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class CreateProfile {
    public static Scene create(Stage stage, String origin){
        //Vars
        List<String> profileList = new ArrayList();
        profileList = Profiles.getProfiles();
        //Initialize all functional entities
        //Initialize all entities
        Label titleLabel = new Label("Medusa UI");
        Label subTitle = new Label("Create Profile");
        Label enterName = new Label("Enter Profile Name");
        Label warning = new Label("Profile already exists!");
        Label lightCreation = new Label("Light Profile Creation");
        Label lightCreationSubTitle = new Label("Select from a preset profile's configuration and start chatting right away!");
        Label deepCreation = new Label("Deep Profile Creation");
        //Make minute count dynamic
        Label deepCreationSubTitle = new Label("Fully customize the profile's configuration and dictionary. Takes around 30 minutes.");
        TextField newProfileName = new TextField();
        ComboBox presetConfigurations = new ComboBox();
        Button goBack = new Button();
        Button startChatting = new Button();
        Button startConfig = new Button();
        Ellipse dividerLine = new Ellipse();
        Ellipse divider1 = new Ellipse();
        Ellipse divider2 = new Ellipse();
        //Title text
        titleLabel.setFont(new Font("Century Gothic", 32));
        titleLabel.setTranslateX(25);
        titleLabel.setTranslateY(20);
        //Page title
        subTitle.setFont(new Font("Century Gothic", 14));
        subTitle.setTranslateX(25);
        subTitle.setTranslateY(60);
        //Enter name
        enterName.setFont(new Font(14));
        enterName.setPrefSize(130, 25);
        enterName.setLayoutX(25);
        enterName.setLayoutY(100);
        //Warning
        warning.setVisible(false);
        warning.setTextFill(Color.web("#FF5959"));
        warning.setPrefSize(180, 20);
        warning.setLayoutX(190);
        warning.setLayoutY(135);
        //Light creation title
        lightCreation.setFont(new Font(16));
        lightCreation.setTextAlignment(TextAlignment.CENTER);
        lightCreation.setPrefSize(160, 30);
        lightCreation.setLayoutX(30);
        lightCreation.setLayoutY(190);
        //Light creation subtitle
        lightCreationSubTitle.setFont(new Font(10));
        lightCreationSubTitle.setTextAlignment(TextAlignment.CENTER);
        lightCreationSubTitle.setWrapText(true);
        lightCreationSubTitle.setPrefSize(150, 55);
        lightCreationSubTitle.setLayoutX(32.5);
        lightCreationSubTitle.setLayoutY(215);
        //Deep creation title
        deepCreation.setFont(new Font(16));
        deepCreation.setTextAlignment(TextAlignment.CENTER);
        deepCreation.setPrefSize(160, 30);
        deepCreation.setLayoutX(240);
        deepCreation.setLayoutY(190);
        //Deep creation subtitle
        deepCreationSubTitle.setFont(new Font(10));
        deepCreationSubTitle.setTextAlignment(TextAlignment.CENTER);
        deepCreationSubTitle.setWrapText(true);
        deepCreationSubTitle.setPrefSize(150, 55);
        deepCreationSubTitle.setLayoutX(240);
        deepCreationSubTitle.setLayoutY(215);
        //TextField
        newProfileName.setPrefSize(155, 25);
        newProfileName.setLayoutX(25);
        newProfileName.setLayoutY(135);
        
        newProfileName.setOnAction(e -> {
            Boolean flag = false;
            if(newProfileName.getCharacters().toString().isEmpty()){
                warning.setVisible(false);
                presetConfigurations.setDisable(true);
                startConfig.setDisable(true);
            }else{
                for(String s : Profiles.profileNames){
                    if(s.equals(newProfileName.getCharacters().toString())){
                        System.out.println("Now testing: " + s);
                        flag = true;
                        break;
                    }
                }
                if(flag == true){
                    warning.setVisible(true);
                    presetConfigurations.setDisable(true);
                    startConfig.setDisable(true);
                } else {
                    warning.setVisible(false);
                    presetConfigurations.setDisable(false);
//                    startConfig.setDisable(false);
                }
            }
            
        });
        //ComboBox
        presetConfigurations.setDisable(true);
        presetConfigurations.setPromptText("Select PRESET Configuration");
        for(String s : profileList){
            presetConfigurations.getItems().add(s);
        }
        presetConfigurations.setPrefSize(195, 25);
        presetConfigurations.setLayoutX(10);
        presetConfigurations.setLayoutY(270);
        presetConfigurations.setOnAction(e -> { 
            if(presetConfigurations.isDisabled() == true || presetConfigurations.getValue() == null)
                startChatting.setDisable(true);
            else
                startChatting.setDisable(false);
        });
        //Buttons
        //Start chatting
        startChatting.setDisable(true);
        startChatting.setText("Start Chatting!");
        startChatting.setPrefSize(120, 25);
        startChatting.setLayoutX(10);
        startChatting.setLayoutY(300);
        startChatting.setOnAction(e -> {
            //Saving data for the program
            ChatSession.setName(newProfileName.getCharacters().toString());
            Profiles.addProfile(newProfileName.getCharacters().toString());
            Profiles.addProfileType("CUSTOM");
            Profiles.addCore(Profiles.setCore());
            Profiles.addTimestamp(Profiles.setTimestamp());
            Profiles.getData();
            stage.setScene(ChatSession.create(stage));
        });
        //Start deep config
        startConfig.setDisable(true);
        startConfig.setText("Start Deep Configuration");
        startConfig.setPrefSize(155, 25);
        startConfig.setLayoutX(240);
        startConfig.setLayoutY(300);
        //Go back
        goBack.setText("Go Back");
        goBack.setStyle("-fx-text-size: 13");
        goBack.setMinSize(70, 20);
        goBack.setLayoutX(350);
        goBack.setLayoutY(100);
        goBack.setOnAction((ActionEvent e) -> {
            switch(origin){
                case "CreateSession":
                    stage.setScene(CreateSessionScene.create(stage));
                    break;
                case "ViewProfiles":
                    stage.setScene(ViewProfiles.create(stage));
                    break;
            }
        });
        //Dividers
        //Horizontal
        divider1.setCenterX(215);
        divider1.setCenterY(180);
        divider1.setRadiusX(215);
        divider1.setRadiusY(1);
        divider1.setFill(Color.web("#D3D3D3"));
        //Vertical
        divider2.setCenterX(215);
        divider2.setCenterY(265);
        divider2.setRadiusX(1);
        divider2.setRadiusY(85);
        divider2.setFill(Color.web("#D3D3D3"));
        //Side Panel
        dividerLine.setCenterX(430);
        dividerLine.setCenterY(200);
        dividerLine.setRadiusX(1);
        dividerLine.setRadiusY(195);
        dividerLine.setFill(Color.web("#D3D3D3"));
        //Version label
        Label versionLabel = new Label("Version V2.04");
        versionLabel.setFont(new Font("Century Gothic", 12));
        versionLabel.setTranslateX(479);
        versionLabel.setTranslateY(300);
        //Groups
        Group titles = new Group(titleLabel, subTitle);
        Group buttons = new Group(goBack);
        Group setupName = new Group(enterName, newProfileName, warning);
        Group layout = new Group(divider1, divider2);
        Group lightProfileCreation = new Group(lightCreation, lightCreationSubTitle, presetConfigurations, startChatting);
        Group deepProfileCreation = new Group(deepCreation, deepCreationSubTitle, startConfig);
        Group sidePane = new Group(dividerLine,  versionLabel);
        //Initializing data
        //Bundling
        Group page = new Group(titles, buttons, setupName, lightProfileCreation, deepProfileCreation, layout, sidePane);
        Scene createScession = new Scene(page, 600, 350);
        return createScession;
    }
}
