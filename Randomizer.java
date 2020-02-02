import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Randomizer {

    /**
     * cons is the consistent blocks.
     * inc is the inconsistent blocks.
     * order is the order of forced-choice tasks (1 = CC, 2 = CI, 3 = IC, 4 = II).
     * lat is the laterality of the blicket. Always, 2 are on the left and two are on the right.
     * fc is the ArrayList for the combined order-laterality object.
     */
    protected List<Integer> cons = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));
    protected List<Integer> inc = new ArrayList<>(List.of(1, 2, 3));
    protected List<String> order = new ArrayList<>(List.of("CC", "CI", "IC", "II"));
    protected List<String> lat = new ArrayList<>(List.of("-L", "-L", "-R", "-R"));
    protected List<String> fc = new ArrayList<>();
    protected List<Integer> whichBlock = new ArrayList<>(List.of(1, 2));

    /**
     * Randomizer for a given session of physical blicket tasks.
     */
    public Randomizer(){
        Collections.shuffle(cons);
        Collections.shuffle(inc);
        Collections.shuffle(order);
        Collections.shuffle(lat);
        for (int i = 1; i < 5; i++){
            fc.add(order.remove(0) + lat.remove(0));
        }
        System.out.println(cons + " - Consistent Block Familiarization Trials");
        System.out.println(inc + " - Inconsistent Block Familiarization Trials");
        System.out.println(fc + " - FC stimuli distribution");
        System.out.println();

        /*
        Randomizes whichBlock so the blicket-distractor pairings are not always the same.
         */
        //Collections.shuffle(whichBlock);
        //if (whichBlock.indexOf(1) == 0) System.out.println("CC blicket 1, CI blicket 2"); else System.out.println("CC blicket 2, CI blicket 1");
        //Collections.shuffle(whichBlock);
        //if (whichBlock.indexOf(1) == 0) System.out.println("CC distr 1, IC distr 2"); else System.out.println("CC distr 2, IC distr 1");
        //System.out.println();
        //Collections.shuffle(whichBlock);
        //if (whichBlock.indexOf(1) == 0) System.out.println("II blicket 1, IC blicket 2"); else System.out.println("II blicket 2, IC blicket 1");
        //Collections.shuffle(whichBlock);
        //if (whichBlock.indexOf(1) == 0) System.out.println("II distr 1, CI distr 2"); else System.out.println("II distr 2, CI distr 1");
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(cons.get(0)); sb.append(',');
        sb.append(cons.get(1)); sb.append(',');
        sb.append(cons.get(2)); sb.append(',');
        sb.append(cons.get(3)); sb.append(',');
        sb.append(cons.get(4)); sb.append(',');
        sb.append(cons.get(5)); sb.append(',');
        sb.append(cons.get(6)); sb.append(',');
        sb.append(inc.get(0)); sb.append(',');
        sb.append(inc.get(1)); sb.append(',');
        sb.append(inc.get(2)); sb.append(',');
        sb.append(fc.get(0)); sb.append(',');
        sb.append(fc.get(1)); sb.append(',');
        sb.append(fc.get(2)); sb.append(',');
        sb.append(fc.get(3)); sb.append(',');

        Collections.shuffle(whichBlock);
        if (whichBlock.indexOf(1) == 0) sb.append("CC blicket 1 - CI blicket 2"); else sb.append("CC blicket 2 - CI blicket 1");
        sb.append(',');
        Collections.shuffle(whichBlock);
        if (whichBlock.indexOf(1) == 0) sb.append("CC distr 1 - IC distr 2"); else sb.append("CC distr 2 - IC distr 1");
        sb.append(',');
        Collections.shuffle(whichBlock);
        if (whichBlock.indexOf(1) == 0) sb.append("II blicket 1 - IC blicket 2"); else sb.append("II blicket 2 - IC blicket 1");
        sb.append(',');
        Collections.shuffle(whichBlock);
        if (whichBlock.indexOf(1) == 0) sb.append("II distr 1 - CI distr 2"); else sb.append("II distr 2 - CI distr 1");
        return sb.toString();
    }

    public static void printToCsv(String path){

        Path output = Paths.get(path);
        List<String> lines = new ArrayList<>();
        String header = "Consistent Block 1, Consistent Block 2, Consistent Block 3, Consistent Block 4, Consistent Block 5, Consistent Block 6, Consistent Block 7, Inconsistent Block 1, Inconsistent Block 2, Inconsistent Block 3, FC Task 1, FC Task 2, FC Task 3, FC Task 4, Consistent Blicket Distribution, Consistent Distractor Distribution, Inconsistent Blicket Distribution, Inconsistent Distractor Distribution";
        lines.add(header);
        for (int i = 1; i <= 25; i++){
            Randomizer r = new Randomizer();
            lines.add(r.toString());
        }
        try { Files.write(output,lines, Charset.forName("UTF-8")); }
        catch (IOException e) { e.printStackTrace(); }

    }

    public static void main(String[] args){
        printToCsv("random.csv");
    }
}
