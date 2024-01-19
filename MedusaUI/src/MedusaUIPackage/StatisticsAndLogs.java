package MedusaUIPackage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class StatisticsAndLogs {

    public static int foundId = 0;
    public static int determinedCore = 0;
    public static int highestTaggedCount = 0;
    public static String highestTaggedWord = "";
    public static String successfulClosedID = "";
    public static String date1 = "";
    public static String date2 = "";
    public static String longestChatSessionID = "";
    public static List<String> gridPaneData = new ArrayList();
    public static List<Integer> gridPaneDataIndeces = new ArrayList();

    public static int getCoreOf(String profile, String type) {
        int testCore = 0, foundCore = 0;
        int i = 0, j = 0;
        String foundName = "", foundType = "";
        //searching for core
        for (i = 0; i < Profiles.profileNames.size(); i++) {
            for (j = 0; j < 4; j++) {
                switch (j) {
                    case 0:
                        foundName = Profiles.profileInformation[i][j];
                        break;
                    case 1:
                        foundType = Profiles.profileInformation[i][j];
                        break;
                    case 2:
                        testCore = Integer.parseInt(Profiles.profileInformation[i][j]);
                        if (foundName.equals(profile) && foundType.equals(type)) {
                            foundCore = testCore;
                            foundId = i;
                        }
                }
            }
        }
        return foundCore;
    }

    public static long getTimeDifference() {
        //Set the times here
//        System.out.println("Received: " + date1 + ", " + date2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time1 = LocalDateTime.parse(date1, formatter);
        LocalDateTime time2 = LocalDateTime.parse(date2, formatter);
        long minutes = java.time.Duration.between(time1, time2).toMinutes();
        return minutes;
    }

    public static Scene create(Stage stage) {
        //Vars
        ObservableList<String> profilesToLoad = FXCollections.observableArrayList();
        //Initialize all functional entities
        //Initialize all entities
        Label titleLabel = new Label("Medusa UI");
        Label subTitle = new Label("Profile Statistics and Logs");
        Label versionLabel = new Label("Version V2.04");
        Label generalData = new Label("General data for \"Profile\"");
        Label highestTagCount = new Label("Highest tags for a word:");
        Label highestTagCountWord = new Label();
        Label highestTagCountAmount = new Label();
        Label lastSuccessfulSession = new Label("Last successfully terminated session:");
        Label sessionSuccessID = new Label("None");
        Label longestChatSession = new Label("Longest chat session:");
        Label longestSession = new Label("None");
        Label longestChatSessionTime = new Label();
        //Generic lables
        Label A = new Label();
        Label B = new Label();
        Label C = new Label();
        Label D = new Label();
        Button goBack = new Button();
        ComboBox selectType = new ComboBox();
        ComboBox selectProfile = new ComboBox();
        ComboBox selectData = new ComboBox();
        GridPane displayData = new GridPane();
        ScrollPane displayView = new ScrollPane();
        Ellipse dividerLine = new Ellipse();
        Ellipse horDivider1 = new Ellipse();
        Ellipse horDivider2 = new Ellipse();
        //Define base screen
        titleLabel.setFont(new Font("Century Gothic", 32));
        titleLabel.setTranslateX(25);
        titleLabel.setTranslateY(20);
        //Page title
        subTitle.setFont(new Font("Century Gothic", 14));
        subTitle.setTranslateX(25);
        subTitle.setTranslateY(60);
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
        //Comboboxes
        //Select type
        selectType.setPromptText("Select Profile Type");
        selectType.getItems().addAll(
                "PRESET",
                "CUSTOM"
        );
        selectType.setPrefSize(140, 25);
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
                        selectProfile.setDisable(true);
                    } else //Add in all entities from array
                    {
                        selectProfile.getItems().addAll(profilesToLoad);
                        selectProfile.setDisable(false);
                    }
                    //STAGE 2 : DETERMINE CHOICE SELECTION
                    break;
            }
        });
        selectType.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            selectProfile.setValue(null);
            selectData.setValue(null);
            displayData.getChildren().clear();

        });
        //Select profile
        selectProfile.setDisable(true);
        selectProfile.setPromptText("Select Profile");
        selectProfile.getItems().addAll();
        selectProfile.setPrefSize(140, 25);
        selectProfile.setLayoutX(185);
        selectProfile.setLayoutY(100);
        selectProfile.setOnAction(e -> {
            if (selectType.getValue() == null || selectProfile.getValue() == null) {
                selectData.setDisable(true);
            } else {
                determinedCore = getCoreOf(selectProfile.getValue().toString(), selectType.getValue().toString());
                selectData.setDisable(false);
                date1 = "";
                date2 = "";
                successfulClosedID = "";
                generalData.setText("General data for " + selectProfile.getValue().toString());
                MedusaDBCore.getDBData(6, "", selectProfile.getValue().toString(), determinedCore);
                MedusaDBCore.getDBData(7, "", selectProfile.getValue().toString(), determinedCore);
                MedusaDBCore.getDBData(8, "", selectProfile.getValue().toString(), determinedCore);
                MedusaDBCore.getDBData(9, "", selectProfile.getValue().toString(), determinedCore);
                MedusaDBCore.getDBData(10, "", selectProfile.getValue().toString(), determinedCore);
                //Setting data texts
                highestTagCountWord.setText(highestTaggedWord + ":");
                highestTagCountAmount.setText(Integer.toString(highestTaggedCount));
                if(successfulClosedID.isEmpty()){
                    sessionSuccessID.setText("None");
                } else {
                    sessionSuccessID.setText(successfulClosedID);
                }
                if (date1.isEmpty() || date2.isEmpty()) {
                    longestSession.setText("None");
                    longestChatSessionTime.setText("0 Minutes");
                } else {

                    longestSession.setText(longestChatSessionID);
                    longestChatSessionTime.setText(getTimeDifference() + " Minutes");
                }
            }
        });
        selectProfile.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            selectData.setValue(null);
            displayData.getChildren().clear();
        });
        //Select data to display
        selectData.setDisable(true);
        selectData.setPromptText("Select Data Type");
        selectData.getItems().addAll(
                "Change log",
                "Chat log"
        );
        selectData.setPrefSize(140, 25);
        selectData.setLayoutX(25);
        selectData.setLayoutY(130);
        selectData.setOnAction(e -> {
            if (selectData.getValue() == null) {
                //Do nothing to handle nothing
            } else {
                switch (selectData.getValue().toString()) {
                    case "Change log":
                        //Clear grid
                        displayData.getChildren().clear();
                        displayData.setGridLinesVisible(false);
                        //Populate titles
                        displayData.setGridLinesVisible(true);
                        A.setText("Change Log ID");
                        A.setPrefSize(100, 30);
                        B.setText("Recorded Change");
                        B.setPrefSize(120, 30);
                        C.setText("Change Type");
                        C.setPrefSize(100, 30);
                        D.setText("Last Updated Time");
                        D.setPrefSize(140, 30);
                        displayData.addColumn(0, A);
                        displayData.addColumn(1, B);
                        displayData.addColumn(2, C);
                        displayData.addColumn(3, D);
                        displayData.setGridLinesVisible(true);
                        //Populate data
                        gridPaneData.clear();
                        gridPaneDataIndeces.clear();
                        MedusaDBCore.getDBData(4, "", selectProfile.getValue().toString(), determinedCore);
                        for (int i = 0; i < gridPaneDataIndeces.size(); i++) {
                            Label changeLogId = new Label();
                            Label recordedChange = new Label();
                            Label changeType = new Label();
                            Label lastUpdated = new Label();
                            switch (gridPaneDataIndeces.get(i)) {
                                case 0:
                                    changeLogId.setText(gridPaneData.get(i));
                                    changeLogId.setPrefSize(100, 30);
                                    displayData.addColumn(gridPaneDataIndeces.get(i), changeLogId);
                                    break;
                                case 1:
                                    recordedChange.setText(gridPaneData.get(i));
                                    recordedChange.setPrefSize(120, 30);
                                    displayData.addColumn(gridPaneDataIndeces.get(i), recordedChange);
                                    break;
                                case 2:
                                    changeType.setText(gridPaneData.get(i));
                                    changeType.setPrefSize(100, 30);
                                    displayData.addColumn(gridPaneDataIndeces.get(i), changeType);
                                    break;
                                case 3:
                                    lastUpdated.setText(gridPaneData.get(i));
                                    lastUpdated.setPrefSize(140, 30);
                                    displayData.addColumn(gridPaneDataIndeces.get(i), lastUpdated);
                                    break;
                            }
                        }
                        break;
                    case "Chat log":
                        //Clear grid
                        displayData.getChildren().clear();
                        displayData.setGridLinesVisible(false);
                        //Populate titles
                        A.setText("Chat Log ID");
                        A.setPrefSize(100, 30);
                        B.setText("Stored Message");
                        B.setMinWidth(100);
                        B.setMaxWidth(160);
                        B.setPrefHeight(30);
                        C.setText("Message Source");
                        C.setPrefSize(100, 30);
                        D.setText("Message Timestamp");
                        D.setPrefSize(140, 30);
                        displayData.addColumn(0, A);
                        displayData.addColumn(1, B);
                        displayData.addColumn(2, C);
                        displayData.addColumn(3, D);
                        displayData.setGridLinesVisible(true);
                        //Populate data
                        gridPaneData.clear();
                        gridPaneDataIndeces.clear();
                        MedusaDBCore.getDBData(5, "", selectProfile.getValue().toString(), determinedCore);
                        for (int i = 0; i < gridPaneDataIndeces.size(); i++) {
                            Label chatLogId = new Label();
                            Label storedMessage = new Label();
                            Label messageSource = new Label();
                            Label messageTimestamp = new Label();
                            switch (gridPaneDataIndeces.get(i)) {
                                case 0:
                                    chatLogId.setText(gridPaneData.get(i));
                                    chatLogId.setPrefSize(100, 30);
                                    displayData.addColumn(gridPaneDataIndeces.get(i), chatLogId);
                                    break;
                                case 1:
                                    storedMessage.setText(gridPaneData.get(i));
                                    storedMessage.setMinWidth(100);
                                    storedMessage.setMaxWidth(160);
                                    storedMessage.setMinHeight(30);
                                    storedMessage.setWrapText(true);
                                    displayData.addColumn(gridPaneDataIndeces.get(i), storedMessage);
                                    break;
                                case 2:
                                    messageSource.setText(gridPaneData.get(i));
                                    messageSource.setPrefSize(100, 30);
                                    displayData.addColumn(gridPaneDataIndeces.get(i), messageSource);
                                    break;
                                case 3:
                                    messageTimestamp.setText(gridPaneData.get(i));
                                    messageTimestamp.setPrefSize(140, 30);
                                    displayData.addColumn(gridPaneDataIndeces.get(i), messageTimestamp);
                                    break;
                            }
                        }
                        break;
                }
            }
        });

        //Scroll pane
        displayView.setPrefSize(395, 170);
        displayView.setLayoutX(25);
        displayView.setLayoutY(170);
        displayView.setContent(displayData);
        //Side Panel
        dividerLine.setCenterX(430);
        dividerLine.setCenterY(200);
        dividerLine.setRadiusX(1);
        dividerLine.setRadiusY(195);
        dividerLine.setFill(Color.web("#D3D3D3"));
        //General data
        generalData.setWrapText(true);
        generalData.setTextAlignment(TextAlignment.CENTER);
        generalData.setContentDisplay(ContentDisplay.BOTTOM);
        generalData.setStyle("-fx-text-size: 14");
        generalData.setPrefSize(150, 60);
        generalData.setLayoutX(440);
        generalData.setLayoutY(0);
        //Highest tag count
        highestTagCount.setWrapText(true);
        highestTagCount.setTextAlignment(TextAlignment.CENTER);
        highestTagCount.setStyle("-fx-text-size: 14");
        highestTagCount.setPrefSize(150, 30);
        highestTagCount.setLayoutX(440);
        highestTagCount.setLayoutY(70);

        highestTagCountWord.setPrefSize(120, 30);
        highestTagCountWord.setLayoutX(440);
        highestTagCountWord.setLayoutY(90);

        highestTagCountAmount.setPrefSize(30, 30);
        highestTagCountAmount.setLayoutX(560);
        highestTagCountAmount.setLayoutY(90);
        //Last successfully closed session
        lastSuccessfulSession.setWrapText(true);
        lastSuccessfulSession.setPrefSize(150, 50);
        lastSuccessfulSession.setLayoutX(440);
        lastSuccessfulSession.setLayoutY(120);

        sessionSuccessID.setPrefSize(150, 30);
        sessionSuccessID.setLayoutX(440);
        sessionSuccessID.setLayoutY(160);
        //Longest chat session
        longestChatSession.setWrapText(true);
        longestChatSession.setPrefSize(150, 50);
        longestChatSession.setLayoutX(440);
        longestChatSession.setLayoutY(190);

        longestSession.setPrefSize(70, 30);
        longestSession.setLayoutX(440);
        longestSession.setLayoutY(220);

        longestChatSessionTime.setPrefSize(80, 30);
        longestChatSessionTime.setLayoutX(510);
        longestChatSessionTime.setLayoutY(220);
        //Horizontal divider 1
        horDivider1.setCenterX(515);
        horDivider1.setCenterY(55);
        horDivider1.setRadiusX(85);
        horDivider1.setRadiusY(1);
        horDivider1.setFill(Color.web("#D3D3D3"));
        //Horizontal divider 2
        horDivider2.setCenterX(515);
        horDivider2.setCenterY(280);
        horDivider2.setRadiusX(85);
        horDivider2.setRadiusY(1);
        horDivider2.setFill(Color.web("#D3D3D3"));
        //Version label
        versionLabel.setFont(new Font("Century Gothic", 12));
        versionLabel.setTranslateX(479);
        versionLabel.setTranslateY(300);
        //Bundling
        Group titles = new Group(titleLabel, subTitle);
        Group sidePanel = new Group(dividerLine, horDivider1, horDivider2, generalData, highestTagCount, highestTagCountWord, highestTagCountAmount, lastSuccessfulSession, sessionSuccessID, longestChatSession, longestSession, longestChatSessionTime, versionLabel);
        Group buttons = new Group(goBack);
        Group scrollPane = new Group(displayView);
        Group comboBoxes = new Group(selectType, selectProfile, selectData);
        Scene homeScreen = new Scene(new Group(titles, buttons, scrollPane, comboBoxes, sidePanel), 600, 350);
        return homeScreen;
    }
}
