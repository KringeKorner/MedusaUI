package MedusaUIPackage;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class OpenSession {

    public static List<String> availableSessions = new ArrayList();
    public static List<Integer> indeces = new ArrayList();

    public static void getSessions(String type) {
        int i = 0, j = 0;
        String foundProfile = "", foundType = "";
        for (i = 0; i < Profiles.profileCores.size(); i++) {
            for (j = 0; j < 4; j++) {
                switch (j) {
                    case 0:
                        foundProfile = Profiles.profileInformation[i][j];
                        break;
                    case 1:
                        foundType = Profiles.profileInformation[i][j];
                        if (foundType.equals(type)) {
                            availableSessions.add(foundProfile);
                            indeces.add(i);
                        }
                        break;
                }
            }
        }
    }

    public static Scene create(Stage stage) {
        //Vars
        //Initialize all functional entities
        //Initialize all entities
        Label titleLabel = new Label("Medusa UI");
        Label subTitle = new Label("Open Session");
        Label profileNameTitle = new Label("Profile Name");
        Label coreIdTitle = new Label("Core ID");
        Label lastModifiedTitle = new Label("Last Modified");
        Label createProfileHint = new Label("Not seeing a profile you like? Create a new profile here!");
        ComboBox selectType = new ComboBox();
        ComboBox selectSession = new ComboBox();
        Button goBack = new Button();
        Button open = new Button();
        Button createAProfile = new Button();
        GridPane profileInfo = new GridPane();
        ScrollPane displayInfo = new ScrollPane();
        Ellipse dividerLine = new Ellipse();
        //Title text
        titleLabel.setFont(new Font("Century Gothic", 32));
        titleLabel.setTranslateX(25);
        titleLabel.setTranslateY(20);
        //Page title
        subTitle.setFont(new Font("Century Gothic", 14));
        subTitle.setTranslateX(25);
        subTitle.setTranslateY(60);
        //Profile name
        profileNameTitle.setPrefSize(120, 20);
        //Core ID
        coreIdTitle.setPrefSize(75, 20);
        //last modified
        lastModifiedTitle.setPrefSize(200, 20);
        //ComboBox
        //Select type
        selectType.setPromptText("Select Session Type");
        selectType.getItems().addAll(
                "PRESET",
                "CUSTOM"
        );
        selectType.setPrefSize(180, 25);
        selectType.setLayoutX(25);
        selectType.setLayoutY(100);
        selectType.setOnAction(e -> {
            availableSessions.clear();
            indeces.clear();
            getSessions(selectType.getValue().toString());
            if (selectType.getValue() == null || selectType.getValue().toString().isEmpty()) {
                selectSession.setDisable(true);
            } else {
                //Clearing old data
                while (profileInfo.getChildren().size() > 4) {
                    profileInfo.getChildren().remove(profileInfo.getChildren().size() - 1);
                }
                //Adding new entries
                for (int i : indeces) {
                    //Configuring labels
                    Label name = new Label();
                    name.setPrefSize(120, 20);
                    Label core = new Label();
                    core.setPrefSize(75, 20);
                    Label datetime = new Label();
                    datetime.setPrefSize(200, 20);
                    name.setText(Profiles.profileNames.get(i));
                    core.setText(Profiles.profileCores.get(i).toString());
                    datetime.setText(Profiles.profileTimestamps.get(i));
                    profileInfo.addRow(profileInfo.getChildren().size(), name, core, datetime);
                }
                //Clearing old values for select profile
                selectSession.getItems().clear();
                //Setting values for select profile
                if (availableSessions.size() == 0) {
                    selectSession.setDisable(true);
                } else {
                    for (String s : availableSessions) {
                        selectSession.getItems().add(s);
                    }
                    selectSession.setDisable(false);
                }
            }
        });
        //Select session
        selectSession.setDisable(true);
        selectSession.setPromptText("Select existing session");
        //Populate based on type
        selectSession.setPrefSize(180, 25);
        selectSession.setLayoutX(25);
        selectSession.setLayoutY(130);
        selectSession.setOnAction(e -> {
            if (selectSession.getValue() == null || selectSession.getValue().toString().isEmpty()) {
                open.setDisable(true);
            } else {
                open.setDisable(false);
            }
        });
        //GridPane
        profileInfo.setGridLinesVisible(true);
        ColumnConstraints nameColumn = new ColumnConstraints(120);
        ColumnConstraints coreColumn = new ColumnConstraints(75);
        ColumnConstraints datetimeColumn = new ColumnConstraints(200);
        profileInfo.getColumnConstraints().add(nameColumn);
        profileInfo.getColumnConstraints().add(coreColumn);
        profileInfo.getColumnConstraints().add(datetimeColumn);
        profileInfo.addColumn(0, profileNameTitle);
        profileInfo.addColumn(1, coreIdTitle);
        profileInfo.addColumn(2, lastModifiedTitle);
        //ScrollPane
        displayInfo.setPrefSize(395, 150);
        displayInfo.setLayoutX(25);
        displayInfo.setLayoutY(170);
        displayInfo.setContent(profileInfo);
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
        //Open session
        open.setDisable(true);
        open.setText("Open");
        open.setStyle("-fx-text-size: 13");
        open.setMinSize(70, 20);
        open.setLayoutX(350);
        open.setLayoutY(130);
        open.setOnAction((ActionEvent e) -> {
            ChatSession.setName(selectSession.getValue().toString());
            stage.setScene(ChatSession.create(stage));
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
        //Dividers
        //Side Panel
        dividerLine.setCenterX(430);
        dividerLine.setCenterY(200);
        dividerLine.setRadiusX(1);
        dividerLine.setRadiusY(195);
        dividerLine.setFill(Color.web("#D3D3D3"));
        //Create profile hint
        createProfileHint.setFont(new Font(12));
        createProfileHint.setWrapText(true);
        createProfileHint.setTranslateX(455);
        createProfileHint.setTranslateY(20);
        createProfileHint.setMaxSize(120, 200);
        //Version label
        Label versionLabel = new Label("Version V2.04");
        versionLabel.setFont(new Font("Century Gothic", 12));
        versionLabel.setTranslateX(479);
        versionLabel.setTranslateY(300);
        //Groups
        Group titles = new Group(titleLabel, subTitle);
        Group comboBoxes = new Group(selectType, selectSession);
        Group buttons = new Group(goBack, open, createAProfile);
        Group scrollPane = new Group(displayInfo);
        Group sidePane = new Group(dividerLine, createProfileHint, versionLabel);
        //Initializing data
        //Bundling
        Group page = new Group(titles, comboBoxes, buttons, scrollPane, sidePane);
        Scene createScession = new Scene(page, 600, 350);
        return createScession;
    }
}
