import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImaginaryData {

    public ImaginaryData(){
    }

    public static ArrayList<ImaginaryParticipant> makeParticipants(){

        ArrayList<ImaginaryParticipant> near4 = new ArrayList<>();
        ArrayList<ImaginaryParticipant> near5 = new ArrayList<>();
        ArrayList<ImaginaryParticipant> near6 = new ArrayList<>();
        ArrayList<ImaginaryParticipant> dist4 = new ArrayList<>();
        ArrayList<ImaginaryParticipant> dist5 = new ArrayList<>();
        ArrayList<ImaginaryParticipant> dist6 = new ArrayList<>();
        ArrayList<ImaginaryParticipant> participants = new ArrayList<>();

        for(Integer i = 1; i <= 15; i++){

            Integer j = i+15;
            Integer k = j+15;
            Integer l = k+15;
            Integer m = l+15;
            Integer n = m+15;

            near4.add(new ImaginaryParticipant(i.toString(),true,ageMaker(4),"4",flip(),flip(0.4),flip(),flip(0.4)));
            near5.add(new ImaginaryParticipant(j.toString(),true,ageMaker(5),"5",flip(),flip(0.4),flip(),flip(0.4)));
            near6.add(new ImaginaryParticipant(k.toString(),true,ageMaker(6),"6",flip(0.8),flip(),flip(0.6),flip(0.6)));
            dist4.add(new ImaginaryParticipant(l.toString(),false,ageMaker(4),"4",flip(0.6),flip(0.4),flip(0.6),flip(0.4)));
            dist5.add(new ImaginaryParticipant(m.toString(),false,ageMaker(5),"5",flip(),flip(0.35),flip(),flip(0.35)));
            dist6.add(new ImaginaryParticipant(n.toString(),false,ageMaker(6),"6",flip(),flip(0.25),flip(),flip(0.25)));
        }

        participants.addAll(near4);
        participants.addAll(near5);
        participants.addAll(near6);
        participants.addAll(dist4);
        participants.addAll(dist5);
        participants.addAll(dist6);

        return participants;
    }

    public static ArrayList<ImaginaryParticipant> makeContinuousParticipants(){

        ArrayList<ImaginaryParticipant> near = new ArrayList<>(45);
        ArrayList<ImaginaryParticipant> dist = new ArrayList<>(45);
        ArrayList<ImaginaryParticipant> participants = new ArrayList<>();

        for(Integer i = 1; i <= 15; i++){

            Integer j = i+15;
            Integer k = j+15;
            Integer l = k+15;
            Integer m = l+15;
            Integer n = m+15;

            double near4Age = ageMaker(4);
            double near5Age = ageMaker(5);
            double near6Age = ageMaker(6);
            double dist4Age = ageMaker(4);
            double dist5Age = ageMaker(5);
            double dist6Age = ageMaker(6);

            near.add(new ImaginaryParticipant(i.toString(),true,near4Age,"4",flip(0.5+((near4Age-4)/10)),flip(),flip(0.5+((near4Age-4)/30)),flip(0.5+((near4Age-4)/30))));
            near.add(new ImaginaryParticipant(j.toString(),true,near5Age,"5",flip(0.5+((near5Age-4)/10)),flip(),flip(0.5+((near5Age-4)/30)),flip(0.5+((near4Age-4)/30))));
            near.add(new ImaginaryParticipant(k.toString(),true,near6Age,"6",flip(0.5+((near6Age-4)/10)),flip(),flip(0.5+((near6Age-4)/30)),flip(0.5+((near4Age-4)/30))));
            dist.add(new ImaginaryParticipant(l.toString(),false,dist4Age,"4",flip(0.6-((dist4Age/4)/30)),flip(0.45-((dist4Age/4)/15)),flip(0.6-((dist4Age/4)/30)),flip(0.45-((dist4Age/4)/15))));
            dist.add(new ImaginaryParticipant(m.toString(),false,dist5Age,"5",flip(0.6-((dist5Age/4)/30)),flip(0.45-((dist5Age/4)/15)),flip(0.6-((dist5Age/4)/30)),flip(0.45-((dist5Age/4)/15))));
            dist.add(new ImaginaryParticipant(n.toString(),false,dist6Age,"6",flip(0.6-((dist6Age/4)/30)),flip(0.45-((dist6Age/4)/15)),flip(0.6-((dist6Age/4)/30)),flip(0.45-((dist6Age/4)/15))));
        }

        participants.addAll(near);
        participants.addAll(dist);

        return participants;
    }

    public static void printToCsv(String path,ArrayList<ImaginaryParticipant> participants){
        Path output = Paths.get(path);
        List<String> lines = new ArrayList<>();
        String header = "Participant_ID,Condition,Age,AgeGroup,Blicket_Consistency,Nonblicket_Consistency,Correct";
        lines.add(header);
        for(ImaginaryParticipant p : participants){
            lines.add(p.stringList('C','C',p.CC));
            lines.add(p.stringList('C','I',p.CI));
            lines.add(p.stringList('I','C',p.IC));
            lines.add(p.stringList('I','I',p.II));

        }
        try { Files.write(output,lines, Charset.forName("UTF-8")); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public static double ageMaker(Integer age){
        double random = age + Math.random();
        return random;
    }

    public static boolean flip(double chance){
        double coin = Math.random();
        if(coin < chance) return true; else return false;
    }

    public static boolean flip(){
        double coin = Math.random();
        System.out.println("testing");
        System.out.println(coin);
        if(coin < 0.5) return true; else return false;
    }


    public static void main(String[] args){
        ArrayList<ImaginaryParticipant> p;
        ArrayList<ImaginaryParticipant> q;
        p = ImaginaryData.makeParticipants();
        q = ImaginaryData.makeContinuousParticipants();
        ImaginaryData.printToCsv("/Users/rgelpi1/Documents/Explore-Exploit/test.csv", p);
        ImaginaryData.printToCsv("/Users/rgelpi1/Documents/Explore-Exploit/test2.csv", q);
        //data.printToCsv("/Users/rgelpi1/Documents/Explore-Exploit/test.csv");
    }
}
