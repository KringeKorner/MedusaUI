package MedusaUIPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedusaDBCore {

    //Follow this: http://www.tutorialspoint.com/sqlite/sqlite_java.htm
    public static void getDBData(int queryType, String tableName, String profileName, int coreID) {
        //vars
        String command = "";
        int i = 1;
        List<String> columnNames = new ArrayList();
        //JDBC specific vars
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:MedusaUI.db");
            c.setAutoCommit(false);
//            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            switch (queryType) {
                case 0:
                    //basic select
                    command = "SELECT * FROM " + tableName + ";";
                    break;
                case 1:
                    //retreive base set of operational data
                    command = "SELECT Name, Type, config.CoreId AS cores, change_log.LastUpdated AS lastUpd FROM profile INNER JOIN defines ON defines.ProfileId = profile.ProfileId INNER JOIN config ON config.ConfigurationId = defines.ConfigurationId INNER JOIN updated_by ON updated_by.ConfigurationId = config.ConfigurationId INNER JOIN change_log ON change_log.ChangeLogId = updated_by.ChangeLogId";
                    break;
                case 2:
                    //retrieve config
                    command = "SELECT ConfigAttribute FROM config  INNER JOIN defines ON defines.ConfigurationId = config.ConfigurationId INNER JOIN profile ON profile.ProfileId = defines.ProfileId WHERE profile.Name = \"" + profileName + "\";";
                    break;
                case 3:
                    //Retreive dictionary
                    command = "SELECT DictionaryEntry, Context, EmotionalInflection FROM dictionary INNER JOIN associated_with ON associated_with.DictionaryId = dictionary.DictionaryId INNER JOIN profile ON profile.ProfileId = associated_with.ProfileId WHERE profile.Name = \"" + profileName + "\";";
                    break;
                case 4:
                    //Retreive change log
                    command = "SELECT change_log.ChangeLogId, change_log.RecordedChange, change_log.ChangeType, change_log.LastUpdated FROM change_log \n"
                            + "INNER JOIN saved_to ON saved_to.ChangeLogId = change_log.ChangeLogId \n"
                            + "INNER JOIN save ON save.SaveId = saved_to.SaveId \n"
                            + "INNER JOIN accessed_by ON accessed_by.SaveId = save.SaveId \n"
                            + "INNER JOIN profile ON profile.ProfileId = accessed_by.ProfileId \n"
                            + "INNER JOIN writes_to ON writes_to.SaveId = save.SaveId\n"
                            + "INNER JOIN config ON config.ConfigurationId = writes_to.ConfigurationId\n"
                            + "WHERE profile.Name = \"" + profileName + "\" AND config.CoreId = " + coreID;
                    break;
                case 5:
                    //Retrieve chat log
                    command = "SELECT chat_log.ChatLogId, chat_log.StoredMessage, chat_log.MessageSource, chat_log.MessageTimestamp FROM chat_log \n"
                            + "INNER JOIN added_to ON added_to.ChatLogId = chat_log.ChatLogId \n"
                            + "INNER JOIN save ON save.SaveId = added_to.SaveId \n"
                            + "INNER JOIN accessed_by ON accessed_by.SaveId = save.SaveId \n"
                            + "INNER JOIN profile ON profile.ProfileId = accessed_by.ProfileId \n"
                            + "INNER JOIN defines ON defines.ProfileId = profile.ProfileId\n"
                            + "INNER JOIN config ON config.ConfigurationId = defines.ConfigurationId\n"
                            + "WHERE profile.Name = \"" + profileName + "\" AND config.CoreId = " + coreID;
                    break;
                case 6:
                    //Retreive word with most context tags
                    command = "SELECT dictionary.DictionaryEntry, MAX(length(EmotionalInflection)-length(replace(EmotionalInflection, ',', ''))+1+(length(Context)-length(replace(Context, ',', ''))+1) ) TagCount\n"
                            + "FROM dictionary\n"
                            + "INNER JOIN associated_with on associated_with.DictionaryId = dictionary.DictionaryId\n"
                            + "INNER JOIN profile ON profile.ProfileId = associated_with.ProfileId\n"
                            + "INNER JOIN defines ON defines.ProfileId = profile.ProfileId\n"
                            + "INNER JOIN config ON config.ConfigurationId = defines.ConfigurationId\n"
                            + "WHERE profile.Name = \"" + profileName + "\" AND config.CoreId = " + coreID + "\n"
                            + "GROUP BY dictionary.DictionaryId, dictionary.DictionaryEntry\n"
                            + "ORDER BY TagCount DESC\n"
                            + "LIMIT 1;";
                    break;
                case 7:
                    //get chat opened log data
                    command = "SELECT change_log.ChangeLogId, change_log.RecordedChange, change_log.ChangeType, change_log.LastUpdated FROM change_log\n"
                            + "INNER JOIN saved_to ON saved_to.ChangeLogId = change_log.ChangeLogId\n"
                            + "INNER JOIN save ON save.SaveId = saved_to.SaveId\n"
                            + "INNER JOIN accessed_by ON accessed_by.SaveId = save.SaveId\n"
                            + "INNER JOIN profile ON profile.ProfileId = accessed_by.ProfileId\n"
                            + "INNER JOIN writes_to ON writes_to.SaveId = save.SaveId\n"
                            + "INNER JOIN config ON config.ConfigurationId = writes_to.ConfigurationId\n"
                            + "WHERE profile.Name = \"" + profileName + "\" AND config.CoreId = " + coreID + " AND change_log.RecordedChange = \"Session Opened\"";
                    break;
                case 8:
                    //get chat closed log data
                    command = "SELECT change_log.ChangeLogId, change_log.RecordedChange, change_log.ChangeType, change_log.LastUpdated FROM change_log\n"
                            + "INNER JOIN saved_to ON saved_to.ChangeLogId = change_log.ChangeLogId\n"
                            + "INNER JOIN save ON save.SaveId = saved_to.SaveId\n"
                            + "INNER JOIN accessed_by ON accessed_by.SaveId = save.SaveId\n"
                            + "INNER JOIN profile ON profile.ProfileId = accessed_by.ProfileId\n"
                            + "INNER JOIN writes_to ON writes_to.SaveId = save.SaveId\n"
                            + "INNER JOIN config ON config.ConfigurationId = writes_to.ConfigurationId\n"
                            + "WHERE profile.Name = \"" + profileName + "\" AND config.CoreId = " + coreID + " AND change_log.RecordedChange = \"Session Closed\"";
                    break;
                case 9:
                    //Get chat session ID
                    command = "SELECT chatSession.SessionId from chatSession\n"
                            + "INNER JOIN records_to ON records_to.SessionId = chatSession.SessionId\n"
                            + "INNER JOIN chat_log ON chat_log.ChatLogId = records_to.ChatLogId\n"
                            + "INNER JOIN added_to ON added_to.ChatLogId = chat_log.ChatLogId\n"
                            + "INNER JOIN save ON save.SaveId = added_to.SaveId\n"
                            + "INNER JOIN writes_to ON writes_to.SaveId = save.SaveId\n"
                            + "INNER JOIN saved_to ON saved_to.SaveId = save.SaveId\n"
                            + "INNER JOIN change_log ON change_log.ChangeLogId = saved_to.ChangeLogId\n"
                            + "INNER JOIN config ON config.ConfigurationId = writes_to.ConfigurationId\n"
                            + "INNER JOIN defines ON defines.ConfigurationId = config.ConfigurationId\n"
                            + "INNER JOIN profile ON profile.ProfileId = defines.ProfileId\n"
                            + "WHERE profile.Name = \"" + profileName + "\" AND config.CoreId = " + coreID + " AND change_log.RecordedChange = \"Session Closed\"";
                    break;
                case 10:
                    //Get chat session ID
                    command = "SELECT chatSession.SessionId from chatSession\n"
                            + "INNER JOIN records_to ON records_to.SessionId = chatSession.SessionId\n"
                            + "INNER JOIN chat_log ON chat_log.ChatLogId = records_to.ChatLogId\n"
                            + "INNER JOIN added_to ON added_to.ChatLogId = chat_log.ChatLogId\n"
                            + "INNER JOIN save ON save.SaveId = added_to.SaveId\n"
                            + "INNER JOIN writes_to ON writes_to.SaveId = save.SaveId\n"
                            + "INNER JOIN saved_to ON saved_to.SaveId = save.SaveId\n"
                            + "INNER JOIN change_log ON change_log.ChangeLogId = saved_to.ChangeLogId\n"
                            + "INNER JOIN config ON config.ConfigurationId = writes_to.ConfigurationId\n"
                            + "INNER JOIN defines ON defines.ConfigurationId = config.ConfigurationId\n"
                            + "INNER JOIN profile ON profile.ProfileId = defines.ProfileId\n"
                            + "WHERE profile.Name = \"" + profileName + "\" AND config.CoreId = " + coreID + " AND chatSession.SessionState = \"CLOSED\"";
                    break;
            }
            ResultSet rs = stmt.executeQuery(command);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount() + 1;
            for (i = 1; i < columnCount; i++) {
                columnNames.add(rsmd.getColumnName(i));
            }
            for (i = 0; i < columnNames.size(); i++) {
            }
//            System.out.println("-----------------------------------------------");
            while (rs.next()) {
                int j = 0;
                for (String s : columnNames) {
                    String readVal = rs.getString(s);
                    switch (queryType) {
                        case 0:
                            System.out.println(s + ": " + readVal);
                            System.out.println("-----------------------------------------------");
                            break;
                        case 1:
                            Profiles.initializeArray(readVal, j);
                            break;
                        case 2:
                            ConfigFunctionality.fetchedConfig = readVal;
                            break;
                        case 3:
                            Dictionary.fetchedData.add(readVal);
                            Dictionary.fetchedDataIndeces.add(j);
                            break;
                        case 4:
//                            System.out.println(s + ": " + readVal);
                            StatisticsAndLogs.gridPaneData.add(readVal);
                            StatisticsAndLogs.gridPaneDataIndeces.add(j);
                            break;
                        case 5:
//                            System.out.println(s + ": " + readVal);
                            StatisticsAndLogs.gridPaneData.add(readVal);
                            StatisticsAndLogs.gridPaneDataIndeces.add(j);
                            break;
                        case 6:
//                            System.out.println(s + ": " + readVal);
                            switch (j) {
                                case 0:
//                                    System.out.println(readVal);
                                    StatisticsAndLogs.highestTaggedWord = readVal;
                                    break;
                                case 1:
//                                    System.out.println(readVal);
                                    StatisticsAndLogs.highestTaggedCount = Integer.parseInt(readVal);
                                    break;
                            }
                            break;
                        case 7:
//                            System.out.println(s + ": " + readVal);
                            if (j == 3) {
                                StatisticsAndLogs.date1 = readVal;
                            }
                            break;
                        case 8:
//                            System.out.println(s + ": " + readVal);
                            if (j == 3) {
                                StatisticsAndLogs.date2 = readVal;
                            }
                            break;
                        case 9:
                            StatisticsAndLogs.longestChatSessionID = readVal;
                            break;
                        case 10:
//                            System.out.println(s + ": " + readVal);
                            StatisticsAndLogs.successfulClosedID = readVal;
                            break;
                    }
                    j++;
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
//        System.out.println("Operation done successfully");
    }
}
