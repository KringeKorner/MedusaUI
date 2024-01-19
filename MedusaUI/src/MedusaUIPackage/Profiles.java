package MedusaUIPackage;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Profiles {

    //Make 2D array to load in data from DB then sort it into specific arrays for ease of access
    static String[][] profileInformation = new String[50][4];
    static List<String> profileNames = new ArrayList();
    static List<String> profileTypes = new ArrayList();
    static List<Integer> profileCores = new ArrayList();
    static List<String> profileTimestamps = new ArrayList();
    
    public static void initializeArray(String data, int position) {
        //Read in values from DB
//        System.out.println(data);
        switch(position){
            case 0:
                profileNames.add(data);
//                System.out.println("Size of names: " + profileNames.size());
                break;
            case 1:
                profileTypes.add(data);
//                System.out.println("Size of types: " + profileTypes.size());
                break;
            case 2:
                profileCores.add(Integer.parseInt(data));
//                System.out.println("Size of cores: " + profileCores.size());
                break;
            case 3:
                profileTimestamps.add(data);
//                System.out.println("Size of timestamps: " + profileTimestamps.size());
                break;
        }
    }
    
    public static void getData(){
        int i, j;
        for(i=0;i<profileNames.size();i++){
            for(j=0;j<4;j++){
                switch(j){
                    case 0:
                        profileInformation[i][j] = profileNames.get(i);
                        break;
                    case 1:
                        profileInformation[i][j] = profileTypes.get(i);
                        break;
                    case 2:
                        profileInformation[i][j] = profileCores.get(i).toString();
                        break;
                    case 3:
                        profileInformation[i][j] = profileTimestamps.get(i);
                        break;
                }
            }
        }
//        for(i=0;i<profileNames.size();i++){
//            for(j=0;j<4;j++){
//                System.out.println(profileInformation[i][j]);
//            }
//        }
    }

    public static int setCore() {
        int newestRecord = Collections.max(profileCores);
        int newCore = newestRecord + 1;
        return newCore;
    }

    public static String setTimestamp() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String stringedDate = date.format(formatter);
        return stringedDate;
    }
    
    public static void forkProfile(String profileName, String type){
        //Establishing new ID
        int newestRecord = Collections.max(profileCores);
        int forkedCore = newestRecord + 1;
        //Filling data
        profileNames.add(profileName);
        profileTypes.add(type);
        profileCores.add(forkedCore);
        profileTimestamps.add(setTimestamp());
        getData();
    }
    
    public static void convertProfile(int position){
        //switch to custom
        profileTypes.set(position, "CUSTOM");
        getData();
    }

    public static void addProfile(String profileName) {
//        System.out.println(profileName);
        profileNames.add(profileName);
    }

    public static void addProfileType(String profileType) {
//        System.out.println(profileType);
        profileTypes.add(profileType);
    }

    public static void addCore(int core) {
//        System.out.println(core);
        profileCores.add(core);
    }

    public static void addTimestamp(String date) {
//        System.out.println(date);
        profileTimestamps.add(date);
    }

    public static List getProfiles() {
        return profileNames;
    }

    public static List getProfileTypes() {
        return profileTypes;
    }

    public static List getCoreIds() {
        return profileCores;
    }

    public static List getTimestamps() {
        return profileTimestamps;
    }
}
