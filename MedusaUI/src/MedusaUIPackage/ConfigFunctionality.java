package MedusaUIPackage;

import java.util.ArrayList;
import java.util.List;

public class ConfigFunctionality {

    public static List<Double> configList = new ArrayList();
    public static String fetchedConfig = "";

    public static List getConfig(String profileName) {
        int i = 0;
        configList.clear();
        fetchedConfig = "";
        MedusaDBCore.getDBData(2, "", profileName, 0);
        //For testing
//        System.out.println(fetchedConfig);
        if(fetchedConfig.isEmpty()){
            for(i=0;i<8;i++){
                configList.add(0.5);
            }
        } else {
            //Happiness
            configList.add(Double.parseDouble(fetchedConfig.substring(10, 13)));
            //Sadness
            configList.add(Double.parseDouble(fetchedConfig.substring(23, 26)));
            //Insignificance
            configList.add(Double.parseDouble(fetchedConfig.substring(43, 46)));
            //Anger
            configList.add(Double.parseDouble(fetchedConfig.substring(54, 57)));
            //Curiosity
            configList.add(Double.parseDouble(fetchedConfig.substring(69, 72)));
            //Distaste
            configList.add(Double.parseDouble(fetchedConfig.substring(83, 86)));
            //Spontinaety
            configList.add(Double.parseDouble(fetchedConfig.substring(100, 103)));
            //Sarcasm
            configList.add(Double.parseDouble(fetchedConfig.substring(113, 116)));
        }
//        for(Double d : configList){
//            System.out.println("Retrieved value: " + d);
//        }
        return configList;
    }
}
