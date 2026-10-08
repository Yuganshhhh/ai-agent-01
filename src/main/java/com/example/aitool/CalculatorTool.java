package com.example.aitool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class CalculatorTool {

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
            if(b == 0) {
                throw new IllegalArgumentException("cannot divide by 0");
            }
            return (double) a / b;
        }

        if(operation.equals("mod")) {
            if(b == 0) {
                throw new IllegalArgumentException("cannot mod by 0");
            }
            return a % b;
        }

        if(operation.equals("power")) {
            return Math.pow(a, b);
        }

        else {
            throw new IllegalArgumentException("Illegal argument");
        }
    }


}
