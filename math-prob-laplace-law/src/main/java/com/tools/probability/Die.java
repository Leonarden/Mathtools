package com.tools.probability;

import java.util.Random;

public class Die {
private Integer value;
private Random random1;
private long t0;
public Die() {
	value = -1;
	random1 = new Random(6);
	t0 = System.currentTimeMillis();
}
	

	public void thrown() {
		long t = System.currentTimeMillis()-t0;
		for(int i=0; i<t;i++) {
			value = random1.nextInt(6) + 1;
		}
	}


	public Integer get() {
		return value;
	}

}
