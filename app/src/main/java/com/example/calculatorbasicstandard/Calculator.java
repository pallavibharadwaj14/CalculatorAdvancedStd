package com.example.calculatorbasicstandard;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

//Logic for calculation
    public class Calculator {
    private List<String> expression;
    private List<String> history;  // To store operator history
    private boolean advanceMode;  //Track mode of calculator

    public Calculator()  // Constructor for initializing
    {
        expression = new ArrayList<>();
        history = new ArrayList<>();
        advanceMode = false;
    }
    public void push(String value)
    {
        expression.add(value);
    }
    public void clear() // Clearing expression list
    {
        expression.clear();
    }
    // Clears the operator history
    public void clearHistory()
    {

        history.clear();
    }
    public int calculate() {
        if (expression.size() == 0) return 0;

        int result = Integer.parseInt(expression.get(0));

        for (int i = 1; i < expression.size(); i += 2) {
            String operator = expression.get(i);
            int nextValue = Integer.parseInt(expression.get(i + 1));

            // Add the operator to the history if in advanced mode
            if (advanceMode) {
                history.add(getExpression()+ " = "+ result);
            }

            switch (operator) {
                case "+":
                    result += nextValue;
                    break;
                case "-":
                    result -= nextValue;
                    break;
                case "*":
                    result *= nextValue;
                    break;
                case "/": // Handling /0 error
                    try {
                        if (nextValue == 0) {
                            throw new ArithmeticException("Cannot divide by zero");
                        }
                        result /= nextValue;
                    } catch (ArithmeticException e) {
                        System.out.println(e.getMessage()); // Output error message
                        result = 0;
                    }
                    break;
            }
        }
        return result;
    }
    // Returns the current expression as a string
    public String getExpression()
    {
        StringBuilder sb = new StringBuilder();
        for (String value : expression)
        {
            sb.append(value).append(" ");
        }
        return sb.toString().trim();
    }
    // Get the operator history as a string
    public String getHistory() {
        StringBuilder sb = new StringBuilder();
        for (String op : history) {
            sb.append(op).append("\n");
        }
        return sb.toString();
    }

    // Enable/disable advanced mode
    public void setAdvancedMode(boolean advanceMode) {

        this.advanceMode = advanceMode;
    }

}

