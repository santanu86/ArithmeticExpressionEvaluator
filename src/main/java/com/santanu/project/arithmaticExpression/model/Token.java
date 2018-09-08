package com.santanu.project.arithmaticExpression.model;

public class Token {
	public static final int UNKNOWN = -1;
	public static final int NUMBER = 0;
	public static final int OPERATOR = 1;
	public static final int LEFT_PARENTHESIS = 2;
	public static final int RIGHT_PARENTHESIS = 3;

	private int type;
	private Integer value;
	private char operator;
	private int precedence;

	public Token() {
		type = UNKNOWN;
	}

	public Token(String contents) {
		switch(contents) {
		case "+":
			type = OPERATOR;
			operator = contents.charAt(0);
			precedence = 1;
			break;
		case "-":
			type = OPERATOR;
			operator = contents.charAt(0);
			precedence = 1;
			break;
		case "*":
			type = OPERATOR;
			operator = contents.charAt(0);
			precedence = 2;
			break;
		case "/":
			type = OPERATOR;
			operator = contents.charAt(0);
			precedence = 2;
			break;
		case "^":
			type = OPERATOR;
			operator = contents.charAt(0);
			precedence = 3;
			break;
		case "(":
			type = LEFT_PARENTHESIS;
			break;
		case ")":
			type = RIGHT_PARENTHESIS;
			break;
		default:
			type = NUMBER;
			try {
				value = Integer.parseInt(contents);
			} catch (Exception ex) {
				type = UNKNOWN;
			}
		}
	}

	public Token(Integer x) {
		type = NUMBER;
		value = x;
	}

	public int getType() { return type; }
	public int getValue() { return value; }
	public int getPrecedence() { return precedence; }

	public Token operate(int a,int b) {
		Integer result = 0;
		switch(operator) {
		case '+':
			result = a + b;
			break;
		case '-':
			result = a - b;
			break;
		case '*':
			result = a * b;
			break;
		case '/':
			result = a / b;
			break;
		case '^':
			result = (int) Math.pow(a, b);
			break;
		}
		return new Token(result);
	}
}
