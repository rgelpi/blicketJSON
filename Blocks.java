public class Blocks {

    protected String CB1;
    protected String CB2;
    protected String CN1;
    protected String CN2;
    protected String IB1;
    protected String IB2;
    protected String IN1;
    protected String IN2;

    public Blocks(String condition){
        switch(condition){
            case "near red":
                CB1 = "rbbrr";
                CB2 = "rrbrb";
                CN1 = "bbrrr";
                CN2 = "brbrr";
                IB1 = "bbrbr";
                IB2 = "brrbr";
                IN1 = "rrrrb";
                IN2 = "rbrbr";
            case "near blue":
                CB1 = "brrbb";
                CB2 = "bbrbr";
                CN1 = "rrbbb";
                CN2 = "rbrbb";
                IB1 = "rrbrb";
                IB2 = "rbbrb";
                IN1 = "bbbbr";
                IN2 = "brbrb";
            case "distant red":
                CB1 = "rrbrr";
                CB2 = "rbrbr";
                CN1 = "brrrb";
                CN2 = "brbbr";
                IB1 = "brrbb";
                IB2 = "bbbrr";
                IN1 = "rbrrb";
                IN2 = "rbbbb";
            case "distant blue":
                CB1 = "bbrbb";
                CB2 = "brbrb";
                CN1 = "rbbbr";
                CN2 = "rbrrb";
                IB1 = "rbbrr";
                IB2 = "rrrbb";
                IN1 = "brbbr";
                IN2 = "brrrr";
        }
    }
}
