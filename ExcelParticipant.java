import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExcelParticipant {

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

    protected boolean inclusion;
    public final char c = ',';

    public ExcelParticipant(String[] line){
        this.values = line;
        this.subjectId = values[0];
        try{
        this.condition = values[23];
        this.distractor = values[24];
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
        System.out.println(values.toString()); }
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
        printLines("/Users/rgelpi1/Documents/Explore-Exploit/Physical/FCData.csv","/Users/rgelpi1/Documents/Explore-Exploit/Physical/rdata.csv");
    }


}
