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
    protected List<String> order = new ArrayList<>(List.of("1", "2", "3", "4"));
    protected List<String> lat = new ArrayList<>(List.of("L", "L", "R", "R"));
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
        System.out.println(cons);
        System.out.println(inc);
        System.out.println(fc);
        System.out.println();

        /*
        Randomizes whichBlock so the blicket-distractor pairings are not always the same.
         */
        Collections.shuffle(whichBlock);
        if (whichBlock.indexOf(1) == 0) System.out.println("CC blicket 1, CI blicket 2"); else System.out.println("CC blicket 2, CI blicket 1");
        Collections.shuffle(whichBlock);
        if (whichBlock.indexOf(1) == 0) System.out.println("CC distr 1, IC distr 2"); else System.out.println("CC distr 2, IC distr 1");
        System.out.println();
        Collections.shuffle(whichBlock);
        if (whichBlock.indexOf(1) == 0) System.out.println("II blicket 1, IC blicket 2"); else System.out.println("II blicket 2, IC blicket 1");
        Collections.shuffle(whichBlock);
        if (whichBlock.indexOf(1) == 0) System.out.println("II distr 1, CI distr 2"); else System.out.println("II distr 2, CI distr 1");
    }

    public static void main(String[] args){
        Randomizer r = new Randomizer();
    }
}
