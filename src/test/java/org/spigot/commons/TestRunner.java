package org.spigot.commons;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.spigot.commons.messages.MessageChainTest;

public class TestRunner {
	final static Class<?>[] CLASSES = {
			MessageChainTest.class
	};
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(CLASSES);
		
	    for(Failure failure : result.getFailures()) 
	    	System.out.println(failure.toString());
	}
}
