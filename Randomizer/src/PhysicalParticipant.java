import java.util.Date;

public class PhysicalParticipant {

    protected String subjectID;
    protected String sex;
    //protected Date dateOfBirth; //Unclear whether this is necessary right now.
    protected Integer age;
    protected String comments;
    protected boolean video;
    protected boolean excluded;
    protected String exclusionReason;
    protected float decimalAge;
    protected String[] taskOrder;
    protected String[] consistentOrder;
    protected String[] inconsistentOrder;

    protected String task1Left;
    protected String task1Right;
    protected String task1Choice;
    protected boolean task1Correct;
    protected BlicketTrial task1;

    protected String task2Left;
    protected String task2Right;
    protected String task2Choice;
    protected boolean task2Correct;
    protected BlicketTrial task2;

    protected String task3Left;
    protected String task3Right;
    protected String task3Choice;
    protected boolean task3Correct;
    protected BlicketTrial task3;

    protected String task4Left;
    protected String task4Right;
    protected String task4Choice;
    protected boolean task4Correct;
    protected BlicketTrial task4;

    protected boolean condition;
    protected boolean distractor;

    /**
     * Constructor for the data analysis input.
     * Represents the total data of a given session.
     * @param subjectID represents the unique ID for each participant.
     * @param sex records the participant's sex.
     * @param dateOfBirth records the participant's date of birth
     * @param age records the participant's age.
     * @param comments represents any comments about the trial or any additional issues
     *                 made by the coder or experimenter.
     * @param video is a boolean representing whether video was taken.
     * @param excluded is TRUE if the participant was excluded, FALSE if not.
     * @param exclusionReason explains why the participant was excluded.
     * @param decimalAge is a float recording the exact age of the participant.
     * @param //taskBlickets records the stimulus code of the blicket block in a trial.
     * @param //taskDistractors records the stimulus code of the distractor block in a trial.
     * @param //taskChoices records the participant's choice (L/R) of a block.
     * @param //taskCorrects records whether the participant made the correct choice.
     * @param //tasks imports the participant's choices as a BlicketTrial.
     * @param taskOrder records the pre-selected order (in L/R) of the blocks, as well
     *                  as the order of the presentation options for the blickets
     *                  (represented as 1, 2, 3, 4). Sample outcome: [1R, 4L, 3L, 2R].
     * @param consistentOrder records the pre-selected order of the consistent blocks
     *                        in the familiarization period. Possible numbers are 1-7.
     * @param inconsistentOrder records the pre-selected order of the inconsistent
     *                          blocks in the familiarization period. Numbers are 1-3.
     * @param condition records the condition of this testing session.
     * @param distractor records the salient naive rule.
     *
     */

    public PhysicalParticipant(String subjectID, String sex, String dateOfBirth, Integer age, String comments, String video, String excluded, String exclusionReason, float decimalAge, String task1Choice, String task2Choice, String task3Choice, String task4Choice, String task1Left, String task1Right, String task2Left, String task2Right, String task3Left, String task3Right, String task4Left, String task4Right, String taskOrder, String consistentOrder, String inconsistentOrder, String condition, String distractor) {
        this.subjectID = subjectID;
        this.sex = sex;
        //this.dateOfBirth = dateOfBirth; //Reformat according to DOB later when I have access to the CSV.
        this.age = age;
        this.comments = comments;
        this.video = video.contentEquals("Y");
        this.excluded = excluded.contentEquals("Y");
        this.exclusionReason = exclusionReason;
        this.decimalAge = decimalAge;
        this.task1Choice = task1Choice;
        this.task2Choice = task2Choice;
        this.task3Choice = task3Choice;
        this.task4Choice = task4Choice;
        this.taskOrder = taskOrder.split("");
        this.consistentOrder = consistentOrder.split("");
        this.inconsistentOrder = inconsistentOrder.split("");
        this.task1Correct = this.taskOrder[0].contains(task1Choice);
        this.task2Correct = this.taskOrder[1].contains(task2Choice);
        this.task3Correct = this.taskOrder[2].contains(task3Choice);
        this.task4Correct = this.taskOrder[3].contains(task4Choice);
        this.task1Left = task1Left;
        this.task2Left = task2Left;
        this.task3Left = task3Left;
        this.task4Left = task4Left;
        this.task1Right = task1Right;
        this.task2Right = task2Right;
        this.task3Right = task3Right;
        this.task4Right = task4Right;
        this.condition = condition.contains("e"); //True = near, false = distant
        this.distractor = condition.contains("b"); //True = blue, false = red
        this.task1 = new BlicketTrial(sideChooser(1), this.condition, this.distractor, sideUnchooser(1)); //FIX SO IT'S NOT ALWAYS CORRECT CHOICE
        this.task2 = new BlicketTrial(sideChooser(2), this.condition, this.distractor, sideUnchooser(2));
        this.task3 = new BlicketTrial(sideChooser(3), this.condition, this.distractor, sideUnchooser(3));
        this.task4 = new BlicketTrial(sideChooser(4), this.condition, this.distractor, sideUnchooser(4));

    }

    /**
     * Gets the string of the chosen stimulus on a given trial.
     * @param order is the number of the task chosen (1-4).
     * @return the string of the chosen stimulus.
     */
    public String sideChooser(Integer order){
        String[] left = {task1Left, task2Left, task3Left, task4Left};
        String[] right = {task1Right,task2Right, task3Right, task4Right};
        if(order == 1){
            if(task1Choice == "L") return left[0]; else return right[0];
        }else if(order == 2){
            if(task2Choice == "L") return left[1]; else return right[1];
        }else if(order == 3){
            if(task3Choice == "L") return left[2]; else return right[2];
        }else{
            if(task4Choice == "L") return left[3]; else return right[3];
        }
    }

    /**
     * Gets the string of the unchosen stimulus on a given trial.
     * @param order is the number of the task chosen (1-4).
     * @return the string of the chosen stimulus.
     */
    public String sideUnchooser(Integer order){
        String[] left = {task1Left, task2Left, task3Left, task4Left};
        String[] right = {task1Right,task2Right, task3Right, task4Right};
        if(order == 1){
            if(task1Choice == "R") return left[0]; else return right[0];
        }else if(order == 2){
            if(task2Choice == "R") return left[1]; else return right[1];
        }else if(order == 3){
            if(task3Choice == "R") return left[2]; else return right[2];
        }else{
            if(task4Choice == "R") return left[3]; else return right[3];
        }
    }
}
