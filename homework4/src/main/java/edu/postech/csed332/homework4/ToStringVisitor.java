package edu.postech.csed332.homework4;

import edu.postech.csed332.homework4.expression.*;

/**
 * A visitor to compute the string expression of a given expression
 */
public class ToStringVisitor implements ExpVisitor<String> {
    // TODO write and implement the visitor methods for ToStringVisitor, satisfying
    //  the specification of Exp.toString. A double value is written using exponents,
    //  e.g., using Double.toString().

    @Override
    public String visitVariableExp(VariableExp v) {
        return ("x" + v.getName());
    }

    @Override
    public String visitNumberExp(NumberExp v) {
        return v.getValue().toString();
    }

    @Override
    public String visitPlusExp(PlusExp v) {
        return (v.getLeft().toString() + " + " + v.getRight().toString());
    }

    @Override
    public String visitMinusExp(MinusExp v) {
        return (v.getLeft().toString() + " - " + v.getRight().toString());
    }

    @Override
    public String visitMultiplyExp(MultiplyExp v) {
        return (v.getLeft().toString() + " * " + v.getRight().toString());
    }

    @Override
    public String visitDivideExp(DivideExp v) {
        return (v.getLeft().toString() + " / " + v.getRight().toString());
    }

    @Override
    public String visitExponentiationExp(ExponentiationExp v) {
        return (v.getLeft().toString() + " ^ " + v.getRight().toString());
    }

}
