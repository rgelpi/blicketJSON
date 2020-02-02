import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProbabilityMaker {

    protected String[] values;
    protected String subjectId;
    protected Boolean condition;
    protected Boolean distractor;
    protected String age;

    protected Blicket T1L;
    protected Blicket T1R;
    protected Blicket T2L;
    protected Blicket T2R;
    protected Blicket T3L;
    protected Blicket T3R;
    protected Blicket T4L;
    protected Blicket T4R;

    protected String[] choices;

    protected Boolean inclusion;

    /* The actual choices made by participants. */
    protected BlicketTrial T1;
    protected BlicketTrial T2;
    protected BlicketTrial T3;
    protected BlicketTrial T4;



    public ProbabilityMaker(String[] line){
        this.values = line;
        this.subjectId = values[0];
        this.age = values[1];
        this.inclusion = values[2].contains("N");
        this.condition = values[3].contains("n");
        this.distractor = values[4].contains("b");

        for(int i = 0; i <= 3; i++){
            choices[i] = values[i+5];
        }

        this.T1L = new Blicket(values[9], this.condition, this.distractor);
        this.T1R = new Blicket(values[10], this.condition, this.distractor);
        this.T2L = new Blicket(values[11], this.condition, this.distractor);
        this.T2R = new Blicket(values[12], this.condition, this.distractor);
        this.T3L = new Blicket(values[13], this.condition, this.distractor);
        this.T3R = new Blicket(values[14], this.condition, this.distractor);
        this.T4L = new Blicket(values[15], this.condition, this.distractor);
        this.T4R = new Blicket(values[16], this.condition, this.distractor);

        this.T1 = (this.choices[0].contains("L") ? new BlicketTrial(this.T1L.stimulus,this.condition,this.distractor,this.T1R.stimulus) : new BlicketTrial(this.T1R.stimulus,this.condition,this.distractor,this.T1L.stimulus));
        this.T2 = (this.choices[1].contains("L") ? new BlicketTrial(this.T2L.stimulus,this.condition,this.distractor,this.T2R.stimulus) : new BlicketTrial(this.T2R.stimulus,this.condition,this.distractor,this.T2L.stimulus));
        this.T3 = (this.choices[2].contains("L") ? new BlicketTrial(this.T3L.stimulus,this.condition,this.distractor,this.T3R.stimulus) : new BlicketTrial(this.T3R.stimulus,this.condition,this.distractor,this.T3L.stimulus));
        this.T4 = (this.choices[3].contains("L") ? new BlicketTrial(this.T4L.stimulus,this.condition,this.distractor,this.T4R.stimulus) : new BlicketTrial(this.T4R.stimulus,this.condition,this.distractor,this.T4L.stimulus));
    }

    /**
     * Get the probability of choosing a block according to a given rule.
     * @param block1 is the first forced-choice block being tested in the trial.
     * @param block2 is the second forced-choice block being tested in the trial.
     * @param input is the string form of the rule to be initiated in the program.
     * @param noiseFactor is a probability from 0 to 1 that a participant will not
     *                    follow the outcome of the rule, and instead discard it
     *                    and choose the other block.
     * @return an array of two doubles representing the probability of choosing the
     * target stimulus and the probability of choosing the alternative stimulus.
     */
    /* public static Double[] getProbs(Blicket block1, Blicket block2, String input, Double noiseFactor){
        Rule rule = new Rule(input);
        Double block1Prob = block1.isBlicket(rule) ? 1.0-noiseFactor : 0.0+noiseFactor;
        Double block2Prob = block2.isBlicket(rule) ? 1.0-noiseFactor : 0.0+noiseFactor;
        // If both are blickets or non-blickets, guess equally, otherwise report the answer
        return (block1Prob == block2Prob) ? new Double[] {0.5, 0.5} : new Double[] {block1Prob, block2Prob};
    } */

    public static void printProbs(String input, String output){
        Path out = Paths.get(output);
        List<String> lines = new ArrayList<>();
        lines.add("subjectId,T1LProb,T1RProb,T2LProb,T2RProb,T3LProb,T3RProb,T4LProb,T4RProb");
        try(Scanner scanner = new Scanner(Paths.get(input))){
            scanner.nextLine();
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                ProbabilityMaker p = new ProbabilityMaker(fields);

            }
            }catch(IOException e){
            e.printStackTrace();
        }
    }

    /* public String toString(String rule, Double noiseFactor){
        Double[] task1 = getProbs(T1L,T1R,rule,noiseFactor);
        Double[] task2 = getProbs(T2L,T2R,rule,noiseFactor);
        Double[] task3 = getProbs(T3L,T3R,rule,noiseFactor);
        Double[] task4 = getProbs(T4L,T4R,rule,noiseFactor);
        StringBuilder sb = new StringBuilder();
        sb.append(subjectId); sb.append(',');
        sb.append(task1[0]); sb.append(',');
        sb.append(task1[1]); sb.append(',');
        sb.append(task2[0]); sb.append(',');
        sb.append(task2[1]); sb.append(',');
        sb.append(task3[0]); sb.append(',');
        sb.append(task3[1]); sb.append(',');
        sb.append(task4[0]); sb.append(',');
        sb.append(task4[1]);
        return sb.toString();
    } */
}
