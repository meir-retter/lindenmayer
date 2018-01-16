import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by Meir on 12/29/2015.
 */
public class PythagorasTree {

    static int ITERATIONS = 10;
    // A and B mean move forward (A will draw a leaf, B will draw an interior segment)
    // O (open) means push position and angle, turn left 45 degrees
    // C (close) means pop position and angle, turn right 45 degrees
    public static void drawPythagorasTree(ArrayList<LSystem.Symbol> symbols) {
        Turtle turtle = new Turtle(.5,.02,90);
        Stack<double[]> positionStack = new Stack<double[]>();
        for (LSystem.Symbol s : symbols) {
            if (s.equals(LSystem.Symbol.A) || s.equals(LSystem.Symbol.B)) {
                turtle.goForward(.008 * Math.pow(.5, ITERATIONS - 7));
            } else if (s.equals(LSystem.Symbol.O)) {
                positionStack.push(new double[]{turtle.x, turtle.y, turtle.angle});
                turtle.turnLeft(45);
            } else if (s.equals(LSystem.Symbol.C)) {
                double[] data = positionStack.pop();
                turtle.x = data[0];
                turtle.y = data[1];
                turtle.angle = data[2];
                turtle.turnLeft(315); // turn right 45 degrees
            }
        }
    }

    public static void main(String[] args) {
        HashMap<LSystem.Symbol, LSystem.Symbol[]> rules = new HashMap<LSystem.Symbol, LSystem.Symbol[]>();
        rules.put(LSystem.Symbol.B, new LSystem.Symbol[]{
                LSystem.Symbol.B,
                LSystem.Symbol.B});
        rules.put(LSystem.Symbol.A, new LSystem.Symbol[]{
                LSystem.Symbol.B,
                LSystem.Symbol.O,
                LSystem.Symbol.A,
                LSystem.Symbol.C,
                LSystem.Symbol.A});
        ArrayList<LSystem.Symbol> axiom = new ArrayList<LSystem.Symbol>();
        axiom.add(LSystem.Symbol.A);
        LSystem system = new LSystem(rules, axiom);
        for (int i = 0; i < ITERATIONS; i++) {
            axiom = system.expandedList(axiom);
        }
        drawPythagorasTree(axiom);
    }
}
