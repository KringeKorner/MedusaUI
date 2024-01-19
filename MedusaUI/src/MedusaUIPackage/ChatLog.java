package MedusaUIPackage;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.control.Label;

public class ChatLog {
    public static List<Group> chatLog = new ArrayList();
    
    public static void recordMessage(String source, Group message){
        chatLog.add(message);
    }
}
