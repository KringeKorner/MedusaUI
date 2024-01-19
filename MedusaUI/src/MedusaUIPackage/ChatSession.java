package MedusaUIPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.*;

public class ChatSession {
    private static String retrievedName = "";
    
    public static void setName(String gotName){
        ChatSession.retrievedName = gotName;
//        System.out.println(ChatSession.retrievedName);
    }
    
    public static String getName(){
//        System.out.println(ChatSession.retrievedName);
        return ChatSession.retrievedName;
    }
    
    public static Scene create(Stage stage){
        //Vars
        int logSize = 0;
        //Initialize all functional entities
        ChatFunctionality chatFunction = new ChatFunctionality();
        //Initialize all entities
        Label titleLabel = new Label("Medusa UI");
        Label subTitle = new Label("Chat Session 4XXX");
        Label sessionOptions = new Label("Session Options");
        Button editConfig = new Button();
        Button editDictionary = new Button();
        Button closeSession = new Button();
        VBox chatWindowContent = new VBox();
        TextField chatBox = new TextField();
        ScrollPane chatWindow = new ScrollPane();
        //Title text
        titleLabel.setFont(new Font("Century Gothic", 32));
        titleLabel.setTranslateX(25);
        titleLabel.setTranslateY(20);
        //Page title
        subTitle.setFont(new Font("Century Gothic", 14));
        subTitle.setTranslateX(25);
        subTitle.setTranslateY(60);
        //Buttons
        //Edit configuration
        editConfig.setText("Edit Config");
        editConfig.setPrefSize(110, 30);
        editConfig.setLayoutX(460);
        editConfig.setLayoutY(65);
        editConfig.setOnAction(e -> {
            stage.setScene(ProfileConfiguration.create(stage, "ChatSession", retrievedName));
        });
        //Edit dictionary
        editDictionary.setText("Edit Dictionary");
        editDictionary.setPrefSize(110, 30);
        editDictionary.setLayoutX(460);
        editDictionary.setLayoutY(105);
        editDictionary.setOnAction(e -> {
            stage.setScene(EditDictionary.create(stage, "ChatSession", retrievedName));
        });
        //Close session
        closeSession.setText("Close Session");
        closeSession.setPrefSize(110, 30);
        closeSession.setLayoutX(460);
        closeSession.setLayoutY(145);
        closeSession.setOnAction(e -> {
            stage.setScene(HomeScreen.create(stage));
            ChatLog.chatLog.clear();
            Dictionary.dictionaryList.clear();
        });
        //TextFields
        chatBox.setPrefSize(384, 20);
        chatBox.setLayoutX(25.5);
        chatBox.setLayoutY(319);
        chatBox.setPromptText("Press enter to send message");
        chatBox.setOnAction(e -> {
            //function to get back group: chatFunction.formUserMessage(chatBox.getText())
            chatWindowContent.getChildren().add(chatFunction.formUserMessage(chatBox.getText()));
            ChatLog.recordMessage(retrievedName, chatFunction.formUserMessage(chatBox.getText()));
            //Add delay here for time between messages as if bot is thinking or something
            chatWindowContent.getChildren().add(chatFunction.getProfileResponse(retrievedName));
            ChatLog.recordMessage(retrievedName, chatFunction.getProfileResponse(retrievedName));
            chatBox.clear();
        });
        //VBoxes
        chatWindowContent.setMinHeight(250);
        chatWindowContent.setPadding(new Insets(5, 0, 5, 0));
        chatWindowContent.setSpacing(5);
        //Clearing and setting to chatlog if exists
        logSize = ChatLog.chatLog.size();
        if(logSize > 0 && chatWindowContent.getChildren().isEmpty()){
            ChatLog.chatLog.forEach((Group g) -> {
                chatWindowContent.getChildren().add(g);
            });
        }
        //Function to auto scroll chat
        chatWindowContent.heightProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(Double.compare(Double.parseDouble(oldValue.toString()), 0.0) != 0)
                    if(Double.compare(Double.parseDouble(oldValue.toString()), Double.parseDouble(newValue.toString())) != 0)
                        chatWindow.setVvalue(1.0);
            }
        
        });
        
        //Scroll pane
        chatWindow.setPrefSize(385, 230);
        chatWindow.setLayoutX(25);
        chatWindow.setLayoutY(90);
        chatWindow.setContent(chatWindowContent);
        //Side Panel
        Ellipse dividerLine = new Ellipse();
        dividerLine.setCenterX(430);
        dividerLine.setCenterY(200);
        dividerLine.setRadiusX(1);
        dividerLine.setRadiusY(195);
        dividerLine.setFill(Color.web("#D3D3D3"));
        //Options label
        sessionOptions.setFont(new Font(14));
        sessionOptions.setPrefSize(100, 30);
        sessionOptions.setLayoutX(465);
        sessionOptions.setLayoutY(20);
        //Version label
        Label versionLabel = new Label("Version V2.04");
        versionLabel.setFont(new Font("Century Gothic", 12));
        versionLabel.setTranslateX(479);
        versionLabel.setTranslateY(300);
        //Groups
        Group titles = new Group(titleLabel, subTitle);
        Group chatPanel = new Group(chatWindow, chatBox);
        Group sidePane = new Group(dividerLine, sessionOptions, editConfig, editDictionary, closeSession, versionLabel);
        Group buttons = new Group();
        //Initializing data
        //Bundling
        Group page = new Group(titles, chatPanel, sidePane, buttons);
        Scene createScession = new Scene(page, 600, 350);
        return createScession;
    }
}
