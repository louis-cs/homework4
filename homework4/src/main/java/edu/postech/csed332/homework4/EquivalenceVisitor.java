package edu.postech.csed332.homework4;

import edu.postech.csed332.homework4.expression.*;
import org.jetbrains.annotations.NotNull;

/**
 * A visitor to check whether a given expression is syntactically equivalent to another expression.
 */
public class EquivalenceVisitor implements ExpVisitor<Boolean> {

    Exp other;

    public EquivalenceVisitor(@NotNull Exp other) {
        this.other = other;
    }

    @Override
    public Boolean visitVariableExp(VariableExp v) {
        if (v.toString().equals(other.toString())) return true;
        else return false;
    }

    @Override
    public Boolean visitNumberExp(NumberExp v) {
        if (v.toString().equals(other.toString())) return true;
        else return false;
    }

    @Override
    public Boolean visitPlusExp(PlusExp v) {
        if (v.toString().equals(other.toString())) return true;
        else return false;
    }

    @Override
    public Boolean visitMinusExp(MinusExp v) {
        if (v.toString().equals(other.toString())) return true;
        else return false;
    }

    @Override
    public Boolean visitMultiplyExp(MultiplyExp v) {
        if (v.toString().equals(other.toString())) return true;
        else return false;
    }

    @Override
    public Boolean visitDivideExp(DivideExp v) {
        if (v.toString().equals(other.toString())) return true;
        else return false;
    }

    @Override
    public Boolean visitExponentiationExp(ExponentiationExp v) {
        if (v.toString().equals(other.toString())) return true;
        else return false;
    }
}
