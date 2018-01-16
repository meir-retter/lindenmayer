import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Meir on 12/30/2015.
 */
public class Rings {

    static int ITERATIONS = 4;
    // F means move forward, R means turn right 90 deg., L means turn left 90 deg.
    public static void drawRings(ArrayList<LSystem.Symbol> symbols) {
        Turtle turtle = new Turtle(.5,.25,0);
        double stepSize = .001*(Math.pow(.285714,ITERATIONS-5));
        for (LSystem.Symbol s : symbols) {
            if (s.equals(LSystem.Symbol.F)) {
                turtle.goForward(stepSize);
            } else if (s.equals(LSystem.Symbol.L)) {
                turtle.turnLeft(90);
            } else if (s.equals(LSystem.Symbol.R)) {
                turtle.turnLeft(270); // turn right 90
            } else {
                assert false;
            }
        }
    }

    public static void main(String[] args) {
        HashMap<LSystem.Symbol, LSystem.Symbol[]> rules = new HashMap<LSystem.Symbol, LSystem.Symbol[]>();
        rules.put(LSystem.Symbol.F, new LSystem.Symbol[]{
                LSystem.Symbol.F,
                LSystem.Symbol.F,
                LSystem.Symbol.L,
                LSystem.Symbol.F,
                LSystem.Symbol.L,
                LSystem.Symbol.F,
                LSystem.Symbol.L,
                LSystem.Symbol.F,
                LSystem.Symbol.L,
                LSystem.Symbol.F,
                LSystem.Symbol.L,
                LSystem.Symbol.F,
                LSystem.Symbol.R,
                LSystem.Symbol.F});
        ArrayList<LSystem.Symbol> axiom = new ArrayList<LSystem.Symbol>();
        axiom.add(LSystem.Symbol.F);
        axiom.add(LSystem.Symbol.L);
        axiom.add(LSystem.Symbol.F);
        axiom.add(LSystem.Symbol.L);
        axiom.add(LSystem.Symbol.F);
        axiom.add(LSystem.Symbol.L);
        axiom.add(LSystem.Symbol.F);
        LSystem system = new LSystem(rules, axiom);
        for (int i = 0; i < ITERATIONS; i++) {
            axiom = system.expandedList(axiom);
        }
        drawRings(axiom);

    }
}
