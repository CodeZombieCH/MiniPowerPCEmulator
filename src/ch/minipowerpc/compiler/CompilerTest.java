package ch.minipowerpc.compiler;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CompilerTest {
	private Compiler instance;

	@Before
	public void setUp() throws CompilerException {
		instance = new Compiler();
	}

	@Test
	public void testParseLWDD() throws CompilerException {
		String input;
		short expected, actual;

		input = "LWDD R0, #0";
		expected = 0b0100_00_0000000000;
		actual = instance.parse(input);
		assertEquals(expected, actual);

		input = "LWDD R1, #1";
		expected = 0b0100_01_0000000001;
		actual = instance.parse(input);
		assertEquals(expected, actual);
		
		input = "LWDD R2, #500";
		expected = 0b0100_10_0111110100;
		actual = instance.parse(input);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testParseSWDD() throws CompilerException {
		String input;
		short expected, actual;

		input = "SWDD R0, #0";
		expected = 0b0110_00_0000000000;
		actual = instance.parse(input);
		assertEquals(expected, actual);

		input = "SWDD R1, #1";
		expected = 0b0110_01_0000000001;
		actual = instance.parse(input);
		assertEquals(expected, actual);
		
		input = "SWDD R2, #500";
		expected = 0b0110_10_0111110100;
		actual = instance.parse(input);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testParseAND() throws CompilerException {
		String input;
		short expected, actual;

		input = "AND R0";
		expected = 0b0000_00_100_0000000;
		actual = instance.parse(input);
		assertEquals(expected, actual);

		input = "AND R3";
		expected = 0b0000_11_100_0000000;
		actual = instance.parse(input);
		assertEquals(expected, actual);		
	}
	
	@Test
	public void testParseOR() throws CompilerException {
		String input;
		short expected, actual;

		input = "OR R0";
		expected = 0b0000_00_110_0000000;
		actual = instance.parse(input);
		assertEquals(expected, actual);

		input = "OR R3";
		expected = 0b0000_11_110_0000000;
		actual = instance.parse(input);
		assertEquals(expected, actual);		
	}
	
	@Test
	public void testParseNOT() throws CompilerException {
		String input;
		short expected, actual;

		input = "NOT";
		expected = 0b00000000_10000000;
		actual = instance.parse(input);
		assertEquals(expected, actual);
	}
}
