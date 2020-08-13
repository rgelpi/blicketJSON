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
        lines.add("attention1,attention2,comments,condition,distractor,experimentId,explanation,isComplete,fc_1_selection,fc_1_unselected,fc_2_selection,fc_2_unselected,fc_3_selection,fc_3_unselected,fc_4_selection,fc_4_unselected,rulepreference,sessionId,shape,submitDateTime");
        for (JSONParticipant p : this.getParticipants()){
            lines.add(p.toString());
        }
        try { Files.write(output,lines, Charset.forName("UTF-8")); }
        catch (IOException e) { e.printStackTrace(); }

    }

    public void printScoreToCsv(String path){

        Path output = Paths.get(path);
        List<String> lines = new ArrayList<>();
        lines.add("Participant ID,Condition,Distractor,FC,CONB.CON,CONB.INC,INCB.CON,INCB.INC,Attention,Shape");
        int count = 1;
        for (JSONParticipant p : this.getParticipants()) {
            Trials t = new Trials(p,count);
            lines.add(t.toString() + t.getSplitScores());
            count++;
        }
        try { Files.write(output,lines, Charset.forName("UTF-8")); }
        catch (IOException e) { e.printStackTrace(); }

    }

    public void printNoveltyToCsv(String path){
        Path output = Paths.get(path);
        List<String> lines = new ArrayList<>();
        lines.add("Participant ID,Condition,Distractor,Seen1,Seen2,Seen3,Seen4,Seen5,Seen6,Seen7,Seen8,Sort1,Sort2,Sort3,Sort4,Sort5,Sort6,Sort7,Sort8,Score1,Score2,Score3,Score4,Score5,Score6,Score7,Score8,NovelBC,SeenBC,NovelBI,SeenBI,NovelNC,SeenNC,NovelNI,SeenNI");
        int count = 1;
        for (JSONParticipant p : this.getParticipants()) {
            Trials t = new Trials(p,count);
            lines.add(t.getNovelties(t.R));
            count++;
        }
        try { Files.write(output,lines, Charset.forName("UTF-8")); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public void printRatingJson(String path){
        Path output = Paths.get(path);

    }

    public void reshapeAdultBRData(String path){
        Path output = Paths.get(path);
        List<String> lines = new ArrayList<>();
        lines.add("subjectId,condition,distractor,blicketness,consistency,adjScore,rawScore,attention,shape");
        int count = 1;
        for (JSONParticipant p : this.getParticipants()) {
            Trials t = new Trials(p,count);
            lines.add(t.scorer("BC"));
            lines.add(t.scorer("BI"));
            lines.add(t.scorer("NC"));
            lines.add(t.scorer("NI"));
            count++;
        }
        try { Files.write(output,lines, Charset.forName("UTF-8")); }
        catch (IOException e) { e.printStackTrace(); }
    }


    public static void main(String[] args){

        Path file = Paths.get("/Users/rgelpi/Documents/Explore-Exploit/JSON/localOptima_070.json");
        JSONData data = new JSONData(file);
        //data.printToCsv("/Users/rgelpi/Documents/Explore-Exploit/raw_070.csv");
        data.printScoreToCsv("/Users/rgelpi/Documents/Explore-Exploit/score.csv");
        //data.printNoveltyToCsv("/Users/rgelpi1/Documents/2019/Explore-Exploit/Excels/novelty.csv");
        //data.reshapeAdultBRData("/Users/rgelpi/Documents/Explore-Exploit/brdata.csv");

    }
}
