import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;

public class Blicket {

    protected String stimulus;
    protected Boolean blicketness;
    protected Boolean consistency;
    protected Boolean condition;
    protected Boolean distractor;
    protected Boolean[] features;

    /**
     * Represents the stimulus in a given blicket rating trial.
     * @param stimulus is the string indicating the features.
     * @param condition is the condition of the trial (TRUE = near, FALSE = distant)
     * @param distractor is the colour of the counterbalancing (TRUE = blue, FALSE = red).
     */
    public Blicket(String stimulus, boolean condition, boolean distractor){

        this.stimulus = stimulus;
        this.condition = condition;
        this.distractor = distractor;
        this.consistency = isConsistent();
        this.blicketness = isBlicket();
        this.features = getFeatures();

    }

    /**
     * An array representing the colours of the five features.
     * getFeatures[0] = background
     * getFeatures[1] = outline / star
     * getFeatures[2] = corners / circle
     * getFeatures[3] = centre-left triangle / square
     * getFeatures[4] = centre-right triangle / triangle
     * @return an array of booleans representing the features of the stimulus.
     */
    public Boolean[] getFeatures(){

        Boolean[] list = new Boolean[5];
        for (int x = 0; x <= 4; x++){
            list[x] = Colour(stimulus.charAt(x));
        }
        return list;

    }

    /**
     * Tests whether the block is a blicket according to the near or distant
     * rule. Contains a sub-function, isNearBlicket(), that specifically tests
     * whether the block is a near blicket
     * @return a boolean value indicating whether the block is a blicket.
     */
    public Boolean isBlicket(){

        if (condition){ return isNearBlicket(); }
        else { return !(getFeatures()[2] == getFeatures()[3]); }
    }

    public Boolean isNearBlicket(){

        if (distractor){ return (getFeatures()[0] & !getFeatures()[2]) ^ (!getFeatures()[0] & !getFeatures()[3]); }
        else { return (!getFeatures()[0] & getFeatures()[2]) ^ (getFeatures()[0] & getFeatures()[3]); }

    }

    /**
     * Tests whether the block is a blicket according to an arbitrary rule.
     * @param rule is a rule object represented as a disjunction of conjunctions (DNF).
     * @return a boolean value indicating whether the block is a blicket.
     */
    /* public Boolean isBlicket(Rule rule){
        /* int conjCount = 0;
        for(String[] conj : rule.terms){
            int featureCount = 0;
            for(String feature : conj){
                /*  Create an entry in the evaluation array that takes a feature term, e.g. "~A", and checks it
                    against the feature in the existing blicket object.
                    A feature evaluating to TRUE is a blue feature, so the term "~A" would specify a red background. */
        /*        System.out.println(feature);
                rule.evals[conjCount][featureCount] = feature.contains("~") != this.getFeatures()[parseIndex(feature)];
                featureCount++;
            }
            conjCount++;
        }
        /*  Finally, combine the two features in every conjunction into a single boolean,
            and leave a list of booleans that will be evaluated disjunctively. */
        /* for(Boolean[] eval : rule.evals){
            ArrayUtils.add(rule.conjBool,eval[0] & eval[1]);
        }
        /* If any one of the conjunctions is true, the block is a blicket according to the rule.*/
        /* return ArrayUtils.contains(rule.conjBool,true); */
        /* if (Rule.isTerminal(rule.feature1)){

        }
        return true;
    } */

    /**
     *
     * @param feature is the string value of the feature, a character from "A" to "E".
     * @return the index number of the designated feature in the blicket object.
     */
    public Integer parseIndex(String feature){
        if(feature.contains("A")){
            return 0;
        }
        if(feature.contains("B")){
            return 1;
        }
        if(feature.contains("C")){
            return 2;
        }
        if(feature.contains("D")){
            return 3;
        }
        if(feature.contains("E")){
            return 4;
        }
        else return 0; /* failsafe */
    }

    /**
     * TRUE = blue.
     * FALSE = red.
     */
    public Boolean Colour(Character c){
        return c.toString().contains("b");
    }

    public Boolean isConsistent() { return (distractor == getFeatures()[0]) == (isBlicket()); }

    public static void main(String[] args){

        String[] NR_test_blickets = {"rrbrr","rbbrb","rrbbb","rbbbr","brbbb","brrrr","brrrb","bbbrr","rbrrr","rbrbb"};
        String[] NB_test_blickets = {"bbrbb","brrbr","bbrrr","brrrb","rbrrr","rbbbb","rbbbr","rrrbb","brbbb","brbrr"};
        String[] DR_test_blickets = {"rrrbr","rrrbb","rrbrb","rbbrb","bbrbr","brrrr","bbrrb","bbbbr","rbrrr","rrbbb"};
        String[] DB_test_blickets = {"bbbrb","bbbrr","bbrbr","brrbr","rrbrb","rbbbb","rrbbr","rrrrb","brbbb","bbrrr"};

        Boolean[] NR_test_labels = new Boolean[10];
        Boolean[] NB_test_labels = new Boolean[10];
        Boolean[] DR_test_labels = new Boolean[10];
        Boolean[] DB_test_labels = new Boolean[10];

        for(String block : NR_test_blickets){
            Boolean out = new Blicket(block,true,false).isBlicket();
            NR_test_labels[ArrayUtils.indexOf(NR_test_blickets,block)] = out;
        }

        for(String block : NB_test_blickets){
            Boolean out = new Blicket(block,true,true).isBlicket();
            NB_test_labels[ArrayUtils.indexOf(NB_test_blickets,block)] = out;
        }

        for(String block : DR_test_blickets){
            Boolean out = new Blicket(block,false,false).isBlicket();
            DR_test_labels[ArrayUtils.indexOf(DR_test_blickets,block)] = out;
        }

        for(String block : DB_test_blickets){
            Boolean out = new Blicket(block,false,true).isBlicket();
            DB_test_labels[ArrayUtils.indexOf(DB_test_blickets,block)] = out;
        }

        System.out.println(Arrays.toString(NR_test_labels));
        System.out.println(Arrays.toString(NB_test_labels));
        System.out.println(Arrays.toString(DR_test_labels));
        System.out.println(Arrays.toString(DB_test_labels));
    }
}