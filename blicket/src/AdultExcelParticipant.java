import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdultExcelParticipant extends ExcelParticipant {

    protected boolean attention;
    protected boolean shape;

    public AdultExcelParticipant(String[] line) {
        super(line);
        this.values = line;
        this.subjectId = values[0];
        this.condition = values[1];
        this.distractor = values[2];
        this.age = "Adult";
        this.decimalAge = "Adult";
        CCscore = Integer.parseInt(values[4]);
        CIscore = Integer.parseInt(values[5]);
        ICscore = Integer.parseInt(values[6]);
        IIscore = Integer.parseInt(values[7]);
        this.attention = Boolean.parseBoolean(values[13]);
        this.shape = values[14].toLowerCase().contains("shape");
        this.inclusion = Boolean.logicalAnd(this.attention, this.shape);
    }

    public static void printLines(String input, String output) {
        Path out = Paths.get(output);
        List<String> lines = new ArrayList<>();
        lines.add("subjectId,condition,distractor,blicketConsistency,nonBlicketConsistency,score,age,decimalAge,inclusion");

        try (Scanner scanner = new Scanner(Paths.get(input))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                AdultExcelParticipant e = new AdultExcelParticipant(fields);
                lines.add(e.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.write(out, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        printLines("/Users/rgelpi1/Documents/Explore-Exploit/Physical/adultdata.csv","/Users/rgelpi1/Documents/Explore-Exploit/Physical/adultScore.csv");
    }
}
