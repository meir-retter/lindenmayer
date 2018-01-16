import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Meir on 12/29/2015.
 */
public class Sierpinski {

    static int ITERATIONS = 7;
    // A and B mean move forward, R means turn right 60 deg., L means turn left 60 deg.
    public static void drawSierpinski(ArrayList<LSystem.Symbol> symbols) {
        Turtle turtle = new Turtle(.05,.15,0);
        for (LSystem.Symbol s : symbols) {
            if (s.equals(LSystem.Symbol.A) || s.equals(LSystem.Symbol.B)) {
                turtle.goForward(.007 * Math.pow(.5, ITERATIONS - 7));
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
                LSystem.Symbol.L,
                LSystem.Symbol.B,
                LSystem.Symbol.R,
                LSystem.Symbol.A,
                LSystem.Symbol.R,
                LSystem.Symbol.B,
                LSystem.Symbol.L});
        rules.put(LSystem.Symbol.B, new LSystem.Symbol[]{
                LSystem.Symbol.R,
                LSystem.Symbol.A,
                LSystem.Symbol.L,
                LSystem.Symbol.B,
                LSystem.Symbol.L,
                LSystem.Symbol.A,
                LSystem.Symbol.R});
        ArrayList<LSystem.Symbol> axiom = new ArrayList<LSystem.Symbol>();
        axiom.add(LSystem.Symbol.A);
        LSystem system = new LSystem(rules, axiom);
        for (int i = 0; i < ITERATIONS; i++) {
            axiom = system.expandedList(axiom);
        }
        drawSierpinski(axiom);
    }
}
