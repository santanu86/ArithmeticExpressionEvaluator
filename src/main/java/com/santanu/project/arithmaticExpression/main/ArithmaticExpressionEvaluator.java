package com.santanu.project.arithmaticExpression.main;

import java.util.Scanner;

import com.santanu.project.arithmaticExpression.model.Token;
import com.santanu.project.arithmaticExpression.model.TokenStack;

public class ArithmaticExpressionEvaluator {

	private TokenStack operatorStack;
	private TokenStack valueStack;
	private boolean error;

	public ArithmaticExpressionEvaluator() {
		operatorStack = new TokenStack();
		valueStack = new TokenStack();
		error = false;
	}

	private void processOperator(Token t) {
		Token A = null, B = null;
		if (valueStack.isEmpty()) {
			error = true;
			return;
		} else {
			B = valueStack.top();
			valueStack.pop();
		}
		if (valueStack.isEmpty()) {
			error = true;
			return;
		} else {
			A = valueStack.top();
			valueStack.pop();
		}
		Token R = t.operate(A.getValue(), B.getValue());
		valueStack.push(R);
	}

	/** Process the Input Expression */
	public String processInput(String input) {
		
		String[] parts = input.split("");
		Token[] tokens = new Token[parts.length];
		for (int n = 0; n < parts.length; n++) {
			tokens[n] = new Token(parts[n]);
		}
		
		// clear the stack
		operatorStack = new TokenStack();
		valueStack = new TokenStack();
		error = false;
		
		
		// Main loop - process all input tokens
		for (int n = 0; n < tokens.length; n++) {
			Token nextToken = tokens[n];
			if (nextToken.getType() == Token.NUMBER) {
				valueStack.push(nextToken);
			} else if (nextToken.getType() == Token.OPERATOR) {
				if (operatorStack.isEmpty() || nextToken.getPrecedence() > operatorStack.top().getPrecedence()) {
					operatorStack.push(nextToken);
				} else {
					while (!operatorStack.isEmpty() && nextToken.getPrecedence() <= operatorStack.top().getPrecedence()) {
						Token toProcess = operatorStack.top();
						operatorStack.pop();
						processOperator(toProcess);
					}
					operatorStack.push(nextToken);
				}
			} else if (nextToken.getType() == Token.LEFT_PARENTHESIS) {
				operatorStack.push(nextToken);
			} else if (nextToken.getType() == Token.RIGHT_PARENTHESIS) {
				while (!operatorStack.isEmpty() && operatorStack.top().getType() == Token.OPERATOR) {
					Token toProcess = operatorStack.top();
					operatorStack.pop();
					processOperator(toProcess);
				}
				if (!operatorStack.isEmpty() && operatorStack.top().getType() == Token.LEFT_PARENTHESIS) {
					operatorStack.pop();
				} else {					
					error = true;
				}
			}

		}
		// Empty out the operator stack at the end of the input
		while (!operatorStack.isEmpty() && operatorStack.top().getType() == Token.OPERATOR) {
			Token toProcess = operatorStack.top();
			operatorStack.pop();
			processOperator(toProcess);
		}
		// Print the result if no error has been seen.
		if(error == false) {
			Token result = valueStack.top();
			valueStack.pop();
			if (!operatorStack.isEmpty() || !valueStack.isEmpty()) {
				return "INVALID EXPRESSION";
			} else {				
				return String.valueOf(result.getValue());
			}
		} 
		return "INVALID EXPRESSION";
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int testCase = Integer.parseInt(input.nextLine());
		String[] inputExpression = new String[testCase];
		for(int j=0; j<testCase; j++) {
			inputExpression[j] = input.nextLine();
		}
		String[] result = new String[testCase];
		ArithmaticExpressionEvaluator calc = new ArithmaticExpressionEvaluator();
		for(int i = 0; i < inputExpression.length; i++ ) {
			result[i] = calc.processInput(inputExpression[i]);
		}
		for(int i = 0; i < result.length; i++ ) {
			System.out.println("Case #"+(i+1)+": " + result[i]);
		}
	}
}
