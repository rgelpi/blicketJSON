public class Blicket {

    protected String stimulus;
    protected boolean blicketness;
    protected boolean consistency;
    protected boolean condition;
    protected boolean distractor;
    protected boolean[] features;

    /**
     * Represents the stimulus in a given blicket rating trial.
     * @param stimulus is the string indicating the features.
     * @param condition is the condition of the trial (TRUE = blue, FALSE = red)
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
     * getFeatures[1] = outline
     * getFeatures[2] = corners
     * getFeatures[3] = centre-left triangle
     * getFeatures[4] = centre-right triangle
     * @return an array of booleans representing the features of the stimulus.
     */
    public boolean[] getFeatures(){

        boolean[] list = new boolean[5];
        for (int x = 0; x <= 4; x++){
            list[x] = Colour(stimulus.charAt(x));
        }
        return list;

    }

    public boolean isBlicket(){

        if (condition){ return isNearBlicket(); }
        else { return !(getFeatures()[2] == getFeatures()[3]); }
    }

    public boolean isNearBlicket(){

        if (distractor){ return (getFeatures()[0] & !getFeatures()[2]) ^ (!getFeatures()[0] & !getFeatures()[3]); }
        else { return (!getFeatures()[0] & getFeatures()[2]) ^ (getFeatures()[0] & getFeatures()[3]); }

    }

    /**
     * TRUE = blue.
     * FALSE = red.
     */
    public boolean Colour(Character c){
        return c.toString().contains("b");
    }

    public boolean isConsistent() { return (distractor == getFeatures()[0]) == (isBlicket()); }

    }