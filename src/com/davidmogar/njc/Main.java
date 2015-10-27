package com.davidmogar.njc;

import com.davidmogar.njc.statements.blocks.Block;
import com.davidmogar.njc.statements.blocks.FunctionBlock;
import com.davidmogar.njc.expressions.Expression;
import com.davidmogar.njc.expressions.Variable;
import com.davidmogar.njc.expressions.numbers.Integer;
import com.davidmogar.njc.expressions.operators.ArithmeticOperator;
import com.davidmogar.njc.expressions.operators.NegationOperator;
import com.davidmogar.njc.statements.AssignmentStatement;
import com.davidmogar.njc.statements.ReadStatement;
import com.davidmogar.njc.statements.Statement;
import com.davidmogar.njc.statements.WriteStatement;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Statement> statements = new ArrayList<>();

        /* Read statement */
        List<Expression> expressions = new ArrayList<>();
        expressions.add(new Variable(2, 10, "a"));
        expressions.add(new Variable(2, 13, "b"));
        statements.add(new ReadStatement(2, 5, expressions));

        /* Assignment statement */
        Expression leftValue = new Variable(3, 5, "a");
        Expression rightValue = new ArithmeticOperator(3, 22,
                new ArithmeticOperator(3, 18,
                    new ArithmeticOperator(3, 13,
                        new NegationOperator(3, 10, new Variable(3, 11, "b")),
                        new com.davidmogar.njc.expressions.numbers.Integer(3, 15, 3), "+"),
                    new Variable(3, 20, "c"), "*"),
                new Integer(3, 24, 2), "/");
        statements.add(new AssignmentStatement(3, 5, leftValue, rightValue));

        /* Write statement */
        expressions = new ArrayList<>();
        expressions.add(new Variable(4, 11, "a"));
        expressions.add(new ArithmeticOperator(4, 16,
                new Variable(4, 14, "c"),
                new Integer(4, 18, 2), "*"));
        statements.add(new WriteStatement(2, 5, expressions));

        /* Function */
        List<Block> blocks = new ArrayList<Block>();
        blocks.add(new FunctionBlock(1, 1, "main", "void", statements));

        /* Program */
        Program program = new Program(blocks);
        System.out.println(program);
    }
}

//void main() {
//    read a, b;
//    a = (-b + 3) * c / 2;
//    white a, c * 2;
//}