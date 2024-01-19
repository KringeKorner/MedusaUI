package MedusaUIPackage;

import java.util.Date;
import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.Group;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ChatFunctionality {
    
    public Group formUserMessage(String receivedMessage){
        //Setting up and formatting time
        LocalTime sentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String format = sentTime.format(formatter);
        //Initializing labels
        Label user = new Label("User:");
        Label message = new Label(receivedMessage);
        Label timeStamp = new Label(format);
        //Grouping
        Group userMessage = new Group(user, message, timeStamp);
        user.setTextAlignment(TextAlignment.LEFT);
        user.setFont(new Font(14));
        user.setPrefSize(40, 30);
        user.setTranslateX(10);
        message.setFont(new Font(14));
        message.setWrapText(true);
        message.setPrefWidth(240);
        message.setMinHeight(30);
        message.setTranslateX(55);
        timeStamp.setFont(new Font(14));
        timeStamp.setPrefSize(60, 30);
        timeStamp.setTranslateX(310);
        return userMessage;
    }
    
    public Group getProfileResponse(String name){
        //Setting up and formatting time
        LocalTime sentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String format = sentTime.format(formatter);
        //Initializing labels
        Label profileName = new Label(name + ":");
        Label message = new Label("Test response");
        Label timeStamp = new Label(format);
        FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
        //Grouping
        Group botResponse = new Group(profileName, message, timeStamp);
        profileName.setTextAlignment(TextAlignment.LEFT);
        profileName.setContentDisplay(ContentDisplay.TOP);
        profileName.setFont(new Font(14));
        profileName.setMaxSize(120, 30);
        profileName.setMinSize(40, 30);
        profileName.setTranslateX(10);
        message.setTextAlignment(TextAlignment.LEFT);
        message.setFont(new Font(14));
        message.setWrapText(true);
        message.setPrefWidth(240);
        message.setMinHeight(30);
        message.setTranslateX(fontLoader.computeStringWidth(profileName.getText(), profileName.getFont())+ 20);
        timeStamp.setFont(new Font(14));
        timeStamp.setPrefSize(60, 30);
        timeStamp.setTranslateX(310);
        return botResponse;
    }
}
