package MedusaUIPackage;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.paint.*;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.*;

public class ProfileConfiguration {

    public static double oldHappiness = 0.0;
    public static double oldSadness = 0.0;
    public static double oldInsignificance = 0.0;
    public static double oldAnger = 0.0;
    public static double oldCuriosity = 0.0;
    public static double oldDistaste = 0.0;
    public static double oldSpontinaety = 0.0;
    public static double oldSarcasm = 0.0;
    public static List<Double> oldValues = new ArrayList();
    public static String oldText = "";

    public static String updatedValue(TextField inputField, Button saveConfiguration) {
        //Vars
        String updatedVal = "";
        //Validation
        if ((!"0123456789.".contains(inputField.getCharacters().toString())) && ((Double.parseDouble(inputField.getCharacters().toString())) <= 0 || (Double.parseDouble(inputField.getCharacters().toString()) > 1))) {
            saveConfiguration.setDisable(true);
        } else {
            updatedVal = inputField.getCharacters().toString();
            saveConfiguration.setDisable(false);
        }
        return updatedVal;
    }

    public static String updatedSample(Label modifiedLabel, TextField modField, int position) {
        //Vars
        String updatedSample = "";
        String fieldVal = "";
        String modifiedType = "";
        String[] splitAt;
        Double multiplier = 0.0;
        int comparator = 0;
        //Get label
        fieldVal = modifiedLabel.getText();
        splitAt = fieldVal.split(":");
        modifiedType = splitAt[0];
        //Get value
        multiplier = Double.parseDouble(modField.getText());
//        System.out.println("Inputted value: " + multiplier + ", Old value: " + oldValue);
        comparator = Double.compare(multiplier, oldValues.get(position));
//        System.out.println("Comparator value for " + multiplier + ", " + oldValues.get(position) + ": " + comparator);
        switch (modifiedType) {
            case "Happiness":
                if (comparator > 0) {
                    updatedSample = "Chatter: Hello!\nChatter: How are you!";
                    oldText = "Chatter: Hello.\nChatter: How are you?";
                } else if (comparator <= 0) {
                    updatedSample = "Chatter: Hello.\nChatter: How are you?";
                    oldText = "Chatter: Hello!\nChatter: How are you!";
                }
                break;
            case "Sadness":
                if (comparator > 0) {
                    updatedSample = "Chatter: Hello.\nChatter: How are you?";
                    oldText = "Chatter: Hello!\nChatter: How are you!";
                } else if (comparator <= 0) {
                    updatedSample = "Chatter: Hello!\nChatter: How are you!";
                    oldText = "Chatter: Hello.\nChatter: How are you?";
                }
                break;
            case "Insignificance":
                if (comparator > 0) {
                    updatedSample = "Chatter: Hi, I guess.";
                    oldText = "Chatter: Hello.\nChatter: How are you?";
                } else if (comparator <= 0) {
                    updatedSample = "Chatter: Hello.\nChatter: How are you?";
                    oldText = "Chatter: Hi, I guess.";
                }
                break;
            case "Anger":
                if (comparator > 0) {
                    updatedSample = "Chatter: What do you want?";
                    oldText = "Chatter: Hello,\nChatter: How are you?";
                } else if (comparator <= 0) {
                    updatedSample = "Chatter: Hello,\nChatter: How are you?";
                    oldText = "Chatter: What do you want?";
                }
                break;
            case "Curiosity":
                if (comparator > 0) {
                    updatedSample = "Chatter: Hey, anything new happen?";
                    oldText = "Chatter: Hey, anything new happen?";
                } else if (comparator <= 0) {
                    updatedSample = "Chatter: Hi!";
                    oldText = "Chatter: Hi!";
                }
                break;
            case "Distaste":
                if (comparator > 0) {
                    updatedSample = "Chatter: Don't bother me.";
                    oldText = "Chatter: Hello.";
                } else if (comparator <= 0) {
                    updatedSample = "Chatter: Hello.";
                    oldText = "Chatter: Don't bother me.";
                }
                break;
            case "Spontinaety":
                if (comparator > 0) {
                    updatedSample = "Chatter: I THINK I MIGHT EXPLODE!\nChatter: I FOUND CANNED BEANS!";
                    oldText = "Chatter: I THINK I MIGHT EXPLODE!\nChatter: I FOUND CANNED BEANS!";
                } else if (comparator <= 0) {
                    updatedSample = "Chatter: Hello, hello.";
                    oldText = "Chatter: Hello, hello.";
                }
                break;
            case "Sarcasm":
                if (comparator > 0) {
                    updatedSample = "Chatter: That's so cool\nChatter: anyways";
                    oldText = "Chatter: That's actually kind of cool";
                } else if (comparator <= 0) {
                    updatedSample = "Chatter: That's actually kind of cool";
                    oldText = "Chatter: That's so cool\nChatter: anyways";
                }
                break;
        }
        oldValues.set(position, multiplier);
//        System.out.println("Updated " + position + " to " + oldValues.get(position));
        return updatedSample;
    }

