import java.util.ArrayList;
import java.util.Arrays;

public class Trials {

    protected Integer participantId;
    protected boolean cond;
    protected boolean dist;
    protected String condition;
    protected String distractor;
    protected boolean attention;
    protected String shape;

    protected BlicketTrial FC1;
    protected BlicketTrial FC2;
    protected BlicketTrial FC3;
    protected BlicketTrial FC4;
    protected BlicketTrial[] FC;

    protected BlicketTrial R1;
    protected BlicketTrial R2;
    protected BlicketTrial R3;
    protected BlicketTrial R4;
    protected BlicketTrial R5;
    protected BlicketTrial R6;
    protected BlicketTrial R7;
    protected BlicketTrial R8;
    protected BlicketTrial[] R;

    protected Integer scoreCC = 0;
    protected Integer scoreCI = 0;
    protected Integer scoreIC = 0;
    protected Integer scoreII = 0;

    protected Integer scoreBC = 0;
    protected Integer scoreBI = 0;
    protected Integer scoreNC = 0;
    protected Integer scoreNI = 0;

    //For use with independent confidence and correctness calculations.
    protected Integer corScoreBC = 0;
    protected Integer corScoreBI = 0;
    protected Integer corScoreNC = 0;
    protected Integer corScoreNI = 0;

    protected Integer conScoreBC = 0;
    protected Integer conScoreBI = 0;
    protected Integer conScoreNC = 0;
    protected Integer conScoreNI = 0;

    //For use with ChanceToRateBlicket.
    protected Integer chanceBC = 0;
    protected Integer chanceBI = 0;
    protected Integer chanceNC = 0;
    protected Integer chanceNI = 0;

    protected char c = ',';

    //For use with physical blicket task.
    protected String subjectID;
    protected boolean excluded;

    //For use with getNovelties.
    protected final ArrayList<String> seenNR = new ArrayList<>(Arrays.asList("rrbrr","rbbrb","rrbbb","rbbbr","brbbb","brrrr","brrrb","bbbrr","rbrrr","rbrbb"));
    protected final ArrayList<String> seenNB = new ArrayList<>(Arrays.asList("bbrbb","brrbr","bbrrr","brrrb","rbrrr","rbbbb","rbbbr","rrrbb","brbbb","brbrr"));
    protected final ArrayList<String> seenDR = new ArrayList<>(Arrays.asList("rrrbr","rrrbb","rrbrb","rbbrb","bbrbr","brrrr","bbrrb","bbbbr","rbrrr","rrbbb"));
    protected final ArrayList<String> seenDB = new ArrayList<>(Arrays.asList("bbbrb","bbbrr","bbrbr","brrbr","rrbrb","rbbbb","rrbbr","rrrrb","brbbb","bbrrr"));

    /**
     * Collates the data on the participant's trials into sorted bins for use in R.
     * Uses data from a JSON object from the web app.
     * @param j is an object containing the participant's responses on the trials.
     */
    public Trials(JSONParticipant j, Integer participantId){

        this.participantId = participantId;

        this.condition = j.condition;
        this.distractor = j.distractor;
        this.cond = j.condition.contains("near");
        this.dist = j.distractor.contains("blue");

        if (Integer.parseInt(j.attention1) == 1 && Integer.parseInt(j.attention2) == 1){
            this.attention = true;
        } else { this.attention = false; }

        this.shape = j.shape;

        this.FC1 = new BlicketTrial(j.fc_1_selection,this.cond,this.dist,j.fc_1_unselected);
        this.FC2 = new BlicketTrial(j.fc_2_selection,this.cond,this.dist,j.fc_2_unselected);
        this.FC3 = new BlicketTrial(j.fc_3_selection,this.cond,this.dist,j.fc_3_unselected);
        this.FC4 = new BlicketTrial(j.fc_4_selection,this.cond,this.dist,j.fc_4_unselected);
        this.FC = new BlicketTrial[]{this.FC1, this.FC2, this.FC3, this.FC4};

        this.R1 = new BlicketTrial(j.rating_1_ID,this.cond,this.dist,j.rating_1_Value,j.rating_1_IsBlicket);
        this.R2 = new BlicketTrial(j.rating_2_ID,this.cond,this.dist,j.rating_2_Value,j.rating_2_IsBlicket);
        this.R3 = new BlicketTrial(j.rating_3_ID,this.cond,this.dist,j.rating_3_Value,j.rating_3_IsBlicket);
        this.R4 = new BlicketTrial(j.rating_4_ID,this.cond,this.dist,j.rating_4_Value,j.rating_4_IsBlicket);
        this.R5 = new BlicketTrial(j.rating_5_ID,this.cond,this.dist,j.rating_5_Value,j.rating_5_IsBlicket);
        this.R6 = new BlicketTrial(j.rating_6_ID,this.cond,this.dist,j.rating_6_Value,j.rating_6_IsBlicket);
        this.R7 = new BlicketTrial(j.rating_7_ID,this.cond,this.dist,j.rating_7_Value,j.rating_7_IsBlicket);
        this.R8 = new BlicketTrial(j.rating_8_ID,this.cond,this.dist,j.rating_8_Value,j.rating_8_IsBlicket);
        this.R = new BlicketTrial[]{this.R1, this.R2, this.R3, this.R4, this.R5, this.R6, this.R7, this.R8};

        for (BlicketTrial b : this.FC){
            if (b.sort == "CC"){ this.scoreCC = b.score; }
            if (b.sort == "CI"){ this.scoreCI = b.score; }
            if (b.sort == "IC"){ this.scoreIC = b.score; }
            if (b.sort == "II"){ this.scoreII = b.score; }
        }

        for (BlicketTrial b : this.R){
            if (b.sort == "BC"){ this.scoreBC += b.score; this.corScoreBC += b.corr; this.conScoreBC += b.confidence; this.chanceBC += b.response? 1 : 0; }
            if (b.sort == "BI"){ this.scoreBI += b.score; this.corScoreBI += b.corr; this.conScoreBI += b.confidence; this.chanceBI += b.response? 1 : 0; }
            if (b.sort == "NC"){ this.scoreNC += b.score; this.corScoreNC += b.corr; this.conScoreNC += b.confidence; this.chanceNC += b.response? 1 : 0; }
            if (b.sort == "NI"){ this.scoreNI += b.score; this.corScoreNI += b.corr; this.conScoreNI += b.confidence; this.chanceNI += b.response? 1 : 0; }
        }

    }

