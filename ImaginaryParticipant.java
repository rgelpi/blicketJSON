import java.util.*;

public class ImaginaryParticipant {

    public String id;
    public boolean condition;
    public double age;
    public String ageGroup;
    public boolean CC;
    public boolean CI;
    public boolean IC;
    public boolean II;
    public final char a = ',';

    public ImaginaryParticipant(String id, boolean condition, double age, String ageGroup, boolean CC, boolean CI, boolean IC, boolean II) {
        this.id = id;
        this.condition = condition;
        this.age = age;
        this.ageGroup = ageGroup;
        this.CC = CC;
        this.CI = CI;
        this.IC = IC;
        this.II = II;
        System.out.println("id = " + id + ", condition = " + condition + ", age = " + age);
    }

    public String stringList(char b, char nb, boolean correct){
        StringBuilder sb = new StringBuilder();
        sb.append(id); sb.append(a);
        sb.append(condition); sb.append(a);
        sb.append(age); sb.append(a);
        sb.append(ageGroup); sb.append(a);
        sb.append(b);
        sb.append(a);
        sb.append(nb);
        sb.append(a);
        sb.append(correct);
        return sb.toString();
    }
}
