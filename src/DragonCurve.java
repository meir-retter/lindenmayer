import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Meir on 12/29/2015.
 */
public class DragonCurve {

    static int ITERATIONS = 13;
    // F means move forward, R means turn right 90 deg., L means turn left 90 deg.
    public static void drawDragonCurve(ArrayList<LSystem.Symbol> symbols) {
        Turtle turtle;
        int type; // depending on how many iterations, the curve will be oriented dirrerently,
                  // so we plan around that in order to show the picture nicely
        type = ((int)Math.ceil(ITERATIONS/2.0)) % 4; // divides the integers into 4 equivalence classes
        double far = .75;
        double near = .25;
        if (type == 1) {
            turtle = new Turtle(near, far, 0);
        } else if (type == 2) {
            turtle = new Turtle(far, far,0);
        } else if (type == 3) {
            turtle = new Turtle(far, near,0);
        } else if (type == 0) {
            turtle = new Turtle(near, near,0);
        } else {
            turtle = new Turtle(0,0,0);
            assert false;
        }

        for (LSystem.Symbol s : symbols) {
            if (s.equals(LSystem.Symbol.F)) {
                turtle.goForward(.005 * Math.pow(.5, ITERATIONS-13));
            } else if (s.equals(LSystem.Symbol.L)) {
                turtle.turnLeft(90);
            } else if (s.equals(LSystem.Symbol.R)) {
                turtle.turnLeft(270); // turn right 90
            }
        }
    }

    public static void main(String[] args) {
        HashMap<LSystem.Symbol, LSystem.Symbol[]> rules = new HashMap<LSystem.Symbol, LSystem.Symbol[]>();
        rules.put(LSystem.Symbol.X, new LSystem.Symbol[]{
                LSystem.Symbol.X,
                LSystem.Symbol.R,
                LSystem.Symbol.Y,
                LSystem.Symbol.F,
                LSystem.Symbol.R});
        rules.put(LSystem.Symbol.Y, new LSystem.Symbol[]{
                LSystem.Symbol.L,
                LSystem.Symbol.F,
                LSystem.Symbol.X,
                LSystem.Symbol.L,
                LSystem.Symbol.Y});
        ArrayList<LSystem.Symbol> axiom = new ArrayList<LSystem.Symbol>();
        axiom.add(LSystem.Symbol.F);
        axiom.add(LSystem.Symbol.X);
        LSystem system = new LSystem(rules, axiom);
        for (int i = 0; i < ITERATIONS; i++) {
            axiom = system.expandedList(axiom);
        }
        drawDragonCurve(axiom);
    }
}
