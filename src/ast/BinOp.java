package ast;

import environment.Environment;
import emitter.Emitter;

import java.io.IOException;

/**
 * BinOp represents a binary operation expression. It contains an operator and two expressions.
 *
 * @author Akul Goyal
 * @version 10/14/2023
 */
public class BinOp extends Expression
{
    private String op;
    private Expression exp1;
    private Expression exp2;

    /**
     * Constructor for objects of class BinOp
     *
     * @param op   the operator
     * @param exp1 the first expression
     * @param exp2 the second expression
     */
    public BinOp(String op, Expression exp1, Expression exp2)
    {
        this.op = op;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    /**
     * Evaluates the binary operation expression by applying the operator to the two expressions.
     * Supports addition, subtraction, multiplication, division, and modulo.
     *
     * @param env the environment in which the expression is evaluated
     * @return the result of the binary operation
     * @throws InvalidOperator           if the operator is not valid
     * @throws ContinueException         if a continue statement is called
     * @throws BreakException            if a break statement is called
     * @throws ArgumentMismatchException if the wrong number of arguments are passed to a function
     */
    @Override
    public int eval(Environment env) throws InvalidOperator, ContinueException, BreakException,
            ArgumentMismatchException
    {
        switch (op)
        {
            case "+":
                return exp1.eval(env) + exp2.eval(env);
            case "*":
                return exp1.eval(env) * exp2.eval(env);
            case "/":
                return exp1.eval(env) / exp2.eval(env);
            case "-":
                return exp1.eval(env) - exp2.eval(env);
            case "mod":
                return exp1.eval(env) % exp2.eval(env);
            default:
                throw new InvalidOperator(op + " is not a valid operator.");
        }
    }

    /**
     * Converts the binary operation expression into a String.
     *
     * @return the String representation of the binary operation expression
     */
    public String toString()
    {
        return exp1 + op + exp2;
    }

    /**
     * Compiles the binary operation expression into MIPS by compiling the two expressions and then
     * applying the operator and storing the result in $v0.
     *
     * @param e the emitter that writes the code to a file
     * @throws InvalidOperator if the operator is not valid
     */
    @Override
    public void compile(Emitter e) throws InvalidOperator
    {
        exp2.compile(e);
        e.emitPush("$v0");
        exp1.compile(e);
        e.emitPop("$t0");
        switch (op)
        {
            case "+":
                e.emit("add $v0 $v0 $t0", "addition");
                break;
            case "*":
                e.emit("mult $v0 $t0", "");
                e.emit("mflo $v0", "multiplication");
                break;
            case "/":
                e.emit("div $v0 $t0", "");
                e.emit("mflo $v0", "division");
                break;
            case "-":
                e.emit("sub $v0 $v0 $t0", "subtraction");
                break;
            case "mod":
                e.emit("div $v0 $t0", "");
                e.emit("mfhi $v0", "mod");
                break;
            default:
                throw new InvalidOperator(op + " is not a valid operator.");
        }
    }
}
