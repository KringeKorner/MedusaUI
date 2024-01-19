package MedusaUIPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Dictionary {
//    public static List<ArrayList> dictionaries = new ArrayList();

    public static List<GridPane> dictionaryList = new ArrayList();
    public static List<String> profileList = new ArrayList();
    public static List<String> fetchedData = new ArrayList();
    public static List<Integer> fetchedDataIndeces = new ArrayList();
    public static List<String> entry = new ArrayList();
    public static List<String> contextTags = new ArrayList();
    public static List<String> emotionalTags = new ArrayList();

    public static void formatEntry(String entry, String cTags, String eTags) {
        GridPane formattedEntry = new GridPane();
        List<String> addedCtags = new ArrayList<String>(Arrays.asList(cTags.split(",[ ]*")));
        List<String> addedEtags = new ArrayList<String>(Arrays.asList(eTags.split(",[ ]*")));
        VBox retrievedCTags = new VBox();
        VBox retrievedETags = new VBox();

        //Test purposes
        for (String s : addedCtags) {
//            System.out.println("Found cTag: " + s);
            Label l = new Label(s);
            l.setPrefSize(130, 20);
            retrievedCTags.getChildren().add(l);
        }
        for (String s : addedEtags) {
//            System.out.println("Found eTag: " + s);
            Label l = new Label(s);
            l.setPrefSize(130, 20);
            retrievedETags.getChildren().add(l);
        }
        //Formatting the entry
        CheckBox check = new CheckBox();
        Label entryLabel = new Label(entry);
        //customizing
        check.setPrefWidth(40);
        entryLabel.setPrefWidth(145);
        retrievedCTags.setPrefWidth(100);
        retrievedETags.setPrefWidth(100);
        //Functionalities
        check.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
            if(old_val == false){
                EditDictionary.toBeDeleted.add(formattedEntry);
//                System.out.println("Added, new size:" + EditDictionary.toBeDeleted.size());
            } else if(EditDictionary.toBeDeleted.contains(formattedEntry) && old_val == true){
                EditDictionary.toBeDeleted.remove(formattedEntry);
//                System.out.println("Removed, new size:" + EditDictionary.toBeDeleted.size());
            }
        });
        //adding
        formattedEntry.add(check, 0, 0);
        formattedEntry.add(entryLabel, 1, 0);
        formattedEntry.add(retrievedCTags, 2, 0);
        formattedEntry.add(retrievedETags, 3, 0);
        formattedEntry.setBorder(new Border(new BorderStroke(Color.web("#D3D3D3"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        dictionaryList.add(formattedEntry);
    }

    public static void getEntries() {
        contextTags.clear();
        emotionalTags.clear();
        entry.clear();
        int i = 0;
        //Getting data
        for (i = 0; i < fetchedDataIndeces.size(); i++) {
            switch (fetchedDataIndeces.get(i)) {
                case 0:
                    entry.add(fetchedData.get(i));
                    break;
                case 1:
                    contextTags.add(fetchedData.get(i));
                    break;
                case 2:
                    emotionalTags.add(fetchedData.get(i));
                    break;
            }
        }
        //Clearing dataread arrays
        fetchedData.clear();
        fetchedDataIndeces.clear();
        //For testing purposes
        for (i = 0; i < entry.size(); i++) {
//            System.out.println("Sending entry #" + i);
            formatEntry(entry.get(i), contextTags.get(i), emotionalTags.get(i));
        }
    }

    public static void recordEntry(String profile, GridPane entry) {
        if (profileList.size() > 0) {
            String lastAdded = profileList.get(profileList.size() - 1);
            if (!profile.equals(lastAdded)) {
                dictionaryList.clear();
            }
        }
        dictionaryList.add(entry);
        profileList.add(profile);
    }
}
