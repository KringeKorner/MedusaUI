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

public class ForkProfile {

    public static List<Integer> cores = new ArrayList();
    public static List<Integer> indeces = new ArrayList();

    public static List getCores(String profile) {
        int i = 0, j = 0, testCore = 0;
        String foundName = "";
        //Getting all branch cores
        for (i = 0; i < Profiles.profileCores.size(); i++) {
            for (j = 0; j < 4; j++) {
                switch (j) {
                    case 0:
                        foundName = Profiles.profileInformation[i][j];
                        break;
                    case 2:
                        testCore = Integer.parseInt(Profiles.profileInformation[i][j]);
                        if (foundName.equals(profile)) {
                            cores.add(testCore);
                            indeces.add(i);
                        }  
                }
            }
        }
        return cores;
    }

    public static Scene create(Stage stage, String profile, String type) {
        //Vars
        //Initialize all functional entities
        //Initialize all entities
        Label titleLabel = new Label("Medusa UI");
        Label subTitle = new Label("Fork Profile");
        Label profileNameTitle = new Label("Profile Name");
        Label coreIdTitle = new Label("Core ID");
        Label lastModifiedTitle = new Label("Last Modified");
        ComboBox selectType = new ComboBox();
        Button goBack = new Button();
        Button createFork = new Button();
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
        selectType.setPromptText("Select Core ID");
        //Populate based on profileName core portion
        getCores(profile);
        selectType.getItems().addAll(cores);
        selectType.setPrefSize(180, 25);
        selectType.setLayoutX(25);
        selectType.setLayoutY(100);
        selectType.setOnAction(e -> {
            if (selectType.getValue() == null || selectType.getValue().toString().isEmpty()) {
                //Do nothing
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
                createFork.setDisable(false);
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
        displayInfo.setLayoutY(140);
        displayInfo.setContent(profileInfo);
        //Buttons
        //Go back
        goBack.setText("Go Back");
        goBack.setStyle("-fx-text-size: 13");
        goBack.setMinSize(70, 20);
        goBack.setLayoutX(350);
        goBack.setLayoutY(100);
        goBack.setOnAction((ActionEvent e) -> {
            cores.clear();
            indeces.clear();
            stage.setScene(ModifyProfile.create(stage));
        });
        //Create Fork
        createFork.setDisable(true);
        createFork.setText("Create Fork");
        createFork.setStyle("-fx-text-size: 13");
        createFork.setMinSize(100, 20);
        createFork.setLayoutX(25);
        createFork.setLayoutY(300);
        createFork.setOnAction((ActionEvent e) -> {
            Profiles.forkProfile(profile, type);
            selectType.valueProperty().set(null);
            selectType.getItems().clear();
            cores.clear();
            indeces.clear();
            stage.setScene(ForkProfile.create(stage, profile, type));
        });
        //Dividers
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
        Group comboBoxes = new Group(selectType);
        Group buttons = new Group(goBack, createFork);
        Group scrollPane = new Group(displayInfo);
        Group sidePane = new Group(dividerLine, versionLabel);
        //Initializing data
        //Bundling
        Group page = new Group(titles, comboBoxes, buttons, scrollPane, sidePane);
        Scene createScession = new Scene(page, 600, 350);
        return createScession;
    }
}
