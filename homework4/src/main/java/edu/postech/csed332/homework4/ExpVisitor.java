package edu.postech.csed332.homework4;

import edu.postech.csed332.homework4.expression.*;

/**
 * A visitor interface for expressions
 *
 * @param <T> type of return values
 */
public interface ExpVisitor<T> {

    T visitVariableExp(VariableExp v);

    T visitNumberExp(NumberExp v);

    T visitPlusExp(PlusExp v);

    T visitMinusExp(MinusExp v);

    T visitMultiplyExp(MultiplyExp v);

    T visitDivideExp(DivideExp v);

    T visitExponentiationExp(ExponentiationExp v);

}
