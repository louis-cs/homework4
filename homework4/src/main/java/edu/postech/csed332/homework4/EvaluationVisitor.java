package edu.postech.csed332.homework4;

import edu.postech.csed332.homework4.expression.*;

import java.util.Map;
import java.util.NoSuchElementException;

/**
 * A visitor to evaluate a value of an expression, given a valuation for each variable
 */
public class EvaluationVisitor implements ExpVisitor<Double> {
    private final Map<Integer, Double> valuation;

    public EvaluationVisitor(Map<Integer, Double> valuation) {
        this.valuation = valuation;
    }

    @Override
    public Double visitVariableExp(VariableExp v) {
        if (!(valuation.containsKey(v.getName()))) throw new NoSuchElementException("No value attributed to variable");
        else return valuation.get(v.getName());
    }

    @Override
    public Double visitNumberExp(NumberExp v) {
        return v.getValue();
    }

    @Override
    public Double visitPlusExp(PlusExp v) {
        return (v.getLeft().eval(valuation) + v.getRight().eval(valuation));
    }

    @Override
    public Double visitMinusExp(MinusExp v) {
        return (v.getLeft().eval(valuation) - v.getRight().eval(valuation));
    }

    @Override
    public Double visitMultiplyExp(MultiplyExp v) {
        return (v.getLeft().eval(valuation) * v.getRight().eval(valuation));
    }

    @Override
    public Double visitDivideExp(DivideExp v) {
        return (v.getLeft().eval(valuation) / v.getRight().eval(valuation));
    }

    @Override
    public Double visitExponentiationExp(ExponentiationExp v) {
        return (Math.pow(v.getLeft().eval(valuation), v.getRight().eval(valuation)));
    }
    
}
