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

    protected char c = ',';

    /**
     * Collates the data on the participant's trials into sorted bins for use in R.
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
            if (b.sort == "BC"){ this.scoreBC += b.score; }
            if (b.sort == "BI"){ this.scoreBI += b.score; }
            if (b.sort == "NC"){ this.scoreNC += b.score; }
            if (b.sort == "NI"){ this.scoreNI += b.score; }
        }

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
