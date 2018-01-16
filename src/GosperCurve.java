import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Meir on 12/30/2015.
 */
public class GosperCurve {

    static int ITERATIONS = 4;
    // A and B mean move forward, R means turn right 60 deg., L means turn left 60 deg.
    public static void drawGosperCurve(ArrayList<LSystem.Symbol> symbols) {
        Turtle turtle = new Turtle(.6,.85,0);
        for (LSystem.Symbol s : symbols) {
            if (s.equals(LSystem.Symbol.A) || s.equals(LSystem.Symbol.B)) {
                turtle.goForward(.013 * Math.pow(1/Math.sqrt(7), ITERATIONS - 4));
            } else if (s.equals(LSystem.Symbol.L)) {
                turtle.turnLeft(60);
            } else if (s.equals(LSystem.Symbol.R)) {
                turtle.turnLeft(300); // turn right 60
            }
        }
    }

    public static void main(String[] args) {
        HashMap<LSystem.Symbol, LSystem.Symbol[]> rules = new HashMap<LSystem.Symbol, LSystem.Symbol[]>();
        rules.put(LSystem.Symbol.A, new LSystem.Symbol[]{
                LSystem.Symbol.A,
                LSystem.Symbol.R,
                LSystem.Symbol.B,
                LSystem.Symbol.R,
                LSystem.Symbol.R,
                LSystem.Symbol.B,
                LSystem.Symbol.L,
                LSystem.Symbol.A,
                LSystem.Symbol.L,
                LSystem.Symbol.L,
                LSystem.Symbol.A,
                LSystem.Symbol.A,
                LSystem.Symbol.L,
                LSystem.Symbol.B,
                LSystem.Symbol.R});
        rules.put(LSystem.Symbol.B, new LSystem.Symbol[]{
                LSystem.Symbol.L,
                LSystem.Symbol.A,
                LSystem.Symbol.R,
                LSystem.Symbol.B,
                LSystem.Symbol.B,
                LSystem.Symbol.R,
                LSystem.Symbol.R,
                LSystem.Symbol.B,
                LSystem.Symbol.R,
                LSystem.Symbol.A,
                LSystem.Symbol.L,
                LSystem.Symbol.L,
                LSystem.Symbol.A,
                LSystem.Symbol.L,
                LSystem.Symbol.B});
        ArrayList<LSystem.Symbol> axiom = new ArrayList<LSystem.Symbol>();
        axiom.add(LSystem.Symbol.A);
        LSystem system = new LSystem(rules, axiom);
        for (int i = 0; i < ITERATIONS; i++) {
            axiom = system.expandedList(axiom);
        }
        drawGosperCurve(axiom);
    }
}
