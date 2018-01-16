import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Meir on 12/29/2015.
 */
public class LSystem {

    public enum Symbol{
        A,B,C,F,L,O,R,X,Y
    }

    HashMap<Symbol, Symbol[]> rules;
    ArrayList<Symbol> axiom;

    public LSystem(HashMap<Symbol, Symbol[]> rules0, ArrayList<Symbol> axiom0) {
        rules = rules0;
        axiom = axiom0;
    }

    public ArrayList<Symbol> expandedList(ArrayList<Symbol> list) {
        ArrayList<Symbol> ret = new ArrayList<Symbol>();
        for (Symbol s : list) {
            if (rules.containsKey(s)) {
                for (Symbol s0 : rules.get(s)) {
                    ret.add(s0);
                }
            } else {
                ret.add(s);
            }
        }
        return ret;
    }
}
