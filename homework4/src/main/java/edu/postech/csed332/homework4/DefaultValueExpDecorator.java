package edu.postech.csed332.homework4;

import edu.postech.csed332.homework4.expression.*;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.NoSuchElementException;

/**
 * When evaluating the expression, use the default value for variables not present in the valuation.
 */
public class DefaultValueExpDecorator extends ExpDecorator {
    @NotNull
    private final Double defaultValue;

    private class PrivateEvaluationVisitor extends EvaluationVisitor{
        private final Map<Integer, Double> valuation;

        public PrivateEvaluationVisitor(Map<Integer, Double> valuation) {
            super(valuation);
            this.valuation = valuation;
        }

        @Override
        public Double visitVariableExp(VariableExp v) {
            return valuation.getOrDefault(v.getName(), defaultValue);
        }

        @Override
        public Double visitNumberExp(NumberExp v) {
            return v.getValue();
        }

        @Override
        public Double visitPlusExp(PlusExp v) {
            return (new DefaultValueExpDecorator(v.getLeft(), defaultValue).eval(valuation)
                    + new DefaultValueExpDecorator(v.getRight(), defaultValue).eval(valuation));
        }

        @Override
        public Double visitMinusExp(MinusExp v) {
            return (new DefaultValueExpDecorator(v.getLeft(), defaultValue).eval(valuation)
                    - new DefaultValueExpDecorator(v.getRight(), defaultValue).eval(valuation));
        }

        @Override
        public Double visitMultiplyExp(MultiplyExp v) {
            return (new DefaultValueExpDecorator(v.getLeft(), defaultValue).eval(valuation)
                    * new DefaultValueExpDecorator(v.getRight(), defaultValue).eval(valuation));
        }

        @Override
        public Double visitDivideExp(DivideExp v) {
            return (new DefaultValueExpDecorator(v.getLeft(), defaultValue).eval(valuation)
                    / new DefaultValueExpDecorator(v.getRight(), defaultValue).eval(valuation));
        }

        @Override
        public Double visitExponentiationExp(ExponentiationExp v) {
            return (Math.pow(new DefaultValueExpDecorator(v.getLeft(), defaultValue).eval(valuation)
                    ,new DefaultValueExpDecorator(v.getRight(), defaultValue).eval(valuation)));
        }

    }


    public DefaultValueExpDecorator(@NotNull Exp e, @NotNull Double defaultValue) {
        super(e);
        this.defaultValue = defaultValue;
    }

    @Override
    @NotNull
    public Double eval(@NotNull Map<Integer, Double> valuation) {
        return accept(new PrivateEvaluationVisitor(valuation));
    }
}
