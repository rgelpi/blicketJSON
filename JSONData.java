import com.google.gson.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.ArrayList;

public class JSONData {

    protected JSONParticipant[] participants;
    protected Gson gson;

    public JSONData(Path file) {

        gson = new Gson();
        try { String json = new String(Files.readAllBytes(file));
            participants = gson.fromJson(json, JSONParticipant[].class); }
        catch (IOException e) { e.printStackTrace(); }

    }

    public JSONParticipant[] getParticipants() {
        return participants;
    }

    public void printToCsv(String path){

        Path output = Paths.get(path);
        List<String> lines = new ArrayList<>();
        lines.add("attention1,attention2,comments,condition,distractor,experimentId,explanation,isComplete,fc_1_selection,fc_1_unselected,fc_2_selection,fc_2_unselected,fc_3_selection,fc_3_unselected,fc_4_selection,fc_4_unselected,rating_1_ID,rating_1_IsBlicket,rating_1_Value,rating_2_ID,rating_2_IsBlicket,rating_2_Value,rating_3_ID,rating_3_IsBlicket,rating_3_Value,rating_4_ID,rating_4_IsBlicket,rating_4_Value,rating_5_ID,rating_5_IsBlicket,rating_5_Value,rating_6_ID,rating_6_IsBlicket,rating_6_Value,rating_7_ID,rating_7_IsBlicket,rating_7_Value,rating_8_ID,rating_8_IsBlicket,rating_8_Value,rulepreference,sessionId,shape,submitDateTime");
        for (JSONParticipant p : this.getParticipants()){
            lines.add(p.toString());
        }
        try { Files.write(output,lines, Charset.forName("UTF-8")); }
        catch (IOException e) { e.printStackTrace(); }

    }

    public void printScoreToCsv(String path){

        Path output = Paths.get(path);
        List<String> lines = new ArrayList<>();
        lines.add("Participant ID,Condition,Distractor,FC,CONB.CON,CONB.INC,INCB.CON,INCB.INC,BR,YESB.CON,YESB.INC,NONB.CON,NONB.INC,Attention,Shape,Corr,YESB.CON,YESB.INC,NONB.CON,NONB.INC,Conf,YESB.CON,YESB.INC,NONB.CON,NONB.INC");
        int count = 1;
        for (JSONParticipant p : this.getParticipants()) {
            Trials t = new Trials(p,count);
            lines.add(t.toString() + t.getSplitScores());
            count++;
        }
        try { Files.write(output,lines, Charset.forName("UTF-8")); }
        catch (IOException e) { e.printStackTrace(); }

    }


    public static void main(String[] args){

        Path file = Paths.get("/Users/rgelpi1/Documents/2019/Explore-Exploit/JSON/new2.json");
        JSONData data = new JSONData(file);
        data.printToCsv("/Users/rgelpi1/Documents/2019/Explore-Exploit/Excels/raw.csv");
        data.printScoreToCsv("/Users/rgelpi1/Documents/2019/Explore-Exploit/Excels/score.csv");

    }
}