    public static void getOldValue(int position) {
        for (int i = 0; i < 8; i++) {
            oldValues.set(i, ConfigFunctionality.configList.get(i));
        }
    }

    public static Scene create(Stage stage, String origin, String profileName) {
        //Vars
        int hapPos = 0;
        int sadPos = 1;
        int insPos = 2;
        int angPos = 3;
        int curPos = 4;
        int disPos = 5;
        int spoPos = 6;
        int sarPos = 7;
        //Initialize all functional entities
        ConfigFunctionality.getConfig(profileName);
        oldValues.add(oldHappiness);
        oldValues.add(oldSadness);
        oldValues.add(oldInsignificance);
        oldValues.add(oldAnger);
        oldValues.add(oldDistaste);
        oldValues.add(oldCuriosity);
        oldValues.add(oldSpontinaety);
        oldValues.add(oldSarcasm);
        for (int i = 0; i < 8; i++) {
            getOldValue(i);
        }
        //Initialize all local entities
        Label titleLabel = new Label("Medusa UI");
        Label subTitle = new Label("Profile Configuration");
        Label oldSubTitle = new Label("Before: ");
        Label newSubTitle = new Label("After: ");
        Label oldVal = new Label();
        Label newVal = new Label();
        Label sampleOld = new Label();
        Label sampleNew = new Label();
        Rectangle modifyBox = new Rectangle();
        ScrollPane modifyConfig = new ScrollPane();
        Button saveConfiguration = new Button();
        Button cancelConfiguration = new Button();
        //Title text
        titleLabel.setFont(new Font("Century Gothic", 32));
        titleLabel.setTranslateX(25);
        titleLabel.setTranslateY(20);
        //Page title
        subTitle.setFont(new Font("Century Gothic", 14));
        subTitle.setTranslateX(25);
        subTitle.setTranslateY(60);
        //ScrollPane content
        //Happiness
        Label hapLabel = new Label("Happiness:");
        hapLabel.setFont(new Font(14));
        TextField hapField = new TextField(ConfigFunctionality.configList.get(0).toString());
        hapField.setOnAction(e -> {
            oldVal.setText(Double.toString(oldValues.get(hapPos)));
            newVal.setText(updatedValue(hapField, saveConfiguration));
            sampleNew.setText(updatedSample(hapLabel, hapField, hapPos));
            sampleOld.setText(oldText);
        });
        Group happiness = new Group(hapLabel, hapField);
        hapLabel.setPrefSize(80, 30);
        hapField.setPrefSize(30, 20);
        hapField.setTranslateX(110);
        //Sadness
        Label sadLabel = new Label("Sadness:");
        sadLabel.setFont(new Font(14));
        TextField sadField = new TextField(ConfigFunctionality.configList.get(1).toString());
        sadField.setOnAction(e -> {
            oldVal.setText(Double.toString(oldValues.get(sadPos)));
            newVal.setText(updatedValue(sadField, saveConfiguration));
            sampleNew.setText(updatedSample(sadLabel, sadField, sadPos));
            sampleOld.setText(oldText);
        });
        Group sadness = new Group(sadLabel, sadField);
        sadLabel.setPrefSize(80, 30);
        sadField.setPrefSize(30, 20);
        sadField.setTranslateX(110);
        //Insignificance
        Label insLabel = new Label("Insignificance:");
        insLabel.setFont(new Font(14));
        TextField insField = new TextField(ConfigFunctionality.configList.get(2).toString());
        insField.setOnAction(e -> {
            oldVal.setText(Double.toString(oldValues.get(insPos)));
            newVal.setText(updatedValue(insField, saveConfiguration));
            sampleNew.setText(updatedSample(insLabel, insField, insPos));
            sampleOld.setText(oldText);
        });
        Group insignificance = new Group(insLabel, insField);
        insLabel.setPrefSize(95, 30);
        insField.setPrefSize(30, 20);
        insField.setTranslateX(110);
        //Anger
        Label angLabel = new Label("Anger");
        angLabel.setFont(new Font(14));
        TextField angField = new TextField(ConfigFunctionality.configList.get(3).toString());
        angField.setOnAction(e -> {
            oldVal.setText(Double.toString(oldValues.get(angPos)));
            newVal.setText(updatedValue(angField, saveConfiguration));
            sampleNew.setText(updatedSample(angLabel, angField, angPos));
            sampleOld.setText(oldText);
        });
        Group anger = new Group(angLabel, angField);
        angLabel.setPrefSize(80, 30);
        angField.setPrefSize(30, 20);
        angField.setTranslateX(110);
        //Curiosity
        Label curLabel = new Label("Curiosity:");
        curLabel.setFont(new Font(14));
        TextField curField = new TextField(ConfigFunctionality.configList.get(4).toString());
        curField.setOnAction(e -> {
            oldVal.setText(Double.toString(oldValues.get(curPos)));
            newVal.setText(updatedValue(curField, saveConfiguration));
            sampleNew.setText(updatedSample(curLabel, curField, curPos));
            sampleOld.setText(oldText);
        });
        Group curiosity = new Group(curLabel, curField);
        curLabel.setPrefSize(80, 30);
        curField.setPrefSize(30, 20);
        curField.setTranslateX(110);
        //Distaste
        Label disLabel = new Label("Distaste:");
        disLabel.setFont(new Font(14));
        TextField disField = new TextField(ConfigFunctionality.configList.get(5).toString());
        disField.setOnAction(e -> {
            oldVal.setText(Double.toString(oldValues.get(disPos)));
            newVal.setText(updatedValue(disField, saveConfiguration));
            sampleNew.setText(updatedSample(disLabel, disField, disPos));
            sampleOld.setText(oldText);
        });
        Group distaste = new Group(disLabel, disField);
        disLabel.setPrefSize(80, 30);
        disField.setPrefSize(30, 20);
        disField.setTranslateX(110);
        //Spontinaety
        Label spoLabel = new Label("Spontinaety:");
        spoLabel.setFont(new Font(14));
        TextField spoField = new TextField(ConfigFunctionality.configList.get(6).toString());
        spoField.setOnAction(e -> {
            oldVal.setText(Double.toString(oldValues.get(spoPos)));
            newVal.setText(updatedValue(spoField, saveConfiguration));
            sampleNew.setText(updatedSample(spoLabel, spoField, spoPos));
            sampleOld.setText(oldText);
        });
        Group spontinaety = new Group(spoLabel, spoField);
        spoLabel.setPrefSize(80, 30);
        spoField.setPrefSize(30, 20);
        spoField.setTranslateX(110);
        //Sarcasm
        Label sarLabel = new Label("Sarcasm:");
        sarLabel.setFont(new Font(14));
        TextField sarField = new TextField(ConfigFunctionality.configList.get(7).toString());
        sarField.setOnAction(e -> {
            oldVal.setText(Double.toString(oldValues.get(sarPos)));
            newVal.setText(updatedValue(sarField, saveConfiguration));
            sampleNew.setText(updatedSample(sarLabel, sarField, sarPos));
            sampleOld.setText(oldText);
        });
        Group sarcasm = new Group(sarLabel, sarField);
        sarLabel.setPrefSize(80, 30);
        sarField.setPrefSize(30, 20);
        sarField.setTranslateX(110);
        VBox modifiables = new VBox();
        modifiables.setPadding(new Insets(10, 0, 10, 10));
        modifiables.setSpacing(10);
        modifiables.getChildren().add(happiness);
        modifiables.getChildren().add(sadness);
        modifiables.getChildren().add(insignificance);
        modifiables.getChildren().add(anger);
        modifiables.getChildren().add(curiosity);
        modifiables.getChildren().add(distaste);
        modifiables.getChildren().add(spontinaety);
        modifiables.getChildren().add(sarcasm);
        //ScrollPane
        modifyConfig.setPrefSize(385, 250);
        modifyConfig.setLayoutX(25);
        modifyConfig.setLayoutY(90);
        modifyConfig.setContent(modifiables);
        //Info rectangle
        modifyBox.setFill(Color.WHITE);
        modifyBox.setStroke(Color.BLACK);
        modifyBox.setStrokeWidth(0.3);
        modifyBox.setHeight(249);
        modifyBox.setWidth(205);
        modifyBox.setLayoutX(190);
        modifyBox.setLayoutY(90.5);
        oldSubTitle.setFont(new Font(14));
        //Old value
        oldSubTitle.setPrefSize(80, 30);
        oldSubTitle.setLayoutX(210);
        oldSubTitle.setLayoutY(100);
        oldVal.setUnderline(true);
        oldVal.setTextFill(Color.STEELBLUE);
        oldVal.setPrefSize(80, 30);
        oldVal.setLayoutX(270);
        oldVal.setLayoutY(100);
        sampleOld.setContentDisplay(ContentDisplay.TOP);
        sampleOld.setFont(new Font(14));
        sampleOld.setPrefWidth(180);
        sampleOld.setMinHeight(80);
        sampleOld.setWrapText(true);
        sampleOld.setLayoutX(210);
        sampleOld.setLayoutY(125);
        //New value
        newSubTitle.setFont(new Font(14));
        newSubTitle.setPrefSize(80, 30);
        newSubTitle.setLayoutX(210);
        newSubTitle.setLayoutY(220);
        newVal.setUnderline(true);
        newVal.setTextFill(Color.STEELBLUE);
        newVal.setPrefSize(80, 30);
        newVal.setLayoutX(270);
        newVal.setLayoutY(220);
        sampleNew.setContentDisplay(ContentDisplay.TOP);
        sampleNew.setFont(new Font(14));
        sampleNew.setPrefWidth(180);
        sampleNew.setMinHeight(80);
        sampleNew.setWrapText(true);
        sampleNew.setLayoutX(210);
        sampleNew.setLayoutY(245);
        Group showModified = new Group(modifyBox, oldSubTitle, oldVal, sampleOld, newSubTitle, newVal, sampleNew);
        //Article on how to get focus value for new property
        //https://stackoverflow.com/questions/16549296/how-perform-task-on-javafx-textfield-at-onfocus-and-outfocus
        //Buttons
        saveConfiguration.setText("Save Configuration");
        saveConfiguration.setStyle("-fx-text-size: 13");
        saveConfiguration.setMinSize(130, 20);
        saveConfiguration.setLayoutX(450);
        saveConfiguration.setLayoutY(30);
        saveConfiguration.setOnAction((ActionEvent e) -> {
            switch (origin) {
                case "ModifyProfile":
                    stage.setScene(ModifyProfile.create(stage));
                    break;
                case "ChatSession":
                    stage.setScene(ChatSession.create(stage));
                    break;
                case "CreateSessionScene":
                    stage.setScene(ChatSession.create(stage));
                    break;
            }
        });

        cancelConfiguration.setText("Cancel Configuration");
        cancelConfiguration.setStyle("-fx-text-size: 13");
        cancelConfiguration.setMinSize(130, 20);
        cancelConfiguration.setLayoutX(450);
        cancelConfiguration.setLayoutY(80);
        cancelConfiguration.setOnAction((ActionEvent e) -> {
            switch (origin) {
                case "ModifyProfile":
                    stage.setScene(ModifyProfile.create(stage));
                    break;
                case "ChatSession":
                    stage.setScene(ChatSession.create(stage));
                    break;
                case "CreateSessionScene":
                    stage.setScene(CreateSessionScene.create(stage));
                    break;
            }
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
        //Groups
        Group titles = new Group(titleLabel, subTitle);
        Group scrollPane = new Group(modifyConfig, showModified);
        Group sidePane = new Group(dividerLine, saveConfiguration, cancelConfiguration, versionLabel);
        Group comboBoxes = new Group();
        Group checkBoxes = new Group();
        Group buttons = new Group();
        //Bundling
        Group page = new Group(titles, scrollPane, comboBoxes, checkBoxes, buttons, sidePane);
        Scene createScession = new Scene(page, 600, 350);
        return createScession;
    }
}
