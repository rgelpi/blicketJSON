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
    protected List<String> tasks = new ArrayList<>(4);


    /**
     * Randomizer for a given session of physical blicket tasks.
     */
    public Randomizer(){
        /*
        TRUE = first value to CB-1, FALSE = first value to CB-2, etc.
         */
        boolean cbval;
        boolean cnval;
        boolean ibval;
        boolean inval;
        StringBuilder ccblocks = new StringBuilder();
        StringBuilder ciblocks = new StringBuilder();
        StringBuilder icblocks = new StringBuilder();
        StringBuilder iiblocks = new StringBuilder();

        Collections.shuffle(whichBlock);
        cbval = (whichBlock.indexOf(1) == 0);
        Collections.shuffle(whichBlock);
        cnval = (whichBlock.indexOf(1) == 0);
        Collections.shuffle(whichBlock);
        ibval = (whichBlock.indexOf(1) == 0);
        Collections.shuffle(whichBlock);
        inval = (whichBlock.indexOf(1) == 0);

        ccblocks.append(cbval ? " (CB-1" : " (CB-2");
        ccblocks.append(cnval ? " CN-1)" : " CN-2)");
        ciblocks.append(cbval ? " (CB-2" : " (CB-1");
        ciblocks.append(inval ? " IN-2)" : " IN-1)");
        icblocks.append(ibval ? " (IB-2" : " (IB-1");
        icblocks.append(cnval ? " CN-2)" : " CN-1)");
        iiblocks.append(ibval ? " (IB-1" : " (IB-2");
        iiblocks.append(inval ? " IN-1)" : " IN-2)");

        ArrayList<String> blocks = new ArrayList<>(4);
        blocks.add(ccblocks.toString());
        blocks.add(ciblocks.toString());
        blocks.add(icblocks.toString());
        blocks.add(iiblocks.toString());

        Collections.shuffle(cons);
        Collections.shuffle(inc);
        Collections.shuffle(lat);

        for (int i = 1; i < 5; i++){
            tasks.add(order.remove(0) + lat.remove(0) + blocks.remove(0));
        }

        Collections.shuffle(tasks);


        //System.out.println(cons + " - Consistent Block Familiarization Trials");
        //System.out.println(inc + " - Inconsistent Block Familiarization Trials");
        //System.out.println(fc + " - FC stimuli distribution");
        //System.out.println();



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
        sb.append(tasks.get(0)); sb.append(',');
        sb.append(tasks.get(1)); sb.append(',');
        sb.append(tasks.get(2)); sb.append(',');
        sb.append(tasks.get(3)); sb.append(',');

        return sb.toString();
    }

    public static void printToCsv(String path){

        Path output = Paths.get(path);
        List<String> lines = new ArrayList<>();
        String header = "Consistent Block 1, Consistent Block 2, Consistent Block 3, Consistent Block 4, Consistent Block 5, Consistent Block 6, Consistent Block 7, Inconsistent Block 1, Inconsistent Block 2, Inconsistent Block 3, FC Task 1, FC Task 2, FC Task 3, FC Task 4";
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
