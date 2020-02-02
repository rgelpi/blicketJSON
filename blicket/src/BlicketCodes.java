public class BlicketCodes {

    public String CB1;
    public String CB2;
    public String IB1;
    public String IB2;
    public String CN1;
    public String CN2;
    public String IN1;
    public String IN2;

    public BlicketCodes(String condition, String distractor){
        if(condition == "near"){
            if(distractor == "blue"){
                this.CB1 = "brrbb";
                this.CB2 = "bbrbr";
                this.IB1 = "rrbrb";
                this.IB2 = "rbbrb";
                this.CN1 = "rrbbb";
                this.CN2 = "rbrbb";
                this.IN1 = "bbbbr";
                this.IN2 = "brbrb";
            }else{
                this.CB1 = "rbbrr";
                this.CB2 = "rrbrb";
                this.IB1 = "bbrbr";
                this.IB2 = "brrbr";
                this.CN1 = "bbrrr";
                this.CN2 = "brbrr";
                this.IN1 = "rrrrb";
                this.IN2 = "rbrbr";
            }
        }else{
            if(distractor == "blue"){
                this.CB1 = "bbrbb";
                this.CB2 = "brbrb";
                this.IB1 = "rbbrr";
                this.IB2 = "rrrbb";
                this.CN1 = "rbbbr";
                this.CN2 = "rbrrb";
                this.IN1 = "brbbr";
                this.IN2 = "brrrr";
            }else{
                this.CB1 = "rrbrr";
                this.CB2 = "rbrbr";
                this.IB1 = "brrbb";
                this.IB2 = "bbbrr";
                this.CN1 = "brrrb";
                this.CN2 = "brbbr";
                this.IN1 = "rbrrb";
                this.IN2 = "rbbbb";
            }
        }
    }
    public String getStimulus(String input){
        switch(input){
            case "CB1": return this.CB1;
            case "CB2": return this.CB2;
            case "IB1": return this.IB1;
            case "IB2": return this.IB2;
            case "CN1": return this.CN1;
            case "CN2": return this.CN2;
            case "IN1": return this.IN1;
            case "IN2": return this.IN2;
            default: return "";
        }
    }
}
