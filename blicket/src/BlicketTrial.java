public class BlicketTrial extends Blicket {

    public Integer confidence;
    public boolean correctness;
    public Integer corr;
    public boolean response;
    public Blicket alternative;
    public Integer score;
    public String sort;
    public boolean novelty;

    /**
     * This constructor represents a given forced-choice trial in EE.
     * @param alt represents the unselected stimulus in a forced-choice trial.
     * Used to create a Blicket object against which the selected stimulus can
     * be compared.
     */
    public BlicketTrial(String stimulus, boolean condition, boolean distractor, String alt){

        super(stimulus,condition,distractor);
        this.alternative = new Blicket(alt,condition,distractor);
        this.correctness = this.isBlicket();
        if (this.correctness){ this.score = 1; }
        else { this.score = 0; }
        if (this.correctness) {
            if (this.isConsistent()) {
                if (this.alternative.isConsistent()) {
                    sort = "CC";
                } else {
                    sort = "CI";
                }
            } else {
                if (this.alternative.isConsistent()) {
                    sort = "IC";
                } else {
                    sort = "II";
                }
            }
        } else {
            if (this.isConsistent()) {
                if (this.alternative.isConsistent()) {
                    sort = "CC";
                } else {
                    sort = "IC";
                }
            } else {
                if (this.alternative.isConsistent()) {
                    sort = "CI";
                } else {
                    sort = "II";
                }
            }
        }

    }

    /**
     * This constructor represents a blicket rating trial in EE.
     * @param confidence is the value of the rating given.
     * @param response represents whether the participant thought the stimulus was a blicket or not.
     */
    public BlicketTrial(String stimulus, Boolean condition, Boolean distractor, Integer confidence, Integer response) {

        super(stimulus, condition, distractor);
        this.response = response == 1;
        this.confidence = confidence;
        if (this.response == this.isBlicket()) {
            this.score = this.confidence;
            this.corr = 1;
        } else {
            this.score = -1 * this.confidence;
            this.corr = 0;
        }
        if (this.isBlicket()) {
            if (this.isConsistent()) {
                sort = "BC";
            } else {
                sort = "BI";
            }
        } else {
            if (this.isConsistent()) {
                sort = "NC";
            } else {
                sort = "NI";
            }
        }
    }

    public void setNovelty(boolean input){
        this.novelty = input;
    }

}
