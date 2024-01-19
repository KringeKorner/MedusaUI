package MedusaUIPackage;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.paint.*;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CreateSessionScene {

    public static Scene create(Stage stage) {
        //Vars
        List<String> profileList = new ArrayList();
        ObservableList<String> profilesToLoad = FXCollections.observableArrayList();
        //Initialize functional entities
        //Initialize all local entities
        Label titleLabel = new Label("Medusa UI");
        Label subTitle = new Label("Create Session");
        Label profile = new Label("Profile:");
        Label profileName = new Label();
        Label type = new Label("Type:");
        Label typeOfProfile = new Label();
        Label configuration = new Label("Configuration:");
        Label configurationSet = new Label();
        Label createProfileHint = new Label("Not seeing a profile you like? Create a new profile here!");
        ComboBox selectProfile = new ComboBox();
        ComboBox sessionType = new ComboBox();
        CheckBox skipConfig = new CheckBox("Skip profile configuration");
        Button createConfigurationButton = new Button();
        Button goBack = new Button();
        Button createAProfile = new Button();
        //Title text
        titleLabel.setFont(new Font("Century Gothic", 32));
        titleLabel.setTranslateX(25);
        titleLabel.setTranslateY(20);
        //Page title
        subTitle.setFont(new Font("Century Gothic", 14));
        subTitle.setTranslateX(25);
        subTitle.setTranslateY(60);
        //Profile info
        profile.setFont(new Font(13));
        profile.setTranslateX(25);
        profile.setTranslateY(220);
        profileName.setFont(new Font(13));
        profileName.setTranslateX(75);
        profileName.setTranslateY(220);
        //Group and set name
        Group profileNameLabel = new Group(profile, profileName);
        //Type info
        type.setFont(new Font(13));
        type.setTranslateX(25);
        type.setTranslateY(237);
        typeOfProfile.setFont(new Font(13));
        typeOfProfile.setTranslateX(65);
        typeOfProfile.setTranslateY(237);
        //Group and set type
        Group profileTypeLabel = new Group(type, typeOfProfile);
        //Configuration
        configuration.setFont(new Font(13));
        configuration.setTranslateX(25);
        configuration.setTranslateY(254);
        configurationSet.setFont(new Font(13));
        configurationSet.setTranslateX(110);
        configurationSet.setTranslateY(254);
        //Group and set type
        Group profileConfigurationLabel = new Group(configuration, configurationSet);
        //Comboboxes
        //Type
        sessionType.setPromptText("Select Session Type");
        sessionType.getItems().addAll(
                "PRESET",
                "CUSTOM"
        );
        sessionType.setPrefSize(180, 25);
        sessionType.setLayoutX(25);
        sessionType.setLayoutY(100);
        sessionType.setOnAction(e -> {
            //Old logic
            //New modification
            switch (sessionType.getValue().toString()) {
                case "PRESET":
                    //STAGE 1 : POPULATE PROFILE NAMES
                    //Clear combobox and list
                    selectProfile.getItems().clear();
                    profilesToLoad.clear();
                    //Add only preset names
                    for (int i = 0; i < Profiles.profileTypes.size(); i++) {
                        if (Profiles.profileTypes.get(i).equals("PRESET")) {
                            //Adding data
                            profilesToLoad.add(Profiles.profileNames.get(i));
                        }
                    }
                    //Add in all entities from array
                    selectProfile.getItems().addAll(profilesToLoad);
                    selectProfile.setDisable(false);
                    //STAGE 2 : DETERMINE CHOICE SELECTION
                    configurationSet.setText("PRESET configuration");
                    selectProfile.setDisable(false);
                    break;

                case "CUSTOM":
                    //STAGE 1 : POPULATE PROFILE NAMES
                    //Clear combobox and list
                    selectProfile.getItems().clear();
                    profilesToLoad.clear();
                    //Add only preset names
                    for (int i = 0; i < Profiles.profileTypes.size(); i++) {
                        if (Profiles.profileTypes.get(i).equals("CUSTOM")) {
                            //Adding data
                            profilesToLoad.add(Profiles.profileNames.get(i));
                        }
                    }
                    if (profilesToLoad.size() == 0) {
                        selectProfile.getItems().add("");
                    } else //Add in all entities from array
                    {
                        selectProfile.getItems().addAll(profilesToLoad);
                    }
                    selectProfile.setDisable(false);
                    //STAGE 2 : DETERMINE CHOICE SELECTION
                    configurationSet.setText("Not Set");
                    selectProfile.setDisable(false);
                    break;
            }
        });

        //Profile
        selectProfile.setDisable(true);
        selectProfile.setPromptText("Select Profile");

        selectProfile.setPrefSize(180, 25);
        selectProfile.setLayoutX(25);
        selectProfile.setLayoutY(140);
        //Select profile
        selectProfile.setDisable(true);
        selectProfile.setPromptText("Select Profile Name");
        selectProfile.setPrefSize(180, 25);
        selectProfile.setLayoutX(25);
        selectProfile.setLayoutY(135);
        selectProfile.setOnAction(e -> {
            if (selectProfile.getValue() == null || selectProfile.getValue().toString().isEmpty()) {
                //Nothing I guess?
            } else {
                profileName.setText(selectProfile.getValue().toString());
                if (selectProfile.getValue() != null) {
                    createConfigurationButton.setDisable(false);
                    ChatSession.setName(selectProfile.getValue().toString());
                } else {
                    createConfigurationButton.setDisable(true);
                }
            }
        });
        //Checkbox
        skipConfig.setLayoutX(25);
        skipConfig.setLayoutY(175);
        skipConfig.setOnAction(e -> {
            //Trying to change label of button below
            if (skipConfig.isSelected() == true) {
                createConfigurationButton.setText("Start Session");
                createConfigurationButton.setMinSize(120, 20);
            } else {
                createConfigurationButton.setText("Create Profile Configuration");
                createConfigurationButton.setMinSize(185, 20);
            }
        });
        //Buttons
        //Go back
        goBack.setText("Go Back");
        goBack.setStyle("-fx-text-size: 13");
        goBack.setMinSize(70, 20);
        goBack.setLayoutX(350);
        goBack.setLayoutY(100);
        goBack.setOnAction((ActionEvent e) -> {
            stage.setScene(HomeScreen.create(stage));
        });

        //Create configuration
        createConfigurationButton.setDisable(true);
        createConfigurationButton.setText("Create Profile Configuration");
        createConfigurationButton.setStyle("-fx-text-size: 13");
        createConfigurationButton.setMinSize(185, 20);
        createConfigurationButton.setLayoutX(25);
        createConfigurationButton.setLayoutY(300);
        createConfigurationButton.setOnAction((ActionEvent e) -> {
            if (skipConfig.isSelected() == true) {
                stage.setScene(ChatSession.create(stage));
            } else {
                stage.setScene(ProfileConfiguration.create(stage, "CreateSessionScene", selectProfile.getValue().toString()));
            }
        });

        //Create profile
        createAProfile.setText("Create Profile");
        createAProfile.setStyle("-fx-text-size: 13");
        createAProfile.setMinSize(100, 20);
        createAProfile.setLayoutX(465);
        createAProfile.setLayoutY(100);
        createAProfile.setOnAction((ActionEvent e) -> {
            stage.setScene(CreateProfile.create(stage, "CreateSession"));
        });
        //Side Panel
        Ellipse dividerLine = new Ellipse();
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
        //Create profile hint
        createProfileHint.setFont(new Font(12));
        createProfileHint.setWrapText(true);
        createProfileHint.setTranslateX(455);
        createProfileHint.setTranslateY(20);
        createProfileHint.setMaxSize(120, 200);
        //Groups
        Group titles = new Group(titleLabel, subTitle);
        Group foundInfo = new Group(profileNameLabel, profileTypeLabel, profileConfigurationLabel);
        Group sidePane = new Group(dividerLine, createProfileHint, createAProfile, versionLabel);
        Group comboBoxes = new Group(sessionType, selectProfile);
        Group checkBoxes = new Group(skipConfig);
        Group buttons = new Group(goBack, createConfigurationButton);
        //Bundling
        Group page = new Group(titles, foundInfo, comboBoxes, checkBoxes, buttons, sidePane);
        Scene createScession = new Scene(page, 600, 350);
        return createScession;
    }
}
