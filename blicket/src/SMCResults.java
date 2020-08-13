import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SMCResults {

    protected SMCRun[] runs;
    protected Integer n_runs;
    protected Integer n_obs;
    protected Integer n_particles;
    protected String condition;
    protected Map<String,Long> marginals = new HashMap<>();
    protected Map<String,String> sigLookup = new HashMap<>();
    protected ArrayList<String> lines = new ArrayList<>();

    public SMCResults(Integer n_obs, Integer n_particles, String condition, SMCRun[] runs){
        this.n_obs = n_obs;
        this.n_particles = n_particles;
        this.condition = condition;
        this.runs = runs;
        this.n_runs = runs.length;
        for(SMCRun run : runs){
            for(int i = 0; i < run.size; i++){
                if(this.marginals.containsKey(run.sigs[i])){
                    Long value = this.marginals.get(run.sigs[i]);
                    System.out.println(value);
                    this.marginals.put(run.sigs[i],value + run.probs[i]);
                }else{
                    this.marginals.putIfAbsent(run.sigs[i],run.probs[i]);
                }
                this.sigLookup.putIfAbsent(run.rules[i],run.sigs[i]);
            }
        }
        lines.add("sig,probs,rule");
        for(Map.Entry<String, Long> pair : marginals.entrySet()){
            StringBuilder rule = new StringBuilder();
            for(Map.Entry<String, String> rule_pair : sigLookup.entrySet()) {
                if (rule_pair.getValue().equals(pair.getKey())) {
                    rule.append(rule_pair.getKey());
                }
            }
            lines.add(pair.getKey() + "," + (pair.getValue()) + "," + rule.toString());
        }
    }

    public static SMCRun[] reshapeSMCOutput(Integer obs, Integer particles){

        Path[] paths = new Path[100];
        SMCRun[] all_runs = new SMCRun[100];
        for(int i = 1; i <= 100; i++){
            paths[i-1] = Paths.get("/Users/rgelpi/Downloads/obs_results/rule_" + particles + "particles_" + obs + "obs_" + i + ".json");
            System.out.println(paths[i-1]);
            Gson gson = new Gson();
            try {
                String json = new String(Files.readAllBytes(paths[i-1]));
                System.out.println(json);
                JsonParser parser = new JsonParser();
                JsonObject o = parser.parse(json).getAsJsonObject();
                all_runs[i-1] = new SMCRun(o);
                System.out.println(all_runs[i-1]);
            } catch (IOException e) { e.printStackTrace(); }
        }
        return all_runs;
    }

    public void printMarginals(Path output){
        try { Files.write(output,lines, StandardCharsets.UTF_8); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public static void main(String[] args){
        Integer obs = 9;
        Integer particles = 1;
        String cond = "NC";
        SMCResults results = new SMCResults(obs,particles,cond,reshapeSMCOutput(obs,particles));
        results.printMarginals(Paths.get("/Users/rgelpi/Downloads/obs_csvs/rule_" + particles + "particles_" + obs + "obs_" + cond + "_marginals.csv"));
    }

}
