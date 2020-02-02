import com.google.gson.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class ModelData extends Trials {

    protected Double score_CC = 0.;
    protected Double score_CI = 0.;
    protected Double score_IC = 0.;
    protected Double score_II = 0.;
    protected HashMap<String,Double> distant_values = new HashMap<String,Double>();
    protected HashMap<String,Double> near_values = new HashMap<String,Double>();;
    public ModelData(JSONParticipant j, Integer participantId, String distant_input, String near_input){
        super(j,participantId);
        // Selection vs. unselected is arbitrary, just used to have two blicket objects in the same structure as the adult data.
        if(j.distractor == "red"){
            FC1 = new BlicketTrial(reverseBlock(j.fc_1_selection),this.cond,true,reverseBlock(j.fc_1_unselected));
            FC2 = new BlicketTrial(reverseBlock(j.fc_2_selection),this.cond,true,reverseBlock(j.fc_2_unselected));
            FC3 = new BlicketTrial(reverseBlock(j.fc_3_selection),this.cond,true,reverseBlock(j.fc_3_unselected));
            FC4 = new BlicketTrial(reverseBlock(j.fc_4_selection),this.cond,true,reverseBlock(j.fc_4_unselected));
        }else{
            FC1 = new BlicketTrial(j.fc_1_selection,this.cond,true,j.fc_1_unselected);
            FC2 = new BlicketTrial(j.fc_2_selection,this.cond,true,j.fc_2_unselected);
            FC3 = new BlicketTrial(j.fc_3_selection,this.cond,true,j.fc_3_unselected);
            FC4 = new BlicketTrial(j.fc_4_selection,this.cond,true,j.fc_4_unselected);
        }

        modelMapper(distant_values, distant_input);
        modelMapper(near_values, near_input);

        for(BlicketTrial b : FC){
            switch(b.sort){
                case "CC": score_CC = b.isBlicket() ?
                        find(b.stimulus)/(find(b.stimulus)+find(b.alternative.stimulus)) :
                        find(b.alternative.stimulus)/(find(b.stimulus)+find(b.alternative.stimulus)); break;
                case "CI": score_CI = b.isBlicket() ?
                        find(b.stimulus)/(find(b.stimulus)+find(b.alternative.stimulus)) :
                        find(b.alternative.stimulus)/(find(b.stimulus)+find(b.alternative.stimulus)); break;
                case "IC": score_IC = b.isBlicket() ?
                        find(b.stimulus)/(find(b.stimulus)+find(b.alternative.stimulus)) :
                        find(b.alternative.stimulus)/(find(b.stimulus)+find(b.alternative.stimulus)); break;
                case "II": score_II = b.isBlicket() ?
                        find(b.stimulus)/(find(b.stimulus)+find(b.alternative.stimulus)) :
                        find(b.alternative.stimulus)/(find(b.stimulus)+find(b.alternative.stimulus)); break;
            }

        }
    }
    public ModelData(ExcelParticipant e, String distant_input, String near_input){
        super(e);

        if(e.distractor == "red"){
            FC1 = new BlicketTrial(reverseBlock(e.values[25]),this.cond,true,reverseBlock(e.values[26]));
            FC2 = new BlicketTrial(reverseBlock(e.values[27]),this.cond,true,reverseBlock(e.values[28]));
            FC3 = new BlicketTrial(reverseBlock(e.values[29]),this.cond,true,reverseBlock(e.values[30]));
            FC4 = new BlicketTrial(reverseBlock(e.values[31]),this.cond,true,reverseBlock(e.values[32]));
        }else{
            FC1 = new BlicketTrial(e.values[25],this.cond,true,e.values[26]);
            FC2 = new BlicketTrial(e.values[27],this.cond,true,e.values[28]);
            FC3 = new BlicketTrial(e.values[29],this.cond,true,e.values[30]);
            FC4 = new BlicketTrial(e.values[31],this.cond,true,e.values[32]);
        }
        this.FC = new BlicketTrial[]{FC1,FC2,FC3,FC4};

        modelMapper(distant_values, distant_input);
        modelMapper(near_values, near_input);

    }



    public void modelMapper(HashMap<String,Double> map, String input){
        try (Scanner scanner = new Scanner(Paths.get(input))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                String block = fields[0].split("\"")[1];
                map.putIfAbsent(block,Double.parseDouble(fields[1]));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Double find(String block){
        if(cond){
            return near_values.get(block);
        }else{
            return distant_values.get(block);
        }
    }

    public static String reverseBlock(String block){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <= 4; i++) {
            if (block.charAt(i) == 'r') {
                sb.append("b");
            } else {
                sb.append("r");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args){
        Path file = Paths.get("/Users/rgelpi/Documents/Explore-Exploit/JSON/new2.json");
        JSONData data = new JSONData(file);
        ArrayList<String> lines = new ArrayList<>();
        lines.add("id,condition,blicketConsistency,nonBlicketConsistency,score");
        Integer count = 1;
        for (JSONParticipant p : data.getParticipants()){
            if((Integer.parseInt(p.attention1) == 1) & (Integer.parseInt(p.attention2) == 1) && (p.shape.toLowerCase().contains("shape"))){
                ModelData m = new ModelData(p,count,"/Users/rgelpi/Downloads/dprobs.csv","/Users/rgelpi/Downloads/nprobs.csv");
                lines.add(count + "," + p.condition + ",C,C," + m.score_CC);
                lines.add(count + "," + p.condition + ",C,I," + m.score_CI);
                lines.add(count + "," + p.condition + ",I,C," + m.score_IC);
                lines.add(count + "," + p.condition + ",I,I," + m.score_II);
                }
            count+=1;
        }
        Path output = Paths.get("/Users/rgelpi/Downloads/modeldata.csv");
        try { Files.write(output,lines, Charset.forName("UTF-8")); }
        catch (IOException e) { e.printStackTrace(); }

    }
}
