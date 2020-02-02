import org.apache.commons.lang3.ArrayUtils;

public class Rule {

    protected String[] conjunctions;
    protected String[][] terms;
    protected Boolean[] conjBool; // Initiated by the isBlicket() function.
    protected Boolean[][] evals; // Initiated by the isBlicket() function.

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
        for(String conj : conjunctions){
            ArrayUtils.add(terms,conj.split("&"));
        }
    }
}
