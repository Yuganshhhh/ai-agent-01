package com.example.aitool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

/**
 * AI tool that lets the model perform arithmetic.
 * <p>
 * LLMs can make mistakes with maths, so instead of calculating answers
 * themselves they call this tool. Spring AI exposes the method annotated with
 * {@link Tool} to the model, which decides when to call it and which
 * arguments to send.
 */
@Component
public class CalculatorTool {

    /**
     * Performs a basic arithmetic operation on two integers.
     * <p>
     * Supported operations: {@code add}, {@code subtract}, {@code multiply},
     * {@code divide}, {@code mod} and {@code power}.
     *
     * @param operation the operation to perform (one of the supported operations)
     * @param a         the first number
     * @param b         the second number
     * @return the result of the operation
     * @throws IllegalArgumentException if dividing or taking the modulo by zero,
     *                                  or if the operation is not supported
     */
    @Tool(description = """
    This is a Calculator Tool used for arithmetic operations.
    Decide which operation to do and put it in the operation parameter.
    Decide number 1, put it in the a parameter.
    Decide number 2, put it in the b parameter.
    """)
    public double calculate(
            @ToolParam(description = """
                    It is a operation parameter.
                    Allowed operation: add, subtract, multiply, divide, mod, power
                    """)
            String operation,

            @ToolParam(description = "It is a parameter for number 1")
            int a,

            @ToolParam(description = "It is a parameter for number 2")
            int b
    ){

        // Log so we can see in the console when the model decides to use this tool
        System.out.println("Calculator Tool called...");

        if(operation.equals("add")) {
            return a + b;
        }

        if(operation.equals("subtract")) {
            return a - b;
        }

        if(operation.equals("multiply")) {
            return a * b;
        }

        if(operation.equals("divide")) {
            // Guard against division by zero
            if(b == 0) {
                throw new IllegalArgumentException("cannot divide by 0");
            }
            // Cast to double so 5 / 2 returns 2.5 instead of 2
            return (double) a / b;
        }

        if(operation.equals("mod")) {
            // Guard against modulo by zero
            if(b == 0) {
                throw new IllegalArgumentException("cannot mod by 0");
            }
            return a % b;
        }

        if(operation.equals("power")) {
            return Math.pow(a, b);
        }

        else {
            // The model sent an operation we do not support
            throw new IllegalArgumentException("Illegal argument");
        }
    }


}
