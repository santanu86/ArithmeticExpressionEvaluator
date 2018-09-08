package com.santanu.project.arithmaticExpression.main;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class ArithmaticExpressionEvaluatorTest {
	
	ArithmaticExpressionEvaluator arithmaticExpressionEvaluator;
	
	@Before
	public void init() {
		arithmaticExpressionEvaluator = new ArithmaticExpressionEvaluator();
	}
	
	@Test
	public void testExpressionSuccess() {
		String input = "7+(6*5^2+3-4/2)";
		Assert.assertEquals("158", arithmaticExpressionEvaluator.processInput(input));	
	}
	
	@Test
	public void testExpressionFailure() {
		String input = "7+(67(56*2))";
		Assert.assertEquals("INVALID EXPRESSION", arithmaticExpressionEvaluator.processInput(input));
	}
}
