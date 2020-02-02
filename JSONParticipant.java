import com.google.gson.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.Writer;
import java.io.IOException;

public class JSONParticipant {

    protected JsonObject values;
    protected String attention1;
    protected String attention2;
    protected String comments;
    protected String condition;
    protected String distractor;
    protected String experimentId;
    protected String explanation;
    protected boolean isComplete;
    protected String rulepreference;
    protected String sessionId;
    protected String shape;
    protected String submitDateTime;
    protected String fc_1_selection;
    protected String fc_1_unselected;
    protected String fc_2_selection;
    protected String fc_2_unselected;
    protected String fc_3_selection;
    protected String fc_3_unselected;
    protected String fc_4_selection;
    protected String fc_4_unselected;
    protected String rating_1_ID;
    protected String rating_2_ID;
    protected String rating_3_ID;
    protected String rating_4_ID;
    protected String rating_5_ID;
    protected String rating_6_ID;
    protected String rating_7_ID;
    protected String rating_8_ID;
    protected Integer rating_1_IsBlicket;
    protected Integer rating_2_IsBlicket;
    protected Integer rating_3_IsBlicket;
    protected Integer rating_4_IsBlicket;
    protected Integer rating_5_IsBlicket;
    protected Integer rating_6_IsBlicket;
    protected Integer rating_7_IsBlicket;
    protected Integer rating_8_IsBlicket;
    protected Integer rating_1_Value;
    protected Integer rating_2_Value;
    protected Integer rating_3_Value;
    protected Integer rating_4_Value;
    protected Integer rating_5_Value;
    protected Integer rating_6_Value;
    protected Integer rating_7_Value;
    protected Integer rating_8_Value;
    protected final String[] blocksNB = {"bbrbb","brrbr","bbrrr","brrrb","rbbbb","rbbbr","rrrbb","brbbb","brbrr","rbrrr"};
    protected final String[] blocksNR = {"rrbrr","rbbrb","rrbbb","rbbbr","brrrr","brrrb","bbbrr","rbrrr","rbrbb","brbbb"};
    protected final String[] blocksDB = {"bbbrb","bbbrr","bbrbr","brrbr","rbbbb","rrbbr","rrrrb","brbbb","bbrrr","rrbrb"};
    protected final String[] blocksDR = {"rrrbr","rrrbb","rrbrb","rbbrb","brrrr","bbrrb","bbbbr","rbrrr","rrbbb","bbrbr"};


    public JSONParticipant(JsonObject j) {

        this.values = j;
        this.attention1 = j.get("attention1").getAsString();
        this.attention2 = j.get("attention2").getAsString();
        this.comments = j.get("comments").getAsString();
        this.condition = j.get("condition").getAsString();
        this.distractor = j.get("distractor").getAsString();
        this.experimentId = j.get("experimentId").getAsString();
        this.explanation = j.get("explanation").getAsString();
        this.fc_1_selection = j.get("fc_1_selection").getAsString();
        this.fc_1_unselected = j.get("fc_1_unselected").getAsString();
        this.fc_2_selection = j.get("fc_2_selection").getAsString();
        this.fc_2_unselected = j.get("fc_2_unselected").getAsString();
        this.fc_3_selection = j.get("fc_3_selection").getAsString();
        this.fc_3_unselected = j.get("fc_3_unselected").getAsString();
        this.fc_4_selection = j.get("fc_4_selection").getAsString();
        this.fc_4_unselected = j.get("fc_4_unselected").getAsString();
        this.rating_1_ID = j.get("rating_1_ID").getAsString();
        this.rating_1_IsBlicket = j.get("rating_1_IsBlicket").getAsInt();
        this.rating_1_Value = j.get("rating_1_Value").getAsInt();
        this.rating_2_ID = j.get("rating_2_ID").getAsString();
        this.rating_2_IsBlicket = j.get("rating_2_IsBlicket").getAsInt();
        this.rating_2_Value = j.get("rating_2_Value").getAsInt();
        this.rating_3_ID = j.get("rating_3_ID").getAsString();
        this.rating_3_IsBlicket = j.get("rating_3_IsBlicket").getAsInt();
        this.rating_3_Value = j.get("rating_3_Value").getAsInt();
        this.rating_4_ID = j.get("rating_4_ID").getAsString();
        this.rating_4_IsBlicket = j.get("rating_4_IsBlicket").getAsInt();
        this.rating_4_Value = j.get("rating_4_Value").getAsInt();
        this.rating_5_ID = j.get("rating_5_ID").getAsString();
        this.rating_5_IsBlicket = j.get("rating_5_IsBlicket").getAsInt();
        this.rating_5_Value = j.get("rating_5_Value").getAsInt();
        this.rating_6_ID = j.get("rating_6_ID").getAsString();
        this.rating_6_IsBlicket = j.get("rating_6_IsBlicket").getAsInt();
        this.rating_6_Value = j.get("rating_6_Value").getAsInt();
        this.rating_7_ID = j.get("rating_7_ID").getAsString();
        this.rating_7_IsBlicket = j.get("rating_7_IsBlicket").getAsInt();
        this.rating_7_Value = j.get("rating_7_Value").getAsInt();
        this.rating_8_ID = j.get("rating_8_ID").getAsString();
        this.rating_8_IsBlicket = j.get("rating_8_IsBlicket").getAsInt();
        this.rating_8_Value = j.get("rating_8_Value").getAsInt();
        this.isComplete = j.get("isComplete").getAsBoolean();
        this.rulepreference = j.get("rulepreference").getAsString();
        this.sessionId = j.get("sessionId").getAsString();
        this.shape = j.get("shape").getAsString();
        this.submitDateTime = j.get("submitDateTime").getAsString();

    }

