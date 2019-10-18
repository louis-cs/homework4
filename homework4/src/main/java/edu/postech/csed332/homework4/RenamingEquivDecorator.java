package edu.postech.csed332.homework4;

import edu.postech.csed332.homework4.expression.Exp;
import edu.postech.csed332.homework4.expression.VariableExp;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * This expression is equivalent to another expression that is syntactically identical up to renaming.
 * For example, "(x1 + x2) * x3 + 1.0 * x1" is equivalent to "(x3 + x1) * x2 + 1.0 * x3", but not
 * equivalent to "(x3 + x1) * x2 + 1.0 * x1".
 */
public class RenamingEquivDecorator extends ExpDecorator {

    private Map<Integer, Integer> renamedMap;

    public RenamingEquivDecorator(Exp e) {
        super(e);
    }

    @Override
    public boolean equiv(@NotNull Exp other) {
        renamedMap = new HashMap<>();
        return accept(new EquivalenceVisitor(other){
            @Override
            public Boolean visitVariableExp(VariableExp v) {
                if (!(other instanceof VariableExp)) return false;
                VariableExp otherVariable = (VariableExp) other;
                if (!(renamedMap.containsKey(v.getName()))) {                        /*if this variable does not have a corresponding variable yet          */
                    if (renamedMap.containsValue(otherVariable.getName())) {    /*check if there's already a variable pointing on other*/
                        return false;
                    } else {
                        renamedMap.put(v.getName(), otherVariable.getName());
                        return true;
                    }
                }
                else if (renamedMap.get(v.getName()) == otherVariable.getName()) return true;
                return false;
            }
        });
    }
}
