import com.google.gson.*;
import java.util.Map;
import java.util.ArrayList;

public class BlicketLikelihood extends Blicket {

    protected Double logLikelihood;
    protected Integer response;
    protected final Double noiseParam = 0.05;
    public BlicketLikelihood(Blicket b, Rule r, Integer response){
        super(b.stimulus,b.condition,b.distractor);
        // this.blicketness = isBlicket(r);
        this.response = response;
        this.logLikelihood = (this.response == 1) ?
                Math.log(this.blicketness ? 1-this.noiseParam : noiseParam) :
                Math.log(this.blicketness ? this.noiseParam : 1-noiseParam);
    }

    public Double getLogLikelihood(){
        return logLikelihood;
    }

    public static void main(String[] args){
        /* Rule anotc = new Rule("A", "&", "~C");
        BlicketLikelihood b = new BlicketLikelihood(new Blicket("bbrrr",true,true),anotc,1);
        Double d = b.getLogLikelihood();
        System.out.println(d); */



    }
}
