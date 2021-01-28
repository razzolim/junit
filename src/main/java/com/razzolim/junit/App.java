package com.razzolim.junit;

/**
 * Hello world!
 *
 */
public class App {

	private static final String HELLO = "Hello";
	private static final String WORLD = "World";

	public String helloWorld() {
		return HELLO + " " + WORLD;
	}

	public String helloWorld(String name) {
		return HELLO + " " + name;
	}

}
