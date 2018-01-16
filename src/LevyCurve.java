import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Meir on 12/30/2015.
 */
public class LevyCurve {
    static int ITERATIONS = 13;
    // F means move forward, R means turn right 45 deg., L means turn left 45 deg.
    public static void drawLevyCurve(ArrayList<LSystem.Symbol> symbols) {
        Turtle turtle = new Turtle(.3,.6,0);
        for (LSystem.Symbol s : symbols) {
            if (s.equals(LSystem.Symbol.F)) {
                turtle.goForward(.07*(Math.pow(1/Math.sqrt(2),ITERATIONS-4)));
            } else if (s.equals(LSystem.Symbol.L)) {
                turtle.turnLeft(45);
            } else if (s.equals(LSystem.Symbol.R)) {
                turtle.turnLeft(315); // turn right 45
            } else {
                assert false;
            }
        }
    }

    public static void main(String[] args) {
        HashMap<LSystem.Symbol, LSystem.Symbol[]> rules = new HashMap<LSystem.Symbol, LSystem.Symbol[]>();
        rules.put(LSystem.Symbol.F, new LSystem.Symbol[]{
                LSystem.Symbol.R,
                LSystem.Symbol.F,
                LSystem.Symbol.L,
                LSystem.Symbol.L,
                LSystem.Symbol.F,
                LSystem.Symbol.R});
        ArrayList<LSystem.Symbol> axiom = new ArrayList<LSystem.Symbol>();
        axiom.add(LSystem.Symbol.F);
        LSystem system = new LSystem(rules, axiom);
        for (int i = 0; i < ITERATIONS; i++) {
            axiom = system.expandedList(axiom);
        }
        drawLevyCurve(axiom);

    }



}
