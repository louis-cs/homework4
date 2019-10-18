package edu.postech.csed332.homework4;

import edu.postech.csed332.homework4.expression.Exp;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * A base decorator class
 */
public class ExpDecorator extends Exp {
    private final Exp expression;

    ExpDecorator(Exp e) {
        expression = e;
    }

    @Override
    public <T> @NotNull T accept(ExpVisitor<T> visitor) {
        return expression.accept(visitor);
    }

    public String toString(){
        return expression.toString();
    }

    public Double eval(@NotNull Map<Integer, Double> valuation) {
        return expression.eval(valuation);
    }

    public boolean equiv(@NotNull Exp other) {
        return expression.equiv(other);
    }

}
