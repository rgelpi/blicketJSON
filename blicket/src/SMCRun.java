import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class SMCRun {

    protected Long[] probs = new Long[10];
    protected String[] rules = new String[10];
    protected String[] sigs = new String[10];
    protected Integer size;

    public SMCRun(JsonObject run){
        this.size = run.get("probs").getAsJsonArray().size();
        for(int i = 0; i < size; i++) {
            probs[i] = run.get("probs").getAsJsonArray().get(i).getAsLong();
            rules[i] = run.get("support").getAsJsonArray().get(i).getAsJsonObject().get("rule").getAsString();
            sigs[i] = run.get("support").getAsJsonArray().get(i).getAsJsonObject().get("signature").getAsString();
        }

    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Rule 1: " + rules[0] + ", Sig 1: " + sigs[0] + ", Prob 1: " + probs[0]);
        return sb.toString();
    }

}
