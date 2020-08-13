import com.google.gson.JsonObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NewExcelParticipant {

    protected String[] values;
    protected String subjectId;
    protected String condition;
    protected String distractor;
    protected String age;
    protected String decimalAge;
    protected String[] tasks;

    protected Integer CCscore;
    protected Integer CIscore;
    protected Integer ICscore;
    protected Integer IIscore;

    protected BlicketCodes codes;

    protected String[] CCblocks;
    protected String[] CIblocks;
    protected String[] ICblocks;
    protected String[] IIblocks;


    protected boolean inclusion;
    public final char c = ',';

    public NewExcelParticipant(String[] line){
        this.values = line;
        this.subjectId = values[0];
        try{
            this.condition = values[23];
            this.distractor = values[24];
            this.codes = new BlicketCodes(this.condition, this.distractor);
            this.age = values[4];
            this.decimalAge = values[14];
            this.tasks = values[19].split(" ");
            for(int i = 0; i < 4; i++){
                boolean correctSide = tasks[i].contains("R");
                boolean chosenSide = values[15+i].contains("R");
                //System.out.print(correctSide + " "); System.out.print(chosenSide + " "); System.out.println(chosenSide == correctSide);
                if(tasks[i].startsWith("CC")){
                    CCscore = (correctSide == chosenSide) ? 1 : 0;
                }
                if(tasks[i].startsWith("CI")){
                    CIscore = (correctSide == chosenSide) ? 1 : 0;
                }
                if(tasks[i].startsWith("IC")){
                    ICscore = (correctSide == chosenSide) ? 1 : 0;
                }
                if(tasks[i].startsWith("II")){
                    IIscore = (correctSide == chosenSide) ? 1 : 0;
                }
            }
            this.inclusion = values[8].contains("N");
            System.out.println(Arrays.toString(values)); }
        catch(ArrayIndexOutOfBoundsException e){
            this.condition = "";
            this.distractor = "";
            e.printStackTrace();
        }
    }

    public static void printLines(String input, String output){
        Path out = Paths.get(output);
        List<String> lines = new ArrayList<>();
        lines.add("subjectId,condition,distractor,blicketConsistency,nonBlicketConsistency,score,age,decimalAge,inclusion");

        try(Scanner scanner = new Scanner(Paths.get(input))){
            scanner.nextLine();
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                ExcelParticipant e = new ExcelParticipant(fields);
                lines.add(e.toString());
            }
        }catch(IOException io){
            io.printStackTrace();
        }

        try { Files.write(out,lines, Charset.forName("UTF-8")); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public JsonObject fcBlocks(){
        JsonObject f = new JsonObject();
        f.addProperty("condition",condition);
        f.addProperty("distractor",distractor);
        BlicketCodes b = new BlicketCodes(condition.toLowerCase(),distractor.toLowerCase());
        System.out.println(values[25] + " " + values[26] + " " +
                values[27] + " " + values[28] + " " + values[29] + " " +
                values[30] + " " + values[31] + " " + values[32]);
        f.addProperty(b.getStimulus(values[25]),values[15].contains("L") ? 1 : 0);
        f.addProperty(b.getStimulus(values[26]),values[15].contains("R") ? 1 : 0);
        f.addProperty(b.getStimulus(values[27]),values[16].contains("L") ? 1 : 0);
        f.addProperty(b.getStimulus(values[28]),values[16].contains("R") ? 1 : 0);
        f.addProperty(b.getStimulus(values[29]),values[17].contains("L") ? 1 : 0);
        f.addProperty(b.getStimulus(values[30]),values[17].contains("R") ? 1 : 0);
        f.addProperty(b.getStimulus(values[31]),values[18].contains("L") ? 1 : 0);
        f.addProperty(b.getStimulus(values[32]),values[18].contains("R") ? 1 : 0);
        return f;
    }

    //15, 16, 17, 18

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(subjectId); sb.append(c);
        sb.append(condition); sb.append(c);
        sb.append(distractor); sb.append(c);
        sb.append("C"); sb.append(c);
        sb.append("C"); sb.append(c);
        sb.append(CCscore); sb.append(c);
        sb.append(age); sb.append(c);
        sb.append(decimalAge); sb.append(c);
        sb.append(inclusion);
        sb.append("\n");
        sb.append(subjectId); sb.append(c);
        sb.append(condition); sb.append(c);
        sb.append(distractor); sb.append(c);
        sb.append("C"); sb.append(c);
        sb.append("I"); sb.append(c);
        sb.append(CIscore); sb.append(c);
        sb.append(age); sb.append(c);
        sb.append(decimalAge); sb.append(c);
        sb.append(inclusion);
        sb.append("\n");
        sb.append(subjectId); sb.append(c);
        sb.append(condition); sb.append(c);
        sb.append(distractor); sb.append(c);
        sb.append("I"); sb.append(c);
        sb.append("C"); sb.append(c);
        sb.append(ICscore); sb.append(c);
        sb.append(age); sb.append(c);
        sb.append(decimalAge); sb.append(c);
        sb.append(inclusion);
        sb.append("\n");
        sb.append(subjectId); sb.append(c);
        sb.append(condition); sb.append(c);
        sb.append(distractor); sb.append(c);
        sb.append("I"); sb.append(c);
        sb.append("I"); sb.append(c);
        sb.append(IIscore); sb.append(c);
        sb.append(age); sb.append(c);
        sb.append(decimalAge); sb.append(c);
        sb.append(inclusion);
        return sb.toString();
    }

    public static void main(String[] args){
        printLines("/Users/rgelpi/Documents/Explore-Exploit/Physical/EEdata_feb.csv","/Users/rgelpi/Documents/Explore-Exploit/Physical/rdata_feb.csv");
        /* JsonObject participantFile = new JsonObject();
        try(Scanner scanner = new Scanner(Paths.get("/Users/rgelpi/Documents/Explore-Exploit/Physical/EEdata_90.csv"))){
            scanner.nextLine();
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                ExcelParticipant e = new ExcelParticipant(fields);
                if(e.inclusion){
                    participantFile.add(e.values[0],e.fcBlocks());
                }

            }
        }catch(IOException io){
            io.printStackTrace();
        }
        Gson gson = new Gson();
        try {
            Writer writer = Files.newBufferedWriter(Paths.get("/Users/rgelpi/Documents/Explore-Exploit/JSON/kid_blocks.json"));
            gson.toJson(participantFile,writer);
            writer.close();
        }catch(IOException e) {
            e.printStackTrace();
        } */

    }
}
