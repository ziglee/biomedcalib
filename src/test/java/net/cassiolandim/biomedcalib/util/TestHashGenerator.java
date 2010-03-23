package net.cassiolandim.biomedcalib.util;

import org.junit.Assert;
import org.junit.Test;

public class TestHashGenerator {
	
	private final static String hashExpected = "E10ADC3949BA59ABBE56E057F20F883E";
	private final static String plain = "123456";
	
	@Test
	public void testHashGenerator(){
		String hash = HashGenerator.convert(plain);
		Assert.assertEquals(hashExpected, hash);
	}

}
