package MedusaUIPackage;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.paint.*;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.*;

public class EditDictionary {

    //Added globally to generate no more than once
    final static VBox tagSelectorContent = DictionaryFunctionality.returnTags();
    public static List<GridPane> toBeDeleted = new ArrayList();

    public static Scene create(Stage stage, String origin, String profile) {
        //Vars
        List<String> addedTagList = new ArrayList();
        int dictSize = 0;
        //Initialize all functional entities
        DictionaryFunctionality dictFunc = new DictionaryFunctionality();
        //Initialize all local entities
        Label titleLabel = new Label("Medusa UI");
        Label subTitle = new Label("Edit Dictionary");
        Label addEntry = new Label("Add word/phrase");
        Label chooseTags = new Label("Choose Tags");
        Label versionLabel = new Label("Version V2.04");
        TextArea entryArea = new TextArea();
        Button addSelection = new Button();
        Button cancelSelection = new Button();
        Button addField = new Button();
        Button deleteField = new Button();
        Button saveChanges = new Button();
        Button cancelChanges = new Button();
        VBox scrollContent = new VBox();
        ScrollPane editablePane = new ScrollPane();
        ScrollPane tagSelector = new ScrollPane();
        Ellipse dividerLine = new Ellipse();
        //Groups
        Group titles = new Group(titleLabel, subTitle);
        Group scrollPane = new Group(editablePane);
        Group addEditSubsection = new Group(
                addEntry,
                entryArea,
                chooseTags,
                tagSelector,
                addSelection,
                cancelSelection
        );
        addEditSubsection.setVisible(false);
        Group sidePane = new Group(
                dividerLine,
                addField,
                deleteField,
                saveChanges,
                cancelChanges,
                versionLabel
        );
        Group buttons = new Group();
        //Title text
        titleLabel.setFont(new Font("Century Gothic", 32));
        titleLabel.setTranslateX(25);
        titleLabel.setTranslateY(20);
        //Page title
        subTitle.setFont(new Font("Century Gothic", 14));
        subTitle.setTranslateX(25);
        subTitle.setTranslateY(60);
        //Add entry
        addEntry.setPrefSize(110, 20);
        addEntry.setLayoutX(25);
        addEntry.setLayoutY(235);
        //Choose tags
        chooseTags.setPrefSize(80, 20);
        chooseTags.setLayoutX(225);
        chooseTags.setLayoutY(235);
        //Dictionary content
        //VBox
        //Add title later
        scrollContent.setMinHeight(230);
        dictSize = Dictionary.dictionaryList.size();
        if(dictSize == 0){
            MedusaDBCore.getDBData(3, "", profile, 0);
            Dictionary.getEntries();
            for (GridPane g : Dictionary.dictionaryList) {
                scrollContent.getChildren().add(g);
            }
        }
        if (dictSize > 0 && scrollContent.getChildren().size() == 0) {
            for (GridPane g : Dictionary.dictionaryList) {
                scrollContent.getChildren().add(g);
            }
        }
        //Scroll pane
        //Default height - 230
        //Shrank height - 125
        editablePane.setPrefSize(385, 230);
        editablePane.setLayoutX(25);
        editablePane.setLayoutY(90);
        editablePane.setContent(scrollContent);
        //Add/Edit content
        //Text area
        entryArea.setPrefSize(180, 80);
        entryArea.setLayoutX(25);
        entryArea.setLayoutY(260);
        entryArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (oldValue.isEmpty() || newValue.isEmpty()) {
                    addSelection.setDisable(true);
                } else {
                    addSelection.setDisable(false);
                }
            }
        });
        //VBox
        tagSelectorContent.setMinHeight(80);
        //Scroll pane
        tagSelector.setPrefSize(130, 80);
        tagSelector.setLayoutX(225);
        tagSelector.setLayoutY(260);
        tagSelector.setContent(tagSelectorContent);
        //Buttons
        //Add
        addSelection.setText("Add");
        addSelection.setPrefSize(60, 20);
        addSelection.setLayoutX(362.5);
        addSelection.setLayoutY(260);
        addSelection.setOnAction(e -> {
            //add entries
            for (Node node : tagSelectorContent.getChildren()) {
                if (((CheckBox) node).isSelected() == true) {
                    addedTagList.add(((CheckBox) node).getText());
                }
            }
            //Add to dictionary list
            scrollContent.getChildren().add(dictFunc.createEntry(entryArea.getText(), addedTagList));
            //Close panel
            entryArea.clear();
            addedTagList.removeAll(addedTagList);
            editablePane.setPrefHeight(230);
            for (Node node : tagSelectorContent.getChildren()) {
                ((CheckBox) node).setSelected(false);
            }
            tagSelector.setVvalue(0);
            addEditSubsection.setVisible(false);
        });
        //Cancel
        cancelSelection.setText("Cancel");
        cancelSelection.setPrefSize(60, 20);
        cancelSelection.setLayoutX(362.5);
        cancelSelection.setLayoutY(300);
        cancelSelection.setOnAction(e -> {
            editablePane.setPrefHeight(230);
            for (Node node : tagSelectorContent.getChildren()) {
                ((CheckBox) node).setSelected(false);
            }
            addEditSubsection.setVisible(false);
        });
        //Side Panel
        dividerLine.setCenterX(430);
        dividerLine.setCenterY(200);
        dividerLine.setRadiusX(1);
        dividerLine.setRadiusY(195);
        dividerLine.setFill(Color.web("#D3D3D3"));
        //Add field
        addField.setText("Add Field");
        addField.setPrefSize(120, 20);
        addField.setLayoutX(455);
        addField.setLayoutY(25);
        addField.setOnAction(e -> {
            editablePane.setPrefHeight(125);
            addEditSubsection.setVisible(true);
        });
        //Delete field
        deleteField.setText("Delete Field");
        deleteField.setPrefSize(120, 20);
        deleteField.setLayoutX(455);
        deleteField.setLayoutY(60);
        deleteField.setOnAction(e -> {
            for (int i = 0; i < toBeDeleted.size(); i++) {
                scrollContent.getChildren().remove(toBeDeleted.get(i));
            }
        });
        //Save changes
        saveChanges.setText("Save Changes");
        saveChanges.setPrefSize(120, 20);
        saveChanges.setLayoutX(455);
        saveChanges.setLayoutY(95);
        saveChanges.setOnAction(e -> {
            //For now only go back, no saving actually done anywhere
            for (int i = 0; i < toBeDeleted.size(); i++) {
                Dictionary.dictionaryList.remove(Dictionary.dictionaryList.indexOf(toBeDeleted.get(i)));
            }
            toBeDeleted.clear();
            switch (origin) {
                case "ModifyProfile":
                    stage.setScene(ModifyProfile.create(stage));
                    break;
                case "ChatSession":
                    stage.setScene(ChatSession.create(stage));
                    break;
            }
        });
        //Cancel changes
        cancelChanges.setText("Cancel Changes");
        cancelChanges.setPrefSize(120, 20);
        cancelChanges.setLayoutX(455);
        cancelChanges.setLayoutY(130);
        cancelChanges.setOnAction(e -> {
            scrollContent.getChildren().clear();
            for (GridPane g : Dictionary.dictionaryList) {
                scrollContent.getChildren().add(g);
            }
            switch (origin) {
                case "ModifyProfile":
                    stage.setScene(ModifyProfile.create(stage));
                    break;
                case "ChatSession":
                    stage.setScene(ChatSession.create(stage));
                    break;
            }
        });
        //Version label
        versionLabel.setFont(new Font("Century Gothic", 12));
        versionLabel.setTranslateX(479);
        versionLabel.setTranslateY(300);
        //Bundling
        Group page = new Group(titles, scrollPane, addEditSubsection, buttons, sidePane);
        Scene createScession = new Scene(page, 600, 350);
        return createScession;
    }
}
