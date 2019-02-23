public class Blicket {

    protected String stimulus;
    protected boolean blicketness;
    protected boolean consistency;
    protected boolean condition;
    protected boolean distractor;
    protected boolean[] features;

    /**
     * Constructor.
     * @blicketness determines whether the stimulus passed in is a blicket or not.
     * TRUE indicates that the stimulus is a blicket.
     * @consistency determines whether the stimulus is consistent with the naive rule.
     * TRUE indicates that the stimulus follows the naive rule.
     * @condition indicates which condition the stimulus is subject to.
     * TRUE indicates the near condition. FALSE indicates distant.
     * @distractor indicates the counterbalancing.
     * TRUE indicates a blue distractor. FALSE indicates red.
     */
    public Blicket(String stimulus, boolean condition, boolean distractor){
        this.stimulus = stimulus;
        this.condition = condition;
        this.distractor = distractor;
        this.consistency = isConsistent();
        this.blicketness = isBlicket();
        this.features = getFeatures();
    }

    public boolean[] getFeatures(){
        boolean[] list = new boolean[5];
        for (int x = 0; x <= 4; x++){
            list[x] = Colour(stimulus.charAt(x));
        }
        return list;
    }
    
    public boolean isBlicket(){
        if (condition){
            return isNearBlicket();
        } else {
            return !(getFeatures()[2] == getFeatures()[3]);
        }
    }

    public boolean isNearBlicket(){
        if (distractor){
            return (getFeatures()[0] & !getFeatures()[2]) ^ (!getFeatures()[0] & !getFeatures()[3]);
        } else {
            return (!getFeatures()[0] & getFeatures()[2]) ^ (getFeatures()[0] & getFeatures()[3]);
        }
    }
    /**
     * TRUE = blue.
     * FALSE = red.
     */
    public boolean Colour(Character c){
        return c.toString().contains("b");
    }

    public boolean isConsistent() {

        return (distractor == getFeatures()[0]) == (isBlicket());

        }

    public static void main(String[] args) {
        // Blicket b = new Blicket("brrrr", false, true);
        // System.out.println(b.isBlicket());
        // Blicket c = new Blicket("r00b0", true, true);
        // System.out.println(c.isBlicket());

        String str = new String("brrrr,rrbbb,rbrrr,brbrb,brbbb,rrrbb,bbrbr,rbbrr");
        String[] strings = str.split(",");
        for (String string : strings) {
            System.out.print(new Blicket(string,true,false).isConsistent() + ",");
        }


    }
    }