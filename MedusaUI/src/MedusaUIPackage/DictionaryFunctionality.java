package MedusaUIPackage;

import java.util.ArrayList;
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

public class DictionaryFunctionality {
    private static VBox tagList = new VBox();
    public static List<CheckBox> ctags = new ArrayList();
    public static List<CheckBox> etags = new ArrayList();
    
    public static VBox returnTags(){
        //31 Tags
        //Context Tags
        Label ctagsLabel = new Label("Context Tags");
        Label etagsLabel = new Label("Emotional Tags");
        CheckBox question = new CheckBox("question");
        CheckBox response = new CheckBox("response");
        CheckBox generic = new CheckBox("generic");
        CheckBox specific = new CheckBox("specific");
        CheckBox word = new CheckBox("word");
        CheckBox phrase = new CheckBox("phrase");
        CheckBox intro = new CheckBox("intro");
        CheckBox terminator = new CheckBox("terminator");
        CheckBox banter = new CheckBox("banter");
        CheckBox fluff = new CheckBox("fluff");
        CheckBox random = new CheckBox("random");
        CheckBox joke = new CheckBox("joke");
        CheckBox fact = new CheckBox("fact");
        CheckBox emotional = new CheckBox("emotional");
        ctags.add(question);
        ctags.add(response);
        ctags.add(generic);
        ctags.add(specific);
        ctags.add(word);
        ctags.add(phrase);
        ctags.add(intro);
        ctags.add(terminator);
        ctags.add(banter);
        ctags.add(fluff);
        ctags.add(random);
        ctags.add(joke);
        ctags.add(fact);
        ctags.add(emotional);
        //Emotional Tags
        CheckBox negative = new CheckBox("negative");
        CheckBox neutral = new CheckBox("neutral");
        CheckBox positive = new CheckBox("positive");
        CheckBox happy = new CheckBox("happy");
        CheckBox sad = new CheckBox("sad");
        CheckBox indifferent = new CheckBox("indifferent");
        CheckBox angry = new CheckBox("angry");
        CheckBox miffed = new CheckBox("miffed");
        CheckBox bored = new CheckBox("bored");
        CheckBox curious = new CheckBox("curious");
        CheckBox dramatic = new CheckBox("dramatic");
        CheckBox confused = new CheckBox("confused");
        CheckBox concerned = new CheckBox("concerned");
        CheckBox scared = new CheckBox("scared");
        CheckBox shy = new CheckBox("shy");
        CheckBox nervous = new CheckBox("nervous");
        etags.add(negative);
        etags.add(neutral);
        etags.add(positive);
        etags.add(happy);
        etags.add(sad);
        etags.add(indifferent);
        etags.add(angry);
        etags.add(miffed);
        etags.add(bored);
        etags.add(curious);
        etags.add(dramatic);
        etags.add(confused);
        etags.add(concerned);
        etags.add(scared);
        etags.add(shy);
        etags.add(nervous);
        //Bundling
        //Ctag list
//        tagList.getChildren().add(ctagsLabel);
        for(CheckBox c : ctags){
            tagList.getChildren().add(c);
        }
//        tagList.getChildren().add(etagsLabel);
        for(CheckBox e : etags){
            tagList.getChildren().add(e);
        }
        return tagList;
    }
    
    //Function to set border
    //.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    public static GridPane createEntry(String userEntry, List<String> tags){
        //Sorting and grouping tags
        List<String> addedCtags = new ArrayList();
        List<String> addedEtags = new ArrayList();
        VBox retrievedCTags = new VBox();
        VBox retrievedETags = new VBox();
        for(String s : tags){
            if(s.equals("question") || s.equals("response") || s.equals("generic") || s.equals("specific") || s.equals("word") || s.equals("phrase") || s.equals("intro") || s.equals("terminator") || s.equals("banter") || s.equals("fluff") || s.equals("random") || s.equals("joke") || s.equals("fact") || s.equals("question") || s.equals("emotional") || s.equals("negative") || s.equals("neutral") || s.equals("positive") || s.equals("greeting"))
                addedCtags.add(s);
            else
                addedEtags.add(s);
        }
        for(String s : addedCtags){
            Label l = new Label(s);
            l.setPrefSize(130, 20);
            retrievedCTags.getChildren().add(l);
        }
        for(String s : addedEtags){
            Label l = new Label(s);
            l.setPrefSize(130, 20);
            retrievedETags.getChildren().add(l);
        }
        //Formatting the entry
        GridPane entry = new GridPane();
        CheckBox check = new CheckBox();
        Label entryLabel = new Label(userEntry);
        VBox cTags = retrievedCTags;
        VBox eTags = retrievedETags;
        //customizing
        check.setPrefWidth(40);
        entryLabel.setPrefWidth(145);
        cTags.setPrefWidth(100);
        eTags.setPrefWidth(100);
        //Functionalities
        check.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
            if(old_val == false){
                EditDictionary.toBeDeleted.add(entry);
//                System.out.println("Added, new size:" + EditDictionary.toBeDeleted.size());
                
            } else if(EditDictionary.toBeDeleted.contains(entry) && old_val == true){
                EditDictionary.toBeDeleted.remove(entry);
//                System.out.println("Removed, new size:" + EditDictionary.toBeDeleted.size());
            }
        });
        //adding
        entry.add(check, 0, 0);
        entry.add(entryLabel, 1, 0);
        entry.add(cTags, 2, 0);
        entry.add(eTags, 3, 0);
        entry.setBorder(new Border(new BorderStroke(Color.web("#D3D3D3"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        Dictionary.recordEntry(ChatSession.getName(), entry);
        return entry;
    }
}
