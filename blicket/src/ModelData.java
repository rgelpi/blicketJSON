import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ModelData extends Trials {

    protected BlicketTrial FC_1;
    protected BlicketTrial FC_2;
    protected BlicketTrial FC_3;
    protected BlicketTrial FC_4;
    protected BlicketTrial[] FC_;
    protected Double score_CC = 0.;
    protected Double score_CI = 0.;
    protected Double score_IC = 0.;
    protected Double score_II = 0.;
    protected HashMap<String,Double> distant_values = new HashMap<String,Double>();
    protected HashMap<String,Double> near_values = new HashMap<String,Double>();;
    public ModelData(JSONParticipant j, Integer participantId, String distant_input, String near_input){
        super(j,participantId);
        // Selection vs. unselected is arbitrary, just used to have two blicket objects in the same structure as the adult data.
            this.FC_1 = new BlicketTrial(j.fc_1_selection,this.cond,this.dist,j.fc_1_unselected);
            this.FC_2 = new BlicketTrial(j.fc_2_selection,this.cond,this.dist,j.fc_2_unselected);
            this.FC_3 = new BlicketTrial(j.fc_3_selection,this.cond,this.dist,j.fc_3_unselected);
            this.FC_4 = new BlicketTrial(j.fc_4_selection,this.cond,this.dist,j.fc_4_unselected);

        this.FC_ = new BlicketTrial[]{FC_1,FC_2,FC_3,FC_4};

        modelMapper(distant_values, distant_input);
        modelMapper(near_values, near_input);

        for(BlicketTrial b : FC_){
            System.out.println(find(b.stimulus)/(find(b.stimulus)+find(b.alternative.stimulus)) +
                    " " + find(b.alternative.stimulus)/(find(b.stimulus)+find(b.alternative.stimulus))
                    + " " + b.sort);
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
                default: break;
            }
        }
        System.out.println(score_CC + " " + score_CI + " " + score_IC + " " + score_II);
    }
    public ModelData(ExcelParticipant e, String distant_input, String near_input){
        super(e);

            this.FC_1 = new BlicketTrial(e.codes.getStimulus(e.values[25]),this.cond,this.dist,e.codes.getStimulus(e.values[26]));
            this.FC_2 = new BlicketTrial(e.codes.getStimulus(e.values[27]),this.cond,this.dist,e.codes.getStimulus(e.values[28]));
            this.FC_3 = new BlicketTrial(e.codes.getStimulus(e.values[29]),this.cond,this.dist,e.codes.getStimulus(e.values[30]));
            this.FC_4 = new BlicketTrial(e.codes.getStimulus(e.values[31]),this.cond,this.dist,e.codes.getStimulus(e.values[32]));

        this.FC_ = new BlicketTrial[]{FC_1,FC_2,FC_3,FC_4};

        modelMapper(distant_values, distant_input);
        modelMapper(near_values, near_input);

        for(BlicketTrial b : FC_){
            System.out.println(find(b.stimulus)/(find(b.stimulus)+find(b.alternative.stimulus)) +
                    " " + find(b.alternative.stimulus)/(find(b.stimulus)+find(b.alternative.stimulus))
                    + " " + b.sort);
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
                default: break;
            }
        }
        System.out.println(score_CC + " " + score_CI + " " + score_IC + " " + score_II);

    }



    public void modelMapper(HashMap<String,Double> map, String input){
        try (Scanner scanner = new Scanner(Paths.get(input))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                String block = fields[0];
                map.putIfAbsent(block,Double.parseDouble(fields[9]));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Double find(String block){
        if(cond){
            return dist ? near_values.get(block) : near_values.get(reverseBlock(block));
        }else{
            return dist ? distant_values.get(block) : distant_values.get(reverseBlock(block));
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
                ModelData m = new ModelData(p,count,"/Users/rgelpi/Downloads/df_smc_near_signatures_test.csv","/Users/rgelpi/Downloads/df_smc_distant_signatures_test.csv");
                lines.add(count + "," + p.condition + ",C,C," + m.score_CC);
                lines.add(count + "," + p.condition + ",C,I," + m.score_CI);
                lines.add(count + "," + p.condition + ",I,C," + m.score_IC);
                lines.add(count + "," + p.condition + ",I,I," + m.score_II);
                }
            count+=1;
        }
        Path output = Paths.get("/Users/rgelpi/Documents/Explore-Exploit/R Scripts/smc_modeldata_bad.csv");
        try { Files.write(output,lines, Charset.forName("UTF-8")); }
        catch (IOException e) { e.printStackTrace(); }

        /*ArrayList<String> lines = new ArrayList<>();
        try(Scanner scanner = new Scanner(Paths.get("/Users/rgelpi/Documents/Explore-Exploit/Physical/EEdata_90.csv"))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                if(fields[0] == "EXP3_4DR_200") break;
                ExcelParticipant e = new ExcelParticipant(fields);
                if (e.inclusion) {
                    ModelData m = new ModelData(e, "/Users/rgelpi/Downloads/dprobs.csv", "/Users/rgelpi/Downloads/nprobs.csv");
                    lines.add(e.subjectId + "," + e.condition + ",C,C," + m.score_CC);
                    lines.add(e.subjectId + "," + e.condition + ",C,I," + m.score_CI);
                    lines.add(e.subjectId + "," + e.condition + ",I,C," + m.score_IC);
                    lines.add(e.subjectId + "," + e.condition + ",I,I," + m.score_II);
                }
            }
        }catch(IOException io){
            io.printStackTrace();
        }
        Path output = Paths.get("/Users/rgelpi/Downloads/modeldata_kids.csv");
        try { Files.write(output,lines, Charset.forName("UTF-8")); }
        catch (IOException e) { e.printStackTrace(); }*/
    }
}
