package com.tools.stats.util;

import java.util.Random;

public class StatDataUtil {
	
	/**
	 * 
	 */
	public String generateRandomId(int prefix,String separator) {
		Random rnd = new Random(prefix);
		String c = "" + prefix +separator;
		long l = Math.abs(rnd.nextLong()%100000);
		c = c +l;
		return c;
	}
	

}
