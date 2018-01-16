import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Meir on 12/29/2015.
 */
public class Snowflake {

    static int ITERATIONS = 5;
    // F means move forward, R means turn right 60 deg., L means turn left 60 deg.
    public static void drawSnowflake(ArrayList<LSystem.Symbol> symbols) {
        Turtle turtle = new Turtle(.1,.75,0);
        for (LSystem.Symbol s : symbols) {
            if (s.equals(LSystem.Symbol.F)) {
                turtle.goForward(.01*(Math.pow(.333,ITERATIONS-4)));
            } else if (s.equals(LSystem.Symbol.L)) {
                turtle.turnLeft(60);
            } else if (s.equals(LSystem.Symbol.R)) {
                turtle.turnLeft(300); // turn right 60
            } else {
                assert false;
            }
        }
    }

    public static void main(String[] args) {
        HashMap<LSystem.Symbol, LSystem.Symbol[]> rules = new HashMap<LSystem.Symbol, LSystem.Symbol[]>();
        rules.put(LSystem.Symbol.F, new LSystem.Symbol[]{
                LSystem.Symbol.F,
                LSystem.Symbol.L,
                LSystem.Symbol.F,
                LSystem.Symbol.R,
                LSystem.Symbol.R,
                LSystem.Symbol.F,
                LSystem.Symbol.L,
                LSystem.Symbol.F});
        ArrayList<LSystem.Symbol> axiom = new ArrayList<LSystem.Symbol>();
        axiom.add(LSystem.Symbol.F);
        axiom.add(LSystem.Symbol.R);
        axiom.add(LSystem.Symbol.R);
        axiom.add(LSystem.Symbol.F);
        axiom.add(LSystem.Symbol.R);
        axiom.add(LSystem.Symbol.R);
        axiom.add(LSystem.Symbol.F);
        LSystem system = new LSystem(rules, axiom);
        for (int i = 0; i < ITERATIONS; i++) {
            axiom = system.expandedList(axiom);
        }
        drawSnowflake(axiom);

    }
}