    public JsonObject blockRatings(){
        JsonObject b = new JsonObject();
        b.addProperty(rating_1_ID, rating_1_IsBlicket);
        b.addProperty(rating_2_ID, rating_2_IsBlicket);
        b.addProperty(rating_3_ID, rating_3_IsBlicket);
        b.addProperty(rating_4_ID, rating_4_IsBlicket);
        b.addProperty(rating_5_ID, rating_5_IsBlicket);
        b.addProperty(rating_6_ID, rating_6_IsBlicket);
        b.addProperty(rating_7_ID, rating_7_IsBlicket);
        b.addProperty(rating_8_ID, rating_8_IsBlicket);
        return b;
    }

    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();
        sb.append(attention1); sb.append(",");
        sb.append(attention2); sb.append(",\"");
        sb.append(comments); sb.append("\",");
        sb.append(condition); sb.append(",");
        sb.append(distractor); sb.append(",");
        sb.append(experimentId); sb.append(",\"");
        sb.append(explanation); sb.append("\",");
        sb.append(isComplete); sb.append(",");
        sb.append(fc_1_selection); sb.append(",");
        sb.append(fc_1_unselected); sb.append(",");
        sb.append(fc_2_selection); sb.append(",");
        sb.append(fc_2_unselected); sb.append(",");
        sb.append(fc_3_selection); sb.append(",");
        sb.append(fc_3_unselected); sb.append(",");
        sb.append(fc_4_selection); sb.append(",");
        sb.append(fc_4_unselected); sb.append(",");
        sb.append(rating_1_ID); sb.append(",");
        sb.append(rating_1_IsBlicket); sb.append(",");
        sb.append(rating_1_Value); sb.append(",");
        sb.append(rating_2_ID); sb.append(",");
        sb.append(rating_2_IsBlicket); sb.append(",");
        sb.append(rating_2_Value); sb.append(",");
        sb.append(rating_3_ID); sb.append(",");
        sb.append(rating_3_IsBlicket); sb.append(",");
        sb.append(rating_3_Value); sb.append(",");
        sb.append(rating_4_ID); sb.append(",");
        sb.append(rating_4_IsBlicket); sb.append(",");
        sb.append(rating_4_Value); sb.append(",");
        sb.append(rating_5_ID); sb.append(",");
        sb.append(rating_5_IsBlicket); sb.append(",");
        sb.append(rating_5_Value); sb.append(",");
        sb.append(rating_6_ID); sb.append(",");
        sb.append(rating_6_IsBlicket); sb.append(",");
        sb.append(rating_6_Value); sb.append(",");
        sb.append(rating_7_ID); sb.append(",");
        sb.append(rating_7_IsBlicket); sb.append(",");
        sb.append(rating_7_Value); sb.append(",");
        sb.append(rating_8_ID); sb.append(",");
        sb.append(rating_8_IsBlicket); sb.append(",");
        sb.append(rating_8_Value); sb.append(",");
        sb.append(rulepreference); sb.append(",");
        sb.append(sessionId); sb.append(",");
        sb.append(shape); sb.append(",\"");
        sb.append(submitDateTime); sb.append("\"");
        return sb.toString();

    }

    public static void main(String[] args){
        Path file = Paths.get("/Users/rgelpi/Documents/Explore-Exploit/JSON/new2.json");
        JSONData data = new JSONData(file);
        JsonObject participantFile = new JsonObject();
        Integer count = 1;
        for (JSONParticipant p : data.getParticipants()){
            if((Integer.parseInt(p.attention1) == 1) & (Integer.parseInt(p.attention2) == 1) && (p.shape.toLowerCase().contains("shape"))){
                participantFile.add("participant " + count, p.blockRatings()); }
            count+=1;
        }
        Gson gson = new Gson();
        try {
            Writer writer = Files.newBufferedWriter(Paths.get("/Users/rgelpi/Documents/Explore-Exploit/JSON/blocks.json"));
            gson.toJson(participantFile,writer);
            writer.close();
        }catch(IOException e) {
            e.printStackTrace();
        }

    }
}
