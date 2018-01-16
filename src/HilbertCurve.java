import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Meir on 12/30/2015.
 */
public class HilbertCurve {

    static int ITERATIONS = 6;
    // F means move forward, R means turn right 90 deg., L means turn left 90 deg.
    public static void drawHilbertCurve(ArrayList<LSystem.Symbol> symbols) {
        Turtle turtle = new Turtle(.1,.1,0);
        double stepSize = .025*(Math.pow(.5,ITERATIONS-5));
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
        rules.put(LSystem.Symbol.A, new LSystem.Symbol[]{
                LSystem.Symbol.L,
                LSystem.Symbol.B,
                LSystem.Symbol.F,
                LSystem.Symbol.R,
                LSystem.Symbol.A,
                LSystem.Symbol.F,
                LSystem.Symbol.A,
                LSystem.Symbol.R,
                LSystem.Symbol.F,
                LSystem.Symbol.B,
                LSystem.Symbol.L});
        rules.put(LSystem.Symbol.B, new LSystem.Symbol[]{
                LSystem.Symbol.R,
                LSystem.Symbol.A,
                LSystem.Symbol.F,
                LSystem.Symbol.L,
                LSystem.Symbol.B,
                LSystem.Symbol.F,
                LSystem.Symbol.B,
                LSystem.Symbol.L,
                LSystem.Symbol.F,
                LSystem.Symbol.A,
                LSystem.Symbol.R});
        ArrayList<LSystem.Symbol> axiom = new ArrayList<LSystem.Symbol>();
        axiom.add(LSystem.Symbol.A);
        LSystem system = new LSystem(rules, axiom);
        for (int i = 0; i < ITERATIONS; i++) {
            axiom = system.expandedList(axiom);
        }
        drawHilbertCurve(axiom);

    }
}
