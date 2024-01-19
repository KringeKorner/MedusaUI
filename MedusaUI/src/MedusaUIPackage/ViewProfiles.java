
package MedusaUIPackage;

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

public class ViewProfiles {
    public static Scene create(Stage stage){
        //Vars
        //Initialize all functional entities
        //Initialize all entities
        Label titleLabel = new Label("Medusa UI");
        Label subTitle = new Label("View Profile");
        Label profileNameTitle = new Label("Profile Name");
        Label coreIdTitle = new Label("Core ID");
        Label lastModifiedTitle = new Label("Last Modified");
        Label modifyProfileHint = new Label("Modify an existing profile to your liking here!");
        Label createProfileHint = new Label("Not seeing a profile you like? Create a new profile here!");
        ComboBox selectType = new ComboBox();
        Button goBack = new Button();
        Button createAProfile = new Button();
        Button modifyAProfile = new Button();
        GridPane profileInfo = new GridPane();
        ScrollPane displayInfo = new ScrollPane();
        Ellipse dividerLine = new Ellipse();
        Ellipse horDivider1 = new Ellipse();
        Ellipse horDivider2 = new Ellipse();
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
        selectType.setPromptText("Select Profile Type");
        selectType.getItems().addAll(
            "PRESET",
            "CUSTOM"
        );
        selectType.setPrefSize(180, 25);
        selectType.setLayoutX(25);
        selectType.setLayoutY(100);
        selectType.setOnAction(e -> { 
            switch(selectType.getValue().toString()){
                case "PRESET":
                    //Remove everything except titles
                    while (profileInfo.getChildren().size() > 4) {
                        profileInfo.getChildren().remove(profileInfo.getChildren().size()-1);
                    }
                    //Add only presets
                    for(int i=0;i<Profiles.profileTypes.size();i++){
                        if(Profiles.profileTypes.get(i).equals("PRESET")){
                            //Setting up labels
                            Label name = new Label();
                            name.setPrefSize(120, 20);
                            Label core = new Label();
                            core.setPrefSize(75, 20);
                            Label datetime = new Label();
                            datetime.setPrefSize(200, 20);
                            //Adding data
                            name.setText(Profiles.profileNames.get(i));
                            core.setText(Profiles.profileCores.get(i).toString());
                            datetime.setText(Profiles.profileTimestamps.get(i));
                            profileInfo.addRow(profileInfo.getChildren().size(), name, core, datetime);
                        }
                    }
                    break;
                case "CUSTOM":
                    //Remove everything except titles
                    while (profileInfo.getChildren().size() > 4) {
                        profileInfo.getChildren().remove(profileInfo.getChildren().size()-1);
                    }
                    //Add only presets
                    for(int i=0;i<Profiles.profileTypes.size();i++){
                        if(Profiles.profileTypes.get(i).equals("CUSTOM")){
                            //Setting up labels
                            Label name = new Label();
                            name.setPrefSize(120, 20);
                            Label core = new Label();
                            core.setPrefSize(75, 20);
                            Label datetime = new Label();
                            datetime.setPrefSize(200, 20);
                            //Adding data
                            name.setText(Profiles.profileNames.get(i));
                            core.setText(Profiles.profileCores.get(i).toString());
                            datetime.setText(Profiles.profileTimestamps.get(i));
                            profileInfo.addRow(profileInfo.getChildren().size(), name, core, datetime);
                        }
                    }
                    break;
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
        //Modify profile
        modifyAProfile.setText("Modify Profile");
        modifyAProfile.setStyle("-fx-text-size: 13");
        modifyAProfile.setMinSize(100, 20);
        modifyAProfile.setLayoutX(465);
        modifyAProfile.setLayoutY(100);
        modifyAProfile.setOnAction((ActionEvent e) -> {
            stage.setScene(ModifyProfile.create(stage));
        });
        //Create profile
        createAProfile.setText("Create Profile");
        createAProfile.setStyle("-fx-text-size: 13");
        createAProfile.setMinSize(100, 20);
        createAProfile.setLayoutX(465);
        createAProfile.setLayoutY(230);
        createAProfile.setOnAction((ActionEvent e) -> {
            stage.setScene(CreateProfile.create(stage, "ViewProfiles"));
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
        displayInfo.setPrefSize(395, 200);
        displayInfo.setLayoutX(25);
        displayInfo.setLayoutY(140);
        displayInfo.setContent(profileInfo);
        //Dividers
        //Side Panel
        dividerLine.setCenterX(430);
        dividerLine.setCenterY(200);
        dividerLine.setRadiusX(1);
        dividerLine.setRadiusY(195);
        dividerLine.setFill(Color.web("#D3D3D3"));
        //Horizontal divider 1
        horDivider1.setCenterX(515);
        horDivider1.setCenterY(150);
        horDivider1.setRadiusX(85);
        horDivider1.setRadiusY(1);
        horDivider1.setFill(Color.web("#D3D3D3"));
        //Horizontal divider 2
        horDivider2.setCenterX(515);
        horDivider2.setCenterY(280);
        horDivider2.setRadiusX(85);
        horDivider2.setRadiusY(1);
        horDivider2.setFill(Color.web("#D3D3D3"));
        //Modify profile hint
        modifyProfileHint.setFont(new Font(12));
        modifyProfileHint.setWrapText(true);
        modifyProfileHint.setTranslateX(455);
        modifyProfileHint.setTranslateY(30);
        modifyProfileHint.setMaxSize(120,200);
        //Create profile hint
        createProfileHint.setFont(new Font(12));
        createProfileHint.setWrapText(true);
        createProfileHint.setTranslateX(455);
        createProfileHint.setTranslateY(160);
        createProfileHint.setMaxSize(120,200);
        //Version label
        Label versionLabel = new Label("Version V2.04");
        versionLabel.setFont(new Font("Century Gothic", 12));
        versionLabel.setTranslateX(479);
        versionLabel.setTranslateY(300);
        //Groups
        Group titles = new Group(titleLabel, subTitle);
        Group comboBoxes = new Group(selectType);
        Group buttons = new Group(goBack);
        Group scrollPane = new Group(displayInfo);
        Group sidePane = new Group(dividerLine, horDivider1, horDivider2, modifyProfileHint, modifyAProfile, createProfileHint, createAProfile, versionLabel);
        //Initializing data
        //Bundling
        Group page = new Group(titles, comboBoxes, buttons, scrollPane, sidePane);
        Scene createScession = new Scene(page, 600, 350);
        return createScession;
    }
}
