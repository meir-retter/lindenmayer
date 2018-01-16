import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by Meir on 12/29/2015.
 */
public class FractalPlant {

    static int ITERATIONS = 7;
    // F means go forward
    // L means turn left 25 degrees
    // R means turn right 25 degrees
    // O (open) means push the current position and angle onto the stack
    // C (close) means pop the stack and use that to reset the position and angle
    public static void drawFractalPlant(ArrayList<LSystem.Symbol> symbols) {
        Turtle turtle = new Turtle(.1,.02,70);
        Stack<double[]> positionStack = new Stack<double[]>();
        for (LSystem.Symbol s : symbols) {
            if (s.equals(LSystem.Symbol.F)) {
                turtle.goForward(.003 * Math.pow(.5, ITERATIONS - 7));
            } else if (s.equals(LSystem.Symbol.O)) {
                positionStack.push(new double[]{turtle.x, turtle.y, turtle.angle});
            } else if (s.equals(LSystem.Symbol.C)) {
                double[] data = positionStack.pop();
                turtle.x = data[0];
                turtle.y = data[1];
                turtle.angle = data[2];
            } else if (s.equals(LSystem.Symbol.L)) {
                turtle.turnLeft(25);
            } else if (s.equals(LSystem.Symbol.R)) {
                turtle.turnLeft(335); // turn right 25 degrees
            }
        }
    }

    public static void main(String[] args) {
        HashMap<LSystem.Symbol, LSystem.Symbol[]> rules = new HashMap<LSystem.Symbol, LSystem.Symbol[]>();
        rules.put(LSystem.Symbol.F, new LSystem.Symbol[]{
                LSystem.Symbol.F,
                LSystem.Symbol.F});
        rules.put(LSystem.Symbol.X, new LSystem.Symbol[]{
                LSystem.Symbol.F,
                LSystem.Symbol.L,
                LSystem.Symbol.O,
                LSystem.Symbol.O,
                LSystem.Symbol.X,
                LSystem.Symbol.C,
                LSystem.Symbol.R,
                LSystem.Symbol.X,
                LSystem.Symbol.C,
                LSystem.Symbol.R,
                LSystem.Symbol.F,
                LSystem.Symbol.O,
                LSystem.Symbol.R,
                LSystem.Symbol.F,
                LSystem.Symbol.X,
                LSystem.Symbol.C,
                LSystem.Symbol.L,
                LSystem.Symbol.X});
        ArrayList<LSystem.Symbol> axiom = new ArrayList<LSystem.Symbol>();
        axiom.add(LSystem.Symbol.X);
        LSystem system = new LSystem(rules, axiom);
        for (int i = 0; i < ITERATIONS; i++) {
            axiom = system.expandedList(axiom);
        }
        drawFractalPlant(axiom);
    }
}
