package MedusaUIPackage;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ModifyProfile {
    public static int foundId = 0;
    public static int getCoreOf(String profile) {
        int testCore = 0, foundCore = 0;
        int i = 0, j = 0;
        String foundName = "";
        //searching for core
        for (i = 0; i < Profiles.profileNames.size(); i++) {
            for (j = 0; j < 4; j++) {
                switch (j) {
                    case 0:
                        foundName = Profiles.profileInformation[i][j];
                        break;
                    case 2:
                        testCore = Integer.parseInt(Profiles.profileInformation[i][j]);
                        if (foundName.equals(profile)) {
                            foundCore = testCore;
                            foundId = i;
                        }  
                }
            }
        }
        return foundCore;
    }

    public static Scene create(Stage stage) {
        //Vars
        ObservableList<String> profilesToLoad = FXCollections.observableArrayList();
        //Initialize all functional entities
        //Initialize all entities
        Label titleLabel = new Label("Medusa UI");
        Label subTitle = new Label("Modify Profile");
        Label modifyConfigSubTitle = new Label("Modify the profile's attributes");
        Label modifyDictSubTitle = new Label("Modify the profile's available dictionary");
        Label forkProfileSubTitle = new Label("Create a branch of a profile, keeps all data before fork");
        Label convertProfileSubTitle = new Label("Converts PRESET profile into CUSTOM");
        Label deleteProfileSubTitle = new Label("Deletes the profile, non recoverable");
        ComboBox selectType = new ComboBox();
        ComboBox selectProfile = new ComboBox();
        Alert confirmDelete = new Alert(AlertType.CONFIRMATION);
        Button goBack = new Button();
        Button modifyConfig = new Button();
        Button modifyDict = new Button();
        Button forkProfile = new Button();
        Button convertProfile = new Button();
        Button deleteProfile = new Button();
        Ellipse dividerLine = new Ellipse();
        //Group and configure
        Group modConfig = new Group(modifyConfigSubTitle, modifyConfig);
        modifyConfig.setDisable(true);
        Group modDict = new Group(modifyDictSubTitle, modifyDict);
        modifyDict.setDisable(true);
        Group forkProf = new Group(forkProfileSubTitle, forkProfile);
        forkProfile.setDisable(true);
        Group convProf = new Group(convertProfileSubTitle, convertProfile);
        convProf.setVisible(false);
        convertProfile.setDisable(true);
        Group delProf = new Group(deleteProfileSubTitle, deleteProfile);
        deleteProfile.setDisable(true);
        //Labels
        //Title text
        titleLabel.setFont(new Font("Century Gothic", 32));
        titleLabel.setTranslateX(25);
        titleLabel.setTranslateY(20);
        //Page title
        subTitle.setFont(new Font("Century Gothic", 14));
        subTitle.setTranslateX(25);
        subTitle.setTranslateY(60);
        //Modify config
        modifyConfigSubTitle.setAlignment(Pos.CENTER_RIGHT);
        modifyConfigSubTitle.setPrefSize(230, 20);
        modifyConfigSubTitle.setLayoutX(190);
        modifyConfigSubTitle.setLayoutY(182.5);
        //Modify dictionary
        modifyDictSubTitle.setAlignment(Pos.CENTER_RIGHT);
        modifyDictSubTitle.setPrefSize(230, 20);
        modifyDictSubTitle.setLayoutX(190);
        modifyDictSubTitle.setLayoutY(222.5);
        //Fork profile
        forkProfileSubTitle.setWrapText(true);
        forkProfileSubTitle.setTextAlignment(TextAlignment.RIGHT);
        forkProfileSubTitle.setPrefSize(230, 40);
        forkProfileSubTitle.setLayoutX(190);
        forkProfileSubTitle.setLayoutY(255);
        //Convert profile
        convertProfileSubTitle.setAlignment(Pos.CENTER_RIGHT);
        convertProfileSubTitle.setPrefSize(230, 20);
        convertProfileSubTitle.setLayoutX(190);
        convertProfileSubTitle.setLayoutY(302.5);
        //Delete profile
        deleteProfileSubTitle.setAlignment(Pos.CENTER_RIGHT);
        deleteProfileSubTitle.setPrefSize(230, 20);
        deleteProfileSubTitle.setLayoutX(190);
        deleteProfileSubTitle.setLayoutY(302.5);
        //ComboBoxes
        //Select type
        selectType.setPromptText("Select Profile Type");
        selectType.getItems().addAll(
                "PRESET",
                "CUSTOM"
        );
        selectType.setPrefSize(180, 25);
        selectType.setLayoutX(25);
        selectType.setLayoutY(100);
        selectType.setOnAction(e -> {
            switch (selectType.getValue().toString()) {
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
                    convProf.setVisible(true);
                    delProf.setVisible(false);
                    modifyConfig.setDisable(true);
                    modifyDict.setDisable(true);
                    forkProfile.setDisable(true);
                    convertProfile.setDisable(true);
                    deleteProfile.setDisable(true);
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
                    convProf.setVisible(false);
                    delProf.setVisible(true);
                    modifyConfig.setDisable(true);
                    modifyDict.setDisable(true);
                    forkProfile.setDisable(true);
                    convertProfile.setDisable(true);
                    deleteProfile.setDisable(true);
                    break;
            }
        });
        //Select profile
        selectProfile.setDisable(true);
        selectProfile.setPromptText("Select Profile Name");
        selectProfile.setPrefSize(180, 25);
        selectProfile.setLayoutX(25);
        selectProfile.setLayoutY(135);
        selectProfile.setOnAction(e -> {
            if (selectProfile.getValue() == null || selectProfile.getValue().toString().isEmpty()) {
                //Nothing I guess?
                modifyConfig.setDisable(true);
                modifyDict.setDisable(true);
                forkProfile.setDisable(true);
                convertProfile.setDisable(true);
                deleteProfile.setDisable(true);
            } else {
                modifyConfig.setDisable(false);
                modifyDict.setDisable(false);
                forkProfile.setDisable(false);
                convertProfile.setDisable(false);
                deleteProfile.setDisable(false);
            }
        });
//        //Alert
//        EventHandler<ActionEvent> event = new
//                         EventHandler<ActionEvent>() {
//            public void handle(ActionEvent e)
//            {
//                confirmDelete.setHeaderText("Delete Profile");
//                confirmDelete.setContentText("Are you sure you wish to delete the selected profile? This action is not recovereable.");
//            }
//        };
        //Buttons
        //Go back
        goBack.setText("Go Back");
        goBack.setStyle("-fx-text-size: 13");
        goBack.setMinSize(70, 20);
        goBack.setLayoutX(350);
        goBack.setLayoutY(100);
        goBack.setOnAction((ActionEvent e) -> {
            stage.setScene(ViewProfiles.create(stage));
        });
        //Modify configuration
        modifyConfig.setText("Modify Configuration");
        modifyConfig.setPrefSize(130, 25);
        modifyConfig.setLayoutX(25);
        modifyConfig.setLayoutY(180);
        modifyConfig.setOnAction(e -> {
            stage.setScene(ProfileConfiguration.create(stage, "ModifyProfile", selectProfile.getValue().toString()));
        });
        //Modify dictionary
        modifyDict.setText("Modify Dictionary");
        modifyDict.setPrefSize(130, 25);
        modifyDict.setLayoutX(25);
        modifyDict.setLayoutY(220);
        modifyDict.setOnAction(e -> {
            stage.setScene(EditDictionary.create(stage, "ModifyProfile", selectProfile.getValue().toString()));
        });
        //Fork profile
        forkProfile.setText("Fork Profile");
        forkProfile.setPrefSize(130, 25);
        forkProfile.setLayoutX(25);
        forkProfile.setLayoutY(260);
        forkProfile.setOnAction(e -> {
            stage.setScene(ForkProfile.create(stage, selectProfile.getValue().toString(), selectType.getValue().toString()));
        });
        //Convert profile
        convertProfile.setText("Convert Profile");
        convertProfile.setPrefSize(130, 25);
        convertProfile.setLayoutX(25);
        convertProfile.setLayoutY(300);
        convertProfile.setOnAction(e -> {
            getCoreOf(selectProfile.getValue().toString());
            Profiles.convertProfile(foundId);
            //Reset stage
            stage.setScene(ModifyProfile.create(stage));
        });
        //Delete profile
        deleteProfile.setText("Delete Profile");
        deleteProfile.setPrefSize(130, 25);
        deleteProfile.setLayoutX(25);
        deleteProfile.setLayoutY(300);
        deleteProfile.setOnAction(e -> {
            String toDelete = selectProfile.getValue().toString();
            String foundName = "", foundType = "";
            int foundIndex = 0;
            int i = 0, j = 0;
            for (i = 0; i < Profiles.profileNames.size(); i++) {
                for (j = 0; j < 4; j++) {
                    switch (j) {
                        case 0:
                            foundName = Profiles.profileInformation[i][j];
                            break;
                        case 1:
                            foundType = Profiles.profileInformation[i][j];
                            if (foundName.equals(toDelete)) {
                                if (foundType.equals("CUSTOM")) {
//                                    System.out.println("Found: " + foundName + ", " + foundType);
                                    foundIndex = i;
                                }
                            }
//                            System.out.println("Found at: " + foundIndex);
                            break;
                    }
                }
            }

            //Deleting
            Profiles.profileNames.remove(foundIndex);
            Profiles.profileTypes.remove(foundIndex);
            Profiles.profileCores.remove(foundIndex);
            Profiles.profileTimestamps.remove(foundIndex);
            Profiles.getData();
            //Resetting display
            stage.setScene(ModifyProfile.create(stage));
        });
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
        Group comboBoxes = new Group(selectType, selectProfile);
        Group options = new Group(modConfig, modDict, forkProf, convProf, delProf);
        Group buttons = new Group(goBack);
        Group sidePane = new Group(dividerLine, versionLabel);
        //Initializing data
        //Bundling
        Group page = new Group(titles, comboBoxes, options, buttons, sidePane);
        Scene createScession = new Scene(page, 600, 350);
        return createScession;
    }
}