    /**
     * Collates the data on the participant's trials into sorted bins for use in R.
     * For use with physical blicket task data.
     * @param a is a test session object.
     */
    public Trials(PhysicalParticipant a){
        this.subjectID = a.subjectID;
        this.cond = a.condition;
        this.dist = a.distractor;
        this.FC1 = a.task1;
        this.FC2 = a.task2;
        this.FC3 = a.task3;
        this.FC4 = a.task4;
        this.FC = new BlicketTrial[]{this.FC1, this.FC2, this.FC3, this.FC4};

        for (BlicketTrial b : this.FC) {
            if (b.sort == "CC") { this.scoreCC = b.score; }
            if (b.sort == "CI") { this.scoreCI = b.score; }
            if (b.sort == "IC") { this.scoreIC = b.score; }
            if (b.sort == "II") { this.scoreII = b.score; }
        }

        this.excluded = a.excluded;

        if(this.cond) this.condition = "near"; else this.condition = "distant";
        if(this.dist) this.distractor = "blue"; else this.distractor = "red";

    }

    public String getSplitScores(){
        StringBuilder sb = new StringBuilder();
        sb.append(c); sb.append(c);
        sb.append(corScoreBC); sb.append(c);
        sb.append(corScoreBI); sb.append(c);
        sb.append(corScoreNC); sb.append(c);
        sb.append(corScoreNI); sb.append(c);
        sb.append(c);
        sb.append(conScoreBC); sb.append(c);
        sb.append(conScoreBI); sb.append(c);
        sb.append(conScoreNC); sb.append(c);
        sb.append(conScoreNI); sb.append(c);
        sb.append(c);
        sb.append(chanceBC); sb.append(c);
        sb.append(chanceBI); sb.append(c);
        sb.append(chanceNC); sb.append(c);
        sb.append(chanceNI); sb.append(c);
        return sb.toString();
    }

    public String getNovelties(BlicketTrial[] b){
        StringBuilder sb = new StringBuilder();
        Integer novelBC = 0;
        Integer novelBI = 0;
        Integer novelNC = 0;
        Integer novelNI = 0;
        Integer seenBC = 0;
        Integer seenBI = 0;
        Integer seenNC = 0;
        Integer seenNI = 0;
        sb.append(participantId); sb.append(c);
        sb.append(condition); sb.append(c);
        sb.append(distractor); sb.append(c);
        ArrayList<String> list;
        if (cond&&dist) list = seenNB;
        else if (cond&&(!dist)) list = seenNR;
        else if ((!cond)&&dist) list = seenDB;
        else list = seenDR;
        for (BlicketTrial t : b) {
            t.setNovelty(list.contains(t.stimulus));
            switch(t.sort){
                case("BC"): if(t.novelty) novelBC += t.corr; else { seenBC += t.corr; } break;
                case("BI"): if(t.novelty) novelBI += t.corr; else { seenBI += t.corr; } break;
                case("NC"): if(t.novelty) novelNC += t.corr; else { seenNC += t.corr; } break;
                case("NI"): if(t.novelty) novelNI += t.corr; else { seenNI += t.corr; } break;
                default: break;
            }
        }
        for (BlicketTrial t : b){
            sb.append(t.novelty); sb.append(c);
        }
        for (BlicketTrial t : b){
            sb.append(t.sort); sb.append(c);
        }
        for (BlicketTrial t : b){
            sb.append(t.corr); sb.append(c);
        }
        sb.append(novelBC); sb.append(c);
        sb.append(seenBC); sb.append(c);
        sb.append(novelBI); sb.append(c);
        sb.append(seenBI); sb.append(c);
        sb.append(novelNC); sb.append(c);
        sb.append(seenNC); sb.append(c);
        sb.append(novelNI); sb.append(c);
        sb.append(seenNI);
        return sb.toString();
    }

    public String toStringPhys(){
        StringBuilder sb = new StringBuilder();
        sb.append(subjectID); sb.append(c);
        sb.append(condition); sb.append(c);
        sb.append(distractor); sb.append(c);
        sb.append(scoreCC); sb.append(c);
        sb.append(scoreCI); sb.append(c);
        sb.append(scoreIC); sb.append(c);
        sb.append(scoreII); sb.append(c);
        sb.append(excluded);

        return sb.toString();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(participantId); sb.append(c);
        sb.append(condition); sb.append(c);
        sb.append(distractor); sb.append(c);
        sb.append(c);
        sb.append(scoreCC); sb.append(c);
        sb.append(scoreCI); sb.append(c);
        sb.append(scoreIC); sb.append(c);
        sb.append(scoreII); sb.append(c);
        sb.append(c);
        sb.append(scoreBC); sb.append(c);
        sb.append(scoreBI); sb.append(c);
        sb.append(scoreNC); sb.append(c);
        sb.append(scoreNI); sb.append(c);
        sb.append(attention); sb.append(c);
        sb.append(shape);

        return sb.toString();
    }
}
