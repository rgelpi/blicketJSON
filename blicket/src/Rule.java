import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class Rule {

    protected String[] conjunctions;
    protected String[][] terms = new String[1][];
    //protected Boolean[] conjBool; // Initiated by the isBlicket() function.
    //protected Boolean[][] evals = new Boolean[0][0]; // Initiated by the isBlicket() function.

    protected String operator;
    protected String feature1;
    protected String feature2;

    /**
     *  This class interacts with BlicketTrial objects and assigns a probability of being chosen
     *  for a given arbitrary rule, not just the near, distant, or naive rules.
     *
     *  Note that in this program, all rules must be evaluated using the disjunctive normal form.
     *  Rules that use alternative structures, such as equality, inequality,
     *
     * @param rule is the disjunctive normal form of the proposed rule for testing.
     *             The rule must be formatted with zero spaces, two terms in each
     *             conjunction linked by the character "&", and an arbitrary number
     *             of conjunctions linked by the character "v".
     */
    public Rule(String rule){
        /* Example rule: "A&~Bv~A&~C", a.k.a. the near rule.
           This would yield a two-item list, ["A&~B", "~A&~C"]. */
        this.conjunctions = rule.split("v");
        /* Iterates over the list of conjunctions to yield a list
           of lists, each one of which can be negated or not. The
           output of this line: [["A", "~B"],["~A", "~C"]. */
        Integer termsAdded = 0;
        for(String conj : conjunctions){
            String[] term = conj.split("&");
            this.terms[termsAdded] = term;
            termsAdded++;
        }
    }

    public Rule(String feature1, String operator, String feature2){
        this.operator = operator;
        this.feature1 = feature1;
        this.feature2 = feature2;
    }

    public static Boolean isTerminal(String feature){
        return feature.length() <= 2; //All terminals will be of the form F or ~F
    }

    public String conjugate(){
        StringBuilder sb = new StringBuilder();
        if(!Rule.isTerminal(feature1)) sb.append("(").append(feature1).append(")");
        else sb.append(feature1);
        sb.append(operator);
        if(!Rule.isTerminal(feature2)) sb.append("(").append(feature2).append(")");
        else sb.append(feature2);
        return sb.toString();
    }

    public Boolean evaluate(String feature, Blicket blicket){
        Boolean out = true;
        String[] evals = feature.split("~");
        String test = "";
        if(feature.contains("~")){ out = false; test = evals[1]; } else test = feature;
        switch(test){
            case "A": return blicket.getFeatures()[0] && out;
            case "B": return blicket.getFeatures()[1] && out;
            case "C": return blicket.getFeatures()[2] && out;
            case "D": return blicket.getFeatures()[3] && out;
            case "E": return blicket.getFeatures()[4] && out;
            default: return out;
        }
    }

    public static Boolean flip(){
        Double outcome = Math.random();
        return outcome > 0.5;
    }
}
