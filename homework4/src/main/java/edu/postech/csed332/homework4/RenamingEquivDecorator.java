package edu.postech.csed332.homework4;

import edu.postech.csed332.homework4.expression.*;
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

    private class PrivateEquivalenceVisitor extends EquivalenceVisitor{

        Exp other;
        Map<Integer, Integer> renamedMap;

        public PrivateEquivalenceVisitor(@NotNull Exp other, Map<Integer, Integer> renamedMap) {
            super(other);
            this.other=other;
            this.renamedMap=renamedMap;
        }

        @Override
        public Boolean visitVariableExp(VariableExp v) {
            if (!(other instanceof VariableExp)) return false;
            VariableExp otherVariable = (VariableExp) other;
            if (!(renamedMap.containsKey(v.getName()))) {                   /*if this variable does not have a corresponding variable yet          */
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

        @Override
        public Boolean visitNumberExp(NumberExp v) {
            if (v.toString().equals(other.toString())) return true;
            else return false;
        }

        @Override
        public Boolean visitPlusExp(PlusExp v) {
            if (!(other instanceof PlusExp)) return false;
            return ((new RenamingEquivDecorator(v.getLeft()).equiv(((PlusExp) other).getLeft()))
                    && (new RenamingEquivDecorator(v.getRight()).equiv(((PlusExp) other).getRight())));
        }

        @Override
        public Boolean visitMinusExp(MinusExp v) {
            if (!(other instanceof MinusExp)) return false;
            return ((new RenamingEquivDecorator(v.getLeft()).equiv(((MinusExp) other).getLeft()))
                    && (new RenamingEquivDecorator(v.getRight()).equiv(((MinusExp) other).getRight())));
        }

        @Override
        public Boolean visitMultiplyExp(MultiplyExp v) {
            if (!(other instanceof MultiplyExp)) return false;
            return ((new RenamingEquivDecorator(v.getLeft()).equiv(((MultiplyExp) other).getLeft()))
                    && (new RenamingEquivDecorator(v.getRight()).equiv(((MultiplyExp) other).getRight())));
        }

        @Override
        public Boolean visitDivideExp(DivideExp v) {
            if (!(other instanceof DivideExp)) return false;
            return ((new RenamingEquivDecorator(v.getLeft()).equiv(((DivideExp) other).getLeft()))
                    && (new RenamingEquivDecorator(v.getRight()).equiv(((DivideExp) other).getRight())));
        }

        @Override
        public Boolean visitExponentiationExp(ExponentiationExp v) {
            if (!(other instanceof ExponentiationExp)) return false;
            return ((new RenamingEquivDecorator(v.getLeft()).equiv(((MinusExp) other).getLeft()))
                    && (new RenamingEquivDecorator(v.getRight()).equiv(((MinusExp) other).getRight())));
        }
    }

    public RenamingEquivDecorator(Exp e) {
        super(e);
    }

    @Override
    public boolean equiv(@NotNull Exp other) {
        renamedMap = new HashMap<>();
        return accept(new PrivateEquivalenceVisitor(other, renamedMap));
    }
}
